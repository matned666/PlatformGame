package eu.mrndesign.matned.client.core.utils;

public class Texts {

    public static final String TITLE_TEXT_STYLE = "h1";
    public static String gameTitle(){
        switch (LanguageSingleton.getInstance().getLanguage()){
            case ENGLISH: return En.GAME_TITLE;
            case GERMAN: return De.GAME_TITLE;
            default: return Pl.GAME_TITLE;
        }
    }

    private static class En {
        public static final String GAME_TITLE = "Platform game";
    }

    private static class De {
        public static final String GAME_TITLE = "Platform game";
    }

    private static class Pl {
        public static final String GAME_TITLE = "Platform game";
    }



}
