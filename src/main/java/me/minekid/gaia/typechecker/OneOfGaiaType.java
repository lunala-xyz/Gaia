package me.minekid.gaia.typechecker;

import java.util.Set;
import java.util.HashSet;

/**
 * Represents a oneOf group type in Gaia. A oneOf group can hold any of its member types.
 */
public class OneOfGaiaType extends GaiaType {
    private final String name;
    private final Set<GaiaType> memberTypes;

    public OneOfGaiaType(String name) {
        this.name = name;
        this.memberTypes = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public String getTypeName() {
        return "oneOf " + name;
    }

    @Override
    public boolean isAssignableFrom(GaiaType other) {
        if (memberTypes.contains(other)) return true;

        if (other instanceof OneOfGaiaType) return name.equals(((OneOfGaiaType) other).name);

        if (!(other instanceof ClassGaiaType classType)) return false;

        for (GaiaType member : memberTypes) {
            if (member instanceof ClassGaiaType memberType) {
                if (memberType.getName().equals(classType.getName())) {
                    return true;
                }
            }
        }

        return false;
    }
}
