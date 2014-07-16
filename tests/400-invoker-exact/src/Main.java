import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Main {
    private static MethodHandle mh;
    private static MethodType typeExact;

    static {
        try {
            typeExact = MethodType.methodType(Double.class, int.class, long.class);
            mh = MethodHandles.lookup().findStatic(Main.class, "method", typeExact);
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
            // nothing
        }
    }

    public static void main(String[] args) {
        testExactExactInvoker();
        testExactGenericInvoker();
    }

    public static Double method(int a, long b) {
        return (double) (a + b);
    }

    /***************************/
    /** test each method kind **/
    /***************************/
    private static void testExactGenericInvoker() {
        MethodHandle exaGenMH = MethodHandles.exactInvoker(typeExact);
        try {
            Object result = exaGenMH.invoke(mh, (Object) 2, (Object) 290);
            boolean test = result.equals(292.0);
            System.out.println("  exactInvoker -> (Object) invoke(mh, (Object) 2, (Object) 290):\n\t" + result + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            System.out.println("  exactInvoker -> (Object) invoke(mh, (Object) 2, (Object) 290):\n\tFAILED");
            throwable.printStackTrace();
        }
    }

    private static void testExactExactInvoker() {
        MethodHandle exaExaMH = MethodHandles.exactInvoker(typeExact);
        try {
            Object result = (Double) exaExaMH.invokeExact(mh, 2, 290L);
            boolean test = result.equals(292.0);
            System.out.println("  exactInvoker -> (Double) invokeExact(mh, 2, 290L):\n\t" + result + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            System.out.println("  exactInvoker -> (Double) invokeExact(mh, 2, 290L):\n\tFAILED");
            throwable.printStackTrace();
        }
    }
}
