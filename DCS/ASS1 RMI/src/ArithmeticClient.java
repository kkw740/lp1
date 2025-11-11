import java.rmi.*;
import java.util.*;

public class ArithmeticClient {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            ArithmeticInterface obj = (ArithmeticInterface) Naming.lookup("rmi://localhost/AddService");
            System.out.print("Enter two numbers: ");
            int a = sc.nextInt(), b = sc.nextInt();
            System.out.println("Result = " + obj.add(a, b));
             sc.close();
        } catch (Exception e) { System.out.println(e); }
   
    }

}
