package TigersDen.UI.DrawingService.BussinessLogic;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.BussinessLogic.Pieces.PawnPiece;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.UI.DrawingService.Contract.IPAppletWrapper;
import TigersDen.UI.DrawingService.Contract.ISpriteManager;
import processing.core.PImage;

public class SpriteManager implements ISpriteManager {
    private PImage whitePawn;
    private PImage blackPawn;
    private boolean inisialized = false;
    private IPAppletWrapper pAppletWrapper;

    @Inject
    public SpriteManager(IPAppletWrapper  pAppletWrapper) {
        this.pAppletWrapper = pAppletWrapper;
    }

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
            whitePawn = pAppletWrapper.loadImage("src/main/resources/TigersDen/w-pawn.png");
            blackPawn = pAppletWrapper.loadImage("src/main/resources/TigersDen/b-pawn.png");
            inisialized = true;
        } catch (Exception e) {
            System.err.println("Error: Could not load sprites");
            e.printStackTrace();
        }
    }
}