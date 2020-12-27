import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * <h1>This Scene allows users to register their pets appointments after log-in</h1>
 * <p>A brief description of this scene is that it allows
 * users register their pets using a form</p>
 * @version 1.0
 * @since 8/09/2019
 * @author Stephen, Jeremy and Manushi
 */

public class petInformation extends Application implements StartVetManagementSystem{
    
    /**
     * 
     * @param primarystage2 
     */
    
    @Override
    public void start(Stage primarystage2)throws Exception{
        
        //GridPane
        GridPane gridpane =  new GridPane();
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setAlignment(Pos.CENTER);
        //Background Colour
        //gridpane.setStyle("-fx-background-color: #DADEE9;");
        
        //primarystage
        Scene petScene = new Scene(gridpane, 900, 700);
        primarystage2.setTitle("VETMANAGEMENT SYSTEM - PET APPOINTMENT");
        primarystage2.setScene(petScene);
        primarystage2.show();
        
              //Alert TextBox
       Alert alert = new Alert(AlertType.INFORMATION);
        
        //Label, ComboBox,Button
        Label petName = new Label("Pet Name : ");
        petName.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(petName, 0, 0);
        
        TextField petNameTextField = new TextField();
        petNameTextField.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(petNameTextField, 0, 1);
        
        Label petColour = new Label("Pet Colour : ");
        petColour.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(petColour, 1, 0);
        
        TextField petColourTextField = new TextField();
        petColourTextField.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(petColourTextField, 1, 1);
        
        Label petType = new Label("Pet Type : ");
        petType.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(petType, 0, 2);
        
        TextField petTypeField = new TextField();
        petTypeField.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(petTypeField, 0, 3);
        
        Label appointmentDate = new Label("Appointment Date (dd/mm/yy) : ");
        appointmentDate.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(appointmentDate, 1, 2);
        
        //Used when choosing dates
        //DatePicker petDAppointmentDate = new DatePicker();
        //gridpane.add(petDAppointmentDate, 1, 3);
        TextField petDAppointmentDate = new TextField();
        petDAppointmentDate.setFont(Font.font(java.awt.Font.SERIF, 15));
        gridpane.add(petDAppointmentDate, 1, 3);
        
        Button petSignup = new Button("PET SIGNUP !");
        petSignup.setMaxWidth(250);
        petSignup.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-font-size:10pt;");
        petSignup.setAlignment(Pos.CENTER);
        gridpane.add(petSignup, 1, 5);
        
        //Clear button
        Button showData = new Button("SHOW DATA");
        showData.setMaxWidth(250);
        showData.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-font-size:10pt;");
        gridpane.add(showData, 0, 5);
        
        //Fetch data button
        showData.setOnAction((event2) -> {
			    
        Connection con = null;
        
        try{
        //Step 1 : Loading the Driver
         Class.forName("com.mysql.jdbc.Driver");
            
         //Step 2 : Establish a connection
         //Connecting to the Database (Establishing conn btween DB and JavaApp)
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vetmanagementsystem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

        // if you only need a few columns, specify them by name instead of using "*"
        String query = "SELECT * FROM petregistration";

        // create the java statement
        Statement st = con.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);

        // iterate through the java resultset
        while (rs.next()){
          
          String petname = rs.getString(1);
          String petcolour = rs.getString(2);
          String pettype = rs.getString(3);
          String appointDate = rs.getString(4);
          
          //ShowDataGUI show = new ShowDataGUI();
          //show.start(primarystage2);
			
			
            System.out.println("Pet Name : "+petname);
            System.out.println(" Pet Colour : "+petcolour);
            System.out.println(" Pet Type : "+pettype);
            System.out.println("Appointment Date : "+appointDate);
            
            
        }
        st.close();
    }
    catch ( ClassNotFoundException | SQLException e){
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
        });
        //Fetch data button
        
        //Setting ActionEvent on (petSignup)
        //Button ActionEvent to Register Pet
        petSignup.setOnAction((event) -> {

        // Button was clicked, Signup user...
        String pName = petNameTextField.getText();
        String pColour = petColourTextField.getText();
        String pType = petTypeField.getText();
        String pAppointment = petDAppointmentDate.getText();

        //Form Validation if fields are empty
        if(pName.equals("")){
      
            alert.setTitle("VETMANAGEMENT SYSTEM - PET INFORMATION");
            alert.setHeaderText("Please pet name ! ");
            alert.setContentText("Thank you !");
            alert.showAndWait();
        }
        else if(pAppointment.equals("")){
            
            alert.setTitle("VETMANAGEMENT SYSTEM - PET INFORMATION");
            alert.setHeaderText("Please appointment date ! ");
            alert.setContentText("Thank you !");
            alert.showAndWait(); 
        }
        
        //DB code
        PreparedStatement ps;
        //Statement stmt;
        String query_2 = "INSERT INTO `petregistration`(`petName`, `petColour`, `petType`, `appointmentDate`) VALUES (?,?,?,?)";
     
       //Try catch block
       try{
           
           //Object of class (myConnection) with method (getConnection())
           ps = myConnection.getConnection().prepareStatement(query_2);
           
           ps.setString(1, pName);
           ps.setString(2, pColour);
           ps.setString(3, pType);
           ps.setString(4, pAppointment);
           
           //Alert Dialog box
           if(ps.executeUpdate() > 0){
               
            alert.setTitle("VETMANAGEMENT SYSTEM - PET INFORMATION");
            alert.setHeaderText("Pet Registered !");
            alert.setContentText("Thank you !");
            alert.showAndWait(); 
           }
           else{
               
            alert.setTitle("VETMANAGEMENT SYSTEM - PET INFORMATION");
            alert.setHeaderText("Pet not Registered !");
            alert.setContentText("Thank you !");
            alert.showAndWait(); 
           }
           
       }catch(SQLException ex){
           System.out.println(ex.getMessage());   
       } 
});   
    }
    
    /**
     * 
     * <p>Main method that launches the petInformation Scene</p> 
     */
    //Main method
    public void startApp() {
        launch();
    }
}
