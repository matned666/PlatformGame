package eu.mrndesign.matned.client.core.game;

import eu.mrndesign.matned.client.core.game.utils.MouseListener;

public enum Direction {
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
            default:
                return 1;
        }
    }

    private static double angle;

    public static Direction turnToMouse(double xPos, double yPos) {
        return getDirection(xPos, yPos, MouseListener.getInstance().getMouseX(), MouseListener.getInstance().getMouseY());
    }

    private static Direction getDirection(double heroX, double heroY, double mouseX, double mouseY) {
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

    private static double angle(double heroX, double heroY, double mouseX, double mouseY) {
        double a = (Math.abs(heroX - mouseX));
        double b = (Math.abs(heroY - mouseY));
        return Math.atan((b / a));
    }

    private static boolean setUp(double heroX, double heroY, double mouseX, double mouseY) {
        return ((mouseY < heroY) && (mouseX == heroX || angle >= Math.toRadians(65.5)));
    }

    private static boolean setUpR(double heroX, double heroY, double mouseX, double mouseY) {
        return (mouseY < heroY && mouseX > heroX) && (angle > Math.toRadians(22.5) && angle < Math.toRadians(67.5));
    }

    private static boolean setR(double heroX, double heroY, double mouseX, double mouseY) {
        return ((mouseX > heroX) && (mouseY == heroY || angle <= Math.toRadians(22.5)));
    }

    private static boolean setRD(double heroX, double heroY, double mouseX, double mouseY) {
        return (mouseY > heroY && mouseX > heroX) && (angle > Math.toRadians(22.5) && angle < Math.toRadians(67.5));
    }

    private static boolean setD(double heroX, double heroY, double mouseX, double mouseY) {
        return ((mouseY > heroY) && (mouseX == heroX || angle >= Math.toRadians(22.5)));
    }

    private static boolean setDL(double heroX, double heroY, double mouseX, double mouseY) {
        return (mouseY > heroY && mouseX < heroX) && (angle > Math.toRadians(22.5) && angle < Math.toRadians(67.5));
    }

    private static boolean setL(double heroX, double heroY, double mouseX, double mouseY) {
        return ((mouseX < heroX) && (mouseY == heroY || angle <= Math.toRadians(22.5)));
    }

    private static boolean setLUp(double heroX, double heroY, double mouseX, double mouseY) {
        return (mouseY < heroY && mouseX < heroX) && (angle > Math.toRadians(22.5) && angle < Math.toRadians(67.5));
    }

}
