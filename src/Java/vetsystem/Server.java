import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * <h1>Server-side code for the Vet system RMI</h1>
 * <h2>Server program should implement the remote Interface/ extend the implementation class</h2>
 * <p>Server program that extends to Implementation class</p>
 * @version 1.0
 * @since 8/09/2019
 * @author Stephen, Jeremy and Manushi
 */

public class Server extends Vetmanagementsystem{
   public Server() {}  
   public static void main(String args[]) { 
      try { 
         // Instantiating the implementation class 
         Vetmanagementsystem obj = new Vetmanagementsystem(); 
    
         // Exporting the object of implementation class  
         // (here we are exporting the remote object to the stub) 
         StartVetManagementSystem stub = (StartVetManagementSystem) UnicastRemoteObject.exportObject(obj, 0);  
         
         // Binding the remote object (stub) in the registry 
         Registry registry = LocateRegistry.getRegistry(); 
         
         
         registry.bind("StartVetManagementSystem", stub);  
         System.err.println("Server ready !"); 
      } catch (Exception e) { 
         System.err.println("Server exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
   }   
}
