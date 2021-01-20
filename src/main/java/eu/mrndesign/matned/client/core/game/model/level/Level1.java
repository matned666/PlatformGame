package eu.mrndesign.matned.client.core.game.model.level;

import eu.mrndesign.matned.client.core.game.model.*;
import eu.mrndesign.matned.client.core.game.model.mainmodel.GameObject;
import eu.mrndesign.matned.client.core.game.model.mainmodel.GameObjectModel;
import eu.mrndesign.matned.client.core.utils.Constants;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.client.core.utils.Images.BACKGROUND_1;
import static eu.mrndesign.matned.client.core.utils.Images.JPG;
import static eu.mrndesign.matned.client.core.utils.Texts.ENEMY_1;

public class Level1 extends BaseLevel {


    public Level1() {
        enemies = new LinkedList<>();
        for (int i = 0; i < 12; i++) {
            enemies.add(new GameObjectModel.GameObjectModelBuilder(ENEMY_1, Constants.ENEMY_1_BASE_HEALTH, ModelType.ENEMY)
                    .hitPower(4)
                    .maxSpeed(5)
                    .build());
        }
    }

    @Override
    public String getBackgroundImage() {
        return BACKGROUND_1 + JPG;
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
