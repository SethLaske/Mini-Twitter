package assignment2;
//import java.util.Observable;
//import java.util.Observer;
import java.util.ArrayList;


public class User extends Followed implements Entity, Follower{

	private String ID;
	//private ArrayList<User> followers;	//This list will probably be removed
	private ArrayList<User> followings = new ArrayList<User>();		//this list will probably remain to show in the UI
	private ArrayList<String> feed = new ArrayList<String>();
	private String newPost;
	private int numberOfPosts = 0;
	private int numberOfPositivePosts = 0;

        /*protected User (String ID, Group parentGroup){
            this.ID = ID;
            parentGroup.addEntity(this);
        }*/
        
        
        
	//will contain all the information from Entity
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getID(){
		return ID;
	}
	
        public String toString(){
            return ID;
        }
        
	public String getNewPost() {
		return newPost;
	}
	
	//Add to the list of users being followed by this user
	public void follow(User follow) {
		followings.add(follow);
                follow.addFollower(this);
	}
	
	//Add to the list of followers of this user
	//These users will need to be updated whenever this user posts
	/*
	public void addfollower(User follower) {
		followers.add(follower);
	}*/
	
	//set up to observe
	public void update(Followed user) {
		//add new message to the feed
                System.out.println("Received a message");
		if(user instanceof User) {
			feed.add(0, ((User)user).getNewPost());	//add the new post to the top of the feed
		}
	}
	
	
	//set up to be observed
	public void post(String post) {
		//take String
		newPost = (this.ID + ": " + post);
		numberOfPosts ++;
		
		//check for if the message is positive
		if(post.contains("CS") || post.contains("OOP")){
                    numberOfPositivePosts ++;
                }
                
		//add it to the top feed
		feed.add(0, newPost);
		//notify 
		notifyFollowers();
	}

        public ArrayList<String> getFeed(){
            return feed;
        }
        
        public ArrayList<User> getFollowing(){
            return followings;
        }
        
	public int getNumberOfPosts() {
		return numberOfPosts;
	}
	
	public int getNumberOfPositivePosts() {
		return numberOfPositivePosts;
	}
	
	@Override
	public int accept(EntityVisitor visitor) {
		return visitor.visitUser(this);
		//return 1;
	}
	
	/*
	public void notifyObservers() {
		for(User follower : followers) {
			follower.update(follower);
		}
	}*/

	/*
	@Override
	public int countUsers() {
		return 1;	
	}

	@Override
	public int countGroups() {
		return 0;
	}

	@Override
	public int countMessages() {
		return numberOfPosts;	//posts will be counted each time this user posts
	}

	@Override
	public int countPositiveMessages() {
		return numberOfPositivePosts;
	}
	*/	
	
}
