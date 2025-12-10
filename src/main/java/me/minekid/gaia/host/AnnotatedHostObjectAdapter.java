package me.minekid.gaia.host;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import me.minekid.gaia.runtime.*;
import me.minekid.gaia.vm.GaiaInterpreter;
import me.minekid.gaia.vm.GaiaNativeFunction;

public class AnnotatedHostObjectAdapter {
    private final Object hostObject;
    private final List<MethodBinding> bindings;

    public AnnotatedHostObjectAdapter(Object hostObject) {
        this.hostObject = hostObject;
        this.bindings = new ArrayList<>();

        discoverMethods();
    }

    private void discoverMethods() {
        for (Method method : hostObject.getClass().getMethods()) {
            HostFunction annotation = method.getAnnotation(HostFunction.class);
            if (annotation != null) {
                String name = annotation.name().isEmpty() ? method.getName() : annotation.name();
                bindings.add(new MethodBinding(name, method, annotation.description()));
            }
        }
    }

    public List<MethodBinding> getBindings() {
        return bindings;
    }

    public void registerWith(GaiaInterpreter interpreter) {
        for (MethodBinding binding : bindings) {
            GaiaNativeFunction nativeFunc = createNativeFunction(binding);
            interpreter.registerNativeFunction(nativeFunc);
        }
    }

    public GaiaObject createHostObject() {
        GaiaObject obj = new GaiaObject(hostObject.getClass().getSimpleName());

        for (MethodBinding binding : bindings) {
            GaiaNativeFunction nativeFunc = createNativeFunction(binding);
            obj.setField(binding.name(), nativeFunc);
        }

        return obj;
    }

    private GaiaNativeFunction createNativeFunction(MethodBinding binding) {
        Method method = binding.method();
        int arity = method.getParameterCount();

        return new GaiaNativeFunction(binding.name(), arity) {

            @Override
            public GaiaValue call(List<GaiaValue> arguments) {
                try {
                    Object[] javaArgs = new Object[method.getParameterCount()];
                    Parameter[] params = method.getParameters();

                    for (int i = 0; i < params.length; i++) {
                        if (i < arguments.size()) {
                            javaArgs[i] = convertArgument(arguments.get(i), params[i].getType());
                        } else {
                            javaArgs[i] = null;
                        }
                    }

                    Object result = method.invoke(hostObject, javaArgs);

                    return Converters.toGaia(result);

                } catch (HostException e) {
                    return new GaiaError(e.getErrorType(), e.getMessage());
                } catch (Exception e) {
                    Throwable cause = e.getCause();

                    if (cause instanceof HostException he) {
                        return new GaiaError(he.getErrorType(), he.getMessage());
                    }

                    return new GaiaError("HostError", e.getMessage() != null ? e.getMessage() : e.toString());
                }
            }

        };
    }

    private Object convertArgument(GaiaValue value, Class<?> targetType) {
        if (targetType == GaiaValue.class || targetType.isAssignableFrom(value.getClass())) {
            return value;
        }

        return Converters.toJava(value, targetType);
    }

    public record MethodBinding(String name, Method method, String description) {
    }
}
