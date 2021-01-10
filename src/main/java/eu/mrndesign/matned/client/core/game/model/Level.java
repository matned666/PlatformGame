package eu.mrndesign.matned.client.core.game.model;

import java.util.List;

public interface Level {

    String getBackgroundImage();
    List<GameObject> enemies();
    Level nextLevel();

}
