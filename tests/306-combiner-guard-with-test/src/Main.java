import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Main {
    private static MethodHandle gwt;

    static {
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        try {
            MethodHandle test = lookup.findStatic(GWTTest.class, "test", MethodType.methodType(boolean.class, int.class, int.class));
            MethodHandle target = lookup.findStatic(GWTTest.class, "target", MethodType.methodType(String.class, int.class, int.class));
            MethodHandle fallback = lookup.findStatic(GWTTest.class, "fallback", MethodType.methodType(String.class, int.class, int.class));
            gwt = MethodHandles.guardWithTest(test, target, fallback);
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
            // nothing
        }
    }

    public static void main(String[] args) {
        String svalue;
        try {
            System.out.print("gwt.invoke(1, 2) = ");
            svalue = (String) gwt.invoke(1, 2);
            if (!"a != b".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("gwt.invoke(2, 2) = ");
            svalue = (String) gwt.invoke(2, 2);
            if (!"a == b".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }
    }

    public static class GWTTest {
        public static boolean test(int a, int b) {
            return a == b;
        }

        public static String target(int a, int b) {
            return "a == b";
        }

        public static String fallback(int a, int b) {
            return "a != b";
        }
    }
}
