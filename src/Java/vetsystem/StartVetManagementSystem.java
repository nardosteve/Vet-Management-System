import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * <h1>StartVetManagementSystem interface is the Remote interface</h1>
 * @author Stephen, Jeremy and Manushi
 */

//Creting Remote Interface for our app
public interface StartVetManagementSystem extends Remote {    
    //Empty Method body
    void startApp() throws RemoteException;   
}
