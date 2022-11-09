package assignment2;

public class MessageCounterVisitor implements EntityVisitor{
	
	@Override
	public int visitUser(User user) {
		return user.getNumberOfPosts();
	}

	@Override
	public int visitGroup(Group group) {
		int postCount = 0;
		for(Entity entity : group.getEntities()) {
			postCount += entity.accept(this);
		}
		return postCount;
	}

	@Override
	public int visitRoot(Root root) {
		int postCount = 0;
		for(Entity entity : root.getEntities()) {
			postCount += entity.accept(this);
		}
		return postCount;
	}
}
