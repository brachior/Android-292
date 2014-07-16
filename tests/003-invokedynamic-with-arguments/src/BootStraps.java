import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BootStraps {
  public static CallSite bootstrapWithArguments(MethodHandles.Lookup caller, String name, MethodType type,
                                                MethodType mt1, final MethodHandle mh, MethodType mt2)
      throws NoSuchMethodException, IllegalAccessException {
    if (type.parameterCount() != 0) {
      throw new BootstrapMethodError("parameterCount != 0");
    }

    Class<?> interfaceType = type.returnType();
    Object instance = Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class<?>[]{interfaceType},
        new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getDeclaringClass() == Object.class) {
                switch(method.getName()) {
                    case "toString":
                        return proxy.getClass().getName() + '@' + System.identityHashCode(proxy);
                    case "hashCode":
                        return System.identityHashCode(proxy);
                    case "equals":
                        return proxy == args[0];
                    default:
                        //fallthrough
                }
              return method.invoke(proxy, args);
            }
            return mh.invokeWithArguments(args);
          }
        });
    return new ConstantCallSite(MethodHandles.constant(interfaceType, instance));
  }
}
