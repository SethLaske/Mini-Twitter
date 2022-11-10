package assignment2;

public interface EntityVisitor {

    //Setup for Visitor Pattern
    public int visitUser(User user);

    public int visitGroup(Group group);

    //public int visitRoot(Root root);

}
