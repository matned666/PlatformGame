package eu.mrndesign.matned.client.core.game.model;

import java.util.List;

public interface Game {

    Difficulty getDifficulty();
    int getPoints();
    void addPoints(int points);
    List<GameObject> objectsInGame();
    Level currentLevel();
    void setNextLevel();
    void addObject(GameObject gameObject);
    void remove(GameObject gameObject);
    GameObject getHero();
    GameObject getGun();
    GameObject getTarget();

}
