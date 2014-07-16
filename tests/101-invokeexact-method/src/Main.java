import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;

public class Main {
    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();
    private static final ArrayList<MethodHandle> mhs_virtual = new ArrayList<>();
    private static final ArrayList<MethodHandle> mhs_static = new ArrayList<>();
    private static final ArrayList<MethodHandle> mhs_interface = new ArrayList<>();
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
        try { System.out.print("    noParameters: "); System.out.println(((int) mhs_interface.get(0).invokeExact(i) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersWide: "); System.out.println(((long) mhs_interface.get(1).invokeExact(i) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(2).invokeExact(i) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersVoid: "); mhs_interface.get(3).invokeExact(i); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_interface.get(4).invokeExact(i, true) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_interface.get(5).invokeExact(i, (byte) 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_interface.get(6).invokeExact(i, 'z') == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_interface.get(7).invokeExact(i, (short) 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_interface.get(8).invokeExact(i, 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_interface.get(9).invokeExact(i, 25L) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_interface.get(10).invokeExact(i, 25.F) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_interface.get(11).invokeExact(i, 25.) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_interface.get(12).invokeExact(i, (Object) i) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_interface.get(13).invokeExact(i, true) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_interface.get(14).invokeExact(i, (byte) 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_interface.get(15).invokeExact(i, 'z') == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_interface.get(16).invokeExact(i, (short) 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_interface.get(17).invokeExact(i, 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_interface.get(18).invokeExact(i, 25L) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_interface.get(19).invokeExact(i, 25.F) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_interface.get(20).invokeExact(i, 25.) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_interface.get(21).invokeExact(i, (Object) i) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(22).invokeExact(i, true) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(23).invokeExact(i, (byte) 25) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(24).invokeExact(i, 'z') == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(25).invokeExact(i, (short) 25) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(26).invokeExact(i, 25) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(27).invokeExact(i, 25L) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(28).invokeExact(i, 25.F) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(29).invokeExact(i, 25.) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_interface.get(30).invokeExact(i, (Object) i) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersVoid: "); mhs_interface.get(31).invokeExact(i, true); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(32).invokeExact(i, (byte) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(33).invokeExact(i, 'z'); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(34).invokeExact(i, (short) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(35).invokeExact(i, 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(36).invokeExact(i, 25L); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(37).invokeExact(i, 25.F); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(38).invokeExact(i, 25.); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_interface.get(39).invokeExact(i, (Object) i); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
    }

    private static void testVirtualMethod() {
        VirtualMethods v = new VirtualMethods();
        System.out.println("virtual:");
    //    try { System.out.print("    hierarchy: "); System.out.println((((String) mhHier.invokeExact("toto")).equals("toto"))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    noParameters: "); System.out.println(((int) mhs_virtual.get(0).invokeExact(v) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersWide: "); System.out.println(((long) mhs_virtual.get(1).invokeExact(v) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(2).invokeExact(v) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersVoid: "); mhs_virtual.get(3).invokeExact(v); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_virtual.get(4).invokeExact(v, true) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_virtual.get(5).invokeExact(v, (byte) 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_virtual.get(6).invokeExact(v, 'z') == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_virtual.get(7).invokeExact(v, (short) 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_virtual.get(8).invokeExact(v, 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_virtual.get(9).invokeExact(v, 25L) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_virtual.get(10).invokeExact(v, 25.F) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_virtual.get(11).invokeExact(v, 25.) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_virtual.get(12).invokeExact(v, (Object) v) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_virtual.get(13).invokeExact(v, true) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_virtual.get(14).invokeExact(v, (byte) 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_virtual.get(15).invokeExact(v, 'z') == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_virtual.get(16).invokeExact(v, (short) 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_virtual.get(17).invokeExact(v, 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_virtual.get(18).invokeExact(v, 25L) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_virtual.get(19).invokeExact(v, 25.F) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_virtual.get(20).invokeExact(v, 25.) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_virtual.get(21).invokeExact(v, (Object) v) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(22).invokeExact(v, true) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(23).invokeExact(v, (byte) 25) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(24).invokeExact(v, 'z') == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(25).invokeExact(v, (short) 25) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(26).invokeExact(v, 25) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(27).invokeExact(v, 25L) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(28).invokeExact(v, 25.F) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(29).invokeExact(v, 25.) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_virtual.get(30).invokeExact(v, (Object) v) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(31).invokeExact(v, true); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(32).invokeExact(v, (byte) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(33).invokeExact(v, 'z'); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(34).invokeExact(v, (short) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(35).invokeExact(v, 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(36).invokeExact(v, 25L); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(37).invokeExact(v, 25.F); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(38).invokeExact(v, 25.); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_virtual.get(39).invokeExact(v, (Object) v); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
    }

    private static void testStaticMethod() {
        System.out.println("static:");
        try { System.out.print("    noParameters: "); System.out.println(((int) mhs_static.get(0).invokeExact() == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersWide: "); System.out.println(((long) mhs_static.get(1).invokeExact() == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(2).invokeExact() == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersVoid: "); mhs_static.get(3).invokeExact(); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_static.get(4).invokeExact(true) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_static.get(5).invokeExact((byte) 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_static.get(6).invokeExact('z') == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_static.get(7).invokeExact((short) 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_static.get(8).invokeExact(25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_static.get(9).invokeExact(25L) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_static.get(10).invokeExact(25.F) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_static.get(11).invokeExact(25.) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println(((int) mhs_static.get(12).invokeExact((Object) StaticMethods.l) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_static.get(13).invokeExact(true) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_static.get(14).invokeExact((byte) 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_static.get(15).invokeExact('z') == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_static.get(16).invokeExact((short) 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_static.get(17).invokeExact(25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_static.get(18).invokeExact(25L) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_static.get(19).invokeExact(25.F) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_static.get(20).invokeExact(25.) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println(((long) mhs_static.get(21).invokeExact((Object) StaticMethods.l) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(22).invokeExact(true) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(23).invokeExact((byte) 25) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(24).invokeExact('z') == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(25).invokeExact((short) 25) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(26).invokeExact(25) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(27).invokeExact(25L) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(28).invokeExact(25.F) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(29).invokeExact(25.) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((mhs_static.get(30).invokeExact((Object) StaticMethods.l) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersVoid: "); mhs_static.get(31).invokeExact(true); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(32).invokeExact((byte) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(33).invokeExact('z'); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(34).invokeExact((short) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(35).invokeExact(25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(36).invokeExact(25L); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(37).invokeExact(25.F); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(38).invokeExact(25.); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); mhs_static.get(39).invokeExact((Object) StaticMethods.l); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
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
