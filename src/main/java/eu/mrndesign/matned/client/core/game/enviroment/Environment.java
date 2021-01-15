package eu.mrndesign.matned.client.core.game.enviroment;


import eu.mrndesign.matned.client.core.game.utils.MouseListener;

import static eu.mrndesign.matned.client.core.utils.Constants.ENVIRONMENT_FRAME;

public class Environment implements ViewEnvironment {

    private final long id;
    private String image;
    private double speedX;
    private double speedY;
    private double xPos;
    private double yPos;
    private final double xSize;
    private final double ySize;
    private boolean step;

    private final double objectBorderThickness;

    public Environment(long id, String image, double xPos, double yPos, double xSize, double ySize) {
        this.id = id;
        this.image = image;
        speedX = 0;
        speedY = 0;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
        this.objectBorderThickness = ENVIRONMENT_FRAME;
        step = false;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public double getSpeedX() {
        return speedX;
    }

    @Override
    public double getSpeedY() {
        return speedY;
    }

    @Override
    public void setSpeedX(double speed) {
        this.speedX = speed;
    }

    @Override
    public void setSpeedY(double speed) {
        this.speedY = speed;
    }

    //    checks if there is a mouse on the environment object
    @Override
    public boolean isMouseOn() {
        double mouseX = MouseListener.getInstance().getMouseX();
        double mouseY = MouseListener.getInstance().getMouseY();
        return mouseX >= xPos && mouseX <= xPos+xSize && mouseY >= yPos && mouseY <= yPos+ySize;
    }

//    checks if his object collides with another object
    @Override
    public boolean collisionWith(ViewEnvironment environment) {
        double eXPos = environment.getxPos();
        double eYPos = environment.getyPos();
        double eXSize = environment.getxSize();
        double eYSize = environment.getySize();
        return (xPos+xSize- objectBorderThickness >= eXPos) && (yPos+ySize- objectBorderThickness >= eYPos) && (xPos+ objectBorderThickness <= eXPos+eXSize) && (yPos+ objectBorderThickness <= eYPos+eYSize);
    }

    @Override
    public String getPrefix() {
        if (step) return "s";
        else return "r";
    }

    @Override
    public void setStep() {
        step = !step;
    }

    @Override
    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    @Override
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public double getxPos() {
        return xPos;
    }

    @Override
    public double getyPos() {
        return yPos;
    }

    @Override
    public double getxSize() {
        return xSize;
    }

    @Override
    public double getySize() {
        return ySize;
    }

}
