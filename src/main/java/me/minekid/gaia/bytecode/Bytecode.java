package me.minekid.gaia.bytecode;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.ArrayList;

public class Bytecode {
    private final List<Object> constantPool;
    private final List<BytecodeFunction> functions;
    private int mainFunctionIndex = -1;

    public Bytecode() {
        this.constantPool = new ArrayList<>();
        this.functions = new ArrayList<>();
    }

    public int addConstant(Object constant) {
        int index = constantPool.indexOf(constant);

        if (index >= 0) return index;

        constantPool.add(constant);

        return constantPool.size() - 1;
    }

    public Object getConstant(int index) {
        return constantPool.get(index);
    }

    public int getConstantPoolSize() {
        return constantPool.size();
    }

    public List<Object> getConstantPool() {
        return constantPool;
    }

    public int addFunction(BytecodeFunction function) {
        functions.add(function);

        return functions.size() - 1;
    }

    public BytecodeFunction getFunction(int index) {
        return functions.get(index);
    }

    public int getFunctionCount() {
        return functions.size();
    }

    public List<BytecodeFunction> getFunctions() {
        return functions;
    }

    public void setMainFunctionIndex(int index) {
        this.mainFunctionIndex = index;
    }

    public int getMainFunctionIndex() {
        return mainFunctionIndex;
    }

    public BytecodeFunction getMainFunction() {
        if (mainFunctionIndex >= 0 && mainFunctionIndex < functions.size()) return functions.get(mainFunctionIndex);
        return null;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (BytecodeFunction function : functions) {
            builder.append(function.getName()).append(":\n");

            byte[] code = function.getCodeBytes();

            for (int i = 0; i < code.length; i++) {
                OpCode opCode = OpCode.fromByte(code[i]);
                int args = opCode.getOperandBytes();

                builder.append(String.format("%04d: %s", i, opCode.name()));
                builder.append(" ");

                byte[] argsBytes = new byte[args];
                System.arraycopy(code, i + 1, argsBytes, 0, args);
                builder.append(parseArgs(opCode, argsBytes));
                builder.append("\n");

                i += args;
            }

            builder.append("\n");
        }

        return builder.toString();
    }

    private String parseArgs(OpCode code, byte[] args) {
        switch (code) {
            //TODO: Format arguments
            default:
                return formatDefault(args);
        }
    }

    private String formatDefault(byte[] args) {
        if (args.length == 0) return "";
        StringBuilder builder = new StringBuilder();

        for (byte arg : args) {
            builder.append(String.format("%02X ", arg));
        }

        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
