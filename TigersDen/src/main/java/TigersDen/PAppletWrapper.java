package TigersDen;

import com.google.inject.Inject;

import TigersDen.UI.DrawingService.Contract.IPAppletWrapper;
import processing.core.PApplet;
import processing.core.PImage;



public class PAppletWrapper implements IPAppletWrapper {
    private final PApplet applet;

    @Inject
    public PAppletWrapper(PApplet applet) {
        this.applet = applet;
    }

    @Override
    public void fill(int color) {
        applet.fill(color);
    }

    @Override
    public void rect(float x, float y, float width, float height) {
        applet.rect(x, y, width, height);
    }

    public void fill(float v1, float v2, float v3) 
    {
        applet.fill(v1, v2, v3);
    }
   
    @Override
    public void background(int rgb) {
       applet.background(rgb);
    }

    @Override
    public void textSize(int size) {
        applet.textSize(size);
    }


    @Override
    public PImage loadImage(String path) {
        return applet.loadImage(path);
    }

    @Override
    public void image(PImage image, float x, float y, float width, float height) {
        applet.image(image, x, y, width, height);
    }

    @Override
    public void text(String text, float x, float y) {
        applet.text(text, x, y);
    }

    @Override
    public void textAlign(int center, int center2) {
        applet.textAlign(center, center2);
    }

}