package eu.mrndesign.matned.client.core.game;

import eu.mrndesign.matned.client.core.game.enviroment.ViewEnvironment;

import java.util.List;

public class GameContract {

    public interface View {
    }

    public interface Presenter {
        void heroMove(Direction direction);
        void action(ActionType action, List<ViewEnvironment> environments);

        String backGround();
    }

    enum ActionType{
        MOVE_TARGET,
        GUN_TURN,
        GUN_STICK,
        UPDATE
    }

}
