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
  public static CallSite bsmCallInterface(MethodHandles.Lookup caller, String name, MethodType type)
      throws IllegalAccessException, NoSuchMethodException {
    return makeCallSite(caller, name, InterfaceMethods.class);
  }

  public static CallSite bsmCallVirtual(MethodHandles.Lookup caller, String name, MethodType type)
      throws IllegalAccessException, NoSuchMethodException {
    return makeCallSite(caller, name, VirtualMethods.class);
  }

  public static CallSite bsmCallStatic(MethodHandles.Lookup caller, String name, MethodType type)
      throws IllegalAccessException, NoSuchMethodException {
    switch (name) {
      case "I":
        return new ConstantCallSite(caller.findStatic(StaticMethods.class, "noParameters", MethodType.methodType(int.class)));
      case "J":
        return new ConstantCallSite(caller.findStatic(StaticMethods.class, "noParametersWide", MethodType.methodType(long.class)));
      case "L":
        return new ConstantCallSite(caller.findStatic(StaticMethods.class, "noParametersObject", MethodType.methodType(Object.class)));
      case "V":
        return new ConstantCallSite(caller.findStatic(StaticMethods.class, "noParametersVoid", MethodType.methodType(void.class)));
      default:
        int i = Integer.parseInt(name.substring(1));
        switch (name.charAt(0)) {
          case 'I':
            return new ConstantCallSite(caller.findStatic(StaticMethods.class, "allParameters", makeMethodType(int.class, classes[i])));
          case 'J':
            return new ConstantCallSite(caller.findStatic(StaticMethods.class, "allParametersWide", makeMethodType(long.class, classes[i])));
          case 'L':
            return new ConstantCallSite(caller.findStatic(StaticMethods.class, "allParametersObject", makeMethodType(Object.class, classes[i])));
          case 'V':
            return new ConstantCallSite(caller.findStatic(StaticMethods.class, "allParametersVoid", makeMethodType(void.class, classes[i])));
          default:
            throw new IllegalArgumentException();
        }
    }
  }

  private final static Class<?>[] classes = {boolean.class, byte.class, char.class, short.class, int.class, long.class, float.class, double.class, Object.class};

  private static MethodType makeMethodType(Class<?> rType, Class<?> pType) {
    return MethodType.methodType(rType, pType);
  }

  private static CallSite makeCallSite(MethodHandles.Lookup caller, String name, Class<?> refc) throws NoSuchMethodException, IllegalAccessException {
    switch (name) {
      case "I":
        return new ConstantCallSite(caller.findVirtual(refc, "noParameters", MethodType.methodType(int.class)));
      case "J":
        return new ConstantCallSite(caller.findVirtual(refc, "noParametersWide", MethodType.methodType(long.class)));
      case "L":
        return new ConstantCallSite(caller.findVirtual(refc, "noParametersObject", MethodType.methodType(Object.class)));
      case "V":
        return new ConstantCallSite(caller.findVirtual(refc, "noParametersVoid", MethodType.methodType(void.class)));
      default:
        int i = Integer.parseInt(name.substring(1));
        switch (name.charAt(0)) {
          case 'I':
            return new ConstantCallSite(caller.findVirtual(refc, "allParameters", makeMethodType(int.class, classes[i])));
          case 'J':
            return new ConstantCallSite(caller.findVirtual(refc, "allParametersWide", makeMethodType(long.class, classes[i])));
          case 'L':
            return new ConstantCallSite(caller.findVirtual(refc, "allParametersObject", makeMethodType(Object.class, classes[i])));
          case 'V':
            return new ConstantCallSite(caller.findVirtual(refc, "allParametersVoid", makeMethodType(void.class, classes[i])));
          default:
            throw new IllegalArgumentException();
        }
    }
  }
}
