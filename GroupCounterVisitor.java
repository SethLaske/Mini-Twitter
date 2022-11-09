package assignment2;

public class GroupCounterVisitor implements EntityVisitor{

	@Override
	public int visitUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int visitGroup(Group group) {
		int userCount = 1;
		for(Entity entity : group.getEntities()) {
			userCount += entity.accept(this);
		}
		return userCount;
	}

	@Override
	public int visitRoot(Root root) {
		int userCount = 0;	//saying root doesnt count for now (should initialize with 0 users, 0 groups)
		for(Entity entity : root.getEntities()) {
			userCount += entity.accept(this);
		}
		return userCount;
	}

}
