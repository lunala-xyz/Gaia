package me.minekid.gaia.typechecker;

import java.util.List;

public class FunctionGaiaType extends GaiaType {
    private final List<GaiaType> parameterTypes;
    private final GaiaType returnType;

    public FunctionGaiaType(List<GaiaType> parameterTypes, GaiaType returnType) {
        this.parameterTypes = parameterTypes;
        this.returnType = returnType;
    }

    public List<GaiaType> getParameterTypes() {
        return parameterTypes;
    }

    public GaiaType getReturnType() {
        return returnType;
    }

    @Override
    public String getTypeName() {
        StringBuilder sb = new StringBuilder("(");

        for (int i = 0; i < parameterTypes.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(parameterTypes.get(i).getTypeName());
        }

        sb.append(") -> ");
        sb.append(returnType.getTypeName());

        return sb.toString();
    }

    @Override
    public boolean isAssignableFrom(GaiaType other) {
        if (!(other instanceof FunctionGaiaType otherFunc)) return false;

        if (!returnType.isAssignableFrom(otherFunc.returnType)) return false;

        if (parameterTypes.size() != otherFunc.parameterTypes.size()) return false;

        for (int i = 0; i < parameterTypes.size(); i++) {
            if (!otherFunc.parameterTypes.get(i).isAssignableFrom(parameterTypes.get(i)))  return false;
        }

        return true;
    }
}
