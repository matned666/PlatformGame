package eu.mrndesign.matned.client.core.game;

import eu.mrndesign.matned.client.core.game.enviroment.Environment;
import eu.mrndesign.matned.client.core.game.enviroment.ViewEnvironment;
import eu.mrndesign.matned.client.core.game.model.Game;
import eu.mrndesign.matned.client.core.game.model.GameModel;
import eu.mrndesign.matned.client.core.game.model.GameObject;
import eu.mrndesign.matned.client.core.game.utils.MouseListener;
import eu.mrndesign.matned.client.core.settings.SettingsSingleton;


import java.util.List;

import static eu.mrndesign.matned.client.core.utils.Constants.GUN_X;
import static eu.mrndesign.matned.client.core.utils.Constants.GUN_Y;
import static eu.mrndesign.matned.client.core.utils.Images.PNG;


public class GamePresenter implements GameContract.Presenter {

    private final Game game;
    private final GameContract.View view;
    private boolean isPresent;

    public GamePresenter(GameContract.View view) {
        this.view = view;
        game = new GameModel(SettingsSingleton.getInstance().getDifficulty());
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
    public void action(GameContract.ActionType action, List<ViewEnvironment> environments) {
        switch (action) {
            case GUN_TURN: {
                gunTurn(environments.get(1));
                break;
            }
            case MOVE_TARGET: {
                targetMove();
                break;
            }
            case UPDATE:{
                updateEnvironment(environments);
                break;
            }
        }
    }

    @Override
    public String backGround() {
        return game.currentLevel().getBackgroundImage();
    }

    private void updateEnvironment(List<ViewEnvironment> environments) {
        for (GameObject gameObject : game.objectsInGame()) {
            isPresent = false;
            for (ViewEnvironment environment : environments) {
                if (environment.getId() == gameObject.getId()){
                    applyChangesToViewEnvironment(gameObject, environment);
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent)
                createNewEnvironment(environments, gameObject);
        }
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
        game.getGun().setImage("gun"+turnTo(gun.getxPos() + 50, gun.getyPos() + 50).imgMark()+PNG);
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

}
