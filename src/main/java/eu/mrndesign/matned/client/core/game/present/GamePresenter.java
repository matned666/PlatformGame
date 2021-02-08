package eu.mrndesign.matned.client.core.game.present;

import eu.mrndesign.matned.client.core.game.Direction;
import eu.mrndesign.matned.client.core.game.GameContract;
import eu.mrndesign.matned.client.core.game.GameView;
import eu.mrndesign.matned.client.core.game.enviroment.ViewEnvironment;
import eu.mrndesign.matned.client.core.utils.Constants;

import java.util.List;

import static eu.mrndesign.matned.client.core.utils.Constants.HERO_MOVE_DISTANCE;

public class GamePresenter extends BaseGamePresenter implements GameContract.Presenter {

    private GameContract.View view;

    public GamePresenter(GameContract.View gameView) {
        this.view = gameView;
    }

    @Override
    public void heroMove(Direction direction) {
        if (direction == Direction.LEFT) {
            game.getHero().setxPos(game.getHero().getxPos() - HERO_MOVE_DISTANCE);

        } else {
            game.getHero().setxPos(game.getHero().getxPos() + HERO_MOVE_DISTANCE);
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
                updateEnvironment(environments, view);
                break;
            }
        }
    }

    @Override
    public String backGround() {
        return game.currentLevel().getBackgroundImage();
    }



}
