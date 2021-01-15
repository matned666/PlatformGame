package eu.mrndesign.matned.client.core.game;

public interface BaseModel {

    long getId();
    String getImage();
    void setImage(String image);
    double getSpeedX();
    double getSpeedY();
    void setSpeedX(double speed);
    void setSpeedY(double speed);
    void setxPos(double xPos);
    void setyPos(double yPos);
    double getxPos();
    double getyPos();
    double getxSize();
    double getySize();
}
