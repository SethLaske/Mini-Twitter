package assignment2;

//Basis for the composite pattern
public interface Entity {

    void setID(String ID);

    String getID();

    //Setup for Visitor pattern
    public int accept(EntityVisitor visitor);

}
