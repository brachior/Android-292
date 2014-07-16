import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Main {
    private static MethodHandle asType;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.publicLookup();
            MethodHandle test = lookup.findStatic(AsType.class, "test", MethodType.methodType(double.class, long.class, float.class));
            asType = test.asType(MethodType.methodType(Object.class, int.class, int.class));
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
            // nothing
        }
    }

    public static void main(String[] args) {
        Object value;
        try {
            System.out.print("asType.invokeExact(1, 2) = ");
            value = asType.invokeExact(1, 2);
            if (!value.equals(3.)) { System.out.println(value + " [ERROR]"); } else { System.out.println(value); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("asType.invokeExact(2, 2) = ");
            value = asType.invoke(2, 2);
            if (!value.equals(4.)) { System.out.println(value + " [ERROR]"); } else { System.out.println(value); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }
    }

    public static class AsType {
        public static double test(long a, float b) {
            return a + b;
        }
    }
}
