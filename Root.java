package assignment2;

public class Root extends Group implements Entity {

    //Making the root an extension of the Group, but as a singleton
    private static Root instance = null;

    private Root() {
    }

    public static Root getInstance() {
        if (instance == null) {
            instance = new Root();
        }
        return instance;
    }
}
