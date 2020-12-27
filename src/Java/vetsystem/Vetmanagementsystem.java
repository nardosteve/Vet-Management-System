import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;

/**
 * <h1>This Scene allows users to register for their new accounts</h1>
 * <h2>A brief description of this scene is that it allows users to 
 * create new accounts in order to login into the vet system</h2>
 * <p>This is the implementation class (Remote Object)</p>
 * @version 1.0
 * @since 8/09/2019
 * @author Stephen, Jeremy and Manushi
 */
public class Vetmanagementsystem extends Application implements StartVetManagementSystem{
    
    /**
     * <p>The method start() contains JavaFx code that allows for creation of buttons, TextFields etc</p>
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
        primarystage.setTitle("VETMANAGEMENT SYSTEM - SIGN UP");
        primarystage.setScene(scene);
        primarystage.show();
        
        //Alert TextBox
        Alert alert = new Alert(AlertType.INFORMATION);
         
        //Label, button, comboBox, Textfield
        Label emailAddress = new Label("Email Address : ");
        emailAddress.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(emailAddress, 0, 0);
        
        TextField emailAddressTextField = new TextField("");
        emailAddressTextField.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(emailAddressTextField, 1, 0);
      
        Label fname = new Label("First Name : ");
        fname.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(fname,0, 1);
        
        TextField fnameTextField = new TextField();
        fnameTextField.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(fnameTextField, 1, 1);
        
        Label lname = new Label("Last Name : ");
        lname.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(lname, 0, 2);
        
        TextField lnameTextField = new TextField();
        lnameTextField.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(lnameTextField, 1, 2);
        
        Label dobLabel = new Label("Date of Birth : ");
        dobLabel.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(dobLabel,0, 3);

        TextField DOB = new TextField();
        DOB.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(DOB, 01, 3);
         
        Label homeAddress = new Label("Home Address : ");
        homeAddress.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(homeAddress,0, 4);
        
        TextField homeAddressTextField = new TextField();
        homeAddressTextField.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(homeAddressTextField, 1, 4);
        
        Label password = new Label("Password : ");
        password.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(password, 0, 5);
          
        PasswordField passwordSignup = new PasswordField();
        passwordSignup.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(passwordSignup, 1, 5);
        
        //Button
        Button signupBtn = new Button("SIGN UP");
        signupBtn.setMaxWidth(250);
        //Setting font to the Button Text 
        signupBtn.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-font-size:11pt;");
        gridpane.add(signupBtn, 1, 6);
        
        Button alreadySignedUp = new Button("Already signed up?, Click here to Login");
        alreadySignedUp.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-font-size:8pt;");
        gridpane.add(alreadySignedUp, 1, 7);

        //alreadySignedUp (OnAction Event)
        alreadySignedUp.setOnAction((event) -> {
            
            //Change Scenes to Login Scene
            Login login = new Login();
			//exception
			try{
				login.start(primarystage);
			}catch(Exception e){
				System.out.println(e);
			} 
        });
        
        //SignupBtn (OnAction Event)
        signupBtn.setOnAction((event) -> {
            
        // Button was clicked, Signup user...
        //Assigning varibles to field names
        String emailAdd = emailAddressTextField.getText();
        String Fname = fnameTextField.getText();
        String Lname = lnameTextField.getText();
        String bDate = DOB.getText();
        String hAddrr = homeAddressTextField.getText();
        String pass = String.valueOf(passwordSignup.getText());
        
        //Form Validation (if fields are empty)
        if(emailAdd.equals("")){
      
            alert.setTitle("VETMANAGEMENT SYSTEM - SIGN UP");
            alert.setHeaderText("Please your Email ! ");
            alert.setContentText("Thank you !");
            alert.showAndWait();
        }
        else if(pass.equals("")){
            
            alert.setTitle("VETMANAGEMENT SYSTEM - SIGN UP");
            alert.setHeaderText("Please your Password ! ");
            alert.setContentText("Thank you !");
            alert.showAndWait(); 
        }
        
        //DB code to send data to the DB using button
        PreparedStatement ps;
        String query = "INSERT INTO `signup`(`emailAddress`, `fname`, `lname`, `DOB`, `homeAddress`, `password`) VALUES (?,?,?,?,?,?)";
     
       //Try catch block
       try{
           
           //Object of class (myConnection) with method (getConnection())
           ps = myConnection.getConnection().prepareStatement(query);
    
           ps.setString(1, emailAdd);
           ps.setString(2, Fname);
           ps.setString(3, Lname);
           ps.setString(4, bDate);
           ps.setString(5, hAddrr);
           ps.setString(6, pass);
           
           //Alert Dialog box for account creation/error creating account
           if(ps.executeUpdate() > 0){
               
            alert.setTitle("VETMANAGEMENT SYSTEM - SIGN UP");
            alert.setHeaderText(emailAdd+ ", your account was successfully created");
            alert.setContentText("Welcome "+ Fname+ " to the Vet Management System");
            alert.showAndWait(); 
           }
           else{
               
            alert.setTitle("VETMANAGEMENT SYSTEM - SIGN UP");
            alert.setHeaderText("Your account was not created");
            alert.setContentText("Thank you !");
            alert.showAndWait(); 
           }
           
  }catch(SQLException ex){
           System.out.println(ex.getMessage());        
       } 
});        
}
  
    /**
     * <h1>startApp() method overrides the remote object once called by the server</h1>
     * <p>The startApp() method overrides the remote interface method with an empty method body</p> 
     */

//    Remote interface Method
    public void startApp() {
        launch();
    }
}
