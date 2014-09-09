import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class Main {
    public static void main(String[] args) {
        simple();
        capture();
  }

    private static void simple() {
        System.out.print("bsmWithArgumentsSimple: ");
        try {
            System.out.println(DynamicInvoker.callBsmWithArgumentsSimple(5, 10) == 15 ? "true":"false");
        } catch (Throwable t) {
            System.out.println("false");
            t.printStackTrace();
        }
    }

    private static void capture() {
        System.out.print("bsmWithArguments: ");
        try {
            System.out.println(DynamicInvoker.callBsmWithArguments(5, 10) == 15 ? "true":"false");
        } catch (Throwable t) {
            System.out.println("false");
            t.printStackTrace();
        }
    }
}
