package eu.mrndesign.matned.client.core.utils;

public class Texts {

    public static LanguageSingleton.Language lang;
    static {
        lang = LanguageSingleton.getInstance().getLanguage();
    }


    public static final String SQUIRREL = "Wiewiór";
    public static final String ENEMY_1 = "Enemy1";
    public static final String TITLE_TEXT_STYLE = "h1";

    public static String gameTitle() {
        switch (lang) {
            case POLISH: return Pl.GAME_TITLE;
            default: return En.GAME_TITLE;
        }
    }

    public static String POINTS_LABEL(){
        switch (lang){
            case POLISH: return Pl.POINTS_LABEL;
            default: return En.POINTS_LABEL;
        }
    }

    public static String HEALTH_LABEL(){
        switch (lang){
            case POLISH: return Pl.HEALTH_LABEL;
            default: return En.HEALTH_LABEL;
        }
    }

    public static String HTML_5_CANVAS_ELEMENT(){
        switch (lang){
            case POLISH: return Pl.HTML_5_CANVAS_ELEMENT;
            default: return En.HTML_5_CANVAS_ELEMENT;
        }
    }

    public static String START_GAME(){
        switch (lang){
            case POLISH: return Pl.START_GAME;
            default: return En.START_GAME;
        }
    }

    public static String SETTINGS(){
        switch (lang){
            case POLISH: return Pl.SETTINGS;
            default: return En.SETTINGS;
        }
    }

    private static class En {
        public static final String POINTS_LABEL = "Points: ";
        public static final String HEALTH_LABEL = "Health: ";
        private static final String HTML_5_CANVAS_ELEMENT = "Sorry, your browser doesn't support the HTML5 Canvas element";
        private static final String GAME_TITLE = "Platform game";
        private static final String START_GAME = "START GAME";
        private static final String SETTINGS = "SETTINGS";
    }

    private static class Pl {
        public static final String POINTS_LABEL = "Punkty: ";
        public static final String HEALTH_LABEL = "Życie: ";
        private static final String HTML_5_CANVAS_ELEMENT = "Sorry, twoja przeglądarka nie obsługuje obiektu typu canvas";
        private static final String GAME_TITLE = "Gra platformowa";
        private static final String START_GAME = "ZACZNIJ GRĘ";
        private static final String SETTINGS = "USTAWIENIA";

    }



}
