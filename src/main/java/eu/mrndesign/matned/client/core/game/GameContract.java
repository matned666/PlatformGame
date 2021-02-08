package eu.mrndesign.matned.client.core.game;

import eu.mrndesign.matned.client.core.game.enviroment.ViewEnvironment;

import java.util.List;

public class GameContract {

    public interface View {
        void labelsUpdate(int points, int lives);
    }

    public interface Presenter {
        void heroMove(Direction direction);
        void action(ActionType action);
        void action(ActionType action, List<ViewEnvironment> environments);

        String backGround();
    }

    public enum ActionType{
        MOVE_TARGET,
        GUN_TURN,
        SHOOT,
        UPDATE,
        PUT_ENEMY
    }

}
