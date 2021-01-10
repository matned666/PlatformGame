package eu.mrndesign.matned.client.core.game.model;

import java.util.List;

public interface Game {

    Difficulty getDifficulty();
    int getPoints();
    List<GameObject> objectsInGame();
    Level currentLevel();
    void setNextLevel();


}
