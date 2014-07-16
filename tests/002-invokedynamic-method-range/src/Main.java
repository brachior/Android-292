import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Main {
    private final static MethodHandles.Lookup lookup = MethodHandles.lookup();

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
            Range range = new Range("invoke", "dynamic");
            char c = '#';
            float f = 2.F;
            long j = 9L;
            double d = 2.;
            String result = DynamicInvoker.callVirtualRange(range, c, f, j, d);
            String expected = new Range("invoke", "dynamic").concat('#', 2.F, 9L, 2.);
            boolean test = result.equals(expected);
            System.out.println("  virtual -> new Range(\"invoke\", \"dynamic\").concat('#', 2.F, 9L, 2.):\n\t" + result + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            System.out.println("  virtual -> new Range(\"invoke\", \"dynamic\").concat('#', 2.F, 9L, 2.):\n\tFAILED");
            throwable.printStackTrace();
        }
    }

    private static void testStaticMethod() {
        try {
            MethodType mt = MethodType.methodType(String.class, String.class, String.class, char.class, float.class, long.class, double.class);
            MethodHandle mh = lookup.findStatic(Range.class, "concat", mt);
            String start = "invoke";
            String end = "dynamic";
            char c = '#';
            float f = 2.F;
            long j = 9L;
            double d = 2.;
            String result = DynamicInvoker.callStaticRange(start, end, c, f, j, d);
            String expected = Range.concat("invoke", "dynamic", '#', 2.F, 9L, 2.);
            boolean test = result.equals(expected);
            System.out.println("  static -> Range.concat(\"invoke\", \"dynamic\", '#', 2.F, 9L, 2.):\n\t" + result + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            System.out.println("  static -> Range.concat(\"invoke\", \"dynamic\", '#', 2.F, 9L, 2.):\n\tFAILED");
            throwable.printStackTrace();
        }
    }
}
