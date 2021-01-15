package eu.mrndesign.matned.client.core.game.model;

import eu.mrndesign.matned.client.core.game.model.mainmodel.GameObject;

import java.util.List;

public interface Level {

    String getBackgroundImage();
    List<GameObject> enemies();
    Level nextLevel();

}
