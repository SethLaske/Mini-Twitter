package assignment2;

import java.util.ArrayList;

public class Group implements Entity{

	private String ID;
	private ArrayList<Entity> groupUsers = new ArrayList<Entity>();
	
        /*
        protected Group(String ID, Group parentGroup){
            this.ID = ID;
            parentGroup.addEntity(this);
        }*/
        
        
	//menu stuff
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getID() {
		return ID;
	}
	
        public String toString(){
            return ID;
        }
        
	public void addEntity(Entity entity) {
            
            groupUsers.add(entity);
	}

	public ArrayList<Entity> getEntities() {
            return groupUsers;
	}
	
	
	@Override
	public int accept(EntityVisitor visitor) {
            return visitor.visitGroup(this);
	}
        
        public Entity findEntity(String ID){
            //System.out.println("Searching for user: " + ID);
            if(this.ID == ID){
                    return this;
            }
            
            for(Entity entities : groupUsers) {
                
                if(entities instanceof User){
                    if(entities.getID().equals(ID)){
                        return (User)entities;
                    }
                    //System.out.println("Found User: " + entities.getID());
                }else if (((Group)entities).findEntity(ID) != null){
                    //System.out.println("Found a group that contains User: " + ID);
                    return ((Group)entities).findEntity(ID);
                }
                /*
		if(entities instanceof User && entities.getID() == ID){
                    System.out.println("Found User: " + ID);
                    return (User)entities;   
                } else if (((Group)entities).findUser(ID) != null){
                    System.out.println("Found a group that contains User: " + ID);
                    return ((Group)entities).findUser(ID);
                }*/
            }
            //System.out.println("User not found in " + getID());
            //System.out.println("Checked through this number of Entities " + groupUsers.size());
            return null;
        }
	
	/*
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
