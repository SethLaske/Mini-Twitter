package assignment2;

public interface Entity {
	
	void setID(String ID);
	
	String getID();
	
        //String toString();
        
	public int accept(EntityVisitor visitor);
	
	//Easier Visitor implementation
		/*
		int countUsers();
		int countGroups();
		int countMessages();
		int countPositiveMessages();
		*/
	
	//probably some menu thing is gonna be contained

    
}
