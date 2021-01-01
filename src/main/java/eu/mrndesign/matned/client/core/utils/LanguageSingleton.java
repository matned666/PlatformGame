package eu.mrndesign.matned.client.core.utils;

public class LanguageSingleton {

    private static LanguageSingleton instance;

    public static LanguageSingleton getInstance() {
        if (instance == null) {
            synchronized (LanguageSingleton.class) {
                if (instance == null)
                    instance = new LanguageSingleton();
                instance.setLanguage(Language.ENGLISH);
            }
        }
        return instance;
    }

    private Language language;

    private LanguageSingleton() {
        if (instance != null) {
            throw new IllegalStateException("Cannot create new instance, please use getInstance method instead.");
        }
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public enum Language {
        ENGLISH,
        POLISH,
        GERMAN;

        public String shrt() {
            switch (this) {
                case ENGLISH:
                    return "en";
                case GERMAN:
                    return "de";
                default:
                    return "pl";
            }
        }

        public String lang() {
            switch (this) {
                case ENGLISH:
                    return "English";
                case GERMAN:
                    return "Deutsch";
                default:
                    return "Polski";
            }
        }
    }


}
