package assignment2;

import java.util.ArrayList;

public class Group implements Entity {

    private String ID;
    private ArrayList<Entity> groupUsers = new ArrayList<Entity>();

    //Basic getters and setters
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public String toString() {
        return ("<" + ID + ">");
    }

    //Adds either a group or user for the composite pattern
    public void addEntity(Entity entity) {

        groupUsers.add(entity);
    }

    public ArrayList<Entity> getEntities() {
        return groupUsers;
    }

    //Setup for the Visitor Pattern
    @Override
    public int accept(EntityVisitor visitor) {
        return visitor.visitGroup(this);
    }

    //Checking to see if an ID already exisits
    public Entity findEntity(String ID) {

        if (this.ID.equals(ID)) {    //makes sure the group isnt the ID being searched for
            return this;
        }

        for (Entity entities : groupUsers) {

            if (entities instanceof User) { //Checks all of the users 
                if (entities.getID().equals(ID)) {
                    return (User) entities;
                }

            } else if (((Group) entities).findEntity(ID) != null) { //recurses into any sub groups

                return ((Group) entities).findEntity(ID);
            }

        }
        return null;
    }

}
