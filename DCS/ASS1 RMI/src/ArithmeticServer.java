import java.rmi.*;
import java.rmi.registry.*;

public class ArithmeticServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            ArithmeticInterface obj = new ArithmeticImplementation();
            Naming.rebind("AddService", obj);
            System.out.println("Server Ready...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
