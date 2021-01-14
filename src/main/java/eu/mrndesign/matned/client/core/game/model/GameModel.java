package eu.mrndesign.matned.client.core.game.model;

import eu.mrndesign.matned.client.core.game.model.level.Level1;
import eu.mrndesign.matned.client.core.settings.SettingsSingleton;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.client.core.utils.Constants.*;
import static eu.mrndesign.matned.client.core.utils.GameStats.*;

public class GameModel implements Game {


    private final Difficulty difficulty;
    private int points;
    private List<GameObject> objectsInGame;
    private GameObject hero;
    private GameObject gun;
    private GameObject target;
    private Level currentLevel;

    public GameModel(Difficulty difficulty) {
        this.difficulty = difficulty;
        points = 0;
        initGameObjectsList();
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
    public void addPoints(int points){
        this.points += points;
    }

    @Override
    public void addObject(GameObject gameObject){
        objectsInGame.add(gameObject);
    }

    @Override
    public void remove(GameObject gameObject){
        objectsInGame.remove(gameObject);
    }

    @Override
    public Level currentLevel() {
        return currentLevel;
    }

    @Override
    public void setNextLevel() {
        currentLevel = currentLevel.nextLevel();
    }

    @Override
    public GameObject getHero() {
        return hero;
    }

    @Override
    public GameObject getGun() {
        return gun;
    }

    @Override
    public GameObject getTarget() {
        return target;
    }


    private void initGameObjectsList() {
        int multiplicand = SettingsSingleton.getInstance().getDifficulty().multiplicand();
        objectsInGame = new LinkedList<>();
        hero = new GameObjectModel
                .GameObjectModelBuilder("hero-1.png", DEFAULT_HERO_HP / multiplicand, ModelType.HERO)
                .name("Hero")
                .hitPower(DEFAULT_HERO_POWER/ multiplicand)
                .maxSpeed(DEFAULT_HERO_MAX_SPEED  - multiplicand)
                .xPos(HERO_DEFAULT_START_X_POS)
                .yPos(HERO_DEFAULT_START_Y_POS)
                .xSize(HERO_X_SIZE)
                .ySize(HERO_Y_SIZE)
                .build();
        gun = new GameObjectModel
                .GameObjectModelBuilder("gun1.png", DEFAULT_HERO_GUN_HP / multiplicand, ModelType.HERO_GUN)
                .name("Cannon")
                .hitPower(DEFAULT_HERO_GUN_POWER / multiplicand)
                .maxSpeed(DEFAULT_HERO_MAX_SPEED  - multiplicand)
                .xPos(hero.getxPos()+GUN_X)
                .yPos(hero.getyPos()+GUN_Y)
                .xSize(GUN_SIZE)
                .ySize(GUN_SIZE)
                .build();
        target = new GameObjectModel.GameObjectModelBuilder("target.png", 99, ModelType.TARGET)
                .name("Target point")
                .xPos(0)
                .yPos(0)
                .xSize(TARGET_SIZE)
                .ySize(TARGET_SIZE)
                .build();
        objectsInGame.add(hero);
        objectsInGame.add(gun);
        objectsInGame.add(target);
    }

}
