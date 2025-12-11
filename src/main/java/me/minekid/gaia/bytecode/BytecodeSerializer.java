package me.minekid.gaia.bytecode;

public class BytecodeSerializer {
    private BytecodeSerializer() {}

    public static byte[] serialize(Bytecode bytecode) {
        return new byte[0];
    }

    public static Bytecode deserialize(byte[] bytes) {
        return new Bytecode();
    }
}
