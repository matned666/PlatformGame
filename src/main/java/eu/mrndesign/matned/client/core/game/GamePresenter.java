package eu.mrndesign.matned.client.core.game;

import eu.mrndesign.matned.client.core.game.enviroment.Environment;
import eu.mrndesign.matned.client.core.game.enviroment.ViewEnvironment;
import eu.mrndesign.matned.client.core.game.model.*;
import eu.mrndesign.matned.client.core.game.model.mainmodel.GameObject;
import eu.mrndesign.matned.client.core.game.model.mainmodel.GameObjectModel;
import eu.mrndesign.matned.client.core.game.utils.MouseListener;
import eu.mrndesign.matned.client.core.settings.SettingsSingleton;


import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.client.core.utils.Constants.*;
import static eu.mrndesign.matned.client.core.utils.Images.PNG;


public class GamePresenter implements GameContract.Presenter {

    private final Game game;
    private boolean isPresent;
    private boolean isViewEnvironmentToDelete;
    private final List<GameObject> objectsToDelete;
    private final List<ViewEnvironment> viewObjectsToDelete;

    public GamePresenter() {
        game = new GameModel(SettingsSingleton.getInstance().getDifficulty());
        isPresent = false;
        isViewEnvironmentToDelete = false;
        objectsToDelete = new LinkedList<>();
        viewObjectsToDelete = new LinkedList<>();
    }

    @Override
    public void heroMove(Direction direction) {
        if (direction == Direction.LEFT) {
            game.getHero().setxPos(game.getHero().getxPos() - 5);

        } else {
            game.getHero().setxPos(game.getHero().getxPos() + 5);
        }
        cannonPositionListener();
    }

    @Override
    public void action(GameContract.ActionType action) {
        switch (action) {
            case SHOOT: {
                shoot();
                break;
            }
            case MOVE_TARGET: {
                targetMove();
                break;
            }
            case PUT_ENEMY: {
                game.putEnemy();
                break;
            }
        }
    }


    @Override
    public void action(GameContract.ActionType action, List<ViewEnvironment> environments) {
        switch (action) {
            case GUN_TURN: {
                gunTurn(environments.get(1));
                break;
            }
            case UPDATE: {
                updateEnvironment(environments);
                break;
            }
        }
    }

    @Override
    public String backGround() {
        return game.currentLevel().getBackgroundImage();
    }

    private void shoot() {
        game.addObject(
                new GameObjectModel.GameObjectModelBuilder("bomb1.png", 1, ModelType.BOMB)
                        .xPos(game.getGun().getxPos() + getShootXPos(game.getGun().getxPos(), game.getGun().getyPos()))
                        .yPos(game.getGun().getyPos() + getShootYPos(game.getGun().getxPos(), game.getGun().getyPos()))
                        .speedX(getDirection(game.getGun().getxPos(), game.getGun().getyPos()).getShootXSpeed(BOMB_SPEED))
                        .speedY(getDirection(game.getGun().getxPos(), game.getGun().getyPos()).getShootYSpeed(BOMB_SPEED))
                        .xSize(BOMB_SIZE)
                        .ySize(BOMB_SIZE)
                        .hitPower(100)
                        .maxSpeed(20)
                        .build());
        blow(game.getGun().getxPos() + getShootXPos(game.getGun().getxPos(), game.getGun().getyPos()) - 40,
                game.getGun().getyPos() + getShootYPos(game.getGun().getxPos(), game.getGun().getyPos()) - 40,
                80, "pow.png", 10);
    }


    private void blow(double x, double y, double size, String picture, int duration) {
        game.addObject(
                new GameObjectModel.GameObjectModelBuilder(picture, 1, ModelType.TEMPORARY)
                        .xPos(x)
                        .yPos(y)
                        .xSize(size)
                        .ySize(size)
                        .frameDuration(duration)
                        .build());
    }

    private void updateEnvironment(List<ViewEnvironment> environments) {
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
        checkForRemovedEnvironment(environments);
    }

    private void checkForFallenEnemies(GameObject gameObject) {
        if (gameObject.modelType() == ModelType.ENEMY && gameObject.getxPos() > CANVAS_HEIGHT) {
            game.remove(gameObject);
        }
    }

    private void checkForRemovedEnvironment(List<ViewEnvironment> environments) {
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
                        && go.modelType() != ModelType.HERO_GUN
                        && go.modelType() != ModelType.TARGET
                        && go.modelType() != ModelType.TEMPORARY // TODO put them all in one list
                        && go.modelType() != ModelType.HERO) {
                    go.receiveHit(bomb.getHitPower());
                    blow(bomb.getxPos() - 40, bomb.getyPos() - 40, 80, "boom.png", 4);
                    game.remove(bomb);
                    return;
                }
            }
        }

    }

    private void removeObjectWhenHealthZero(GameObject gameObject) {
        if (gameObject.getHealth() <= 0) {
            gameObject.setModelType(ModelType.TEMPORARY);
            gameObject.setFrameDuration(10);
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

    private void targetMove() {
        game.getTarget().setxPos(MouseListener.getInstance().getMouseX() - 17.5);
        game.getTarget().setyPos(MouseListener.getInstance().getMouseY() - 17.5);
    }


    private void gunTurn(ViewEnvironment gun) {
        game.getGun().setImage("gun" + getDirection(gun.getxPos(), gun.getyPos()).imgMark() + PNG);
    }

    private void cannonPositionListener() {
        GameObject hero = game.getHero();
        GameObject gun = game.getGun();
        gun.setxPos(hero.getxPos() + GUN_X);
        gun.setyPos(hero.getyPos() + GUN_Y);
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
                return 5;
            case UP_RIGHT:
            case LEFT_UP:
                return 10;
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
                return GUN_SIZE / 2 - 5;
            case UP_RIGHT:
                return GUN_SIZE / 2 + 5;
            case LEFT_UP:
                return 10;
            case RIGHT_DOWN:
            case DOWN:
            case RIGHT:
                return GUN_SIZE - 20;
            case DOWN_LEFT:
            case LEFT:
                return 15;
        }
        return 0;
    }

}
