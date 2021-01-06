package eu.mrndesign.matned.client.core.screen;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import eu.mrndesign.matned.client.core.utils.Constants;

import static eu.mrndesign.matned.client.core.utils.Images.*;

public abstract class BaseScreen  extends Composite implements ScreenInterface {

    protected ScreenManagerInterface screenManager;

    public BaseScreen(ScreenManagerInterface screenManager) {
        this.screenManager = screenManager;
    }

    @Override
    public void show() {
        RootPanel.get().add(this);
    }

    @Override
    public void hide() {
        RootPanel.get().remove(this);

    }

    protected void addClickHandlersForImageButtons(Image image, PushButton button, String urlFileName) {
        button.addMouseDownHandler(x->
                image.setUrl(Constants.IMG_FOLDER + urlFileName + MOUSE_CLICK));
        button.addMouseUpHandler(x->
                image.setUrl(Constants.IMG_FOLDER + urlFileName + MOUSE_ON_BUTTON));
        button.addMouseOverHandler(x->
                image.setUrl(Constants.IMG_FOLDER + urlFileName + MOUSE_ON_BUTTON));
        button.addMouseOutHandler(x->
                image.setUrl(Constants.IMG_FOLDER + urlFileName + PNG));
    }


    protected void setButton(PushButton button) {
        button.getElement().getStyle().setHeight(Constants.MENU_BUTTON_HEIGHT, Style.Unit.PX);
        button.getElement().getStyle().setWidth(Constants.MENU_BUTTON_WIDTH, Style.Unit.PX);
    }
}
