package eu.mrndesign.matned.client;

import com.google.gwt.core.client.EntryPoint;
import eu.mrndesign.matned.client.core.screen.ScreenManager;
import eu.mrndesign.matned.client.core.screen.ScreenManagerInterface;

public class App implements EntryPoint
{

    @Override
    public void onModuleLoad() {
        ScreenManagerInterface smi = new ScreenManager();
        smi.start();
    }
}
