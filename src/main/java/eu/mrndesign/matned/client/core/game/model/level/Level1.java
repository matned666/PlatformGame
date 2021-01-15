package eu.mrndesign.matned.client.core.game.model.level;

import eu.mrndesign.matned.client.core.game.model.*;
import eu.mrndesign.matned.client.core.game.model.mainmodel.GameObject;
import eu.mrndesign.matned.client.core.game.model.mainmodel.GameObjectModel;

import java.util.LinkedList;
import java.util.List;

public class Level1 extends BaseLevel {

    public Level1() {
        enemies = new LinkedList<>();
        for (int i = 0; i < 12; i++) {
            enemies.add(new GameObjectModel.GameObjectModelBuilder("Enemy1", 10, ModelType.ENEMY)
                    .hitPower(4)
                    .maxSpeed(5)
                    .build());
        }
    }

    @Override
    public String getBackgroundImage() {
        return "background-1.jpg";
    }

    @Override
    public List<GameObject> enemies() {
        return enemies;
    }

    @Override
    public Level nextLevel() {
        return new Level2();
    }
}
