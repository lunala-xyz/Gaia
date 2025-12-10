package me.minekid.gaia.vm;

import java.util.List;

import me.minekid.gaia.runtime.GaiaValue;
import me.minekid.gaia.runtime.GaiaFunction;
import me.minekid.gaia.bytecode.BytecodeFunction;

public class GaiaRuntimeFunction extends GaiaFunction {
    private final BytecodeFunction bytecodeFunction;

    public GaiaRuntimeFunction(BytecodeFunction bytecodeFunction) {
        super(bytecodeFunction.getName(), bytecodeFunction.getArity());
        this.bytecodeFunction = bytecodeFunction;
    }

    public BytecodeFunction getBytecodeFunction() {
        return bytecodeFunction;
    }

    @Override
    public GaiaValue call(List<GaiaValue> arguments) {
        throw new UnsupportedOperationException("Runtime functions must be called through the interpreter");
    }
}
