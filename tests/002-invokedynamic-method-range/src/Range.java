public class Range {
    private final String start;
    private final String end;

    public Range(String start, String end) {
      this.start = start;
      this.end = end;
    }

    public String concat(char c, float f, long j, double d) {
      return this.start + " " + c + (int)f + j + (int)d + " " + this.end;
    }

    public static String concat(String start, String end, char c, float f, long j, double d) {
      return start + " " + c + (int)f + j + (int)d + " " + end;
    }
  }
