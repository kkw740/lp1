import java.rmi.server.*;
import java.rmi.*;

public class ArithmeticImplementation extends UnicastRemoteObject implements ArithmeticInterface {
    ArithmeticImplementation() throws RemoteException { super(); }
    public int add(int a, int b) { System.out.println("Handled by thread: " + Thread.currentThread().getName());
    return a + b; }
}
