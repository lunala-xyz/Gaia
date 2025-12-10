package me.minekid.gaia.typechecker;

public abstract class GaiaType {
    /**
     * Check if this type is assignable from another type.
     *
     * @param other the type to check
     * @return true if a value of 'other' type can be assigned to a variable of this type
     */
    public abstract boolean isAssignableFrom(GaiaType other);

    public abstract String getTypeName();

    @Override
    public String toString() {
        return getTypeName();
    }
}
