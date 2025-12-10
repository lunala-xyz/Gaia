package me.minekid.gaia.vm;

import java.util.List;

import me.minekid.gaia.runtime.GaiaValue;
import me.minekid.gaia.runtime.GaiaFunction;

public abstract class GaiaNativeFunction extends GaiaFunction {
    public GaiaNativeFunction(String name, int arity) {
        super(name, arity);
    }

    @Override
    public abstract GaiaValue call(List<GaiaValue> arguments);

    @Override
    public String toGaiaString() {
        return "<native function " + getName() + ">";
    }
}
