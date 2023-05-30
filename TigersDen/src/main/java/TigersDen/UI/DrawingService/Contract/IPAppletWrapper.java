package TigersDen.UI.DrawingService.Contract;
import processing.core.PImage;

public interface IPAppletWrapper {
    void fill(int color);
    void rect(float x, float y, float width, float height);
    void fill(float v1, float v2, float v3);
    void background(int rgb); 
    void textSize(int size);
    PImage loadImage(String path);
    void image(PImage image, float x, float y, float width, float height);
}
