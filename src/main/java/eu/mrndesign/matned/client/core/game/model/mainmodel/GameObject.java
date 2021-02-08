package eu.mrndesign.matned.client.core.game.model.mainmodel;

import eu.mrndesign.matned.client.core.game.BaseModel;
import eu.mrndesign.matned.client.core.game.model.ModelType;

public interface GameObject extends BaseModel {

    int frameDuration();
    void setFrameDuration(int duration);
    void nextFrame();
    int getHealth();
    int getPoints();
    String getName();
    double getMaxSpeed();
    int getHitPower();
    ModelType modelType();
    void setModelType(ModelType modelType);
    void receiveHit(int hitPoints);
    boolean collidesWith(GameObject collider);
    boolean isOutOfBorders();
}
