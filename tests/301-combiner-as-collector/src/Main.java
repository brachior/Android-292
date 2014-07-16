import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.WrongMethodTypeException;
import java.util.Arrays;

public class Main {
    private static MethodHandle deepToString;
    private static MethodHandle ts1;
    private static MethodHandle ts2;
    private static MethodHandle ts0;
    private static MethodHandle ts22;
    private static MethodHandle bytesToString;
    private static MethodHandle longsToString;

    static {
        try {
            deepToString = MethodHandles.publicLookup().findStatic(Arrays.class, "deepToString", MethodType.methodType(String.class, Object[].class));
            ts1 = deepToString.asCollector(Object[].class, 1);
            ts2 = deepToString.asCollector(String[].class, 2);
            ts0 = deepToString.asCollector(Object[].class, 0);
            ts22 = deepToString.asCollector(Object[].class, 3).asCollector(String[].class, 2);
            bytesToString = MethodHandles.publicLookup().findStatic(Arrays.class, "toString", MethodType.methodType(String.class, byte[].class)).asCollector(byte[].class, 3);
            longsToString = MethodHandles.publicLookup().findStatic(Arrays.class, "toString", MethodType.methodType(String.class, long[].class)).asCollector(long[].class, 1);
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
            // nothing
        }
    }

    public static void main(String[] args) {
        String svalue;
        MethodType mtvalue;
        Exception caught;
        try {
            System.out.print("deepToString.invokeExact(new Object[]{\"won\"}) = ");
            svalue = (String) deepToString.invokeExact(new Object[]{"won"});
            if (!"[won]".equals(svalue)) { System.out.println(svalue + "[ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println("ERROR"); t.printStackTrace(); }

        try {
            System.out.print("ts1.type() = ");
            mtvalue = ts1.type();
            if (!MethodType.methodType(String.class, Object.class).equals(mtvalue)) { System.out.println(mtvalue + "[ERROR]"); } else { System.out.println(mtvalue); }
        } catch (Throwable t) { System.out.println("ERROR"); t.printStackTrace(); }

        try {
            caught = null;
            System.out.print("ts1.invokeExact(new Object[]{\"won\"}) -> ");
            try { svalue = (String) ts1.invokeExact(new Object[]{"won"}); } catch (Exception ex) { caught = ex; }
            if (!(caught instanceof WrongMethodTypeException)) { System.out.println(caught.getClass().getSimpleName() + " [ERROR]"); } else { System.out.println(caught.getClass().getSimpleName()); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("ts1.invokeExact((Object) new Object[]{\"won\"}) = ");
            svalue = (String) ts1.invokeExact((Object) new Object[]{"won"});
            if (!"[[won]]".equals(svalue)) { System.out.println(svalue + "[ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println("ERROR"); t.printStackTrace(); }

        try {
            // arrayType can be a subtype of Object[]
            System.out.print("ts2.type() = ");
            mtvalue = ts2.type();
            if (!MethodType.methodType(String.class, String.class, String.class).equals(mtvalue)) { System.out.println(mtvalue + "[ERROR]"); } else { System.out.println(mtvalue); }
        } catch (Throwable t) { System.out.println("ERROR"); t.printStackTrace(); }

        try {
            System.out.print("ts2.invokeExact(\"two\", \"too\") = ");
            svalue = (String) ts2.invokeExact("two", "too");
            if (!"[two, too]".equals(svalue)) { System.out.println(svalue + "[ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println("ERROR"); t.printStackTrace(); }

        try {
            System.out.print("ts0.invokeExact() = ");
            svalue = (String) ts0.invokeExact();
            if (!"[]".equals(svalue)) { System.out.println(svalue + "[ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println("ERROR"); t.printStackTrace(); }

        // collectors can be nested, Lisp-style
        try {
            System.out.print("ts22.invokeExact((Object) 'A', (Object) \"B\", \"C\", \"D\") = ");
            svalue = (String) ts22.invokeExact((Object) 'A', (Object) "B", "C", "D");
            if (!"[A, B, [C, D]]".equals(svalue)) { System.out.println(svalue + "[ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println("ERROR"); t.printStackTrace(); }

        // arrayType can be any primitive array type
        try {
            System.out.print("bytesToString.invokeExact((byte) 1, (byte) 2, (byte) 3) = ");
            svalue = (String) bytesToString.invokeExact((byte) 1, (byte) 2, (byte) 3);
            if (!"[1, 2, 3]".equals(svalue)) { System.out.println(svalue + "[ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println("ERROR"); t.printStackTrace(); }

        try {
            System.out.print("longsToString.invokeExact((long) 123) = ");
            svalue = (String) longsToString.invokeExact((long) 123);
            if (!"[123]".equals(svalue)) { System.out.println(svalue + "[ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println("ERROR"); t.printStackTrace(); }
    }
}
