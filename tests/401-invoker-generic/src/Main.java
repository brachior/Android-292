import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Main {
    private static MethodHandle mh;
    private static MethodType typeGeneric;

    static {
        try {
            typeGeneric = MethodType.methodType(Object.class, Object.class, Object.class);
            mh = MethodHandles.lookup().findStatic(Main.class, "method", MethodType.methodType(Double.class, int.class, long.class));
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
            // nothing
        }
    }

    public static void main(String[] args) {
        testGenericExactInvoker();
        testGenericGanericInvoker();
    }

    public static Double method(int a, long b) {
        return (double) (a + b);
    }

    /***************************/
    /** test each method kind **/
    /***************************/
    private static void testGenericGanericInvoker() {
        MethodHandle genGenMH = MethodHandles.invoker(typeGeneric);
        try {
            Object result = genGenMH.invoke(mh, 2, 290);
            boolean test = result.equals(292.0);
            System.out.println("  invoker -> (Object) invoke(mh, 2, 290):\n\t" + result + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            System.out.println("  invoker -> (Object) invoke(mh, 2, 290):\n\tFAILED");
            throwable.printStackTrace();
        }
    }

    private static void testGenericExactInvoker() {
        MethodHandle genExaMH = MethodHandles.invoker(typeGeneric);
        try {
            Object result = genExaMH.invokeExact(mh, (Object) 2, (Object) 290);
            boolean test = result.equals(292.0);
            System.out.println("  invoker -> (Object) invokeExact(mh, (Object) 2, (Object) 290):\n\t" + result + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            System.out.println("  invoker -> (Object) invokeExact(mh, (Object) 2, (Object) 290):\n\tFAILED");
            throwable.printStackTrace();
        }
    }
}
