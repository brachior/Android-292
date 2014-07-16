import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

public class Main {
  private final static Accessors accessors = new Accessors();
  private final static MethodHandles.Lookup lookup = MethodHandles.lookup();

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
      MethodHandle mhg = lookup.findGetter(Accessors.class, "z", boolean.class);
      MethodHandle mhs = lookup.findSetter(Accessors.class, "z", boolean.class);
      System.out.print("  boolean -> before: " + (boolean) mhg.invokeExact(accessors));
      mhs.invokeExact(accessors, true);
      System.out.println(" - after: " + (boolean) mhg.invokeExact(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testByteAccess() {
    try {
      MethodHandle mhg = lookup.findGetter(Accessors.class, "b", byte.class);
      MethodHandle mhs = lookup.findSetter(Accessors.class, "b", byte.class);
      System.out.print("  byte -> before: " + (byte) mhg.invokeExact(accessors));
      mhs.invokeExact(accessors, (byte) 25);
      System.out.println(" - after: " + (byte) mhg.invokeExact(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testCharAccess() {
    try {
      MethodHandle mhg = lookup.findGetter(Accessors.class, "c", char.class);
      MethodHandle mhs = lookup.findSetter(Accessors.class, "c", char.class);
      System.out.print("  char -> before: " + (char) mhg.invokeExact(accessors));
      mhs.invokeExact(accessors, 'c');
      System.out.println(" - after: " + (char) mhg.invokeExact(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testShortAccess() {
    try {
      MethodHandle mhg = lookup.findGetter(Accessors.class, "s", short.class);
      MethodHandle mhs = lookup.findSetter(Accessors.class, "s", short.class);
      System.out.print("  short -> before: " + (short) mhg.invokeExact(accessors));
      mhs.invokeExact(accessors, (short) 25);
      System.out.println(" - after: " + (short) mhg.invokeExact(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testIntAccess() {
    try {
      MethodHandle mhg = lookup.findGetter(Accessors.class, "i", int.class);
      MethodHandle mhs = lookup.findSetter(Accessors.class, "i", int.class);
      System.out.print("  int -> before: " + (int) mhg.invokeExact(accessors));
      mhs.invokeExact(accessors, 25);
      System.out.println(" - after: " + (int) mhg.invokeExact(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testLongAccess() {
    try {
      MethodHandle mhg = lookup.findGetter(Accessors.class, "j", long.class);
      MethodHandle mhs = lookup.findSetter(Accessors.class, "j", long.class);
      System.out.print("  long -> before: " + (long) mhg.invokeExact(accessors));
      mhs.invokeExact(accessors, 25L);
      System.out.println(" - after: " + (long) mhg.invokeExact(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testFloatAccess() {
    try {
      MethodHandle mhg = lookup.findGetter(Accessors.class, "f", float.class);
      MethodHandle mhs = lookup.findSetter(Accessors.class, "f", float.class);
      System.out.print("  float -> before: " + (float) mhg.invokeExact(accessors));
      mhs.invokeExact(accessors, 25.5F);
      System.out.println(" - after: " + (float) mhg.invokeExact(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testDoubleAccess() {
    try {
      MethodHandle mhg = lookup.findGetter(Accessors.class, "d", double.class);
      MethodHandle mhs = lookup.findSetter(Accessors.class, "d", double.class);
      System.out.print("  double -> before: " + (double) mhg.invokeExact(accessors));
      mhs.invokeExact(accessors, 25.5);
      System.out.println(" - after: " + (double) mhg.invokeExact(accessors));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testObjectAccess() {
    try {
      MethodHandle mhg = lookup.findGetter(Accessors.class, "l", Object.class);
      MethodHandle mhs = lookup.findSetter(Accessors.class, "l", Object.class);
      System.out.print("  Object -> before: " + mhg.invokeExact(accessors));
      mhs.invokeExact(accessors, new Object());
      System.out.println(" - after: " + (mhg.invokeExact(accessors) == null ? "null" : "not null"));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }
}
