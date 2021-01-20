package eu.mrndesign.matned.client.core.game.present;

import eu.mrndesign.matned.client.core.game.model.ModelType;

import java.util.Arrays;
import java.util.List;

public class Utils {

    static List<ModelType> getPrimeActions(){
        return Arrays.asList(
                ModelType.HERO_GUN,
                ModelType.TARGET,
                ModelType.TEMPORARY,
                ModelType.HERO);
    }
}
