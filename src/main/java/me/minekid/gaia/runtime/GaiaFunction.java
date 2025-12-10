package me.minekid.gaia.runtime;

import java.util.List;

public abstract class GaiaFunction extends GaiaValue {
    protected final String name;
    protected final int arity; // number of parameters

    public GaiaFunction(String name, int arity) {
        this.name = name;
        this.arity = arity;
    }

    public String getName() {
        return name != null ? name : "<anonymous>";
    }

    public int getArity() {
        return arity;
    }

    public abstract GaiaValue call(List<GaiaValue> arguments);

    @Override
    public String getTypeName() {
        return "function";
    }

    @Override
    public String toGaiaString() {
        return "<function " + getName() + ">";
    }
}
