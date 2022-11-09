package assignment2;

public class UserCounterVisitor implements EntityVisitor{

	@Override
	public int visitUser(User user) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int visitGroup(Group group) {
		int userCount = 0;
		for(Entity entity : group.getEntities()) {
			userCount += entity.accept(this);
		}
		return userCount;
	}

	@Override
	public int visitRoot(Root root) {
		int userCount = 0;
		for(Entity entity : root.getEntities()) {
			userCount += entity.accept(this);
		}
		return userCount;
	}

}
