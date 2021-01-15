package eu.mrndesign.matned.client.core.game.utils;

import eu.mrndesign.matned.client.core.game.model.mainmodel.GameObject;
import eu.mrndesign.matned.client.core.game.model.mainmodel.GameObjectModel;
import eu.mrndesign.matned.client.core.game.model.ModelType;

import static eu.mrndesign.matned.client.core.utils.Constants.*;
import static eu.mrndesign.matned.client.core.utils.Constants.HERO_Y_SIZE;
import static eu.mrndesign.matned.client.core.utils.GameStats.*;

public class StaticObjects {

    public static GameObject hero(int multiplicand){
        return new GameObjectModel
                .GameObjectModelBuilder("hero-1.png", DEFAULT_HERO_HP / multiplicand, ModelType.HERO)
                .name("Hero")
                .hitPower(DEFAULT_HERO_POWER/ multiplicand)
                .maxSpeed(DEFAULT_HERO_MAX_SPEED  - multiplicand)
                .xPos(HERO_DEFAULT_START_X_POS)
                .yPos(HERO_DEFAULT_START_Y_POS)
                .xSize(HERO_X_SIZE)
                .ySize(HERO_Y_SIZE)
                .build();
    }

    public static GameObject gun(int multiplicand, double heroXPos, double heroYPos){
        return new GameObjectModel
                .GameObjectModelBuilder("gun1.png", DEFAULT_HERO_GUN_HP / multiplicand, ModelType.HERO_GUN)
                .name("Cannon")
                .hitPower(DEFAULT_HERO_GUN_POWER / multiplicand)
                .maxSpeed(DEFAULT_HERO_MAX_SPEED  - multiplicand)
                .xPos(heroXPos+GUN_X)
                .yPos(heroYPos+GUN_Y)
                .xSize(GUN_SIZE)
                .ySize(GUN_SIZE)
                .build();
    }

    public static GameObject target(){
        return new GameObjectModel.GameObjectModelBuilder("target.png", 99, ModelType.TARGET)
                .name("Target point")
                .xPos(0)
                .yPos(0)
                .xSize(TARGET_SIZE)
                .ySize(TARGET_SIZE)
                .build();
    }


}
