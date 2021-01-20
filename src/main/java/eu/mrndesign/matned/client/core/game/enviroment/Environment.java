package eu.mrndesign.matned.client.core.game.enviroment;

public class Environment implements ViewEnvironment {

    private final long id;
    private String image;
    private double speedX;
    private double speedY;
    private double xPos;
    private double yPos;
    private final double xSize;
    private final double ySize;

    public Environment(long id, String image, double xPos, double yPos, double xSize, double ySize) {
        this.id = id;
        this.image = image;
        speedX = 0;
        speedY = 0;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
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
