import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;

public class Main {
    private final static MethodHandles.Lookup lookup = MethodHandles.lookup();
    private final static ArrayList<MethodHandle> mhs_virtual = new ArrayList<>();
    private final static ArrayList<MethodHandle> mhs_static = new ArrayList<>();
    private final static ArrayList<MethodHandle> mhs_interface = new ArrayList<>();
    private static MethodHandle mhHier;

    static {
        try {
            mhHier = lookup.findVirtual(String.class, "toString", MethodType.methodType(String.class));
            initInterfaceMethod();
            initVirtualMethod();
            initStaticMethod();
        } catch (NoSuchMethodException | IllegalAccessException ignored) {
            // nothing
        }
    }

    public static void main(String[] args) {
        testInterfaceMethod();
        testVirtualMethod();
        testStaticMethod();
        // test constructor, special, ...
    }

    /***************************/
    /** test each method kind **/
    /***************************/
    private static void testInterfaceMethod() {
        InterfaceMethods i = new VirtualMethods();
        System.out.println("interface:");
        try { System.out.print("    noParameters: "); System.out.println((mhs_interface.get(0).invoke(i).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersWide: "); System.out.println((mhs_interface.get(1).invoke(i).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(2).invoke(i).equals(i))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersVoid: "); mhs_interface.get(3).invoke(i); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParameters: "); System.out.println((mhs_interface.get(4).invoke(i, (Object) true).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_interface.get(5).invoke(i, (Object) (byte) 25).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_interface.get(6).invoke(i, (Object) 'z').equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_interface.get(7).invoke(i, (Object) (short) 25).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_interface.get(8).invoke(i, (Object) 25).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_interface.get(9).invoke(i, (Object) 25L).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_interface.get(10).invoke(i, (Object) 25.F).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_interface.get(11).invoke(i, (Object) 25.).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_interface.get(12).invoke(i, i).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersWide: "); System.out.println((mhs_interface.get(13).invoke(i, (Object) true).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_interface.get(14).invoke(i, (Object) (byte) 25).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_interface.get(15).invoke(i, (Object) 'z').equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_interface.get(16).invoke(i, (Object) (short) 25).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_interface.get(17).invoke(i, (Object) 25).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_interface.get(18).invoke(i, (Object) 25L).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_interface.get(19).invoke(i, (Object) 25.F).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_interface.get(20).invoke(i, (Object) 25.).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_interface.get(21).invoke(i, i).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(22).invoke(i, (Object) true).equals(i))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(23).invoke(i, (Object) (byte) 25).equals(i))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(24).invoke(i, (Object) 'z').equals(i))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(25).invoke(i, (Object) (short) 25).equals(i))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(26).invoke(i, (Object) 25).equals(i))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(27).invoke(i, (Object) 25L).equals(i))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(28).invoke(i, (Object) 25.F).equals(i))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(29).invoke(i, (Object) 25.).equals(i))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(30).invoke(i, i).equals(i))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersVoid: "); mhs_interface.get(31).invoke(i, (Object) true); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(32).invoke(i, (Object) (byte) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(33).invoke(i, (Object) 'z'); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(34).invoke(i, (Object) (short) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(35).invoke(i, (Object) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(36).invoke(i, (Object) 25L); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(37).invoke(i, (Object) 25.F); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(38).invoke(i, (Object) 25.); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(39).invoke(i, i); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
    }

    private static void testVirtualMethod() {
        VirtualMethods v = new VirtualMethods();
        System.out.println("virtual:");
//        try { System.out.print("    hierarchy: "); System.out.println((mhHier.invoke((Object)"toto").equals("toto"))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    noParameters: "); System.out.println((mhs_virtual.get(0).invoke(v).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersWide: "); System.out.println((mhs_virtual.get(1).invoke(v).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(2).invoke(v).equals(v))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersVoid: "); mhs_virtual.get(3).invoke(v); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParameters: "); System.out.println((mhs_virtual.get(4).invoke(v, (Object) true).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_virtual.get(5).invoke(v, (Object) (byte) 25).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_virtual.get(6).invoke(v, (Object) 'z').equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_virtual.get(7).invoke(v, (Object) (short) 25).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_virtual.get(8).invoke(v, (Object) 25).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_virtual.get(9).invoke(v, (Object) 25L).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_virtual.get(10).invoke(v, (Object) 25.F).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_virtual.get(11).invoke(v, (Object) 25.).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_virtual.get(12).invoke(v, v).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersWide: "); System.out.println((mhs_virtual.get(13).invoke(v, (Object) true).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_virtual.get(14).invoke(v, (Object) (byte) 25).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_virtual.get(15).invoke(v, (Object) 'z').equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_virtual.get(16).invoke(v, (Object) (short) 25).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_virtual.get(17).invoke(v, (Object) 25).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_virtual.get(18).invoke(v, (Object) 25L).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_virtual.get(19).invoke(v, (Object) 25.F).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_virtual.get(20).invoke(v, (Object) 25.).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_virtual.get(21).invoke(v, v).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(22).invoke(v, (Object) true).equals(v))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(23).invoke(v, (Object) (byte) 25).equals(v))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(24).invoke(v, (Object) 'z').equals(v))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(25).invoke(v, (Object) (short) 25).equals(v))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(26).invoke(v, (Object) 25).equals(v))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(27).invoke(v, (Object) 25L).equals(v))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(28).invoke(v, (Object) 25.F).equals(v))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(29).invoke(v, (Object) 25.).equals(v))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(30).invoke(v, v).equals(v))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(31).invoke(v, (Object) true); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(32).invoke(v, (Object) (byte) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(33).invoke(v, (Object) 'z'); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(34).invoke(v, (Object) (short) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(35).invoke(v, (Object) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(36).invoke(v, (Object) 25L); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(37).invoke(v, (Object) 25.F); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(38).invoke(v, (Object) 25.); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(39).invoke(v, v); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
    }

    private static void testStaticMethod() {
        System.out.println("static:");
        try { System.out.print("    noParameters: "); System.out.println((mhs_static.get(0).invoke().equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersWide: "); System.out.println((mhs_static.get(1).invoke().equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(2).invoke().equals(StaticMethods.l))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersVoid: "); mhs_static.get(3).invoke(); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParameters: "); System.out.println((mhs_static.get(4).invoke((Object) true).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_static.get(5).invoke((Object) (byte) 25).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_static.get(6).invoke((Object) 'z').equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_static.get(7).invoke((Object) (short) 25).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_static.get(8).invoke((Object) 25).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_static.get(9).invoke((Object) 25L).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_static.get(10).invoke((Object) 25.F).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_static.get(11).invoke((Object) 25.).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((mhs_static.get(12).invoke(StaticMethods.l).equals(42))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersWide: "); System.out.println((mhs_static.get(13).invoke((Object) true).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_static.get(14).invoke((Object) (byte) 25).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_static.get(15).invoke((Object) 'z').equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_static.get(16).invoke((Object) (short) 25).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_static.get(17).invoke((Object) 25).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_static.get(18).invoke((Object) 25L).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_static.get(19).invoke((Object) 25.F).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_static.get(20).invoke((Object) 25.).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((mhs_static.get(21).invoke(StaticMethods.l).equals(42L))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(22).invoke((Object) true).equals(StaticMethods.l))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(23).invoke((Object) (byte) 25).equals(StaticMethods.l))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(24).invoke((Object) 'z').equals(StaticMethods.l))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(25).invoke((Object) (short) 25).equals(StaticMethods.l))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(26).invoke((Object) 25).equals(StaticMethods.l))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(27).invoke((Object) 25L).equals(StaticMethods.l))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(28).invoke((Object) 25.F).equals(StaticMethods.l))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(29).invoke((Object) 25.).equals(StaticMethods.l))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(30).invoke(StaticMethods.l).equals(StaticMethods.l))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersVoid: "); mhs_static.get(31).invoke((Object) true); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(32).invoke((Object) (byte) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(33).invoke((Object) 'z'); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(34).invoke((Object) (short) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(35).invoke((Object) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(36).invoke((Object) 25L); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(37).invoke((Object) 25.F); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(38).invoke((Object) 25.); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(39).invoke(StaticMethods.l); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
    }

    /***************************/
    /** init each method kind **/
    /***************************/
    private static void initInterfaceMethod() throws NoSuchMethodException, IllegalAccessException {
        mhs_interface.add(lookup.findVirtual(InterfaceMethods.class, "noParameters", MethodType.methodType(int.class)));
        mhs_interface.add(lookup.findVirtual(InterfaceMethods.class, "noParametersWide", MethodType.methodType(long.class)));
        mhs_interface.add(lookup.findVirtual(InterfaceMethods.class, "noParametersObject", MethodType.methodType(Object.class)));
        mhs_interface.add(lookup.findVirtual(InterfaceMethods.class, "noParametersVoid", MethodType.methodType(void.class)));
        initInterfaceMethod0("allParameters", int.class);
        initInterfaceMethod0("allParametersWide", long.class);
        initInterfaceMethod0("allParametersObject", Object.class);
        initInterfaceMethod0("allParametersVoid", void.class);
    }

    private static void initInterfaceMethod0(String name, Class<?> rtype) throws NoSuchMethodException, IllegalAccessException {
        Class <?>[] classes = {boolean.class, byte.class, char.class, short.class, int.class, long.class, float.class, double.class, Object.class};
        for (Class<?> ptype: classes) {
            mhs_interface.add(lookup.findVirtual(InterfaceMethods.class, name, MethodType.methodType(rtype, ptype)));
        }
    }

    private static void initVirtualMethod() throws NoSuchMethodException, IllegalAccessException {
        mhs_virtual.add(lookup.findVirtual(VirtualMethods.class, "noParameters", MethodType.methodType(int.class)));
        mhs_virtual.add(lookup.findVirtual(VirtualMethods.class, "noParametersWide", MethodType.methodType(long.class)));
        mhs_virtual.add(lookup.findVirtual(VirtualMethods.class, "noParametersObject", MethodType.methodType(Object.class)));
        mhs_virtual.add(lookup.findVirtual(VirtualMethods.class, "noParametersVoid", MethodType.methodType(void.class)));
        initVirtualMethod0("allParameters", int.class);
        initVirtualMethod0("allParametersWide", long.class);
        initVirtualMethod0("allParametersObject", Object.class);
        initVirtualMethod0("allParametersVoid", void.class);
    }

    private static void initVirtualMethod0(String name, Class<?> rtype) throws NoSuchMethodException, IllegalAccessException {
        Class <?>[] classes = {boolean.class, byte.class, char.class, short.class, int.class, long.class, float.class, double.class, Object.class};
        for (Class<?> ptype: classes) {
            mhs_virtual.add(lookup.findVirtual(VirtualMethods.class, name, MethodType.methodType(rtype, ptype)));
        }
    }

    private static void initStaticMethod() throws NoSuchMethodException, IllegalAccessException {
        mhs_static.add(lookup.findStatic(StaticMethods.class, "noParameters", MethodType.methodType(int.class)));
        mhs_static.add(lookup.findStatic(StaticMethods.class, "noParametersWide", MethodType.methodType(long.class)));
        mhs_static.add(lookup.findStatic(StaticMethods.class, "noParametersObject", MethodType.methodType(Object.class)));
        mhs_static.add(lookup.findStatic(StaticMethods.class, "noParametersVoid", MethodType.methodType(void.class)));
        initStaticMethod0("allParameters", int.class);
        initStaticMethod0("allParametersWide", long.class);
        initStaticMethod0("allParametersObject", Object.class);
        initStaticMethod0("allParametersVoid", void.class);
    }

    private static void initStaticMethod0(String name, Class<?> rtype) throws NoSuchMethodException, IllegalAccessException {
        Class <?>[] classes = {boolean.class, byte.class, char.class, short.class, int.class, long.class, float.class, double.class, Object.class};
        for (Class<?> ptype: classes) {
            mhs_static.add(lookup.findStatic(StaticMethods.class, name, MethodType.methodType(rtype, ptype)));
        }
    }
}
