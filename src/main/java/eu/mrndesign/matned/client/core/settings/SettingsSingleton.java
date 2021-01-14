package eu.mrndesign.matned.client.core.settings;


import eu.mrndesign.matned.client.core.game.model.Difficulty;

public class SettingsSingleton {

    private static SettingsSingleton instance;

    public static SettingsSingleton getInstance() {
        if (instance == null) {
            synchronized (SettingsSingleton.class) {
                if (instance == null)
                {
                    instance = new SettingsSingleton();
                    instance.setDifficulty(Difficulty.EASY); //default difficulty is EASY
                }
            }
        }
        return instance;
    }

    private Difficulty difficulty;

    private SettingsSingleton() {
        if (instance != null) {
            throw new IllegalStateException("Cannot create new instance, please use getInstance method instead.");
        }
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
