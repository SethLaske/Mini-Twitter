package assignment2;

import java.util.ArrayList;

public abstract class Followed {

	
	private ArrayList<Follower> followers = new ArrayList <Follower>();
	
	/*
	private String ID;
	private String newPost;
	
	public String getID() {
		return ID;
	}
	
	public String getNewPost() {
		return newPost;
	}*/
	
	public void addFollower(Follower follower) {
		followers.add(follower);
	}
	
	public void notifyFollowers() {
		for(Follower follower : followers) {
			follower.update(this);
		}
	}
	
}
