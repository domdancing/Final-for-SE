package com.mycompany.CodeforSefinal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FAQController implements Initializable {
    
    @FXML private Button returnButton;
    
    // Needed imports for JavaFX
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    // FXML Connected method, uses ActionEvent to detect when the button "return" is pressed, when it is pressed it will call the method. 
    @FXML
    private void returnToPrimary(ActionEvent event) throws IOException {
        
        // Code that will load up the "primary.fxml"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        root = loader.load();
        PrimaryController primaryController = loader.getController();
        
        // Code that loads up the "primary.fxml"
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
