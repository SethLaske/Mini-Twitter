package assignment2;

public class AdminControl {

	private static AdminControl instance = null;
	private int userCount;
	private int groupCount;
	private int messageCount;
	private int positiveMessages;
	
	private AdminControl() {
	}
	
	//I think this is where singleton shows up, if not its for root (or both)
	public static AdminControl getInstance() {
		if(instance == null) {
			instance = new AdminControl();
		}
		return instance;
	}
	
	//Will be tied to the respective buttons
	public int getUserCount() {
		return userCount;
	}
	
	public int getGroupCount() {
		return groupCount;
	}
	
	public int getMessageCount() {
		return messageCount;
	}
	
	public float getPositivePercentage() {
		float positivePercentage = positiveMessages/messageCount;
		return positivePercentage;
	}
	
}
