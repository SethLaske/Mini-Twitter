package assignment2;
//import java.util.Observable;
//import java.util.Observer;

import java.util.ArrayList;

//User will be part of composite, observer, and visitor patterns
public class User extends Followed implements Entity, Follower {

    private String ID;
    private ArrayList<User> followings = new ArrayList<User>();		//this list will probably remain to show in the UI
    private ArrayList<String> feed = new ArrayList<String>();
    private String newPost;
    private int numberOfPosts = 0;
    private int numberOfPositivePosts = 0;

    //Getters and setters
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public String toString() {
        return ID;
    }

    public String getNewPost() {
        return newPost;
    }

    public ArrayList<String> getFeed() {
        return feed;
    }

    public ArrayList<User> getFollowing() {
        return followings;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public int getNumberOfPositivePosts() {
        return numberOfPositivePosts;
    }

    //Accepting the visitor pattern
    @Override
    public int accept(EntityVisitor visitor) {
        return visitor.visitUser(this);
        //return 1;
    }

    //Add to the list of users being followed by this user
    public void follow(User follow) {
        //Starts following the user, and notifies their followers with a message as well
        newPost = (this.ID + " has started following " + follow.getID());
        followings.add(follow);
        follow.addFollower(this);
        feed.add(0, newPost);
        //notify 
        notifyFollowers();
    }

    //Takes the newest post from the user they are following and adds it to their feed
    public void update(Followed user) {
        if (user instanceof User) {
            feed.add(0, ((User) user).getNewPost());	//add the new post to the top of the feed
        }
    }

    //posts a new message, increments the counters, and notifies their followers
    public void post(String post) {
        //take String
        newPost = (this.ID + ": " + post);
        numberOfPosts++;

        //check for if the message is positive
        if (post.contains("CS") || post.contains("OOP")) {
            numberOfPositivePosts++;
        }

        //add it to the top feed
        feed.add(0, newPost);
        //notify 
        notifyFollowers();
    }

}
