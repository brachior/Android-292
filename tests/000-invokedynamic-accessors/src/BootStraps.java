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
  public static CallSite bsmGetter(MethodHandles.Lookup caller, String name, MethodType type)
      throws IllegalAccessException, NoSuchFieldException {
    switch (name) {
      case "Z":
        return new ConstantCallSite(caller.findGetter(Accessors.class, "z", boolean.class));
      case "B":
        return new ConstantCallSite(caller.findGetter(Accessors.class, "b", byte.class));
      case "C":
        return new ConstantCallSite(caller.findGetter(Accessors.class, "c", char.class));
      case "S":
        return new ConstantCallSite(caller.findGetter(Accessors.class, "s", short.class));
      case "I":
        return new ConstantCallSite(caller.findGetter(Accessors.class, "i", int.class));
      case "J":
        return new ConstantCallSite(caller.findGetter(Accessors.class, "j", long.class));
      case "F":
        return new ConstantCallSite(caller.findGetter(Accessors.class, "f", float.class));
      case "D":
        return new ConstantCallSite(caller.findGetter(Accessors.class, "d", double.class));
      case "L":
        return new ConstantCallSite(caller.findGetter(Accessors.class, "l", Object.class));
      default:
        throw new IllegalArgumentException();
    }
  }

  public static CallSite bsmSetter(MethodHandles.Lookup caller, String name, MethodType type)
      throws IllegalAccessException, NoSuchFieldException {
    switch (name) {
      case "Z":
        return new ConstantCallSite(caller.findSetter(Accessors.class, "z", boolean.class));
      case "B":
        return new ConstantCallSite(caller.findSetter(Accessors.class, "b", byte.class));
      case "C":
        return new ConstantCallSite(caller.findSetter(Accessors.class, "c", char.class));
      case "S":
        return new ConstantCallSite(caller.findSetter(Accessors.class, "s", short.class));
      case "I":
        return new ConstantCallSite(caller.findSetter(Accessors.class, "i", int.class));
      case "J":
        return new ConstantCallSite(caller.findSetter(Accessors.class, "j", long.class));
      case "F":
        return new ConstantCallSite(caller.findSetter(Accessors.class, "f", float.class));
      case "D":
        return new ConstantCallSite(caller.findSetter(Accessors.class, "d", double.class));
      case "L":
        return new ConstantCallSite(caller.findSetter(Accessors.class, "l", Object.class));
      default:
        throw new IllegalArgumentException();
    }
  }
}
