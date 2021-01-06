package eu.mrndesign.matned.client.core.game.enviroment;

public interface ViewEnvironment {

    /*
     * A View object to be placed in a view part - clone of a model object
     */

    boolean isMouseOn();

    boolean collisionWith(ViewEnvironment environment);

    String getPrefix();

    void setStep();

    void setxPos(double xPos);

    void setyPos(double yPos);

    String getImage();

    void setImage(String image);

    double getxPos();

    double getyPos();

    double getxSize();

    double getySize();

}
