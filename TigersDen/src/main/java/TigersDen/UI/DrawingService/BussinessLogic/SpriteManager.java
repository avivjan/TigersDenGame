package TigersDen.UI.DrawingService.BussinessLogic;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import TigersDen.BL.BoardService.BussinessLogic.Pieces.PawnPiece;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.UI.DrawingService.Contract.ISpriteManager;
import processing.core.PImage;

public class SpriteManager implements ISpriteManager {
    private PImage whitePawn;
    private PImage blackPawn;
    private boolean inisialized = false;

    @Override
    public PImage getSprite(IPiece piece, String color) {
        if (!inisialized) {
            inisialize();
            inisialized = true;
        }
        if (piece instanceof PawnPiece) {
            if (color.equals("black")) {
                return blackPawn;
            } else if (color.equals("white")) {
                return whitePawn;
            }
        }
        throw new IllegalArgumentException(
                "Invalid piece type or color: " + piece.getClass().getSimpleName() + ", " + color);
    }

    private void inisialize()  {

        try {
            Image whitePawnImage = ImageIO.read(new File("src/main/resources/TigersDen/w-pawn.png"));
            Image blackPawnImage = ImageIO.read(new File("src/main/resources/TigersDen/b-pawn.png"));
            inisialized = true;
        } catch (IOException e) {
            System.err.println("Error: Could not load sprites");
            e.printStackTrace();
        }

    }

}