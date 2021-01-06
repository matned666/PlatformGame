package eu.mrndesign.matned.client.core.menu;

import eu.mrndesign.matned.client.core.screen.Screen;

public interface MenuScreenInterface extends Screen {

    interface ScreenListener {
        void onStartGame();
    }
}
