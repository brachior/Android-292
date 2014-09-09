import java.lang.*;
import java.lang.Class;
import java.lang.Object;
import java.lang.System;
import java.lang.Throwable;
import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BootStraps {

    public static CallSite bootstrapWithArguments(MethodHandles.Lookup caller, String name, MethodType type,
                                                  MethodType mt1, final MethodHandle mh, MethodType mt2)
            throws NoSuchMethodException, IllegalAccessException {
        if (type.parameterCount() != 0) {
            MethodHandle createProxy = MethodHandles
                    .lookup()
                    .findStatic(
                            BootStraps.class,
                            "createProxy",
                            MethodType.methodType(Object.class, MethodHandle.class, Class.class, Object[].class));
            MethodHandle methodHandle = createProxy
                    .bindTo(mh)
                    .bindTo(type.returnType())
                    .asCollector(Object[].class, type.parameterCount())
                    .asType(type);
            return new ConstantCallSite(methodHandle);
        }

        Class<?> interfaceType = type.returnType();
        Object instance = createProxy(mh, interfaceType, null);
        return new ConstantCallSite(MethodHandles.constant(interfaceType, instance));
    }

    private static Object createProxy(final MethodHandle mh, Class<?> interfaceType, final Object[] capture) {
        return Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class<?>[]{interfaceType},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getDeclaringClass() == Object.class) {
                            switch (method.getName()) {
                                case "toString":
                                    return proxy.getClass().getName() + '@' + java.lang.System.identityHashCode(proxy);
                                case "hashCode":
                                    return System.identityHashCode(proxy);
                                case "equals":
                                    return proxy == args[0];
                                default:
                                    //fallthrough
                            }
                            return method.invoke(proxy, args);
                        }
                        if (capture == null) {
                            return mh.invokeWithArguments(args);
                        }
                        return MethodHandles.insertArguments(mh, 0, capture).invokeWithArguments(args);
                    }
                });
    }
}
