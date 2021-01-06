package eu.mrndesign.matned.client.core.screen;

import com.google.gwt.user.client.ui.RootPanel;
import eu.mrndesign.matned.client.core.game.GameScreen;
import eu.mrndesign.matned.client.core.game.GameScreenInterface;
import eu.mrndesign.matned.client.core.menu.MenuScreen;
import eu.mrndesign.matned.client.core.menu.MenuScreenInterface;
import eu.mrndesign.matned.client.core.settings.SettingsScreen;
import eu.mrndesign.matned.client.core.settings.SettingsScreenInterface;

public class ScreenManager implements
        ScreenManagerInterface,
        MenuScreenInterface.ScreenListener,
        GameScreenInterface.ScreenListener,
        SettingsScreenInterface.ScreenListener

{

    private ScreenInterface screen;
    private final RootPanel rootPanel;

    public ScreenManager(RootPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    //    on start game we have menu
    @Override
    public void start() {
        initializeScreen(ScreenType.MENU);
    }

    //    initializes a screen according to a screen type
    @Override
    public void initializeScreen(ScreenType screenType) {
        if (screen != null) screen.hide();
        switch (screenType) {
            case GAME: {
                screen = new GameScreen(this, this);
                break;
            }
            case SETTINGS: {
                screen = new SettingsScreen(this, this);
                break;
            }
            default: {
                screen = new MenuScreen(this,this);
            }
        }
        screen.show();
    }

    @Override
    public void onStartGame() {
        initializeScreen(ScreenType.GAME);
    }

    public enum ScreenType {
        MENU, GAME, SETTINGS
    }

}

