package eu.mrndesign.matned.client.core.menu;

public class MenuContract {

    public interface View {

        void onStartGame();
        void onSettings();
        void startGame();
        void settings();

    }

    public interface Presenter {

        void onButtonClick(MenuAction menuAction);
    }

    enum MenuAction{
        START, SETTINGS
    }
}
