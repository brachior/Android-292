import java.lang.invoke.MethodHandle;

import static java.lang.invoke.MethodHandles.*;
import static java.lang.invoke.MethodType.*;

public class Main {
    private static MethodHandle cat;
    private static MethodHandle trace;

    static {
        try {
            trace = publicLookup().findVirtual(java.io.PrintStream.class, "println", methodType(void.class, String.class)).bindTo(System.out);
            cat = lookup().findVirtual(String.class, "concat", methodType(String.class, String.class));
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
            // nothing
        }
    }

    public static void main(String[] args) {
        String svalue;
        try {
            System.out.print("cat.invokeExact(\"boo\", \"jum\") = ");
            svalue = (String) cat.invokeExact("boo", "jum");
            if (!"boojum".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            MethodHandle catTrace = foldArguments(cat, trace);
            System.out.print("catTrace.invokeExact(\"boo\", \"jum\") = ");
            svalue = (String) catTrace.invokeExact("boo", "jum");
            if (!"boojum".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }
    }
}
