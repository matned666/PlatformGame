package eu.mrndesign.matned.client.core.game;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;
import eu.mrndesign.matned.client.core.screen.BaseScreen;
import eu.mrndesign.matned.client.core.screen.ScreenManager;
import eu.mrndesign.matned.client.core.screen.ScreenManagerInterface;
import eu.mrndesign.matned.client.core.utils.Constants;
import eu.mrndesign.matned.client.core.utils.Texts;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.client.core.utils.Constants.*;
import static eu.mrndesign.matned.client.core.utils.Images.*;
import static eu.mrndesign.matned.client.core.utils.Texts.POINTS_LABEL;

public class GameScreen extends BaseScreen {

    private final Grid grid;
    private Grid gameDataGrid;
    private Canvas canvas;
    private PushButton menuButton;
    private List<Label> labels;

    public GameScreen(ScreenManagerInterface screenManager) {
        super(screenManager);
        labels = new LinkedList<>();
        grid = new Grid(4,3);
        initCanvas();
        initializeWidget();
        initWidget(grid);
        new GameView(canvas, labels);
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
        gameDataGridLabelSetup();
        menuButtonSetup();
        grid.setWidget(0,1, gameDataGrid);
        grid.setWidget(2,1, menuButton);
    }

    private void gameDataGridLabelSetup() {
         labels.add(new Label(POINTS_LABEL() + 0));
         labels.add(new Label("Lives: " + 0));
         labels.add(new Label("GAME MADE BY MATNED"));
        gameDataGrid = new Grid(1,labels.size());
        for (int i = 0; i < labels.size(); i++) {
            labels.get(i).getElement().getStyle().setFontSize(20, Style.Unit.PX);
            gameDataGrid.setWidget(0,i, labels.get(i));
        }
    }

    private void menuButtonSetup() {
        Image menuButtonImg = new Image(Constants.IMG_FOLDER + "button-menu" + PNG);
        menuButton.getElement().appendChild(menuButtonImg.getElement());
        menuButton.addClickHandler(x-> screenManager.initializeScreen(ScreenManager.ScreenType.MENU));
        addClickHandlersForImageButtons(menuButtonImg, menuButton, "button-menu");
    }



}
