import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Main {
    private static final MethodHandle mh;
    private static final MethodHandle mhA;
    private static final MethodHandle mhB;
    private static final MethodHandle change;
    private static final MethodType typeExact;
    private static final MethodType mhType;

    static {
        try {
            typeExact = MethodType.methodType(Double.class, int.class, long.class);
            mh = MethodHandles.lookup().findStatic(Main.class, "method", typeExact);
            mhType = MethodType.methodType(int.class, Object.class, int.class);
            mhA = MethodHandles.lookup().findStatic(Main.class, "method", MethodType.methodType(int.class, A.class, int.class)).asType(mhType);
            mhB = MethodHandles.lookup().findStatic(Main.class, "method", MethodType.methodType(int.class, B.class, int.class)).asType(mhType);
            change = MethodHandles.lookup().findStatic(Main.class, "change", MethodType.methodType(MethodHandle.class, Object.class))
                    .asType(MethodType.methodType(MethodHandle.class, mhType.parameterType(0)));
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new IllegalAccessError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        testExactExactInvoker();
        testExactGenericInvoker();
        testExactGenericInvokerLinked();
        testExactExactInvokerLinked();
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

    private static void testExactGenericInvokerLinked() {
        MethodHandle target = MethodHandles.exactInvoker(mhType);
        MethodHandle foldArguments = MethodHandles.foldArguments(target, change);
        try {
            A a = new A(5);
            int result = (int) foldArguments.invoke(a, 6);
            boolean test = result == 11;
            System.out.println("  exactInvoker (generic) -> (linked A):\n\t" + result + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("  exactInvoker (generic) -> (linked A):\n\tFAILED");
        }
        try {
            B b = new B(6);
            int result = (int) foldArguments.invoke(b, 5);
            boolean test = result == 11;
            System.out.println("  exactInvoker (generic) -> (linked B):\n\t" + result + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            System.out.println("  exactInvoker (generic) -> (linked B):\n\tFAILED");
        }
    }

    private static void testExactExactInvokerLinked() {
        MethodHandle target = MethodHandles.exactInvoker(mhType);
        MethodHandle foldArguments = MethodHandles.foldArguments(target, change);
        try {
            A a = new A(5);
            int result = (int) foldArguments.invokeExact((Object) a, 6);
            boolean test = result == 11;
            System.out.println("  exactInvoker (exact) -> (linked A):\n\t" + result + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("  exactInvoker (exact) -> (linked A):\n\tFAILED");
        }
        try {
            B b = new B(6);
            int result = (int) foldArguments.invokeExact((Object) b, 5);
            boolean test = result == 11;
            System.out.println("  exactInvoker (exact) -> (linked B):\n\t" + result + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            System.out.println("  exactInvoker (exact) -> (linked B):\n\tFAILED");
        }
    }

    static class A {
        final int a;

        A(int a) {
            this.a = a;
        }
    }

    static class B {
        final int b;

        B(int b) {
            this.b = b;
        }
    }

    private static int method(A a, int b) { return a.a + b;
    }

    private static int method(B b, int a) {
        return b.b + a;
    }

    private static MethodHandle change(Object argument) {
        return argument.getClass() == B.class ? mhB : mhA;
    }
}
