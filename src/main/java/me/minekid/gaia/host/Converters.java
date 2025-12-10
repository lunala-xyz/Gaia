package me.minekid.gaia.host;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import me.minekid.gaia.runtime.*;

public class Converters {
    public static GaiaValue toGaia(Object javaValue) {
        switch (javaValue) {
            case null -> {
                return GaiaNull.INSTANCE;
            }
            case GaiaValue gaiaValue -> {
                return gaiaValue;
            }
            case String s -> {
                return new GaiaString(s);
            }
            case Boolean b -> {
                return GaiaBoolean.of(b);
            }
            case Integer i -> {
                return new GaiaNumber(i);
            }
            case Long l -> {
                return new GaiaNumber(l);
            }
            case Double v -> {
                return new GaiaNumber(v);
            }
            case Float v -> {
                return new GaiaNumber(v);
            }
            case List<?> list -> {
                List<GaiaValue> elements = new ArrayList<>();
                for (Object item : list) {
                    elements.add(toGaia(item));
                }
                return new GaiaArray(elements);
            }
            case Map<?, ?> map -> {
                GaiaObject obj = new GaiaObject("$object");
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    obj.setField(entry.getKey().toString(), toGaia(entry.getValue()));
                }
                return obj;
            }
            case Object[] array -> {
                List<GaiaValue> elements = new ArrayList<>();
                for (Object item : array) {
                    elements.add(toGaia(item));
                }
                return new GaiaArray(elements);
            }
            default -> {
                return new GaiaString(javaValue.toString());
            }
        }
    }

    /**
     * Convert a Gaia value to a Java value.
     */
    public static Object toJava(GaiaValue gaiaValue) {
        switch (gaiaValue) {
            case null -> {
                return null;
            }
            case GaiaNull ignored -> {
                return null;
            }
            case GaiaString gaiaString -> {
                return gaiaString.getValue();
            }
            case GaiaBoolean gaiaBoolean -> {
                return gaiaBoolean.getValue();
            }
            case GaiaNumber num -> {
                if (num.isInteger()) {
                    return num.asLong();
                }
                return num.asDouble();
            }
            case GaiaArray array -> {
                List<Object> list = new ArrayList<>();
                for (GaiaValue element : array.getElements()) {
                    list.add(toJava(element));
                }
                return list;
            }
            case GaiaObject obj -> {
                Map<String, Object> map = new HashMap<>();
                for (Map.Entry<String, GaiaValue> entry : obj.getFields().entrySet()) {
                    map.put(entry.getKey(), toJava(entry.getValue()));
                }
                return map;
            }
            case GaiaError error -> throw new HostException(error.getErrorType(), error.getMessage());
            default -> {
                return gaiaValue;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T toJava(GaiaValue gaiaValue, Class<T> targetType) {
        if (gaiaValue == null || gaiaValue instanceof GaiaNull) {
            return null;
        }

        if (String.class.equals(targetType)) {
            return (T) gaiaValue.toGaiaString();
        }

        if (Boolean.class.equals(targetType) || boolean.class.equals(targetType)) {
            if (gaiaValue instanceof GaiaBoolean gb) {
                return (T) Boolean.valueOf(gb.getValue());
            }
            return (T) Boolean.valueOf(gaiaValue.isTruthy());
        }

        if (gaiaValue instanceof GaiaNumber gn) {
            if (Long.class.equals(targetType) || long.class.equals(targetType)) {
                return (T) Long.valueOf(gn.asLong());
            }
            if (Integer.class.equals(targetType) || int.class.equals(targetType)) {
                return (T) Integer.valueOf((int) gn.asLong());
            }
            if (Double.class.equals(targetType) || double.class.equals(targetType)) {
                return (T) Double.valueOf(gn.asDouble());
            }
            if (Float.class.equals(targetType) || float.class.equals(targetType)) {
                return (T) Float.valueOf((float) gn.asDouble());
            }
        }

        if (GaiaValue.class.equals(targetType) || targetType.isAssignableFrom(gaiaValue.getClass())) {
            return (T) gaiaValue;
        }

        if (List.class.equals(targetType) || Map.class.equals(targetType)) {
            return (T) toJava(gaiaValue);
        }

        throw new IllegalArgumentException("Cannot convert " + gaiaValue.getClass().getSimpleName() + " to " + targetType.getSimpleName());
    }
}
