package com.mycompany.CodeforSefinal;

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
    public static final String faqText = "Frequently Asked Questions:\n" + "\n" + "\"HOW DO I SEARCH THROUGH THE LIST OF INVOICES?\"\n" + "To search through our already-catalogued invoices, click \"Search Invoices\" on the startup screen of the program.\n" + "Add what you wish to search by into the \"Invoice Name\", \"Client Name\", \"ZIP Code\" or \"Delivery Date\" fields.\n" + "Our algorithms will show the invoices that match all of the values placed into the previous fields.\n" + "\n" + "\"HOW DO I SEE WHAT ITEMS THERE ARE IN AN INVOICE?\"\n" + "Once you are on the \"Search Invoices\" screen, select one of the invoices by click on any of the columns in the filtered fields themselves.\n" + "Once an invoice is selected, you are able to click the \"View Items\" button in the bottom right corner of the screen.\n" + "This will display the items located within the selected invoice.\n" + "\n" + "\"HOW DO I CREATE NEW ITEMS TO ADD TO INVOICES?\"\n" + "On the startup screen of the program, click \"Create New Item\".\n" + "Then, fill out the Item Name and Item Price. Finally, click the \"Create Item\" button to add the item.\n" + "\n" + "\"HOW DO I CREATE NEW INVOICES?\"\n" + "On the startup screen of the program, click \"Create New Invoice\".\n" + "Then, fill out Invoice Name, Client Name, the ZIP code, the date, and the items you wish to be included.\n" + "To include items, type the amount of items you wish to input. Then, using the dropdown menu, select the desired item.\n" + "Invoices cannot share names; make sure your input is unique.\n" + "\n" + "\"HOW DO I EDIT INVOICES?\"\n" + "To edit invoices, you first must be logged into an account with administrative privileges.\n" + "If you are, then in the \"Edit Invoices\" page, you are able to double-click on the \"Invoice Name\", \"Client Name\" and \"Zip Code\" fields to modify them.\n" + "\"ID #\" and \"Date Created\" are not able to be modified after creation.\n" + "\n" + "\"HOW DO I DELETE INVOICES?\"\n" + "On the startup screen of the program, click \"Edit Invoices\".\n" + "Select which invoice you want to delete, and select the \"Delete Invoice\" button on the bottom left.\n" + "\n" + "\"WHAT ARE ADMINISTRATIVE PRIVILEDGES? HOW DO I OBTAIN OR USE THEM?\"\n" + "Administrative privileges, available to staff members and select customer representatives, allows users to edit invoices already present in our database.\n" + "To obtain these, you must first have an Admin Username and Admin Password. Once you do so, the following steps must be accomplished in order.\n" + "\t1) On the starting screen, click \"Login As Administrator\".\n" + "\t2) Type the Admin Username and Admin Password into the 'Username' and 'Password' fields respectively.\n" + "\t3) To confirm your credentials, click \"Submit\".\n" + "\n" + "\"HOW DO I READ THE FREQUENTLY ASKED QUESTIONS?\"\n" + "You're doing that right now, actually. Isn't science wonderful?\n" + "\n" + "\"MY PROBLEM ISN'T LISTED HERE!\"\n" + "Don't worry! Simply communicate with us using one of these methods of communication. We are happy to be of assistance.\n" + "\n" + "[ <insert communication lines here> ]\n";

    
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
        
        BufferedReader reader = new BufferedReader(new StringReader(faqText));
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
