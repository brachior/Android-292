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
}
