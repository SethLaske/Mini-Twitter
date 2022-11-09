/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import com.sun.javaws.Main;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author small
 */
public class FXMLDocumentController implements Initializable {
    
    private TreeItem<Entity> selectedEntity;
    private Group selectedGroup;
    private User selectedUser;
    private Root root = Root.getInstance();
    
    @FXML
    private Label testlabel;
    
    //Initializing user and group items
    @FXML
    private Button addUserButton;
    @FXML
    private TextField userID;
    @FXML
    private Button addGroupButton;
    @FXML
    private TextField groupID;
    
    //checking stats items
    @FXML
    private Button countUsers;
    @FXML
    private Button countGroups;
    @FXML
    private Button countPosts;
    @FXML
    private Button percentPositive;
    
    //tree view and user view startup
    @FXML
    private TreeView<Entity> rootHierarchy;
    
    @FXML
    private Button viewUser;
    
    
    
    @FXML 
    private void openUserView(ActionEvent event) throws IOException{
       if(selectedUser != null){
          try{
            FXMLLoader loader = new FXMLLoader( getClass().getResource("UserView.fxml"));
            Parent root = loader.load();
            
            UserViewController controller = loader.getController();
            
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            
            controller.setUser((User)selectedUser);
          } catch (Exception e){
              Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
          }  
        }else{
            testlabel.setText("Please select a user");
        }
    }
    
    @FXML
    //
    private void addUser(ActionEvent event){
        if(userID.getText().isEmpty()){
            testlabel.setText("Please enter a user ID");
        } else if(root.findEntity(userID.getText()) != null){
            testlabel.setText("Please create a unique ID");
        } else if(selectedGroup == null){
            testlabel.setText("Please select a group");
        } else {
            testlabel.setText(userID.getText());
            
            User newUser = new User();
            selectedGroup.addEntity(newUser);
            newUser.setID(userID.getText());
            TreeItem<Entity> addedUser = new TreeItem<>(newUser);
            selectedEntity.getChildren().add(addedUser);
            
            userID.setText("");
        }
    }
    
    @FXML
    private void addGroup(ActionEvent event){
        if(groupID.getText().isEmpty()){
            testlabel.setText("Please enter a group ID");
        } else if(root.findEntity(groupID.getText()) != null){
            testlabel.setText("Please create a unique ID");
        } else if(selectedGroup == null){
            testlabel.setText("Please select a group");
        } else {
            testlabel.setText(groupID.getText());
            
            Group newGroup = new Group();
            selectedGroup.addEntity(newGroup);
            newGroup.setID(groupID.getText());
            TreeItem<Entity> addedGroup = new TreeItem<>(newGroup);
            selectedEntity.getChildren().add(addedGroup);
            
            groupID.setText("");
        }
    }
    
    @FXML
    private void countUsers(ActionEvent event){
        EntityVisitor countUsers = new UserCounterVisitor();
        testlabel.setText("Total number of Users: " + String.valueOf(root.accept(countUsers)));
    }
    
    @FXML
    private void countGroups(ActionEvent event){
        EntityVisitor countGroups = new GroupCounterVisitor();
        testlabel.setText("Total number of Groups: " + String.valueOf(root.accept(countGroups)));
    }
    
    @FXML
    private void countPosts(ActionEvent event){
        EntityVisitor countMessages = new MessageCounterVisitor();
        testlabel.setText("Total number of Posts: " + String.valueOf(root.accept(countMessages)));
        
    }
    
    @FXML
    private void percentPositive(ActionEvent event){
        EntityVisitor countMessages = new MessageCounterVisitor();
        EntityVisitor countPositivePosts = new PositivePostCounterVisitor();
        int messages = root.accept(countMessages);
        if(messages != 0){
            double Percent = root.accept(countPositivePosts) * 100.0 / messages;
            DecimalFormat df = new DecimalFormat("##.#");
            testlabel.setText("Percent of Positive Posts: %" + df.format(Percent));
        }else{
            testlabel.setText("Percent of Positive Posts: %0" );
        }
        
        
    }
    
    @FXML
    private void selectItem(){
        
       TreeItem<Entity> entity = rootHierarchy.getSelectionModel().getSelectedItem();
       
       selectedEntity = entity;
       if(entity.getValue() instanceof User){
           selectedUser = (User)entity.getValue();
           selectedGroup = null;
       } else {
           selectedGroup = (Group)entity.getValue();
           selectedUser = null;
       }

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Root rootEntity = Root.getInstance();
        rootEntity.setID("Root");
        TreeItem<Entity> rootObject = new TreeItem<>(rootEntity);
        
        rootHierarchy.setRoot(rootObject);
        
        /* Just an option to immediately create a root with some users added
        
        User hiUser = new User();
        rootEntity.addEntity(hiUser);
        hiUser.setID("Hi");
        TreeItem<Entity> hiUserTI = new TreeItem<>(hiUser);
        
        User helloUser = new User();
        rootEntity.addEntity(helloUser);
        helloUser.setID("Hello");
        TreeItem<Entity> helloUserTI = new TreeItem<>(helloUser);
        
        User howdyUser = new User();
        rootEntity.addEntity(howdyUser);
        howdyUser.setID("Howdy");
        TreeItem<Entity> howdyUserTI = new TreeItem<>(howdyUser);
        
        rootObject.getChildren().addAll(hiUserTI, helloUserTI, howdyUserTI);
        */
        
        rootObject.setExpanded(true);
    }    
}
