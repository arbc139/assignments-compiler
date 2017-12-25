package lang;

import java.io.*;
import java.util.StringTokenizer;

public class System {

   private static BufferedReader reader = new BufferedReader(new InputStreamReader(java.lang.System.in));

   public final static int getInt() {
      try {
        java.lang.System.out.print("Please enter an integer value: ");
        String s = reader.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int i = Integer.parseInt(st.nextToken());
        java.lang.System.out.println("You have entered " + i + ".");
        return i;
      } catch(java.io.IOException e) {
        java.lang.System.out.println("Caught IOException " + e.getMessage());
        java.lang.System.exit(1);
        return -1;
      }
   }

   public final static boolean getBool() {
      try {
        java.lang.System.out.print("Please enter a bool value (true or false): ");
        String s = reader.readLine();
        StringTokenizer st = new StringTokenizer(s);
        boolean b = Boolean.parseBoolean(st.nextToken());
        java.lang.System.out.println("You have entered " + b + ".");
        return b;
      } catch(java.io.IOException e) {
        java.lang.System.out.println("Caught IOException " + e.getMessage());
        java.lang.System.exit(1);
        return false;
      }
   }

   public final static float getFloat() {
      try {
        java.lang.System.out.print("Please enter a float value: ");
        String s = reader.readLine();
        StringTokenizer st = new StringTokenizer(s);
        float f = Float.parseFloat(st.nextToken());
        java.lang.System.out.println("You have entered " + f + ".");
        return f;
      } catch(java.io.IOException e) {
        java.lang.System.out.println("Caught IOException " + e.getMessage());
        java.lang.System.exit(1);
        return (float) -1.0;
      }
   }

   public final static void getString() {
     java.lang.System.out.println("Error: getString not implemented.");
   }

   public final static void putInt(int i) {
      java.lang.System.out.print(i);
   }

   public final static void putBool(boolean b) {
      java.lang.System.out.print(b);
   }

   public final static void putFloat(float f) {
      java.lang.System.out.print(f);
   }

   public final static void putString(String s) {
     java.lang.System.out.print(s);
   }

   public final static void putLn() {
     java.lang.System.out.println("");
   }
}
