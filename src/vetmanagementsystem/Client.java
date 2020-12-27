import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;

/**
 * <h1>Client-side code for the Vet system RMI</h1>
 * <p>Fetches remote object and invokes requires methods using objects</p>
 * @version 1.0
 * @since 8/09/2019
 * @author Stephen, Jeremy and Manushi
 */

public class Client {  
   private Client() {}  
   
   public static void main(String[] args) {  
      try {  
         // Getting the registry 
         Registry registry = LocateRegistry.getRegistry(null); 
    
         // Looking up the registry for the remote object 
         StartVetManagementSystem stub = (StartVetManagementSystem) registry.lookup("StartVetManagementSystem"); 
   
         // Calling the remote method using the obtained object 
         stub.startApp(); 
         
          System.out.println("Remote method invoked"); 
      } catch (Exception e) {
         System.err.println("Client exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
   } 
}