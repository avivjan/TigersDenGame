package TigersDen;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.data.JSONArray;
import processing.core.PFont;
import processing.event.MouseEvent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.google.inject.Guice;
import com.google.inject.Injector;

import TigersDen.BL.BoardService.BussinessLogic.Board;
import TigersDen.DAL.Contract.IGenericAccessor;
import TigersDen.DAL.DataModel.BoardData;
import TigersDen.DI.GuiceModule;

import java.awt.Font;
import java.io.*;
import java.util.*;

public class App extends PApplet {

    public static final int SPRITESIZE = 480;
    public static final int CELLSIZE = 48;
    public static final int SIDEBAR = 120;
    public static final int BOARD_WIDTH = 14;

    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE;

    public static final int FPS = 60;
	
    public String configPath;

    public App() {
        this.configPath = "config.json";
    }

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);
        Injector injector = Guice.createInjector(new GuiceModule());
    }

    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(){


    }
    
    /**
     * Receive key released signal from the keyboard.
    */
    public void keyReleased(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    /**
     * Draw all elements in the game by current frame. 
    */
    public void draw() {
        
    }
	
	// Add any additional methods or attributes you want. Please put classes in different files.


    public static void main(String[] args) {
        PApplet.main("TigersDen.App");
    }
}
