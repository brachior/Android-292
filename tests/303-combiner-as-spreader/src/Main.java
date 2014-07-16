import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;

public class Main {
    private static MethodHandle equals;
    private static MethodHandle eq0;
    private static MethodHandle eq1;
    private static MethodHandle eq2;
    private static MethodHandle eq2s;
    private static MethodHandle caToString;
    private static MethodHandle caToString2;
    private static MethodHandle caToString3;
    
    static {
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        try {
            equals = lookup.findVirtual(String.class, "equals", MethodType.methodType(boolean.class, Object.class));
            eq0 = equals.asSpreader(Object[].class, 0);
            eq1 = equals.asSpreader(Object[].class, 1);
            eq2 = equals.asSpreader(Object[].class, 2);
            eq2s = equals.asSpreader(String[].class, 2);
            caToString = lookup.findStatic(Arrays.class, "toString", MethodType.methodType(String.class, char[].class));
            caToString3 = caToString.asCollector(char[].class, 3);
            caToString2 = caToString3.asSpreader(char[].class, 2);
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
            // nothing
        }
    }

    public static void main(String[] args) {
        boolean bvalue;
        String svalue;
        try {
            System.out.print("equals.invokeExact(\"me\", (Object) \"me\") = ");
            bvalue = (boolean) equals.invokeExact("me", (Object) "me");
            if (!bvalue) { System.out.println(bvalue + "[ERROR]"); } else { System.out.println(bvalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("equals.invokeExact(\"me\", (Object) \"thee\") = ");
            bvalue = (boolean) equals.invokeExact("me", (Object) "thee");
            if (bvalue) { System.out.println(bvalue + "[ERROR]"); } else { System.out.println(bvalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            // spread both arguments from a 2-array:
            System.out.print("eq2.invokeExact(new Object[]{\"me\", \"me\"}) = ");
            bvalue = (boolean) eq2.invokeExact(new Object[]{"me", "me"});
            if (!bvalue) { System.out.println(bvalue + "[ERROR]"); } else { System.out.println(bvalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("eq2.invokeExact(new Object[]{\"me\", \"thee\"}) = ");
            bvalue = (boolean) eq2.invokeExact(new Object[]{"me", "thee"});
            if (bvalue) { System.out.println(bvalue + "[ERROR]"); } else { System.out.println(bvalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            // spread both arguments from a String array:
            System.out.print("eq2s.invokeExact(new String[]{\"me\", \"me\"}) = ");
            bvalue = (boolean) eq2s.invokeExact(new String[]{"me", "me"});
            if (!bvalue) { System.out.println(bvalue + "[ERROR]"); } else { System.out.println(bvalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("eq2s.invokeExact(new String[]{\"me\", \"thee\"}) = ");
            bvalue = (boolean) eq2s.invokeExact(new String[]{"me", "thee"});
            if (bvalue) { System.out.println(bvalue + "[ERROR]"); } else { System.out.println(bvalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            // spread second arguments from a 1-array:
            System.out.print("eq1.invokeExact(\"me\", new Object[]{\"me\"}) = ");
            bvalue = (boolean) eq1.invokeExact("me", new Object[]{"me"});
            if (!bvalue) { System.out.println(bvalue + "[ERROR]"); } else { System.out.println(bvalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("eq1.invokeExact(\"me\", new Object[]{\"thee\"}) = ");
            bvalue = (boolean) eq1.invokeExact("me", new Object[]{"thee"});
            if (bvalue) { System.out.println(bvalue + "[ERROR]"); } else { System.out.println(bvalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            // spread no arguments from a 0-array or null:
            System.out.print("eq0.invokeExact(\"me\", (Object) \"me\", new Object[0]) = ");
            bvalue = (boolean) eq0.invokeExact("me", (Object) "me", new Object[0]);
            if (!bvalue) { System.out.println(bvalue + "[ERROR]"); } else { System.out.println(bvalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("eq0.invokeExact(\"me\", (Object) \"thee\", (Object[]) null) = ");
            bvalue = (boolean) eq0.invokeExact("me", (Object) "thee", (Object[]) null);
            if (bvalue) { System.out.println(bvalue + "[ERROR]"); } else { System.out.println(bvalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        // asSpreader and asCollector are approximate inverses:
        for (int n = 0; n <= 2; n++) {
            for (Class a : new Class[]{Object[].class, String[].class, CharSequence[].class}) {
                MethodHandle equals2 = equals.asSpreader(a, n).asCollector(a, n);
                try {
                    System.out.print("equals2.invokeWithArguments[" + a.getComponentType() + "," + n + "](\"me\", \"me\") = ");
                    bvalue = (boolean) equals2.invokeWithArguments("me", "me");
                    if (!bvalue) { System.out.println(bvalue + " [ERROR]"); } else { System.out.println(bvalue); }
                } catch (Throwable t) { System.out.println("ERROR"); t.printStackTrace(); }

                try {
                    System.out.print("equals2.invokeWithArguments[" + a.getComponentType() + "," + n + "](\"me\", \"thee\") = ");
                    bvalue = (boolean) equals2.invokeWithArguments("me", "thee");
                    if (bvalue) { System.out.println(bvalue + " [ERROR]"); } else { System.out.println(bvalue); }
                } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }
            }
        }

        try {
            System.out.print("caToString.invokeExact(\"ABC\".toCharArray()) = ");
            svalue = (String) caToString.invokeExact("ABC".toCharArray());
            if (!"[A, B, C]".equals(svalue)) { System.out.println(svalue + "[ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("caToString3.invokeExact('A', 'B', 'C') = ");
            svalue = (String) caToString3.invokeExact('A', 'B', 'C');
            if (!"[A, B, C]".equals(svalue)) { System.out.println(svalue + "[ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("caToString2.invokeExact('A', \"BC\".toCharArray()) = ");
            svalue = (String) caToString2.invokeExact('A', "BC".toCharArray());
            if (!"[A, B, C]".equals(svalue)) { System.out.println(svalue + "[ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }
    }
}
