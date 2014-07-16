import java.lang.Object;
import java.lang.String;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException{
        String svalue;
        boolean bvalue;
        int ivalue;

        String[] argv = { "three", "thee", "tee" };

        MethodHandle deepToString = MethodHandles.publicLookup().findStatic(Arrays.class, "deepToString", MethodType.methodType(String.class, Object[].class));
        MethodHandle ts1 = deepToString.asVarargsCollector(Object[].class);
        MethodHandle asList = MethodHandles.publicLookup().findStatic(Arrays.class, "asList", MethodType.methodType(List.class, Object[].class));

        try {
            System.out.print("ts1.invokeExact(new Object[]{\"won\"}) = ");
            svalue = (String) ts1.invokeExact(new Object[]{"won"});
            if (!"[won]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("ts1.invoke(new Object[]{\"won\"}) = ");
            svalue = (String) ts1.invoke(new Object[]{"won"});
            if (!"[won]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("ts1.invoke(\"won\") = ");
            svalue = (String) ts1.invoke("won");
            if (!"[won]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("ts1.invoke((Object) new Object[]{\"won\"}) = ");
            svalue = (String) ts1.invoke((Object) new Object[]{"won"});
            if (!"[[won]]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            // findStatic of Arrays.asList(...) produces a variable arity method handle:
            System.out.print("asList.type() = ");
            MethodType mtvalue = asList.type();
            if (!MethodType.methodType(List.class, Object[].class).equals(mtvalue)) { System.out.println(mtvalue + " [ERROR]"); } else { System.out.println(mtvalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("asList.isVarargsCollector() = ");
            bvalue = asList.isVarargsCollector();
            if (!bvalue) { System.out.println(bvalue + " [ERROR]"); } else { System.out.println(bvalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("asList.invoke().toString() = ");
            svalue = asList.invoke().toString();
            if (!"[]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("asList.invoke(1).toString() = ");
            svalue = asList.invoke(1).toString();
            if (!"[1]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("asList.invoke(\"two\", \"too\").toString() = ");
            svalue = asList.invoke("two", "too").toString();
            if (!"[two, too]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("asList.invoke(argv).toString() = ");
            svalue = asList.invoke(argv).toString();
            if (!"[three, thee, tee]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.print("asList.invoke((Object[]) argv).toString() = ");
            svalue = asList.invoke((Object[]) argv).toString();
            if (!"[three, thee, tee]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }

        try {
            System.out.println("(List) asList.invoke((Object)argv):");
            List ls = (List) asList.invoke((Object)argv);

            System.out.print("\tls.size() = ");
            ivalue = ls.size();
            if (1 != ivalue) { System.out.println(ivalue + " [ERROR]"); } else { System.out.println(ivalue); }

            System.out.print("\tArrays.toString((Object[])ls.get(0)) = ");
            svalue = Arrays.toString((Object[]) ls.get(0));
            if (!"[three, thee, tee]".equals(svalue)) { System.out.println(svalue + " [ERROR]"); } else { System.out.println(svalue); }
        } catch (Throwable t) { System.out.println(" ERROR"); t.printStackTrace(); }
    }
}
