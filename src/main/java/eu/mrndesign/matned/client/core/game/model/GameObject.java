package eu.mrndesign.matned.client.core.game.model;

import eu.mrndesign.matned.client.core.game.BaseModel;

public interface GameObject extends BaseModel {

    int getHealth();
    String getName();
    double getMaxSpeed();
    int getHitPower();
    ModelType modelType();
    void receiveHit(int hitPoints);

}
