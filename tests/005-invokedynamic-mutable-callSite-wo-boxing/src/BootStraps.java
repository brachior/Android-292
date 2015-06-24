import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;

public class BootStraps {
    public static CallSite bsmMutableCS(MethodHandles.Lookup caller, String name, MethodType type)
            throws IllegalAccessException, NoSuchMethodException {
        MethodType mt = MethodType.methodType(MethodHandle.class, MutableCallSite.class, Object.class);
        MethodHandle mh = MethodHandles.lookup().findStatic(BootStraps.class, "change", mt);
        return new CSMutableBind(type, mh);
    }

    private static final MethodHandle TARGET_INT;
    private static final MethodHandle TARGET_STRING;
    private static final MethodHandle TARGET_CHECK;

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            TARGET_INT = lookup.findStatic(BootStraps.class, "plus", MethodType.methodType(Object.class, int.class, int.class));
            TARGET_STRING = lookup.findStatic(BootStraps.class, "plus", MethodType.methodType(Object.class, String.class, int.class));
            TARGET_CHECK = lookup.findStatic(BootStraps.class, "check", MethodType.methodType(boolean.class, Object.class, Class.class));
        } catch (NoSuchMethodException|IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    private static MethodHandle change(MutableCallSite callSite, Object argument) throws Throwable {
        MethodHandle target;
        MethodType type = callSite.type();
        Class<?> aClass = argument.getClass();
        if (aClass == Integer.class) {
            target = TARGET_INT.asType(type);
        } else if (aClass == String.class) {
            target = TARGET_STRING.asType(type);
        } else {
            throw new LinkageError("bad receiver class");
        }
        MethodHandle test = MethodHandles.insertArguments(TARGET_CHECK, 1, aClass);
        test = MethodHandles.dropArguments(test, 1, type.parameterType(1));
        callSite.setTarget(MethodHandles.guardWithTest(test, target, callSite.getTarget()));
        return target;
    }

    private static Object plus(int a, int b) {
        return a + b;
    }

    private static Object plus(String a, int b) {
        return a + b;
    }

    private static boolean check(Object value, Class<?> clazz) {
        return value.getClass() == clazz;
    }

    private static class CSMutableBind extends MutableCallSite {
        CSMutableBind(MethodType type, MethodHandle mh) {
          super(type);
          setTarget(MethodHandles.foldArguments(MethodHandles.exactInvoker(type), mh.bindTo(this).asType(MethodType.methodType(MethodHandle.class, type.parameterType(0)))));
        }
    }
}
