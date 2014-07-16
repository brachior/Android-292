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
        try { test("static boolean", (String) smhBoolean.invoke(true, true, false, true, true, true, false, true), Arrays.toString(new boolean[]{true, true, false, true, true, true, false, true})); } catch (Throwable e) { failed("static boolean"); e.printStackTrace(); }
        try { test("static byte", (String) smhByte.invoke((byte) 5, (byte) 4, (byte) 6, (byte) 3, (byte) 1, (byte) 2), Arrays.toString(new byte[]{5, 4, 6, 3, 1, 2})); } catch (Throwable e) { failed("static byte"); e.printStackTrace(); }
        try { test("static char", (String) smhChar.invoke('f', 'o', 'o', 'b', 'a', 'r'), Arrays.toString(new char[]{'f', 'o', 'o', 'b', 'a', 'r'})); } catch (Throwable e) { failed("static char"); e.printStackTrace(); }
        try { test("static short", (String) smhShort.invoke((short) 5, (short) 4, (short) 6, (short) 3, (short) 1, (short) 2), Arrays.toString(new short[]{5, 4, 6, 3, 1, 2})); } catch (Throwable e) { failed("static short"); e.printStackTrace(); }
        try { test("static int", (String) smhInt.invoke(5, 4, 6, 3, 1, 2), Arrays.toString(new int[]{5, 4, 6, 3, 1, 2})); } catch (Throwable e) { failed("static int"); e.printStackTrace(); }
        try { test("static long", (String) smhLong.invoke(5L, 4L, 6L, 3L, 1L, 2L), Arrays.toString(new long[]{5L, 4L, 6L, 3L, 1L, 2L})); } catch (Throwable e) { failed("static long"); e.printStackTrace(); }
        try { test("static float", (String) smhFloat.invoke(5.F, 4.F, 6.F, 3.F, 1.F, 2.F), Arrays.toString(new float[]{5.F, 4.F, 6.F, 3.F, 1.F, 2.F})); } catch (Throwable e) { failed("static float"); e.printStackTrace(); }
        try { test("static double", (String) smhDouble.invoke(5., 4., 6., 3., 1., 2.), Arrays.toString(new double[]{5., 4., 6., 3., 1., 2.})); } catch (Throwable e) { failed("static double"); e.printStackTrace(); }
        try { test("static Object", (String) smhObject.invoke("void", "foo", "(", "bar", ")", ";"), Arrays.toString(new String[]{"void", "foo", "(", "bar", ")", ";"})); } catch (Throwable e) { failed("static Object"); e.printStackTrace(); }

        System.out.println(" --------------- ");

        VirtualVarargs v = new VirtualVarargs();
        try { test("virtual boolean", (String) mhBoolean.invoke(v, true, true, false, true, true, true, false, true), Arrays.toString(new boolean[]{true, true, false, true, true, true, false, true})); } catch (Throwable e) { failed("virtual boolean"); e.printStackTrace(); }
        try { test("virtual byte", (String) mhByte.invoke(v, (byte) 5, (byte) 4, (byte) 6, (byte) 3, (byte) 1, (byte) 2), Arrays.toString(new byte[]{5, 4, 6, 3, 1, 2})); } catch (Throwable e) { failed("virtual byte"); e.printStackTrace(); }
        try { test("virtual char", (String) mhChar.invoke(v, 'f', 'o', 'o', 'b', 'a', 'r'), Arrays.toString(new char[]{'f', 'o', 'o', 'b', 'a', 'r'})); } catch (Throwable e) { failed("virtual char"); e.printStackTrace(); }
        try { test("virtual short", (String) mhShort.invoke(v, (short) 5, (short) 4, (short) 6, (short) 3, (short) 1, (short) 2), Arrays.toString(new short[]{5, 4, 6, 3, 1, 2})); } catch (Throwable e) { failed("virtual short"); e.printStackTrace(); }
        try { test("virtual int", (String) mhInt.invoke(v, 5, 4, 6, 3, 1, 2), Arrays.toString(new int[]{5, 4, 6, 3, 1, 2})); } catch (Throwable e) { failed("virtual int"); e.printStackTrace(); }
        try { test("virtual long", (String) mhLong.invoke(v, 5L, 4L, 6L, 3L, 1L, 2L), Arrays.toString(new long[]{5L, 4L, 6L, 3L, 1L, 2L})); } catch (Throwable e) { failed("virtual long"); e.printStackTrace(); }
        try { test("virtual float", (String) mhFloat.invoke(v, 5.F, 4.F, 6.F, 3.F, 1.F, 2.F), Arrays.toString(new float[]{5.F, 4.F, 6.F, 3.F, 1.F, 2.F})); } catch (Throwable e) { failed("virtual float"); e.printStackTrace(); }
        try { test("virtual double", (String) mhDouble.invoke(v, 5., 4., 6., 3., 1., 2.), Arrays.toString(new double[]{5., 4., 6., 3., 1., 2.})); } catch (Throwable e) { failed("virtual double"); e.printStackTrace(); }
        try { test("virtual Object", (String) mhObject.invoke(v, "void", "foo", "(", "bar", ")", ";"), Arrays.toString(new String[]{"void", "foo", "(", "bar", ")", ";"})); } catch (Throwable e) { failed("virtual Object"); e.printStackTrace(); }
    }

    public static void test(String name, String s1, String s2) {
        System.out.println(name + ": " + s1 + " - " + ((s1.equals(s2)) ? "OK" : "FAILED"));
    }

    public static void failed(String name) {
        System.out.println(name + ": FAILED");
    }
}
