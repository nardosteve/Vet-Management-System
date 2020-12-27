import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <h1>JDBC Connection code</h1>
 * <p>The code below is used to establish a connection with the 
 * PHPMYADMIN database in-order to create and retrieve data from the DB</p>
 * @version 1.0
 * @since 8/09/2019
 * @author Stephen, Jeremy and Manushi
 */

public class myConnection {
    
    /**
     * 
     * @return con
     */
    
    public static Connection getConnection(){
        
        //Declaring Variable (Connection)
        Connection con = null;
        
        //Try catch
        try{
            //Step 1 : Loading the Driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //Step 2 : Establish a connection
            //Connecting to the Database (Establishing conn btween DB and JavaApp)
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetmanagementsystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
 
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
    return con;  
    }

}
