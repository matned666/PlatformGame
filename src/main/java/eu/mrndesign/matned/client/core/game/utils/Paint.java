package eu.mrndesign.matned.client.core.game.utils;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.ui.Image;
import eu.mrndesign.matned.client.core.game.enviroment.ViewEnvironment;
import eu.mrndesign.matned.client.core.utils.Constants;

import java.util.List;

public class Paint {

//    paints an image on context
    public static void paintOnCanva(Context2d context, String image, double posx, double posy, double sizex, double sizey) {
        ImageElement img = ImageElement.as(new Image(Constants.IMG_FOLDER + image).getElement());
        context.drawImage(img, posx, posy, sizex, sizey);
    }

//    paints a main view objects of a field of game - list of environment
    public static void putViewElements(Context2d context, List<ViewEnvironment> environments) {
        for (ViewEnvironment el : environments) {
            Paint.paintOnCanva(context, el.getImage(), el.getxPos(), el.getyPos(), el.getxSize(), el.getySize());
        }
        context.strokeText("X: " + MouseListener.getInstance().getMouseX(), 12, 20 + 10);
        context.strokeText("Y: " + MouseListener.getInstance().getMouseY(), 12, 20 + 20);
        context.strokeText("Frame: " + TimeWrapper.getInstance().getFrameNo(), 12, 20 + 30);
    }
}
