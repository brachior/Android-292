import java.lang.String;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Main {
    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();

    public static void main(String[] args) {
        testVirtualMethod();
        testStaticMethod();
        // test constructor, special, ...
    }

    /***************************/
    /** test each method kind **/
    /***************************/
    private static void testVirtualMethod() {
        try {
            MethodType mt = MethodType.methodType(String.class, char.class, float.class, long.class, double.class);
            MethodHandle mh = lookup.findVirtual(Range.class, "concat", mt);
            Range range = new Range("invoke", "exact");
            char c = '#';
            float f = 2.F;
            long j = 9L;
            double d = 2.;
            String result = (String) mh.invokeExact(range, c, f, j, d);
            String expected = new Range("invoke", "exact").concat('#', 2.F, 9L, 2.);
            boolean test = result.equals(expected);
            System.out.println("  virtual -> new Range(\"invoke\", \"exact\").concat('#', 2.F, 9L, 2.):\n\t" + result + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            System.out.println("  virtual -> new Range(\"invoke\", \"exact\").concat('#', 2.F, 9L, 2.):\n\tFAILED");
            throwable.printStackTrace();
        }
    }

    private static void testStaticMethod() {
        try {
            MethodType mt = MethodType.methodType(String.class, String.class, String.class, char.class, float.class, long.class, double.class);
            MethodHandle mh = lookup.findStatic(Range.class, "concat", mt);
            String start = "invoke";
            String end = "exact";
            char c = '#';
            float f = 2.F;
            long j = 9L;
            double d = 2.;
            String result = (String) mh.invokeExact(start, end, c, f, j, d);
            String expected = Range.concat("invoke", "exact", '#', 2.F, 9L, 2.);
            boolean test = result.equals(expected);
            System.out.println("  static -> Range.concat(\"invoke\", \"exact\", '#', 2.F, 9L, 2.):\n\t" + result + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            System.out.println("  static -> Range.concat(\"invoke\", \"exact\", '#', 2.F, 9L, 2.):\n\tFAILED");
            throwable.printStackTrace();
        }
    }
}
