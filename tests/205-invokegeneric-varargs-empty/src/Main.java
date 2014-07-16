import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;

public class Main {
    private static MethodHandle smhBoolean;
    private static MethodHandle smhByte;
    private static MethodHandle smhChar;
    private static MethodHandle smhShort;
    private static MethodHandle smhInt;
    private static MethodHandle smhLong;
    private static MethodHandle smhFloat;
    private static MethodHandle smhDouble;
    private static MethodHandle smhObject;

    private static MethodHandle mhBoolean;
    private static MethodHandle mhByte;
    private static MethodHandle mhChar;
    private static MethodHandle mhShort;
    private static MethodHandle mhInt;
    private static MethodHandle mhLong;
    private static MethodHandle mhFloat;
    private static MethodHandle mhDouble;
    private static MethodHandle mhObject;

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            smhBoolean = lookup.findStatic(StaticVarargs.class, "toString", MethodType.methodType(String.class, boolean[].class));
            smhByte = lookup.findStatic(StaticVarargs.class, "toString", MethodType.methodType(String.class, byte[].class));
            smhChar = lookup.findStatic(StaticVarargs.class, "toString", MethodType.methodType(String.class, char[].class));
            smhShort = lookup.findStatic(StaticVarargs.class, "toString", MethodType.methodType(String.class, short[].class));
            smhInt = lookup.findStatic(StaticVarargs.class, "toString", MethodType.methodType(String.class, int[].class));
            smhLong = lookup.findStatic(StaticVarargs.class, "toString", MethodType.methodType(String.class, long[].class));
            smhFloat = lookup.findStatic(StaticVarargs.class, "toString", MethodType.methodType(String.class, float[].class));
            smhDouble = lookup.findStatic(StaticVarargs.class, "toString", MethodType.methodType(String.class, double[].class));
            smhObject = lookup.findStatic(StaticVarargs.class, "toString", MethodType.methodType(String.class, Object[].class));

            mhBoolean = lookup.findVirtual(VirtualVarargs.class, "toString", MethodType.methodType(String.class, boolean[].class));
            mhByte = lookup.findVirtual(VirtualVarargs.class, "toString", MethodType.methodType(String.class, byte[].class));
            mhChar = lookup.findVirtual(VirtualVarargs.class, "toString", MethodType.methodType(String.class, char[].class));
            mhShort = lookup.findVirtual(VirtualVarargs.class, "toString", MethodType.methodType(String.class, short[].class));
            mhInt = lookup.findVirtual(VirtualVarargs.class, "toString", MethodType.methodType(String.class, int[].class));
            mhLong = lookup.findVirtual(VirtualVarargs.class, "toString", MethodType.methodType(String.class, long[].class));
            mhFloat = lookup.findVirtual(VirtualVarargs.class, "toString", MethodType.methodType(String.class, float[].class));
            mhDouble = lookup.findVirtual(VirtualVarargs.class, "toString", MethodType.methodType(String.class, double[].class));
            mhObject = lookup.findVirtual(VirtualVarargs.class, "toString", MethodType.methodType(String.class, Object[].class));
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
            // nothing
        }
    }

    public static void main(String[] args) {
        try { test("static boolean", (String) smhBoolean.invoke(), "[]"); } catch (Throwable e) { failed("static boolean"); e.printStackTrace(); }
        try { test("static byte", (String) smhByte.invoke(), "[]"); } catch (Throwable e) { failed("static byte"); e.printStackTrace(); }
        try { test("static char", (String) smhChar.invoke(), "[]"); } catch (Throwable e) { failed("static char"); e.printStackTrace(); }
        try { test("static short", (String) smhShort.invoke(), "[]"); } catch (Throwable e) { failed("static short"); e.printStackTrace(); }
        try { test("static int", (String) smhInt.invoke(), "[]"); } catch (Throwable e) { failed("static int"); e.printStackTrace(); }
        try { test("static long", (String) smhLong.invoke(), "[]"); } catch (Throwable e) { failed("static long"); e.printStackTrace(); }
        try { test("static float", (String) smhFloat.invoke(), "[]"); } catch (Throwable e) { failed("static float"); e.printStackTrace(); }
        try { test("static double", (String) smhDouble.invoke(), "[]"); } catch (Throwable e) { failed("static double"); e.printStackTrace(); }
        try { test("static Object", (String) smhObject.invoke(), "[]"); } catch (Throwable e) { failed("static Object"); e.printStackTrace(); }

        System.out.println(" --------------- ");

        VirtualVarargs v = new VirtualVarargs();
        try { test("virtual boolean", (String) mhBoolean.invoke(v), "[]"); } catch (Throwable e) { failed("virtual boolean"); e.printStackTrace(); }
        try { test("virtual byte", (String) mhByte.invoke(v), "[]"); } catch (Throwable e) { failed("virtual byte"); e.printStackTrace(); }
        try { test("virtual char", (String) mhChar.invoke(v), "[]"); } catch (Throwable e) { failed("virtual char"); e.printStackTrace(); }
        try { test("virtual short", (String) mhShort.invoke(v), "[]"); } catch (Throwable e) { failed("virtual short"); e.printStackTrace(); }
        try { test("virtual int", (String) mhInt.invoke(v), "[]"); } catch (Throwable e) { failed("virtual int"); e.printStackTrace(); }
        try { test("virtual long", (String) mhLong.invoke(v), "[]"); } catch (Throwable e) { failed("virtual long"); e.printStackTrace(); }
        try { test("virtual float", (String) mhFloat.invoke(v), "[]"); } catch (Throwable e) { failed("virtual float"); e.printStackTrace(); }
        try { test("virtual double", (String) mhDouble.invoke(v), "[]"); } catch (Throwable e) { failed("virtual double"); e.printStackTrace(); }
        try { test("virtual Object", (String) mhObject.invoke(v), "[]"); } catch (Throwable e) { failed("virtual Object"); e.printStackTrace(); }
    }

    public static void test(String name, String s1, String s2) {
        System.out.println(name + ": " + s1 + " - " + ((s1.equals(s2)) ? "OK" : "FAILED"));
    }

    public static void failed(String name) {
        System.out.println(name + ": FAILED");
    }
}
