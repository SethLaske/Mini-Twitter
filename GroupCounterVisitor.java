package assignment2;

public class GroupCounterVisitor implements EntityVisitor {

    @Override
    public int visitUser(User user) {
        //users are the leaves, so they can return 0
        return 0;
    }

    @Override
    public int visitGroup(Group group) {
        //Counts itself then visits sub Entities
        int userCount = 1;
        for (Entity entity : group.getEntities()) {
            userCount += entity.accept(this);
        }
        return userCount;
    }

    /*
    @Override
    public int visitRoot(Root root) {
        //Counts itself then visits sub Entities
        int userCount = 1;
        for (Entity entity : root.getEntities()) {
            userCount += entity.accept(this);
        }
        return userCount;
    }*/

}
