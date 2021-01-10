package eu.mrndesign.matned.client.core.game.model;

public enum Difficulty {

    EASY,
    MEDIUM,
    HARD;

    public int multiplicand(){
        switch (this){
            case MEDIUM: return 2;
            case HARD: return 4;
            default: return 1;
        }
    }
}
