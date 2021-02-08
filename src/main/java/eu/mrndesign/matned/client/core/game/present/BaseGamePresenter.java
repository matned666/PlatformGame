package eu.mrndesign.matned.client.core.game.present;

import eu.mrndesign.matned.client.core.game.Direction;
import eu.mrndesign.matned.client.core.game.GameContract;
import eu.mrndesign.matned.client.core.game.enviroment.Environment;
import eu.mrndesign.matned.client.core.game.enviroment.ViewEnvironment;
import eu.mrndesign.matned.client.core.game.model.Game;
import eu.mrndesign.matned.client.core.game.model.GameModel;
import eu.mrndesign.matned.client.core.game.model.ModelType;
import eu.mrndesign.matned.client.core.game.model.mainmodel.GameObject;
import eu.mrndesign.matned.client.core.game.model.mainmodel.GameObjectModel;
import eu.mrndesign.matned.client.core.game.utils.MouseListener;
import eu.mrndesign.matned.client.core.settings.SettingsSingleton;
import eu.mrndesign.matned.client.core.utils.Images;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.client.core.utils.Constants.*;
import static eu.mrndesign.matned.client.core.utils.Constants.GUN_SIZE;
import static eu.mrndesign.matned.client.core.utils.Images.BOOM_FILE_NAME;
import static eu.mrndesign.matned.client.core.utils.Images.PNG;

public abstract class BaseGamePresenter {

    protected final Game game;

    private boolean isPresent;
    private boolean isViewEnvironmentToDelete;
    private final List<GameObject> objectsToDelete;
    private final List<ViewEnvironment> viewObjectsToDelete;

    public BaseGamePresenter() {
        game = new GameModel(SettingsSingleton.getInstance().getDifficulty());

        isPresent = false;
        isViewEnvironmentToDelete = false;
        objectsToDelete = new LinkedList<>();
        viewObjectsToDelete = new LinkedList<>();
    }

    protected void cannonPositionListener() {
        GameObject hero = game.getHero();
        GameObject gun = game.getGun();
        gun.setxPos(hero.getxPos() + GUN_X);
        gun.setyPos(hero.getyPos() + GUN_Y);
    }

    protected void shoot() {
        game.addObject(
                new GameObjectModel.GameObjectModelBuilder(Images.BOMB_FILE_NAME + "1" + PNG, 1, ModelType.BOMB)
                        .xPos(game.getGun().getxPos() + getShootXPos(game.getGun().getxPos(), game.getGun().getyPos()))
                        .yPos(game.getGun().getyPos() + getShootYPos(game.getGun().getxPos(), game.getGun().getyPos()))
                        .speedX(getDirection(game.getGun().getxPos(), game.getGun().getyPos()).getShootXSpeed(BOMB_SPEED))
                        .speedY(getDirection(game.getGun().getxPos(), game.getGun().getyPos()).getShootYSpeed(BOMB_SPEED))
                        .xSize(BOMB_SIZE)
                        .ySize(BOMB_SIZE)
                        .hitPower(SHOOT_HIT_POWER)
                        .maxSpeed(SHOOT_MAX_SPEED)
                        .build());
        blow(game.getGun().getxPos() + getShootXPos(game.getGun().getxPos(), game.getGun().getyPos()) - BLOW_SIZE/2,
                game.getGun().getyPos() + getShootYPos(game.getGun().getxPos(), game.getGun().getyPos()) - BLOW_SIZE/2,
                Images.POW_FILE_NAME + PNG, OBJECT_REMOVAL_HOLD_DOWN);
    }

    protected void targetMove() {
        game.getTarget().setxPos(MouseListener.getInstance().getMouseX() - TARGET_SIZE/2);
        game.getTarget().setyPos(MouseListener.getInstance().getMouseY() - TARGET_SIZE/2);
    }

    protected void gunTurn(ViewEnvironment gun) {
        game.getGun().setImage(Images.GUN_FILE_BASE_NAME + getDirection(gun.getxPos(), gun.getyPos()).imgMark() + PNG);
    }

    protected void updateEnvironment(List<ViewEnvironment> environments, GameContract.View view) {
        deleteUsedTemporaryObjects();
        for (GameObject gameObject : game.objectsInGame()) {
            lookForTemporaryObjects(gameObject);
            if (gameObject.modelType() != ModelType.TEMPORARY) {
                checkForFallenEnemies(gameObject);
                useSpeedModification(gameObject);
                removeObjectWhenHealthZero(gameObject);
                bombsListen(gameObject);
            }
            updateGameEnvironmentList(environments, gameObject);
        }
        compareEnvironmentListWithGameModelObjectsList(environments);
        view.labelsUpdate(game.getPoints(), game.getHero().getHealth());
    }








    private void blow(double x, double y, String picture, int duration) {
        game.addObject(
                new GameObjectModel.GameObjectModelBuilder(picture, 1, ModelType.TEMPORARY)
                        .xPos(x)
                        .yPos(y)
                        .xSize(BLOW_SIZE)
                        .ySize(BLOW_SIZE)
                        .frameDuration(duration)
                        .build());
    }

    private void checkForFallenEnemies(GameObject gameObject) {
        if (gameObject.modelType() == ModelType.ENEMY && gameObject.getyPos() > CANVAS_HEIGHT) {
            game.getHero().receiveHit(1);
            game.remove(gameObject);
        }
    }

    private void compareEnvironmentListWithGameModelObjectsList(List<ViewEnvironment> environments) {
        for (ViewEnvironment e :
                environments) {
            isViewEnvironmentToDelete = true;
            for (GameObject g :
                    game.objectsInGame()) {
                if (e.getId() == g.getId()) {
                    isViewEnvironmentToDelete = false;
                    break;
                }
            }
            if (isViewEnvironmentToDelete)
                viewObjectsToDelete.add(e);
        }
        for (ViewEnvironment v :
                viewObjectsToDelete) {
            environments.remove(v);
        }
    }

    private void deleteUsedTemporaryObjects() {
        objectsToDelete.forEach(game::remove);
        objectsToDelete.clear();
    }

    private void lookForTemporaryObjects(GameObject gameObject) {
        if (gameObject.modelType() == ModelType.TEMPORARY) {
            gameObject.nextFrame();
            if (gameObject.frameDuration() <= 0) objectsToDelete.add(gameObject);
        }
    }

    private void bombsListen(GameObject bomb) {
        if (bomb.modelType() == ModelType.BOMB) {
            if (bomb.isOutOfBorders()) {
                game.remove(bomb);
            }
            for (GameObject go : game.objectsInGame()) {
                if (go.collidesWith(bomb)
                        && !go.equals(bomb)
                        && !Utils.getPrimeActions().contains(go.modelType())) {
                    go.receiveHit(bomb.getHitPower());
                    blow(bomb.getxPos() - (BLOW_SIZE/2), bomb.getyPos() - (BLOW_SIZE/2), BOOM_FILE_NAME + PNG, BLOW_DURATION);
                    game.remove(bomb);
                    return;
                }
            }
        }

    }

    private void removeObjectWhenHealthZero(GameObject gameObject) {
        if (gameObject.getHealth() <= 0) {
            gameObject.setModelType(ModelType.TEMPORARY);
            gameObject.setFrameDuration(OBJECT_REMOVAL_HOLD_DOWN);
            game.addPoints(gameObject.getPoints());
        }
    }

    private void updateGameEnvironmentList(List<ViewEnvironment> environments, GameObject gameObject) {
        isPresent = false;
        for (ViewEnvironment environment : environments) {
            if (environment.getId() == gameObject.getId()) {
                applyChangesToViewEnvironment(gameObject, environment);
                isPresent = true;
                break;
            }
        }
        if (!isPresent)
            createNewEnvironment(environments, gameObject);
    }

    private void useSpeedModification(GameObject gameObject) {
        gameObject.setxPos(gameObject.getxPos() + gameObject.getSpeedX());
        gameObject.setyPos(gameObject.getyPos() + gameObject.getSpeedY());
    }

    private void createNewEnvironment(List<ViewEnvironment> environments, GameObject obj) {
        environments.add(new Environment(obj.getId(), obj.getImage(), obj.getxPos(), obj.getyPos(), obj.getxSize(), obj.getySize()));
    }

    private void applyChangesToViewEnvironment(GameObject object, ViewEnvironment viewObject) {
        viewObject.setxPos(object.getxPos());
        viewObject.setyPos(object.getyPos());
        viewObject.setImage(object.getImage());
    }


    private Direction turnTo(double xPos, double yPos) {
        return Direction.turnToMouse(xPos, yPos);
    }

    private Direction getDirection(double x, double y) {
        return turnTo(x + GUN_SIZE, y + GUN_SIZE);
    }

    private double getShootYPos(double x, double y) {
        switch (getDirection(x, y)) {
            case UP:
                return GUN_POSITION_CORRECTION;
            case UP_RIGHT:
            case LEFT_UP:
                return GUN_POSITION_CORRECTION*2;
            case RIGHT_DOWN:
            case DOWN:
            case DOWN_LEFT:
            case RIGHT:
            case LEFT:
                return GUN_SIZE / 2;
        }
        return 0;
    }

    private double getShootXPos(double x, double y) {
        switch (getDirection(x, y)) {
            case UP:
                return GUN_SIZE / 2 - GUN_POSITION_CORRECTION;
            case UP_RIGHT:
                return GUN_SIZE / 2 + GUN_POSITION_CORRECTION;
            case LEFT_UP:
                return GUN_POSITION_CORRECTION*2;
            case RIGHT_DOWN:
            case DOWN:
            case RIGHT:
                return GUN_SIZE - GUN_POSITION_CORRECTION*4;
            case DOWN_LEFT:
            case LEFT:
                return GUN_POSITION_CORRECTION*3;
        }
        return 0;
    }
}
