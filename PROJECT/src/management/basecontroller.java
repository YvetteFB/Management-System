package management;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class basecontroller implements Initializable {
    @FXML
    private Pane Epane, Spane;

    @FXML
    private JFXButton Ebtn, Sbtn;

    @FXML
    private Label eloginLabel, sloginLabel;

    @FXML
    public ImageView loginImage, eUser, ePassword, sUser, sPassword, closeImg;

    @FXML
    private JFXTextField eUsertxt, sUsertxt;

    @FXML
    private JFXPasswordField ePasstxt, sPasstxt;


    public basecontroller()
    {
        con = DatabaseConnection.ConDB();
    }

    @Override
    public void initialize(URL URL, ResourceBundle resourceBundle) {
       /**  File login = new File("management/Images/download (1).jpg");
         Image base = new Image(login.toURI().toString());
         loginImage.setImage(base);

         File icon1 = new File("management/Images/icons8_user_24px.png");
         Image txt = new Image(icon1.toURI().toString());
         eUser.setImage(txt);

         File icon2 = new File("management/Images/icons8_password_30px_1.png");
         Image pass = new Image(icon2.toURI().toString());
         ePassword.setImage(pass);

         File icon3 = new File("management/Images/icons8_user_24px.png");
         Image txt2 = new Image(icon3.toURI().toString());
         sUser.setImage(txt2);

         File icon4 = new File("management/Images/icons8_password_30px_1.png");
         Image pass2 = new Image(icon4.toURI().toString());
         sPassword.setImage(pass2);**/

    }
public void start(){

    //Creating an image
    Image image = null;
    try {
        image = new Image(new FileInputStream("Images/download (1).jpg"));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    //Setting the image view
    loginImage = new ImageView(image);

    try {
        image = new Image(new FileInputStream("Images/icons8_user_24px.png"));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    eUser = new ImageView(image);

    try {
        image = new Image(new FileInputStream("Images/icons8_user_24px.png"));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    sUser = new ImageView(image);

    try {
        image = new Image(new FileInputStream("Images/icons8_password_30px_1.png"));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    sPassword = new ImageView(image);

    try {
        image = new Image(new FileInputStream("Images/icons8_cancel_48px.png"));
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    closeImg = new ImageView(image);

}




    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == Ebtn) {
            Epane.toFront();
        } else if (event.getSource() == Sbtn) {
            Spane.toFront();
        }
       /** } else if(event.getSource() == Close){
            //System.exit(0);
            Stage stage = (Stage) Close.getScene().getWindow();
            stage.close();
        }**/
    }

    @FXML
    private void close(){
        Stage stage = (Stage) closeImg.getScene().getWindow();
        stage.close();

    }


    @FXML
    private void loginButtonAction(ActionEvent event) {
        if (Elogin()) {
        if (eUsertxt.getText().isBlank() == false && ePasstxt.getText().isBlank() == false) {

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

                try {
                    //con = DatabaseConnection.ConDB();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("eHome.fxml")));
                    stage.setScene(scene);
                    stage.show();


                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println(e.getMessage());
                }
            } else if ((eUsertxt.getText().isBlank() == true && ePasstxt.getText().isBlank() == true)) {

                eloginLabel.setText("PLEASE ENTER A USERNAME AND PASSWORD");


            }
        }
    }
    @FXML
    private void sloginButtonAction(ActionEvent event){
        if(Slogin()) {
        if (sUsertxt.getText().isBlank() == false && sPasstxt.getText().isBlank() == false) {


                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

                try {
                   // con = DatabaseConnection.ConDB();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("sHome.fxml")));
                    stage.setScene(scene);
                    stage.show();


                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println(e.getMessage());
                }
            }

        }  else if ((sUsertxt.getText().isBlank() == true && sPasstxt.getText().isBlank() == true))
        {

            sloginLabel.setText("PLEASE ENTER A USERNAME AND PASSWORD");

        }
    }

    Connection con = null;
    //PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    public boolean Elogin(){
        boolean login_success = false;
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.ConDB();
        //query
        String sql = "SELECT count(1) FROM employeedetails WHERE Name = '"+ eUsertxt.getText() +"' and Password = '"+ePasstxt.getText()+"' and type = 'employee';";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(sql);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    login_success = true;
                }else{
                    eloginLabel.setText("ENTER CORRECT USERNAME AND PASSWORD!");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return login_success;

    }
        public boolean Slogin(){
            boolean login_success = false;
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.ConDB();
            String verifyLogin = "SELECT count(1) FROM employeedetails WHERE Name = '"+ sUsertxt.getText() +"' and Password = '"+sPasstxt.getText() +"' and type = 'supervisor';";

            try{
                Statement statement = connectDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);

                while(queryResult.next()){
                    if (queryResult.getInt(1) == 1){
                        login_success = true;
                    }else{
                        sloginLabel.setText("ENTER CORRECT USERNAME AND PASSWORD!");
                    }
                }



            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }

return login_success;
        }
}



