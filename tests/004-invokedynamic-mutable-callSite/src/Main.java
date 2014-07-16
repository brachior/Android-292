public class Main {
    public static void main(String[] args) {
        int ivalue;
        String svalue;
        boolean test = false;
        try {
            test = (ivalue = (int) DynamicInvoker.callPlus(290)) == (290 + 2);
            System.out.println("int(a) + 2 -> callPlus(290): " + ivalue + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            System.out.println("int(a) + 2 -> callPlus(290): FAILED"); throwable.printStackTrace();
        }
        try {
            test = (svalue = (String) DynamicInvoker.callPlus("JSR 29")).equals("JSR 29" + 2);
            System.out.println("str(a) + 2 -> callPlus(\"JSR 29\"): " + svalue + (test ? " -> OK" : " -> FAILED"));
        } catch (Throwable throwable) {
            System.out.println("str(a) + 2 -> callPlus(\"JSR 29\"): FAILED"); throwable.printStackTrace();
        }
    }

    // extract from DynamicInvoker.class
//  public static Object callPlus(Object);
//    Code:
//       0: aload_0
//       1: iconst_2
//       2: invokedynamic #550,  0  // InvokeDynamic #6:"+":(LObject;I)LObject;
//       7: areturn

    // extract from BootStraps.java
//  public static CallSite bsmMutableCS(MethodHandles.Lookup caller, String name, MethodType type)
//      throws IllegalAccessException, NoSuchMethodException {
//    return new CSMutableBind(type, mh_changeTarget);
//  }
//
//  private static Object changeTarget(MutableCallSite callSite, Object[] arguments) throws Throwable {
//    MethodHandle target = callSite.getTarget();
//    Class<?> aClass = arguments[0].getClass();
//    MethodHandle test = MethodHandles.insertArguments(mh_checkClass, 1, aClass);
//    test = MethodHandles.dropArguments(test, 1, callSite.type().parameterType(1));
//    if (aClass == Integer.class) {
//      target = mh_plusInt.asType(target.type());
//    } else if (aClass == String.class) {
//      target = mh_plusString.asType(target.type());
//    } else {
//      throw new Exception("You loose !");
//    }
//    callSite.setTarget(MethodHandles.guardWithTest(test, target, callSite.getTarget()));
//    return target.invokeWithArguments(arguments);
//  }
//
//  private static Object plusInt(int a, int b) { return a + b; }
//
//  private static Object plusString(String a, int b) { return a + b; }
//
//  private static boolean checkClass(Object value, Class<?> clazz) { return value.getClass() == clazz; }
}
