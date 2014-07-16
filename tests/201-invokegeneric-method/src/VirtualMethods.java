public class VirtualMethods implements InterfaceMethods {
    public int noParameters() { return 42; }
    public long noParametersWide()  { return 42L; }
    public Object noParametersObject() { return this; }
    public void noParametersVoid() {}

    public int allParameters(boolean z) { return 42; }
    public int allParameters(byte b)    { return 42; }
    public int allParameters(char c)    { return 42; }
    public int allParameters(short s)   { return 42; }
    public int allParameters(int i)     { return 42; }
    public int allParameters(long j)    { return 42; }
    public int allParameters(float f)   { return 42; }
    public int allParameters(double d)  { return 42; }
    public int allParameters(Object l)  { return 42; }

    public long allParametersWide(boolean z) { return 42L; }
    public long allParametersWide(byte b)    { return 42L; }
    public long allParametersWide(char c)    { return 42L; }
    public long allParametersWide(short s)   { return 42L; }
    public long allParametersWide(int i)     { return 42L; }
    public long allParametersWide(long j)    { return 42L; }
    public long allParametersWide(float f)   { return 42L; }
    public long allParametersWide(double d)  { return 42L; }
    public long allParametersWide(Object l)  { return 42L; }

    public Object allParametersObject(boolean z) { return this; }
    public Object allParametersObject(byte b)    { return this; }
    public Object allParametersObject(char c)    { return this; }
    public Object allParametersObject(short s)   { return this; }
    public Object allParametersObject(int i)     { return this; }
    public Object allParametersObject(long j)    { return this; }
    public Object allParametersObject(float f)   { return this; }
    public Object allParametersObject(double d)  { return this; }
    public Object allParametersObject(Object l)  { return this; }

    public void allParametersVoid(boolean z) {}
    public void allParametersVoid(byte b)    {}
    public void allParametersVoid(char c)    {}
    public void allParametersVoid(short s)   {}
    public void allParametersVoid(int i)     {}
    public void allParametersVoid(long j)    {}
    public void allParametersVoid(float f)   {}
    public void allParametersVoid(double d)  {}
    public void allParametersVoid(Object l)  {}
  }
