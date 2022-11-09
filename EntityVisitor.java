package assignment2;

public interface EntityVisitor {

	public int visitUser(User user);
	public int visitGroup(Group group);
	public int visitRoot(Root root);
	
}
