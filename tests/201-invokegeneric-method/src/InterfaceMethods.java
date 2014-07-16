public interface InterfaceMethods {
  int noParameters();
  long noParametersWide();
  Object noParametersObject();
  void noParametersVoid();

  int allParameters(boolean z);
  int allParameters(byte b);
  int allParameters(char c);
  int allParameters(short s);
  int allParameters(int i);
  int allParameters(long j);
  int allParameters(float f);
  int allParameters(double d);
  int allParameters(Object l);

  long allParametersWide(boolean z);
  long allParametersWide(byte b);
  long allParametersWide(char c);
  long allParametersWide(short s);
  long allParametersWide(int i);
  long allParametersWide(long j);
  long allParametersWide(float f);
  long allParametersWide(double d);
  long allParametersWide(Object l);

  Object allParametersObject(boolean z);
  Object allParametersObject(byte b);
  Object allParametersObject(char c);
  Object allParametersObject(short s);
  Object allParametersObject(int i);
  Object allParametersObject(long j);
  Object allParametersObject(float f);
  Object allParametersObject(double d);
  Object allParametersObject(Object l);

  void allParametersVoid(boolean z);
  void allParametersVoid(byte b);
  void allParametersVoid(char c);
  void allParametersVoid(short s);
  void allParametersVoid(int i);
  void allParametersVoid(long j);
  void allParametersVoid(float f);
  void allParametersVoid(double d);
  void allParametersVoid(Object l);
}
