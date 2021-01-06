package eu.mrndesign.matned.client.core.game;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.user.client.ui.*;
import eu.mrndesign.matned.client.core.screen.BaseScreen;
import eu.mrndesign.matned.client.core.screen.ScreenManager;
import eu.mrndesign.matned.client.core.screen.ScreenManagerInterface;
import eu.mrndesign.matned.client.core.utils.Constants;
import eu.mrndesign.matned.client.core.utils.Texts;

import static eu.mrndesign.matned.client.core.utils.Constants.*;
import static eu.mrndesign.matned.client.core.utils.Images.*;

public class GameScreen extends BaseScreen {

    private Grid grid;
    private Canvas canvas;
    private PushButton menuButton;
    private GameContract.View view;

    public GameScreen(GameScreenInterface.ScreenListener screenListener, ScreenManagerInterface screenManager) {
        super(screenManager);
        grid = new Grid(4,3);
        initCanvas();
        initializeWidget();
        initWidget(grid);
        view = new GameView(canvas);
    }

    private void initCanvas() {
        canvas = Canvas.createIfSupported();

        if (canvas == null) {
            FlowPanel flowPanel = new FlowPanel();
            flowPanel.add(new HTML(Texts.HTML_5_CANVAS_ELEMENT()));
            initWidget(flowPanel);
        }
        canvas.setWidth(CANVAS_WIDTH + "px");
        canvas.setCoordinateSpaceWidth((int) CANVAS_WIDTH);
        canvas.setHeight(CANVAS_HEIGHT + "px");
        canvas.setCoordinateSpaceHeight((int) CANVAS_HEIGHT);
        grid.setWidget(1,1,canvas);
    }

    private void initializeWidget() {
        menuButton = new PushButton();
        menuButtonSetup();
        grid.setWidget(0,1, new Image(IMG_FOLDER+"logo-image.jpg"));
        grid.setWidget(2,1, menuButton);
    }

    private void menuButtonSetup() {
        Image menuButtonImg = new Image(Constants.IMG_FOLDER + "button-menu" + PNG);
        menuButton.getElement().appendChild(menuButtonImg.getElement());
        menuButton.addClickHandler(x-> screenManager.initializeScreen(ScreenManager.ScreenType.MENU));
        addClickHandlersForImageButtons(menuButtonImg, menuButton, "button-menu");
    }



}
