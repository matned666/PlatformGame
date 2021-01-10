package eu.mrndesign.matned.client.core.game.model;

public enum ModelType {

    HERO,
    HERO_GUN,
    ENEMY;


    public String img(){
        switch (this){
            case HERO: return "hero-";
            case HERO_GUN: return "gun";
            case ENEMY: return "enemy-";
            default: return "";
        }
    }
}
