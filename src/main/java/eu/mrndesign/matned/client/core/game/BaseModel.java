package eu.mrndesign.matned.client.core.game;

public interface BaseModel {

    long getId();
    String getImage();
    void setImage(String image);
    void setxPos(double xPos);
    void setyPos(double yPos);
    double getxPos();
    double getyPos();
    double getxSize();
    double getySize();
}
