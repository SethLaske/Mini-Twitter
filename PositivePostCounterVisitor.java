package assignment2;

public class PositivePostCounterVisitor implements EntityVisitor{
	
	@Override
	public int visitUser(User user) {
		return user.getNumberOfPositivePosts();
	}

	@Override
	public int visitGroup(Group group) {
		int positivePostCount = 0;
		for(Entity entity : group.getEntities()) {
			positivePostCount += entity.accept(this);
		}
		return positivePostCount;
	}

	@Override
	public int visitRoot(Root root) {
		int positivePostCount = 0;
		for(Entity entity : root.getEntities()) {
			positivePostCount += entity.accept(this);
		}
		return positivePostCount;
	}
	
}
