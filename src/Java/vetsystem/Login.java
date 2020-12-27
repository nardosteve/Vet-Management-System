import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.text.Font;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>This Scene allows users to login using their already accounts from the DB</h1>
 * <p>A brief description of this scene is it that it allows users to sign-in 
 * using their already created accounts</p>
 * @version 1.0
 * @since 8/09/2019
 * @author Stephen, Jeremy and Manushi
 */

public class Login extends Application implements StartVetManagementSystem{
    
       /**
     * <p>The method start() contains JavaFx code that allows for creation of buttons, TextFields etc</P>
     * @param primarystage This is an instance of the Stage
     *  
     */
    
    @Override
    public void start(Stage primarystage) throws Exception{
         
        //GridPane
        GridPane gridpane =  new GridPane();
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setAlignment(Pos.CENTER);
        //Background Colour
        //gridpane.setStyle("-fx-background-color: #DADEE9;");
        
        //primarystage
        Scene scene = new Scene(gridpane, 900, 700);
        primarystage.setTitle("VETMANAGEMENT SYSTEM - LOGIN");
        primarystage.setScene(scene);
        primarystage.show();
        
        //Alert TextBox
        Alert alert = new Alert(AlertType.INFORMATION);
        
        //Label
        Label loginemailAddr = new Label("Email Address : ");
        loginemailAddr.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(loginemailAddr, 0, 0);
        
        TextField loginemailTextField = new TextField();
        loginemailTextField.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(loginemailTextField, 1, 0);
        
        Label loginpassword = new Label("Password : ");
        loginpassword.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(loginpassword, 0, 1);
        
        PasswordField loginPassword = new PasswordField();
        loginPassword.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(loginPassword, 1, 1);
        
        //Button
        Button loginBtn = new Button("LOGIN !");
        loginBtn.setMaxWidth(250);
        loginBtn.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-font-size:11pt;");
        gridpane.add(loginBtn, 1, 2);
        
        Button DontHaveAnAcc = new Button("Not signed up?, Click here to signup");
        DontHaveAnAcc.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-font-size:8pt;");
        gridpane.add(DontHaveAnAcc, 1, 3);
        
         //DontHaveAnAcc (OnAction Event)
        DontHaveAnAcc.setOnAction((event) -> {
            
            //Change Scenes to Vet SIgnup Scene
			try{
            Vetmanagementsystem vms = new Vetmanagementsystem();
            vms.start(primarystage);
			}
			catch(Exception e){
				 //return null;  
			}
        });
        //DontHaveAnAcc (OnAction Event)
        
        //Setting ActionEvent on (loginBtn)
        //Button ActionEvent to login user
        loginBtn.setOnAction((event) -> {
                      
        //DB code to fetch data from DB
        PreparedStatement ps;
        ResultSet rs;
            
        // Button was clicked, login user...
        String emailAdd = loginemailTextField.getText();
        String pass = String.valueOf(loginpassword.getText());
        
        //Query
        String query = "SELECT * FROM `signup` WHERE `emailAddress` =? AND `password` =?";
               
        //Form Validation to check if fields are empty
        if(emailAdd.equals("")){
      
            alert.setTitle("VETMANAGEMENT SYSTEM - SIGN IN");
            alert.setHeaderText("Please your email");
            alert.setContentText("Thank you !");
            alert.showAndWait();
        }
  
        //Try catch block
        try{
            
           //Object of class (myConnection) with method (getConnection())
           ps = myConnection.getConnection().prepareStatement(query);
           
           ps.setString(1, emailAdd);
           ps.setString(2, pass);     
           rs = ps.executeQuery();
          
           //Check Data and change the Scene to (petInformation)
           if(rs.next()){
//               petInformation pi = new petInformation();
//               pi.start(primarystage); 
           }else{
               petInformation pi = new petInformation();
			   try{
				   pi.start(primarystage);
			   }catch(Exception e){
				   System.out.println(e);
			   }
               
           }    
        }catch(SQLException ex){
           System.out.println(ex.getMessage());  
		   //return null;  		   
        } 
	});     
    }
    
    /**
     * <p>Main method that launches the Login Scene</p>
     * @param args 
     */ 
    
    //Main method
    public void startApp() {
        launch();
    }
}
