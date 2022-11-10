package assignment2;

public class MessageCounterVisitor implements EntityVisitor {

    @Override
    public int visitUser(User user) {
        //Checks the number of posts this user has made
        return user.getNumberOfPosts();
    }

    @Override
    public int visitGroup(Group group) {
        //Counts the number of posts all entities in this composite have made
        int postCount = 0;
        for (Entity entity : group.getEntities()) {
            postCount += entity.accept(this);
        }
        return postCount;
    }

    /*
    @Override
    public int visitRoot(Root root) {
        int postCount = 0;
        for (Entity entity : root.getEntities()) {
            postCount += entity.accept(this);
        }
        return postCount;
    }*/
}
