import java.rmi.*; 

public interface ArithmeticInterface extends Remote {
    int add(int a, int b) throws RemoteException;
}
