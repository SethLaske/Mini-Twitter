/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import static java.lang.Integer.min;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author small
 */
public class UserViewController extends Followed implements Initializable, Follower{

    private User currentUser;
    private Root root = Root.getInstance();
    
    @FXML
    private Text header;
    
    @FXML
    private Label testlabel;
    
    @FXML
    private TextField followID;
    
    @FXML
    private Button follow;
    
    @FXML
    private TextArea followedUsers;
    
    @FXML
    private TextField postMessage;
    
    @FXML
    private Button post;
    
    @FXML
    private TextArea feed;
    
    @FXML
    private Button refresh;
    
    @FXML
    private void followUser(ActionEvent event){
        if(followID.getText().isEmpty()){
            followID.setPromptText("Please enter an ID");
        } else{
            if(root.findEntity(followID.getText()) == null){
                followID.setText("");
                followID.setPromptText("User not Found");
            } else{
               
            
                User followed = (User)root.findEntity(followID.getText());
                currentUser.follow(followed);
            
                followedUsers.setText(followID.getText() + "\n" + followedUsers.getText());
                
                followID.setText("");
                followed.addFollower(this);
                
            }
        }
    }
    
    @FXML
    private void postMessage(ActionEvent event){
        if(!postMessage.getText().isEmpty()){
            currentUser.post(postMessage.getText());
            
            feed.setText(currentUser.getNewPost() + "\n" + feed.getText());
            postMessage.setText("");
            notifyFollowers();
        }else{
            testlabel.setText("Please enter an ID");
        }
    }
    
    //refreshes the feed to see if anything new has been added to a given users feed
    @FXML
    private void refreshFeed(){
        feed.setText("");
        for(int i = 0; i < currentUser.getFeed().size(); i++){
            feed.setText(feed.getText() + currentUser.getFeed().get(i) + "\n");
        }
    }
    
    //this will be called upon starting so the opening scripts will go here
    @FXML
    public void setUser(User user){
        this.currentUser = user;
        header.setText(currentUser.getID());
        System.out.println("Setting user: " + user.getID());
        
        //Initializing the people being followed
        for(int i = 0; i < currentUser.getFollowing().size(); i++){
            followedUsers.setText(followedUsers.getText() + "\n" + currentUser.getFollowing().get(i));
            currentUser.getFollowing().get(i).addFollower(this);
        }
        
        //Initializing the current feed on start up
        for(int i = 0; i < currentUser.getFeed().size(); i++){
            feed.setText(feed.getText() + currentUser.getFeed().get(i) + "\n");
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @Override
    public void update(Followed user) {
        refreshFeed();
    }
    
}
