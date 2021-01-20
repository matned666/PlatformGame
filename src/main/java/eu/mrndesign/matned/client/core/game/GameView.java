package eu.mrndesign.matned.client.core.game;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.user.client.Timer;
import eu.mrndesign.matned.client.core.game.present.GamePresenter;
import eu.mrndesign.matned.client.core.game.enviroment.ViewEnvironment;
import eu.mrndesign.matned.client.core.game.utils.MouseListener;
import eu.mrndesign.matned.client.core.game.utils.TimeWrapper;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.client.core.game.utils.Paint.paintOnCanva;
import static eu.mrndesign.matned.client.core.game.utils.Paint.putViewElements;
import static eu.mrndesign.matned.client.core.utils.Constants.*;

public class GameView implements GameContract.View {

    private final Canvas canvas;
    private final Context2d context;
    private final GameContract.Presenter presenter;
    private List<ViewEnvironment> environment;

    public GameView(Canvas canvas) {
        this.canvas = canvas;
        canvas.setFocus(true);
        this.context = canvas.getContext2d();
        presenter = new GamePresenter(this);
        initAll();
        final Timer timer;
        timer = new Timer() {
            @Override
            public void run() {
                running();
            }
        };
        TimeWrapper.getInstance().initTimer(timer);
        TimeWrapper.getInstance().run();
    }

    private void initAll() {
        initializeEnvironment();
        this.canvas.addMouseMoveHandler(this::mouseListen);
        canvas.addKeyDownHandler(this::arrowsSteering);
        canvas.addClickHandler(this::mouseClick);

    }

    private void initializeEnvironment() {
        environment = new LinkedList<>();
        presenter.action(GameContract.ActionType.UPDATE, environment);
    }

    private void running() {
        presenter.action(GameContract.ActionType.PUT_ENEMY);
        presenter.action(GameContract.ActionType.UPDATE, environment);
        paintOnCanva(context, presenter.backGround(), 0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        putViewElements(context, environment);
        TimeWrapper.getInstance().nextFrame();
    }

    private void mouseListen(MouseMoveEvent mouse) {
        MouseListener.getInstance().setMouseX( mouse.getRelativeX(canvas.getElement()) );
        MouseListener.getInstance().setMouseY( mouse.getRelativeY(canvas.getElement()) );
        presenter.action( GameContract.ActionType.MOVE_TARGET);
        presenter.action( GameContract.ActionType.GUN_TURN, environment );
    }

    private void mouseClick(ClickEvent clickEvent) {
        presenter.action(GameContract.ActionType.SHOOT);
    }

    private void arrowsSteering(KeyDownEvent keyDownEvent) {
        if (keyDownEvent.isLeftArrow() && environment.get(0).getxPos() > 0) {
            moveHero(Direction.LEFT);
        }
        if (keyDownEvent.isRightArrow() && environment.get(0).getxPos() < CANVAS_WIDTH - HERO_X_SIZE) {
            moveHero(Direction.RIGHT);
        }
    }

    private void moveHero(Direction d) {
        presenter.heroMove(d);
    }

}




