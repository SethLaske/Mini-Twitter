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
public class UserViewController extends Followed implements Initializable, Follower {

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
    private Text created;
    @FXML
    private Text updated;
            
    //Passes the user who's UI is being opened, displaying their following and feed
    @FXML
    public void setUser(User user) {
        this.currentUser = user;
        header.setText(currentUser.getID());
        //currentUser.createTimestamp();
        created.setText("Created: " + String.valueOf(currentUser.getTimestamp()));
        currentUser.createUpdatedTimestamp();
        updated.setText("Updated: " + String.valueOf(currentUser.getUpdatedTimestamp()));

        //Initializing the people being followed
        for (int i = 0; i < currentUser.getFollowing().size(); i++) {
            followedUsers.setText(followedUsers.getText() + currentUser.getFollowing().get(i) + "\n");
            currentUser.getFollowing().get(i).addFollower(this);    //Reestablished the connection to this specific User UI 
        }

        //Initializing the current feed on start up
        for (int i = 0; i < currentUser.getFeed().size(); i++) {
            feed.setText(feed.getText() + currentUser.getFeed().get(i) + "\n");
        }
    }

    //Allows this user to follow other users
    @FXML
    private void followUser(ActionEvent event) {
        if (followID.getText().isEmpty()) { //Ensures the user has typed input into the proper text field
            followID.setPromptText("Please enter an ID");
        } else {
            if (root.findEntity(followID.getText()) == null) {  //Makes sure that the inputted user exisits already
                followID.setText("");
                followID.setPromptText("User not Found");
            } else {
                if (followID.getText().equals(currentUser.getID())) {   //Makes sure the user is not trying to follow itself
                    followID.setText("");
                    followID.setPromptText("Can't follow yourself");
                } else {
                    //Follows the user with the observer pattern
                    //Both the user object, and the user view controller will follow the proper user and controller
                    //The user will see their following on the screen and also receive a message that will be shown to their follwers that they have followed someone new
                    User followed = (User) root.findEntity(followID.getText());
                    currentUser.follow(followed);

                    followedUsers.setText(followedUsers.getText() + followID.getText() + "\n");

                    followID.setText("");
                    followed.addFollower(this);

                    feed.setText(currentUser.getNewPost() + "\n" + feed.getText());
                    currentUser.createUpdatedTimestamp();
                    updated.setText("Updated: " + String.valueOf(currentUser.getUpdatedTimestamp()));
                    notifyFollowers();  //The followers will be notified so they can be alerted to the new following
                }
            }
        }
    }

    //Posts the message to this users feed and alerts the rest of the users
    @FXML
    private void postMessage(ActionEvent event) {
        if (!postMessage.getText().isEmpty()) {
            currentUser.post(postMessage.getText());    //posts it to the user

            feed.setText(currentUser.getNewPost() + "\n" + feed.getText());     //adds it to this users feed
            postMessage.setText("");
            notifyFollowers();  //refreshes all of their followers that they need to refresh their feeds
        } else {    //Tells the user to enter a message to be posted
            testlabel.setText("Please type your message");
        }
        //currentUser.createUpdatedTimestamp();
        updated.setText("Updated: " + String.valueOf(currentUser.getUpdatedTimestamp()));
    }

    //Whenever they receive a message the observer pattern will allow the feed to refresh
    @Override
    public void update(Followed user) {
        refreshFeed();
    }

    //refreshes the feed to see if anything new has been added to a given users feed
    @FXML
    private void refreshFeed() {
        feed.setText("");   //Blanks out the feed before adding each message a user has in their feed
        for (int i = 0; i < currentUser.getFeed().size(); i++) {
            feed.setText(feed.getText() + currentUser.getFeed().get(i) + "\n");
        }
        currentUser.createUpdatedTimestamp();
        updated.setText("Updated: " + String.valueOf(currentUser.getUpdatedTimestamp()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
