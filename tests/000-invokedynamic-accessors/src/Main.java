public class Main {
  private final static Accessors accessors = new Accessors();

  public static void main(String[] args) {
    testBooleanAccess();
    testByteAccess();
    testCharAccess();
    testShortAccess();
    testIntAccess();
    testLongAccess();
    testFloatAccess();
    testDoubleAccess();
    testObjectAccess();
  }

  /***************************/
  /** test each accessor    **/
  /***************************/
  private static void testBooleanAccess() {
    try {
      System.out.print("  boolean -> before: " + DynamicInvoker.getBoolean(accessors));
      DynamicInvoker.setBoolean(accessors, true);
      System.out.println(" - after: " + DynamicInvoker.getBoolean(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testByteAccess() {
    try {
      System.out.print("  byte -> before: " + DynamicInvoker.getByte(accessors));
      DynamicInvoker.setByte(accessors, (byte) 25);
      System.out.println(" - after: " + DynamicInvoker.getByte(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testCharAccess() {
    try {
      System.out.print("  char -> before: " + DynamicInvoker.getChar(accessors));
      DynamicInvoker.setChar(accessors, 'c');
      System.out.println(" - after: " + DynamicInvoker.getChar(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testShortAccess() {
    try {
      System.out.print("  short -> before: " + DynamicInvoker.getShort(accessors));
      DynamicInvoker.setShort(accessors, (short) 25);
      System.out.println(" - after: " + DynamicInvoker.getShort(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testIntAccess() {
    try {
      System.out.print("  int -> before: " + DynamicInvoker.getInt(accessors));
      DynamicInvoker.setInt(accessors, 25);
      System.out.println(" - after: " + DynamicInvoker.getInt(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testLongAccess() {
    try {
      System.out.print("  long -> before: " + DynamicInvoker.getLong(accessors));
      DynamicInvoker.setLong(accessors, 25L);
      System.out.println(" - after: " + DynamicInvoker.getLong(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testFloatAccess() {
    try {
      System.out.print("  float -> before: " + DynamicInvoker.getFloat(accessors));
      DynamicInvoker.setFloat(accessors, 25.5F);
      System.out.println(" - after: " + DynamicInvoker.getFloat(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testDoubleAccess() {
    try {
      System.out.print("  double -> before: " + DynamicInvoker.getDouble(accessors));
      DynamicInvoker.setDouble(accessors, 25.5);
      System.out.println(" - after: " + DynamicInvoker.getDouble(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testObjectAccess() {
    try {
      System.out.print("  Object -> before: " + DynamicInvoker.getObject(accessors));
      DynamicInvoker.setObject(accessors, new Object());
      System.out.println(" - after: " + (DynamicInvoker.getObject(accessors) == null ? "null" : "not null"));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }
}
