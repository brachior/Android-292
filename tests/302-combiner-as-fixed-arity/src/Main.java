import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.WrongMethodTypeException;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static MethodHandle asListVar;
    private static MethodHandle asListFix;

    static {
        try {
            asListVar = MethodHandles.publicLookup().findStatic(Arrays.class, "asList", MethodType.methodType(List.class, Object[].class)).asVarargsCollector(Object[].class);
            asListFix = asListVar.asFixedArity();
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
            // nothing
        }
    }

    public static void main(String[] args) {
        String svalue;
        int ivalue;
        Exception caught;
        Object[] argv = {"three", "thee", "tee"};
        try {
            System.out.print("asListVar.invoke(1).toString() = ");
            svalue = asListVar.invoke(1).toString();
            if (!"[1]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            caught = null;
            System.out.print("\"asListFix.invoke((Object)1);\" -> ");
            try { asListFix.invoke((Object)1); } catch (Exception ex) { caught = ex; }
            if (!(caught instanceof ClassCastException)) { System.out.println(caught.getClass().getSimpleName() + " [ERROR]"); } else { System.out.println(caught.getClass().getSimpleName()); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("asListVar.invoke(\"two\", \"too\").toString() = ");
            svalue = asListVar.invoke("two", "too").toString();
            if (!"[two, too]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            caught = null;
            System.out.print("\"asListFix.invoke(\"two\", \"too\");\" -> ");
            try { asListFix.invoke("two", "too"); } catch (Exception ex) { caught = ex; }
            if (!(caught instanceof WrongMethodTypeException)) { System.out.println(caught.getClass().getSimpleName() + " [ERROR]"); } else { System.out.println(caught.getClass().getSimpleName()); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("asListVar.invoke(argv).toString() = ");
            svalue = asListVar.invoke(argv).toString();
            if (!"[three, thee, tee]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("asListFix.invoke(argv).toString() = ");
            svalue = asListFix.invoke(argv).toString();
            if (!"[three, thee, tee]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("asListVar.invoke((Object)argv)).size() = ");
            ivalue = ((List) asListVar.invoke((Object) argv)).size();
            if (1 != ivalue) { System.out.println(ivalue + " [ERROR]"); } else { System.out.println(ivalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("asListFix.invoke((Object)argv).toString() = ");
            svalue = asListFix.invoke((Object) argv).toString();
            if (!"[three, thee, tee]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }
    }
}
