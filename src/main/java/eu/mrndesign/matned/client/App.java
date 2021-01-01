package eu.mrndesign.matned.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import eu.mrndesign.matned.client.core.utils.Texts;

import static eu.mrndesign.matned.client.core.utils.Texts.TITLE_TEXT_STYLE;

public class App implements EntryPoint
{

    @Override
    public void onModuleLoad() {

        RootPanel.get().add(new HTML("<" + TITLE_TEXT_STYLE + ">" + Texts.gameTitle() + "</" + TITLE_TEXT_STYLE + ">"));

    }
}
