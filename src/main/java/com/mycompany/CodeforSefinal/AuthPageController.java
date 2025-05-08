package com.mycompany.CodeforSefinal;

import com.mycompany.CodeforSefinal.Objects.AuthenticationSingleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class AuthPageController implements Initializable {

    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML TextField usernameField;
    @FXML TextField passwordField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleReturnToPrimary(ActionEvent event) throws IOException {

        // Code that will load up the "primary.fxml"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        root = loader.load();
        PrimaryController primaryController = loader.getController();

        // Code that loads up the "primary.fxml"
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void HandleLogin(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        boolean authentication = AuthenticationSingleton.getInstance().login(username, password);
        if (authentication){
            System.out.println("User Entered Correct Credentials");
            handleReturnToPrimary(event);
        }
        else 
            showError("Incorrect Credentials");
        
            
        
        }
            
    
    private void showError(String message){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
    }
    
        @FXML
    private void faqButton(javafx.event.ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("FAQ.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    

    
}
