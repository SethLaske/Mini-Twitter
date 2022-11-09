package assignment2;

import java.util.ArrayList;

public class Root extends Group implements Entity  {

	private static Root instance = null;
	private String ID;
	private ArrayList<Entity> groupUsers;
	
	private Root() {
            //super("Root", null);
	}
	
	public static Root getInstance() {
		if(instance == null) {
			instance = new Root();
		}
		return instance;
	}

	/*
	//probably doesnt need an ID
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getID() {
		return ID;
	}

        
	public void addEntity(Entity entity) {
            groupUsers = new ArrayList<Entity>();
            groupUsers.add(entity);
	}
	
        
	public ArrayList<Entity> getEntities() {
		return groupUsers;
	}
	
	
	@Override
	public int accept(EntityVisitor visitor) {
		return visitor.visitRoot(this);
	}
        
        
        public String toString(){
            return ID;
        }
	*/
	/*
	//Visitor methods will be called directly from the root, and will go down through their hierarchies
	@Override
	public int countUsers() {
		int users = 0;
		
		for(Entity entities : groupUsers) {
			users += entities.countUsers();
		}
		
		return users;
	}

	@Override
	public int countGroups() {
		int groups = 1;
		
		for(Entity entities : groupUsers) {
			groups += entities.countGroups();
		}
		
		return groups;
	}

	@Override
	public int countMessages() {
		int messages = 0;
		
		for(Entity entities : groupUsers) {
			messages += entities.countMessages();
		}
		
		return messages;
	}

	@Override
	public int countPositiveMessages() {
		int positiveMessages = 0;
		
		for(Entity entities : groupUsers) {
			positiveMessages += entities.countPositiveMessages();
		}
		
		return positiveMessages;
	}
	*/
	
	
	
}
