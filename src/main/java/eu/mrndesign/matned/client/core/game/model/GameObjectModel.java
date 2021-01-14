package eu.mrndesign.matned.client.core.game.model;

public class GameObjectModel implements GameObject {

    private long id;
    private final String name;
    private String image;
    private int health;
    private final ModelType modelType;
    private final double maxSpeed;
    private final int hitPower;
    private double xPos;
    private double yPos;
    private final double xSize;
    private final double ySize;

    private GameObjectModel(GameObjectModelBuilder builder) {
        id = Id.getInstance().nextId();
        this.name = builder.name;
        this.image = builder.image;
        this.health = builder.health;
        this.modelType = builder.modelType;
        this.maxSpeed = builder.maxSpeed;
        this.hitPower = builder.hitPower;
        this.xPos = builder.xPos;;
        this.yPos = builder.yPos;;
        this.xSize = builder.xSize;;
        this.ySize = builder.ySize;;
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
    public int getHealth() {
        return health;
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
    public void receiveHit(int hitPoints) {
        health -= hitPoints;
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
        private final ModelType modelType;
        private double maxSpeed;
        private int hitPower;
        private double xPos;
        private double yPos;
        private double xSize;
        private double ySize;

        public GameObjectModelBuilder(String image, int health, ModelType modelType) {
            this.image = image;
            this.health = health;
            this.modelType = modelType;
        }

        public GameObjectModelBuilder name(String name){
            this.name = name;
            return this;
        }

        public GameObjectModelBuilder maxSpeed(double maxSpeed){
            this.maxSpeed = maxSpeed;
            return this;
        }

        public GameObjectModelBuilder hitPower(int hitPower){
            this.hitPower = hitPower;
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
