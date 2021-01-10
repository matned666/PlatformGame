package eu.mrndesign.matned.client.core.game.model;

import eu.mrndesign.matned.client.core.game.model.level.Level1;

import java.util.LinkedList;
import java.util.List;

public class GameModel implements Game{

    private Difficulty difficulty;
    private int points;
    private List<GameObject> objectsInGame;
    private Level currentLevel;

    public GameModel(Difficulty difficulty) {
        this.difficulty = difficulty;
        points = 0;
        objectsInGame = new LinkedList<>();
        currentLevel = new Level1();
    }

    @Override
    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public List<GameObject> objectsInGame() {
        return objectsInGame;
    }

    @Override
    public Level currentLevel() {
        return currentLevel;
    }

    @Override
    public void setNextLevel() {
        currentLevel = currentLevel.nextLevel();
    }
}
