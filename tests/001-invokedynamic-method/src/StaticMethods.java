public class StaticMethods {
    public static final VirtualMethods l = new VirtualMethods();

    public static int noParameters() { return 42; }
    public static long noParametersWide() { return 42L; }
    public static Object noParametersObject() { return l; }
    public static void noParametersVoid() {}

    public static int allParameters(boolean z) { return 42; }
    public static int allParameters(byte b)    { return 42; }
    public static int allParameters(char c)    { return 42; }
    public static int allParameters(short s)   { return 42; }
    public static int allParameters(int i)     { return 42; }
    public static int allParameters(long j)    { return 42; }
    public static int allParameters(float f)   { return 42; }
    public static int allParameters(double d)  { return 42; }
    public static int allParameters(Object l)  { return 42; }

    public static long allParametersWide(boolean z) { return 42L; }
    public static long allParametersWide(byte b)    { return 42L; }
    public static long allParametersWide(char c)    { return 42L; }
    public static long allParametersWide(short s)   { return 42L; }
    public static long allParametersWide(int i)     { return 42L; }
    public static long allParametersWide(long j)    { return 42L; }
    public static long allParametersWide(float f)   { return 42L; }
    public static long allParametersWide(double d)  { return 42L; }
    public static long allParametersWide(Object l)  { return 42L; }

    public static Object allParametersObject(boolean z) { return l; }
    public static Object allParametersObject(byte b)    { return l; }
    public static Object allParametersObject(char c)    { return l; }
    public static Object allParametersObject(short s)   { return l; }
    public static Object allParametersObject(int i)     { return l; }
    public static Object allParametersObject(long j)    { return l; }
    public static Object allParametersObject(float f)   { return l; }
    public static Object allParametersObject(double d)  { return l; }
    public static Object allParametersObject(Object l)  { return l; }

    public static void allParametersVoid(boolean z) {}
    public static void allParametersVoid(byte b)    {}
    public static void allParametersVoid(char c)    {}
    public static void allParametersVoid(short s)   {}
    public static void allParametersVoid(int i)     {}
    public static void allParametersVoid(long j)    {}
    public static void allParametersVoid(float f)   {}
    public static void allParametersVoid(double d)  {}
    public static void allParametersVoid(Object l)  {}
  }
