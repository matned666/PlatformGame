package eu.mrndesign.matned.client.core.game.model;

import static eu.mrndesign.matned.client.core.utils.Constants.MULTIPLICAND_HARD;
import static eu.mrndesign.matned.client.core.utils.Constants.MULTIPLICAND_MEDIUM;

public enum Difficulty {

    EASY,
    MEDIUM,
    HARD;


    public int multiplicand(){
        switch (this){
            case MEDIUM: return MULTIPLICAND_MEDIUM;
            case HARD: return MULTIPLICAND_HARD;
            default: return 1;
        }
    }
}
