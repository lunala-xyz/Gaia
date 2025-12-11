package me.minekid.gaia.vm;

import java.util.*;

import me.minekid.gaia.runtime.*;
import me.minekid.gaia.bytecode.OpCode;
import me.minekid.gaia.bytecode.Bytecode;
import me.minekid.gaia.bytecode.BytecodeFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GaiaInterpreter {
    private final Bytecode bytecode;
    private final Map<String, GaiaValue> globals;
    private final List<CallFrame> callStack;
    private final List<GaiaValue> valueStack;
    private boolean halted;
    private static final Logger logger = LoggerFactory.getLogger(GaiaInterpreter.class);

    private static final int MAX_STACK_SIZE = 65536;
    private static final int MAX_CALL_DEPTH = 1024;

    private int fuel = -1;

    public GaiaInterpreter(Bytecode bytecode) {
        this.bytecode = bytecode;
        this.globals = new HashMap<>();
        this.callStack = new ArrayList<>();
        this.valueStack = new ArrayList<>();
        this.halted = false;
    }

    /**
     * Execute the bytecode starting from the main function.
     *
     * @return the result of execution
     */
    public GaiaValue execute() {
        BytecodeFunction mainFunc = bytecode.getMainFunction();

        if (mainFunc == null) return new GaiaError("No main function found");

        callStack.clear();
        valueStack.clear();
        halted = false;

        callStack.add(new CallFrame(mainFunc, 0, new GaiaValue[mainFunc.getLocalCount()]));

        return run();
    }

    /**
     * Call a function with arguments.
     */
    public GaiaValue call(BytecodeFunction function, List<GaiaValue> arguments) {
        GaiaValue[] locals = new GaiaValue[function.getLocalCount()];
        for (int i = 0; i < arguments.size() && i < function.getArity(); i++) {
            locals[i] = arguments.get(i);
        }

        callStack.add(new CallFrame(function, 0, locals));
        return run();
    }

    public void resume() {
        halted = false;
        run();
    }

    private GaiaValue run() {
        while (!callStack.isEmpty() && !halted) {
            if(!valueStack.isEmpty() && peek().isError()) {
                halted = true;
                logger.error("Error: {}", peek().toGaiaString());
                return pop();
            }

            if(fuel > 0) fuel--;
            if(fuel == 0) {
                halted = true;
                return GaiaNull.INSTANCE;
            }

            CallFrame frame = callStack.getLast();

            if (frame.instructionPointer >= frame.function.getCodeSize()) {
                callStack.removeLast();
                push(GaiaNull.INSTANCE);
                continue;
            }

            byte opByte = frame.function.getCode(frame.instructionPointer);
            OpCode op = OpCode.values()[opByte & 0xFF];
            frame.instructionPointer++;

            try {
                executeInstruction(op, frame);
            } catch (RuntimeException e) {
                return new GaiaError("RuntimeError", e.getMessage());
            }
        }

        if (!valueStack.isEmpty()) {
            return pop();
        }

        return GaiaNull.INSTANCE;
    }

    private void executeInstruction(OpCode op, CallFrame frame) {
        switch (op) {
            case NOP:
                break;

            case POP:
                pop();
                break;

            case DUP:
                push(peek());
                break;

            case CONST_NULL:
                push(GaiaNull.INSTANCE);
                break;

            case CONST_TRUE:
                push(GaiaBoolean.TRUE);
                break;

            case CONST_FALSE:
                push(GaiaBoolean.FALSE);
                break;

            case CONST_INT: {
                long value = frame.function.readLong(frame.instructionPointer);
                frame.instructionPointer += 8;
                push(new GaiaNumber(value));
                break;
            }

            case CONST_FLOAT: {
                double value = frame.function.readDouble(frame.instructionPointer);
                frame.instructionPointer += 8;
                push(new GaiaNumber(value));
                break;
            }

            case LOAD_CONST: {
                int index = frame.function.readShort(frame.instructionPointer);
                frame.instructionPointer += 2;
                Object constant = bytecode.getConstant(index);
                push(toGaiaValue(constant));
                break;
            }

            case LOAD_LOCAL: {
                int slot = frame.function.readShort(frame.instructionPointer);
                frame.instructionPointer += 2;
                GaiaValue value = frame.locals[slot];
                push(value != null ? value : GaiaNull.INSTANCE);
                break;
            }

            case STORE_LOCAL: {
                int slot = frame.function.readShort(frame.instructionPointer);
                frame.instructionPointer += 2;
                frame.locals[slot] = pop();
                break;
            }

            case LOAD_GLOBAL: {
                int index = frame.function.readShort(frame.instructionPointer);
                frame.instructionPointer += 2;
                String name = (String) bytecode.getConstant(index);
                GaiaValue value = globals.get(name);
                push(value != null ? value : new GaiaError("Undefined", "Undefined '" + name + "'"));
                break;
            }

            case STORE_GLOBAL: {
                int index = frame.function.readShort(frame.instructionPointer);
                frame.instructionPointer += 2;
                String name = (String) bytecode.getConstant(index);
                globals.put(name, pop());
                break;
            }

            case ADD: {
                GaiaValue b = pop();
                GaiaValue a = pop();
                if (a instanceof GaiaString || b instanceof GaiaString) {
                    push(new GaiaString(a.toGaiaString() + b.toGaiaString()));
                } else if (a instanceof GaiaNumber na && b instanceof GaiaNumber nb) {
                    if (na.isInteger() && nb.isInteger()) {
                        push(new GaiaNumber(na.asLong() + nb.asLong()));
                    } else {
                        push(new GaiaNumber(na.asDouble() + nb.asDouble()));
                    }
                } else {
                    push(new GaiaError("TypeError", "Cannot add " + a.getTypeName() + " and " + b.getTypeName()));
                }
                break;
            }

            case SUB: {
                GaiaValue b = pop();
                GaiaValue a = pop();
                if (a instanceof GaiaNumber na && b instanceof GaiaNumber nb) {
                    if (na.isInteger() && nb.isInteger()) {
                        push(new GaiaNumber(na.asLong() - nb.asLong()));
                    } else {
                        push(new GaiaNumber(na.asDouble() - nb.asDouble()));
                    }
                } else {
                    push(
                            new GaiaError(
                                    "TypeError",
                                    "Cannot subtract " + b.getTypeName() + " from " + a.getTypeName()));
                }
                break;
            }

            case MUL: {
                GaiaValue b = pop();
                GaiaValue a = pop();
                if (a instanceof GaiaNumber na && b instanceof GaiaNumber nb) {
                    if (na.isInteger() && nb.isInteger()) {
                        push(new GaiaNumber(na.asLong() * nb.asLong()));
                    } else {
                        push(new GaiaNumber(na.asDouble() * nb.asDouble()));
                    }
                } else {
                    push(
                            new GaiaError(
                                    "TypeError", "Cannot multiply " + a.getTypeName() + " and " + b.getTypeName()));
                }
                break;
            }

            case DIV: {
                GaiaValue b = pop();
                GaiaValue a = pop();
                if (a instanceof GaiaNumber na && b instanceof GaiaNumber nb) {
                    if (nb.asDouble() == 0) {
                        push(new GaiaError("DivisionByZero", "Division by zero"));
                    } else {
                        push(new GaiaNumber(na.asDouble() / nb.asDouble()));
                    }
                } else {
                    push(
                            new GaiaError(
                                    "TypeError", "Cannot divide " + a.getTypeName() + " by " + b.getTypeName()));
                }
                break;
            }

            case MOD: {
                GaiaValue b = pop();
                GaiaValue a = pop();
                if (a instanceof GaiaNumber na && b instanceof GaiaNumber nb) {
                    if (nb.asDouble() == 0) {
                        push(new GaiaError("DivisionByZero", "Modulo by zero"));
                    } else if (na.isInteger() && nb.isInteger()) {
                        push(new GaiaNumber(na.asLong() % nb.asLong()));
                    } else {
                        push(new GaiaNumber(na.asDouble() % nb.asDouble()));
                    }
                } else {
                    push(
                            new GaiaError(
                                    "TypeError", "Cannot modulo " + a.getTypeName() + " by " + b.getTypeName()));
                }
                break;
            }

            case NEG: {
                GaiaValue a = pop();
                if (a instanceof GaiaNumber na) {
                    if (na.isInteger()) {
                        push(new GaiaNumber(-na.asLong()));
                    } else {
                        push(new GaiaNumber(-na.asDouble()));
                    }
                } else {
                    push(new GaiaError("TypeError", "Cannot negate " + a.getTypeName()));
                }
                break;
            }

            case EQ: {
                GaiaValue b = pop();
                GaiaValue a = pop();
                push(GaiaBoolean.of(valuesEqual(a, b)));
                break;
            }

            case NE: {
                GaiaValue b = pop();
                GaiaValue a = pop();
                push(GaiaBoolean.of(!valuesEqual(a, b)));
                break;
            }

            case LT: {
                GaiaValue b = pop();
                GaiaValue a = pop();
                push(GaiaBoolean.of(compareValues(a, b) < 0));
                break;
            }

            case LE: {
                GaiaValue b = pop();
                GaiaValue a = pop();
                push(GaiaBoolean.of(compareValues(a, b) <= 0));
                break;
            }

            case GT: {
                GaiaValue b = pop();
                GaiaValue a = pop();
                push(GaiaBoolean.of(compareValues(a, b) > 0));
                break;
            }

            case GE: {
                GaiaValue b = pop();
                GaiaValue a = pop();
                push(GaiaBoolean.of(compareValues(a, b) >= 0));
                break;
            }

            case NOT: {
                GaiaValue a = pop();
                push(GaiaBoolean.of(!a.isTruthy()));
                break;
            }

            case AND:
            case OR:
                // These are handled by short-circuit evaluation in the compiler
                break;

            case JUMP: {
                frame.instructionPointer = frame.function.readShort(frame.instructionPointer);
                break;
            }

            case JUMP_IF_FALSE: {
                int target = frame.function.readShort(frame.instructionPointer);
                frame.instructionPointer += 2;
                GaiaValue condition = pop();
                if (!condition.isTruthy()) {
                    frame.instructionPointer = target;
                }
                break;
            }

            case JUMP_IF_TRUE: {
                int target = frame.function.readShort(frame.instructionPointer);
                frame.instructionPointer += 2;
                GaiaValue condition = peek();
                if (condition.isTruthy()) {
                    frame.instructionPointer = target;
                }
                break;
            }

            case CALL: {
                int argCount = frame.function.getCode(frame.instructionPointer) & 0xFF;
                frame.instructionPointer++;

                // Get arguments from stack
                GaiaValue[] args = new GaiaValue[argCount];
                for (int i = argCount - 1; i >= 0; i--) {
                    args[i] = pop();
                }

                GaiaValue callee = pop();

                if (callee instanceof GaiaRuntimeFunction func) {
                    BytecodeFunction bcFunc = func.getBytecodeFunction();

                    if (callStack.size() >= MAX_CALL_DEPTH) {
                        push(new GaiaError("StackOverflow", "Maximum call depth exceeded"));
                        break;
                    }

                    GaiaValue[] locals = new GaiaValue[bcFunc.getLocalCount()];
                    for (int i = 0; i < args.length && i < bcFunc.getArity(); i++) {
                        locals[i] = args[i];
                    }

                    callStack.add(new CallFrame(bcFunc, 0, locals));
                } else if (callee instanceof GaiaNativeFunction nativeFunc) {
                    List<GaiaValue> argList = new ArrayList<>();
                    Collections.addAll(argList, args);
                    GaiaValue result = nativeFunc.call(argList);
                    push(result);
                } else {
                    push(new GaiaError("TypeError", "Cannot call " + callee.getTypeName()));
                }
                break;
            }

            case RETURN: {
                GaiaValue returnValue = pop();
                callStack.removeLast();
                push(returnValue);
                break;
            }

            case LOAD_FUNCTION: {
                int funcIndex = frame.function.readShort(frame.instructionPointer);
                frame.instructionPointer += 2;
                BytecodeFunction func = bytecode.getFunction(funcIndex);
                push(new GaiaRuntimeFunction(func));
                break;
            }

            case NEW_ARRAY: {
                int count = frame.function.readShort(frame.instructionPointer);
                frame.instructionPointer += 2;
                List<GaiaValue> elements = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    elements.addFirst(pop());
                }
                push(new GaiaArray(elements));
                break;
            }

            case ARRAY_GET: {
                GaiaValue indexVal = pop();
                GaiaValue arrayVal = pop();
                if (arrayVal instanceof GaiaArray array && indexVal instanceof GaiaNumber) {
                    int index = (int) ((GaiaNumber) indexVal).asLong();
                    push(array.get(index));
                } else {
                    push(
                            new GaiaError(
                                    "TypeError",
                                    "Cannot index " + arrayVal.getTypeName() + " with " + indexVal.getTypeName()));
                }
                break;
            }

            case ARRAY_SET: {
                GaiaValue value = pop();
                GaiaValue indexVal = pop();
                GaiaValue arrayVal = pop();
                if (arrayVal instanceof GaiaArray array && indexVal instanceof GaiaNumber) {
                    int index = (int) ((GaiaNumber) indexVal).asLong();
                    array.set(index, value);
                }
                push(value);
                break;
            }

            case ARRAY_LENGTH: {
                GaiaValue arrayVal = pop();
                if (arrayVal instanceof GaiaArray) {
                    push(new GaiaNumber(((GaiaArray) arrayVal).length()));
                } else {
                    push(new GaiaError("TypeError", "Cannot get length of " + arrayVal.getTypeName()));
                }
                break;
            }

            case NEW_OBJECT: {
                int classIndex = frame.function.readShort(frame.instructionPointer);
                frame.instructionPointer += 2;
                String className = (String) bytecode.getConstant(classIndex);
                push(new GaiaObject(className));
                break;
            }

            case GET_FIELD: {
                int fieldIndex = frame.function.readShort(frame.instructionPointer);
                frame.instructionPointer += 2;
                String fieldName = (String) bytecode.getConstant(fieldIndex);
                GaiaValue obj = pop();
                if (obj instanceof GaiaObject) {
                    push(((GaiaObject) obj).getField(fieldName));
                } else {
                    push(new GaiaError("TypeError", "Cannot get field of " + obj.getTypeName()));
                }
                break;
            }

            case SET_FIELD: {
                int fieldIndex = frame.function.readShort(frame.instructionPointer);
                frame.instructionPointer += 2;
                String fieldName = (String) bytecode.getConstant(fieldIndex);
                GaiaValue value = pop();
                GaiaValue obj = pop();
                if (obj instanceof GaiaObject) {
                    ((GaiaObject) obj).setField(fieldName, value);
                }
                break;
            }

            case GET_METHOD:
                // For now, same as GET_FIELD
                executeInstruction(OpCode.GET_FIELD, frame);
                break;

            case CONCAT: {
                GaiaValue b = pop();
                GaiaValue a = pop();
                push(new GaiaString(a.toGaiaString() + b.toGaiaString()));
                break;
            }

            case FAIL: {
                GaiaValue errorValue = pop();
                push(new GaiaError(errorValue.toGaiaString()));
                break;
            }

            case IS_ERROR: {
                GaiaValue value = peek();
                push(GaiaBoolean.of(value.isError()));
                break;
            }

            case PRINT: {
                GaiaValue value = peek();
                logger.info("{}", value.toGaiaString());
                break;
            }

            case HALT:
                halted = true;
                break;

            default:
                throw new RuntimeException("Unknown opcode: " + op);
        }
    }

    // Stack operations
    private void push(GaiaValue value) {
        if (valueStack.size() >= MAX_STACK_SIZE) {
            throw new RuntimeException("Stack overflow");
        }
        valueStack.add(value);
    }

    private GaiaValue pop() {
        if (valueStack.isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return valueStack.removeLast();
    }

    private GaiaValue peek() {
        if (valueStack.isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return valueStack.getLast();
    }

    private GaiaValue toGaiaValue(Object obj) {
        return switch (obj) {
            case null -> GaiaNull.INSTANCE;
            case String s -> new GaiaString(s);
            case Long l -> new GaiaNumber(l);
            case Double v -> new GaiaNumber(v);
            case Boolean b -> GaiaBoolean.of(b);
            case GaiaValue gaiaValue -> gaiaValue;
            default -> new GaiaString(obj.toString());
        };
    }

    private boolean valuesEqual(GaiaValue a, GaiaValue b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        if (a instanceof GaiaNull) return b instanceof GaiaNull;
        return a.equals(b);
    }

    private int compareValues(GaiaValue a, GaiaValue b) {
        if (a instanceof GaiaNumber && b instanceof GaiaNumber) {
            return Double.compare(((GaiaNumber) a).asDouble(), ((GaiaNumber) b).asDouble());
        }
        if (a instanceof GaiaString && b instanceof GaiaString) {
            return ((GaiaString) a).getValue().compareTo(((GaiaString) b).getValue());
        }
        return 0;
    }

    public void setGlobal(String name, GaiaValue value) {
        globals.put(name, value);
    }

    public GaiaValue getGlobal(String name) {
        return globals.getOrDefault(name, GaiaNull.INSTANCE);
    }

    public void registerNativeFunction(GaiaNativeFunction function) {
        globals.put(function.getName(), function);
    }

    public Logger getLogger() {
        return logger;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    private static class CallFrame {
        final BytecodeFunction function;
        int instructionPointer;
        final GaiaValue[] locals;

        CallFrame(BytecodeFunction function, int instructionPointer, GaiaValue[] locals) {
            this.function = function;
            this.instructionPointer = instructionPointer;
            this.locals = locals;
        }
    }
}
