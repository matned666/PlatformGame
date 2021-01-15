package eu.mrndesign.matned.client.core.game.model;

import eu.mrndesign.matned.client.core.game.model.level.Level1;
import eu.mrndesign.matned.client.core.game.model.mainmodel.GameObject;
import eu.mrndesign.matned.client.core.game.model.mainmodel.GameObjectModel;
import eu.mrndesign.matned.client.core.game.utils.TimeWrapper;
import eu.mrndesign.matned.client.core.settings.SettingsSingleton;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.client.core.game.utils.StaticObjects.*;
import static eu.mrndesign.matned.client.core.utils.Constants.CANVAS_WIDTH;

public final class GameModel implements Game {

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
        initMainGameObjects();
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

    @Override
    public void putEnemy() {
        if (TimeWrapper.getInstance().getFrameNo() % 50 == 0) {
            addObject(
                    new GameObjectModel.GameObjectModelBuilder("enemy2.png", 20, ModelType.ENEMY)
                            .xSize(66)
                            .ySize(120)
                            .yPos(0)
                            .xPos(Math.random()*CANVAS_WIDTH - 66)
                            .hitPower(50)
                            .speedY(5)
                            .frameDuration(20)
                            .name("Wiewiór")
                            .build());
        }
    }



    private void initMainGameObjects() {
        int multiplicand = SettingsSingleton.getInstance().getDifficulty().multiplicand();
        objectsInGame = new LinkedList<>();
        initHero(multiplicand);
        initGun(multiplicand);
        initTargetCross();
    }

    private void initHero(int multiplicand) {
        hero = hero(multiplicand);
        objectsInGame.add(hero);
    }

    private void initGun(int multiplicand) {
        gun = gun(multiplicand, hero.getxPos(), hero.getyPos());
        objectsInGame.add(gun);
    }

    private void initTargetCross() {
        target = target();
        objectsInGame.add(target);
    }

}
