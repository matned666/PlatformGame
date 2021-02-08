package eu.mrndesign.matned.client.core.game.model.mainmodel;

import eu.mrndesign.matned.client.core.game.model.Id;
import eu.mrndesign.matned.client.core.game.model.ModelType;

import static eu.mrndesign.matned.client.core.utils.Constants.*;

public final class GameObjectModel implements GameObject {

    private final long id;
    private final String name;
    private String image;
    private int health;
    private int frameDuration;
    protected ModelType modelType;
    private final double maxSpeed;
    private final int hitPower;
    private int pointsWhenDestroyed;
    private double speedX;
    private double speedY;
    private double xPos;
    private double yPos;
    private final double xSize;
    private final double ySize;

    protected GameObjectModel(GameObjectModelBuilder builder) {
        id = Id.getInstance().nextId();
        this.name = builder.name;
        this.pointsWhenDestroyed = builder.pointsWhenDestroyed;
        this.image = builder.image;
        this.health = builder.health;
        this.frameDuration = builder.frameDuration;
        this.modelType = builder.modelType;
        this.maxSpeed = builder.maxSpeed;
        this.hitPower = builder.hitPower;
        this.speedX = builder.speedX;
        this.speedY = builder.speedY;
        this.xPos = builder.xPos;
        this.yPos = builder.yPos;
        this.xSize = builder.xSize;
        this.ySize = builder.ySize;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
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
    public int frameDuration() {
        return frameDuration;
    }

    @Override
    public void setFrameDuration(int duration) {
        this.frameDuration = duration;
    }

    @Override
    public void nextFrame() {
        frameDuration -= 1;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getPoints() {
        return pointsWhenDestroyed;
    }

    @Override
    public double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public int getHitPower() {
        return hitPower;
    }

    @Override
    public ModelType modelType() {
        return modelType;
    }

    @Override
    public void setModelType(ModelType modelType) {
        this.modelType = modelType;
    }

    @Override
    public void receiveHit(int hitPoints) {
        health -= hitPoints;
    }

    @Override
    public boolean collidesWith(GameObject collider) {
        return (xPos+xSize >= collider.getxPos()) &&
                (yPos+ySize >= collider.getyPos()) &&
                (xPos <= collider.getxPos()+collider.getxSize()) &&
                (yPos <= collider.getyPos()+collider.getySize());
    }

    @Override
    public boolean isOutOfBorders() {
        return xPos <= -BOMB_SIZE && yPos <= -BOMB_SIZE && xPos >= CANVAS_WIDTH && yPos >= CANVAS_HEIGHT;
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

    @Override
    public String toString() {
        return "GameObjectModel{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", maxSpeed=" + maxSpeed +
                ", hitPower=" + hitPower +
                '}';
    }

    public static class GameObjectModelBuilder{

        private String name;
        private final String image;
        private final int health;
        private int pointsWhenDestroyed;
        private int frameDuration;
        private final ModelType modelType;
        private double maxSpeed;
        private int hitPower;
        private double xPos;
        private double yPos;
        private double xSize;
        private double ySize;
        private double speedX;
        private double speedY;

        public GameObjectModelBuilder(String image, int health, ModelType modelType) {
            this.image = image;
            this.health = health;
            this.modelType = modelType;
        }

        public GameObjectModelBuilder name(String name){
            this.name = name;
            return this;
        }

        public GameObjectModelBuilder pointsWhenDestroyed(int pointsWhenDestroyed){
            this.pointsWhenDestroyed = pointsWhenDestroyed;
            return this;
        }

        public GameObjectModelBuilder maxSpeed(double maxSpeed){
            this.maxSpeed = maxSpeed;
            return this;
        }

        public GameObjectModelBuilder speedX(double speedX){
            this.speedX = speedX;
            return this;
        }

        public GameObjectModelBuilder speedY(double speedY){
            this.speedY = speedY;
            return this;
        }

        public GameObjectModelBuilder hitPower(int hitPower){
            this.hitPower = hitPower;
            return this;
        }

        public GameObjectModelBuilder frameDuration(int frameDuration){
            this.frameDuration = frameDuration;
            return this;
        }

        public GameObjectModelBuilder xPos(double xPos){
            this.xPos = xPos;
            return this;
        }

        public GameObjectModelBuilder yPos(double yPos){
            this.yPos = yPos;
            return this;
        }

        public GameObjectModelBuilder xSize(double xSize){
            this.xSize = xSize;
            return this;
        }

        public GameObjectModelBuilder ySize(double ySize){
            this.ySize = ySize;
            return this;
        }

        public GameObjectModel build(){
            return new GameObjectModel(this);
        }

    }

}
