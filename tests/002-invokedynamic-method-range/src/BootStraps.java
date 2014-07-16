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
  public static CallSite bsmCallRange(MethodHandles.Lookup caller, String name, MethodType type)
      throws IllegalAccessException, NoSuchMethodException {
    switch (name) {
      case "V":
        return new ConstantCallSite(caller.findVirtual(Range.class, "concat", MethodType.methodType(String.class, char.class, float.class, long.class, double.class)));
      case "S":
        return new ConstantCallSite(caller.findStatic(Range.class, "concat", MethodType.methodType(String.class, String.class, String.class, char.class, float.class, long.class, double.class)));
      default:
        throw new IllegalArgumentException();
    }
  }
}
