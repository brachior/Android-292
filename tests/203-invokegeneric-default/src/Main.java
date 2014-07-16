import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Main {
    private final static MethodHandles.Lookup lookup = MethodHandles.lookup();
    private static MethodHandle smh;
    private static MethodHandle mh;
    
    static {
        try {
            smh = lookup.findStatic(Default.class, "sdef", MethodType.methodType(void.class));
            mh = lookup.findVirtual(Default.class, "def", MethodType.methodType(void.class));
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
            // nothing
        }
    }

    public static void main(String[] args) {
        try { primTest("static byte", (byte) smh.invoke(), (byte) 0); } catch (Throwable e) { failed("static byte"); e.printStackTrace(); }
        try { primTest("static short", (short) smh.invoke(), (short) 0); } catch (Throwable e) { failed("static short"); e.printStackTrace(); }
        try { primTest("static int", (int) smh.invoke(), 0); } catch (Throwable e) { failed("static int"); e.printStackTrace(); }
        try { primTest("static long", (long) smh.invoke(), 0L); } catch (Throwable e) { failed("static long"); e.printStackTrace(); }
        try { primTest("static float", (float) smh.invoke(), 0.0F); } catch (Throwable e) { failed("static float"); e.printStackTrace(); }
        try { primTest("static double", (double) smh.invoke(), 0.0); } catch (Throwable e) { failed("static double"); e.printStackTrace(); }
        try { primTest("static char", (char) smh.invoke(), '\u0000'); } catch (Throwable e) { failed("static char"); e.printStackTrace(); }
        try { objectTest("static Object", (Object) smh.invoke(), null); } catch (Throwable e) { failed("static Object"); e.printStackTrace(); }
        try { primTest("static boolean", (boolean) smh.invoke(), false); } catch (Throwable e) { failed("static boolean"); e.printStackTrace(); }

        System.out.println(" --------------- ");

        Default d = new Default();
        try { primTest("virtual byte", (byte) mh.invoke(d), (byte) 0); } catch (Throwable e) { failed("virtual byte"); e.printStackTrace(); }
        try { primTest("virtual short", (short) mh.invoke(d), (short) 0); } catch (Throwable e) { failed("virtual short"); e.printStackTrace(); }
        try { primTest("virtual int", (int) mh.invoke(d), 0); } catch (Throwable e) { failed("virtual int"); e.printStackTrace(); }
        try { primTest("virtual long", (long) mh.invoke(d), 0L); } catch (Throwable e) { failed("virtual long"); e.printStackTrace(); }
        try { primTest("virtual float", (float) mh.invoke(d), 0.0F); } catch (Throwable e) { failed("virtual float"); e.printStackTrace(); }
        try { primTest("virtual double", (double) mh.invoke(d), 0.0); } catch (Throwable e) { failed("virtual double"); e.printStackTrace(); }
        try { primTest("virtual char", (char) mh.invoke(d), '\u0000'); } catch (Throwable e) { failed("virtual char"); e.printStackTrace(); }
        try { objectTest("virtual Object", (Object) mh.invoke(d), null); } catch (Throwable e) { failed("virtual Object"); e.printStackTrace(); }
        try { primTest("virtual boolean", (boolean) mh.invoke(d), false); } catch (Throwable e) { failed("virtual boolean"); e.printStackTrace(); }
    }

    public static void failed(String name) {
        System.out.println(name + ": FAILED");
    }

    public static <E> void primTest(String name, E e1, E e2) {
        System.out.println(name + ": " + e1 + " - " + ((e1.equals(e2)) ? "OK" : "FAILED"));
    }

    public static void objectTest(String name, Object e1, Object e2) {
        System.out.println(name + ": " + e1 + " - " + ((e1 == e2) ? "OK" : "FAILED"));
    }
}
