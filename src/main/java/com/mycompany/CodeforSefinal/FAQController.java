package com.mycompany.CodeforSefinal;

import com.mycompany.CodeforSefinal.Objects.FAQText;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class FAQController implements Initializable {
    
    @FXML private Button returnButton;
    @FXML private TextArea FAQtxt;
    
    // Needed imports for JavaFX
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private String fileName = "src/main/resources/com/mycompany/CodeforSefinal/Frequently Asked Questions.txt";
    
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
    
    public ArrayList<String> openFile() throws IOException {
        
        BufferedReader reader = new BufferedReader(new StringReader(FAQText.faqText));
        //BufferedReader reader = new BufferedReader(new FileReader((fileName)));
        String line;
        ArrayList<String> content = new ArrayList<>();
        while((line = reader.readLine()) != null){
            content.add(line);
        }
        return content;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            for (String line : openFile()) {
                FAQtxt.appendText(line + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(FAQController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
