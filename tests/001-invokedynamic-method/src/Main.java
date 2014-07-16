public class Main {

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
        try { System.out.print("    noParameters: "); System.out.println((DynamicInvoker.callINoParameters(i) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersWide: "); System.out.println((DynamicInvoker.callINoParametersWide(i) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callINoParametersObject(i) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersVoid: "); DynamicInvoker.callINoParametersVoid(i); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callIAllParameters(i, true) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callIAllParameters(i, (byte) 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callIAllParameters(i, 'z') == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callIAllParameters(i, (short) 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callIAllParameters(i, 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callIAllParameters(i, 25L) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callIAllParameters(i, 25.F) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callIAllParameters(i, 25.) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callIAllParameters(i, i) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callIAllParametersWide(i, true) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callIAllParametersWide(i, (byte) 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callIAllParametersWide(i, 'z') == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callIAllParametersWide(i, (short) 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callIAllParametersWide(i, 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callIAllParametersWide(i, 25L) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callIAllParametersWide(i, 25.F) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callIAllParametersWide(i, 25.) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callIAllParametersWide(i, i) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callIAllParametersObject(i, true) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callIAllParametersObject(i, (byte) 25) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callIAllParametersObject(i, 'z') == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callIAllParametersObject(i, (short) 25) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callIAllParametersObject(i, 25) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callIAllParametersObject(i, 25L) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callIAllParametersObject(i, 25.F) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callIAllParametersObject(i, 25.) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callIAllParametersObject(i, i) == i)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callIAllParametersVoid(i, true); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callIAllParametersVoid(i, (byte) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callIAllParametersVoid(i, 'z'); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callIAllParametersVoid(i, (short) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callIAllParametersVoid(i, 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callIAllParametersVoid(i, 25L); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callIAllParametersVoid(i, 25.F); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callIAllParametersVoid(i, 25.); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callIAllParametersVoid(i, i); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
    }

    private static void testVirtualMethod() {
        VirtualMethods v = new VirtualMethods();
        System.out.println("virtual:");
        //    try { System.out.print("    hierarchy: "); System.out.println((mhHier.invoke("toto").equals("toto"))); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    noParameters: "); System.out.println((DynamicInvoker.callVNoParameters(v) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersWide: "); System.out.println((DynamicInvoker.callVNoParametersWide(v) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callVNoParametersObject(v) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersVoid: "); DynamicInvoker.callVNoParametersVoid(v); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callVAllParameters(v, true) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callVAllParameters(v, (byte) 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callVAllParameters(v, 'z') == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callVAllParameters(v, (short) 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callVAllParameters(v, 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callVAllParameters(v, 25L) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callVAllParameters(v, 25.F) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callVAllParameters(v, 25.) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callVAllParameters(v, v) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callVAllParametersWide(v, true) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callVAllParametersWide(v, (byte) 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callVAllParametersWide(v, 'z') == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callVAllParametersWide(v, (short) 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callVAllParametersWide(v, 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callVAllParametersWide(v, 25L) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callVAllParametersWide(v, 25.F) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callVAllParametersWide(v, 25.) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callVAllParametersWide(v, v) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callVAllParametersObject(v, true) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callVAllParametersObject(v, (byte) 25) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callVAllParametersObject(v, 'z') == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callVAllParametersObject(v, (short) 25) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callVAllParametersObject(v, 25) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callVAllParametersObject(v, 25L) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callVAllParametersObject(v, 25.F) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callVAllParametersObject(v, 25.) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callVAllParametersObject(v, v) == v)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callVAllParametersVoid(v, true); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callVAllParametersVoid(v, (byte) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callVAllParametersVoid(v, 'z'); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callVAllParametersVoid(v, (short) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callVAllParametersVoid(v, 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callVAllParametersVoid(v, 25L); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callVAllParametersVoid(v, 25.F); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callVAllParametersVoid(v, 25.); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callVAllParametersVoid(v, v); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
    }

    private static void testStaticMethod() {
        System.out.println("static:");
        try { System.out.print("    noParameters: "); System.out.println((DynamicInvoker.callSNoParameters() == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersWide: "); System.out.println((DynamicInvoker.callSNoParametersWide() == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callSNoParametersObject() == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersVoid: "); DynamicInvoker.callSNoParametersVoid(); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callSAllParameters(true) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callSAllParameters((byte) 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callSAllParameters('z') == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callSAllParameters((short) 25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callSAllParameters(25) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callSAllParameters(25L) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callSAllParameters(25.F) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callSAllParameters(25.) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParameters: "); System.out.println((DynamicInvoker.callSAllParameters(StaticMethods.l) == 42)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callSAllParametersWide(true) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callSAllParametersWide((byte) 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callSAllParametersWide('z') == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callSAllParametersWide((short) 25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callSAllParametersWide(25) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callSAllParametersWide(25L) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callSAllParametersWide(25.F) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callSAllParametersWide(25.) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersWide: "); System.out.println((DynamicInvoker.callSAllParametersWide(StaticMethods.l) == 42L)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callSAllParametersObject(true) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callSAllParametersObject((byte) 25) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callSAllParametersObject('z') == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callSAllParametersObject((short) 25) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callSAllParametersObject(25) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callSAllParametersObject(25L) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callSAllParametersObject(25.F) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callSAllParametersObject(25.) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    noParametersObject: "); System.out.println((DynamicInvoker.callSAllParametersObject(StaticMethods.l) == StaticMethods.l)); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }

        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callSAllParametersVoid(true); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callSAllParametersVoid((byte) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callSAllParametersVoid('z'); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callSAllParametersVoid((short) 25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callSAllParametersVoid(25); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callSAllParametersVoid(25L); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callSAllParametersVoid(25.F); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callSAllParametersVoid(25.); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
        try { System.out.print("    allParametersVoid: "); DynamicInvoker.callSAllParametersVoid(StaticMethods.l); System.out.println("true"); } catch (Throwable t) { System.out.println("false"); t.printStackTrace(); }
    }
}
