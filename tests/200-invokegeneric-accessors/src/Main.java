import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

public class Main {
  private final static Object access = new Accessors();
  private final static MethodHandles.Lookup lookup = MethodHandles.lookup();;

  public static void main(String[] args) {
    testAccess(boolean.class, "z", true);
    testAccess(byte.class, "b", (byte) 25);
    testAccess(char.class, "c", 'c');
    testAccess(short.class, "s", (short) 25);
    testAccess(int.class, "i", 25);
    testAccess(long.class, "j", 25);
    testAccess(float.class, "f", 25.5F);
    testAccess(double.class, "d", 25.5);
    testAccessObject(Object.class, "l", new Object());
  }

  /***************************/
  /** test each accessor    **/
  /***************************/
  private static void testAccess(Class<?> clazz, String name, Object value) {
    try {
      MethodHandle mhg = lookup.findGetter(Accessors.class, name, clazz);
      MethodHandle mhs = lookup.findSetter(Accessors.class, name, clazz);
      System.out.print("  " + clazz.getSimpleName() + " -> before: " + mhg.invoke(access));
      mhs.invoke(access, value);
      System.out.println(" - after: " + mhg.invoke(access));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  private static void testAccessObject(Class<?> clazz, String name, Object value) {
    try {
      MethodHandle mhg = lookup.findGetter(Accessors.class, name, clazz);
      MethodHandle mhs = lookup.findSetter(Accessors.class, name, clazz);
      System.out.print("  " + clazz.getSimpleName() + " -> before: " + mhg.invoke(access));
      mhs.invoke(access, value);
      System.out.println(" - after: " + (mhg.invoke(access) == null ? "null" : "not null"));
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }
}
