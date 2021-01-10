package eu.mrndesign.matned.client.core.game.model;

public class GameObjectModel implements GameObject {

    private final String name;
    private int health;
    private final ModelType modelType;
    private final double maxSpeed;
    private final int hitPower;
    private final boolean isEnemy;

    private GameObjectModel(GameObjectModelBuilder builder) {
        this.name = builder.name;
        this.health = builder.health;
        this.modelType = builder.modelType;
        this.maxSpeed = builder.maxSpeed;
        this.hitPower = builder.hitPower;
        this.isEnemy = builder.isEnemy;
    }

    @Override
    public String getName() {
        return name;
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
    public boolean isEnemy() {
        return isEnemy;
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
    public String toString() {
        return "GameObjectModel{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", maxSpeed=" + maxSpeed +
                ", hitPower=" + hitPower +
                ", isEnemy=" + isEnemy +
                '}';
    }

    public static class GameObjectModelBuilder{

        private final String name;
        private final int health;
        private final ModelType modelType;
        private double maxSpeed;
        private int hitPower;
        private boolean isEnemy;

        public GameObjectModelBuilder(String name, int health, ModelType modelType) {
            this.name = name;
            this.health = health;
            this.modelType = modelType;
        }

        public GameObjectModelBuilder maxSpeed(double maxSpeed){
            this.maxSpeed = maxSpeed;
            return this;
        }

        public GameObjectModelBuilder hitPower(int hitPower){
            this.hitPower = hitPower;
            return this;
        }

        public GameObjectModelBuilder isEnemy(boolean isEnemy){
            this.isEnemy = isEnemy;
            return this;
        }

        public GameObjectModel build(){
            return new GameObjectModel(this);
        }

    }

}
