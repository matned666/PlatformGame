package eu.mrndesign.matned.client.core.game;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.user.client.Timer;
import eu.mrndesign.matned.client.core.game.enviroment.Environment;
import eu.mrndesign.matned.client.core.game.enviroment.ViewEnvironment;
import eu.mrndesign.matned.client.core.game.utils.MouseListener;
import eu.mrndesign.matned.client.core.game.utils.Paint;
import eu.mrndesign.matned.client.core.game.utils.TimeWrapper;
import eu.mrndesign.matned.client.core.utils.Images;

import java.util.LinkedList;
import java.util.List;

import static eu.mrndesign.matned.client.core.game.utils.Paint.putViewElements;
import static eu.mrndesign.matned.client.core.utils.Constants.*;
import static eu.mrndesign.matned.client.core.utils.Images.PNG;

public class GameView implements GameContract.View {


    private Canvas canvas;
    private Context2d context;
    private List<ViewEnvironment> environment;
    private ViewEnvironment hero;
    private ViewEnvironment targetPoint;
    private ViewEnvironment cannon;
    private int heroSpeed;
    private Direction currentGunDirection;

    public GameView(Canvas canvas) {
        this.canvas = canvas;
        this.context = canvas.getContext2d();

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
        environment = new LinkedList<>();
        hero = new Environment(Images.HERO_IMG + PNG, HERO_DEFAULT_START_X_POS, HERO_DEFAULT_START_Y_POS, HERO_X_SIZE, HERO_Y_SIZE);
        cannon = new Environment("gun1" + PNG, HERO_DEFAULT_START_X_POS + GUN_X, HERO_DEFAULT_START_Y_POS + GUN_Y, 50, 50);
        targetPoint = new Environment(Images.TARGET_IMAGE + PNG, 100, 50, TARGET_SIZE, TARGET_SIZE);
        environment.add(hero);
        environment.add(cannon);
        environment.add(targetPoint);
        this.canvas.addMouseMoveHandler(this::mouseListen);
        canvas.addKeyDownHandler(event -> arrowsSteering(event));

    }


    private void running() {
        Paint.onCanva(context, "background-1.jpg", 0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        putViewElements(environment, context);
    }

    private void mouseListen(MouseMoveEvent mouse) {
        double mouseX = mouse.getRelativeX(canvas.getElement());
        double mouseY = mouse.getRelativeY(canvas.getElement());

        eu.mrndesign.matned.client.core.game.utils.MouseListener.getInstance().setMouseX(mouseX);
        eu.mrndesign.matned.client.core.game.utils.MouseListener.getInstance().setMouseY(mouseY);

        targetPoint.setxPos(mouseX - 17.5);
        targetPoint.setyPos(mouseY - 17.5);

        gunTurn();
    }

    private void arrowsSteering(KeyDownEvent keyDownEvent) {
        if (keyDownEvent.isLeftArrow() && hero.getxPos() > 0) {
            moveHero(Direction.LEFT);
        }
        if (keyDownEvent.isRightArrow() && hero.getxPos() < CANVAS_WIDTH - HERO_X_SIZE) {
            moveHero(Direction.RIGHT);
        }
        cannonPositionListener();
    }

    private void moveHero(Direction d) {
        if (d == Direction.LEFT) {
            hero.setxPos(hero.getxPos() - 5);

        } else {
            hero.setxPos(hero.getxPos() + 5);
        }
    }









    private void gunTurn(){
        cannon.setImage("gun"+turnTo().imgMark()+PNG);
    }

    private void cannonPositionListener(){
        cannon.setxPos(hero.getxPos()+GUN_X);
        cannon.setyPos(hero.getyPos()+GUN_Y);
    }








    private double angle;

    public Direction turnTo() {
        return getDirection(cannon.getxPos()+50, cannon.getyPos()+50, MouseListener.getInstance().getMouseX(), MouseListener.getInstance().getMouseY());
    }

    private Direction getDirection(double heroX, double heroY, double mouseX, double mouseY) {
        angle = angle(heroX, heroY, mouseX, mouseY);
        if (setUpR(heroX, heroY, mouseX, mouseY)) return Direction.UP_RIGHT;
        else if (setRD(heroX, heroY, mouseX, mouseY)) return Direction.RIGHT_DOWN;
        else if (setDL(heroX, heroY, mouseX, mouseY)) return Direction.DOWN_LEFT;
        else if (setLUp(heroX, heroY, mouseX, mouseY)) return Direction.LEFT_UP;
        else if (setUp(heroX, heroY, mouseX, mouseY)) return Direction.UP;
        else if (setR(heroX, heroY, mouseX, mouseY)) return Direction.RIGHT;
        else if (setD(heroX, heroY, mouseX, mouseY)) return Direction.DOWN;
        else if (setL(heroX, heroY, mouseX, mouseY)) return Direction.LEFT;
        else return Direction.POINT;
    }

    double angle(double heroX, double heroY, double mouseX, double mouseY) {
        double a = (Math.abs(heroX - mouseX));
        double b = (Math.abs(heroY - mouseY));
        return Math.atan((b / a));
    }

    private boolean setUp(double heroX, double heroY, double mouseX, double mouseY) {
        return ((mouseY < heroY) && (mouseX == heroX || angle >= Math.toRadians(65.5)));
    }

    private boolean setUpR(double heroX, double heroY, double mouseX, double mouseY) {
        return (mouseY < heroY && mouseX > heroX) && (angle > Math.toRadians(22.5) && angle < Math.toRadians(67.5));
    }

    private boolean setR(double heroX, double heroY, double mouseX, double mouseY) {
        return ((mouseX > heroX) && (mouseY == heroY || angle <= Math.toRadians(22.5)));
    }

    private boolean setRD(double heroX, double heroY, double mouseX, double mouseY) {
        return (mouseY > heroY && mouseX > heroX) && (angle > Math.toRadians(22.5) && angle < Math.toRadians(67.5));
    }

    private boolean setD(double heroX, double heroY, double mouseX, double mouseY) {
        return ((mouseY > heroY) && (mouseX == heroX || angle >= Math.toRadians(22.5)));
    }

    private boolean setDL(double heroX, double heroY, double mouseX, double mouseY) {
        return (mouseY > heroY && mouseX < heroX) && (angle > Math.toRadians(22.5) && angle < Math.toRadians(67.5));
    }

    private boolean setL(double heroX, double heroY, double mouseX, double mouseY) {
        return ((mouseX < heroX) && (mouseY == heroY || angle <= Math.toRadians(22.5)));
    }

    private boolean setLUp(double heroX, double heroY, double mouseX, double mouseY) {
        return (mouseY < heroY && mouseX < heroX) && (angle > Math.toRadians(22.5) && angle < Math.toRadians(67.5));
    }

    enum Direction {
        LEFT,
        RIGHT,
        UP_RIGHT,
        RIGHT_DOWN,
        DOWN_LEFT,
        LEFT_UP,
        UP,
        DOWN,
        POINT;

        public int imgMark() {
            switch (this) {
                case UP: {
                    return 3;
                }
                case UP_RIGHT: {
                    return 4;
                }
                case RIGHT:
                case RIGHT_DOWN:
                case DOWN: {
                    return 5;
                }
                case DOWN_LEFT:
                case LEFT: {
                    return 1;
                }
                case LEFT_UP: {
                    return 2;
                }
                default: return 1;
            }
        }
    }
}




