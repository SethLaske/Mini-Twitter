package assignment2;

import java.util.ArrayList;

//Implementation for observer pattern
public abstract class Followed {

    private ArrayList<Follower> followers = new ArrayList<Follower>();

    public void addFollower(Follower follower) {
        followers.add(follower);
    }

    //A remove follower will be a future addition
    
    public void notifyFollowers() {
        for (Follower follower : followers) {
            follower.update(this);
        }
    }

}
