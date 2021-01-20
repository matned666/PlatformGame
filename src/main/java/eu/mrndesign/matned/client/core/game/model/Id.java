package eu.mrndesign.matned.client.core.game.model;

public class Id {

    private static Id instance;

    public static Id getInstance() {
        if (instance == null) {
            synchronized (Id.class) {
                if (instance == null) {
                    instance = new Id();
                    instance.id = 0;
                }
            }
        }
        return instance;
    }

    protected long id;

    private Id() {
        if (instance != null) {
            throw new IllegalStateException("Cannot create new instance, please use getInstance method instead.");
        }
    }

    public long nextId() {
        return ++id;
    }

}
