package eu.mrndesign.matned.client.core.game.model;

public interface GameObject {

    String getName();
    int getHealth();
    double getMaxSpeed();
    int getHitPower();
    boolean isEnemy();

    ModelType modelType();

    void receiveHit(int hitPoints);


}
