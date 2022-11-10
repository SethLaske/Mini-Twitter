package assignment2;

public class PositivePostCounterVisitor implements EntityVisitor {

    @Override
    public int visitUser(User user) {
        //returns the number of positive posts this user has made
        return user.getNumberOfPositivePosts();
    }

    @Override
    public int visitGroup(Group group) {
        //Counts the number of positive posts all entities in this composite have made
        int positivePostCount = 0;
        for (Entity entity : group.getEntities()) {
            positivePostCount += entity.accept(this);
        }
        return positivePostCount;
    }

    /*
    @Override
    public int visitRoot(Root root) {
        int positivePostCount = 0;
        for (Entity entity : root.getEntities()) {
            positivePostCount += entity.accept(this);
        }
        return positivePostCount;
    }*/

}
