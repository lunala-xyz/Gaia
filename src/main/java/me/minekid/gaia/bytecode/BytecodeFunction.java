package me.minekid.gaia.bytecode;

import java.util.List;
import java.util.ArrayList;

import static java.util.Objects.isNull;

public class BytecodeFunction {
    private final String name;
    private final int arity;
    private final int localCount;
    private final List<Byte> code;
    private final List<Integer> lineNumbers;

    public BytecodeFunction(String name, int arity, int localCount) {
        this.name = name;
        this.arity = arity;
        this.localCount = localCount;
        this.code = new ArrayList<>();
        this.lineNumbers = new ArrayList<>();
    }

    public String getName() {
        return isNull(name) ? "anonymous": name;
    }

    public int getArity() {
        return arity;
    }

    public int getLocalCount() {
        return localCount;
    }

    public int getCodeOffset() {
        return code.size();
    }

    public byte getCode(int offset) {
        return code.get(offset);
    }

    public byte[] getCodeBytes() {
        byte[] bytes = new byte[code.size()];
        for (int i = 0; i < code.size(); i++) {
            bytes[i] = code.get(i);
        }
        return bytes;
    }

    public int getCodeSize() {
        return code.size();
    }

    public void emit(OpCode op) {
        emit(op, -1);
    }

    public void emit(OpCode op, int line) {
        code.add((byte) op.ordinal());
        lineNumbers.add(line);
    }

    public void emitByte(int value) {
        code.add((byte) (value & 0xFF));
    }

    public void emitShort(int value) {
        code.add((byte) ((value >> 8) & 0xFF));
        code.add((byte) (value & 0xFF));
    }

    public void emitLong(long value) {
        for (int i = 7; i >= 0; i--) {
            code.add((byte) ((value >> (i * 8)) & 0xFF));
        }
    }

    public void emitDouble(double value) {
        emitLong(Double.doubleToLongBits(value));
    }

    public void patchShort(int offset, int value) {
        code.set(offset, (byte) ((value >> 8) & 0xFF));
        code.set(offset + 1, (byte) (value & 0xFF));
    }

    public int readShort(int offset) {
        int high = code.get(offset) & 0xFF;
        int low = code.get(offset + 1) & 0xFF;
        return (high << 8) | low;
    }

    public long readLong(int offset) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value = (value << 8) | (code.get(offset + i) & 0xFF);
        }
        return value;
    }

    public double readDouble(int offset) {
        return Double.longBitsToDouble(readLong(offset));
    }

    public int getLineNumber(int codeOffset) {
        if (codeOffset >= 0 && codeOffset < lineNumbers.size()) {
            return lineNumbers.get(codeOffset);
        }
        return -1;
    }

    @Override
    public String toString() {
        return String.format("Function<%s>(%d args, %d locals, %d bytes)", getName(), arity, localCount, code.size());
    }
}
