import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Main {
    private static MethodHandle cat;
    private static MethodType bigType;
    private static MethodHandle d0;

    static {
        try {
            cat = MethodHandles.publicLookup().findVirtual(String.class, "concat", MethodType.methodType(String.class, String.class));
            bigType = cat.type().insertParameterTypes(0, int.class, String.class);
            d0 = MethodHandles.dropArguments(cat, 0, bigType.parameterList().subList(0, 2));
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
            // nothing
        }
    }

    public static void main(String[] args) {
        String svalue;
        try {
            System.out.print("cat.invokeExact(\"x\", \"y\") = ");
            svalue = (String) cat.invokeExact("x", "y");
            if (!"xy".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        System.out.print(bigType + " = ");
        if (bigType != d0.type()) { System.out.println(d0.type() + " [ERROR]"); } else { System.out.println(d0.type()); }

        try {
            System.out.print("d0.invokeExact(123, \"x\", \"y\", \"z\") = ");
            svalue = (String) d0.invokeExact(123, "x", "y", "z");
            if (!"yz".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }
    }
}
