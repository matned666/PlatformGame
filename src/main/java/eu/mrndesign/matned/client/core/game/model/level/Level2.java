package eu.mrndesign.matned.client.core.game.model.level;

import eu.mrndesign.matned.client.core.game.model.*;

import java.util.LinkedList;
import java.util.List;

public class Level2 extends BaseLevel {

    public Level2() {
        enemies = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            enemies.add(new GameObjectModel.GameObjectModelBuilder("Enemy2", 15, ModelType.ENEMY)
                    .hitPower(6)
                    .isEnemy(true)
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
        return new Level1();
    }
}
