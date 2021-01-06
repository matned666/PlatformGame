package eu.mrndesign.matned.client.core.menu;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import eu.mrndesign.matned.client.core.screen.BaseScreen;
import eu.mrndesign.matned.client.core.screen.ScreenManagerInterface;
import eu.mrndesign.matned.client.core.utils.Constants;

import static eu.mrndesign.matned.client.core.utils.Images.*;

public class MenuScreen extends BaseScreen
        implements MenuScreenInterface
{

    private final PushButton startButton;
    private final PushButton settingsButton;
    private final Grid grid;

    private MenuContract.View view;


    public MenuScreen(MenuScreenInterface.ScreenListener screenListener, ScreenManagerInterface screenManager) {
        super(screenManager);
        this.view = new MenuView(screenListener);
        grid = new Grid(3,3);
        startButton = new PushButton();
        settingsButton = new PushButton();
        initialize();
        setOnClickListeners();
    }

    private void initialize(){
        setButton(startButton);
        setButton(settingsButton);
        grid.setWidget(0,1, startButton);
        grid.setWidget(1,1, settingsButton);
        Image image = new Image(Constants.IMG_FOLDER + "menu-image.jpg");
        grid.setWidget(2,1, image);
        initWidget(grid);
    }

    private void setOnClickListeners() {
        startButtonSetup();
        settingsButtonSetup();
        settingsButton.addClickHandler(x-> view.onSettings());
    }

    private void startButtonSetup() {
        Image startButtonImg = new Image(Constants.IMG_FOLDER + SETTINGS_BUTTON + PNG);
        startButton.getElement().appendChild(startButtonImg.getElement());
        startButton.addClickHandler(x-> view.onStartGame());
        addClickHandlersForImageButtons(startButtonImg, startButton, SETTINGS_BUTTON);
    }

    private void settingsButtonSetup() {
        Image settingsButtonImg = new Image(Constants.IMG_FOLDER + START_BUTTON + PNG);
        settingsButton.getElement().appendChild(settingsButtonImg.getElement());
        settingsButton.addClickHandler(x-> view.onSettings());
        addClickHandlersForImageButtons(settingsButtonImg, settingsButton, START_BUTTON);
    }



}
