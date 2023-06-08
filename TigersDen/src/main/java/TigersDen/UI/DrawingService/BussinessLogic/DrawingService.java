package TigersDen.UI.DrawingService.BussinessLogic;

import java.util.List;

import com.google.inject.Inject;

import TigersDen.BL.BoardService.Contract.IBoard;
import TigersDen.BL.BoardService.Contract.ICoordinate;
import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.BL.BoardService.DataModel.CellStatus;
import TigersDen.BL.BoardService.Model.ICell;
import TigersDen.BL.ConfigurationService.Contract.IConfigurationService;
import TigersDen.UI.DrawingService.Contract.IDrawingService;
import TigersDen.UI.DrawingService.Contract.IPAppletWrapper;
import TigersDen.UI.DrawingService.Contract.ISpriteManager;
import processing.core.PImage;

public class DrawingService implements IDrawingService {
    private IPAppletWrapper pAppletWrapper;
    private IBoard board;
    private ISpriteManager spriteManager;
    private IConfigurationService configurationService;

    @Inject
    public DrawingService(IPAppletWrapper appletWrapper, IBoard board,
                        ISpriteManager spriteManager , IConfigurationService configurationService) {
        this.pAppletWrapper = appletWrapper;
        this.board = board;
        this.spriteManager = spriteManager;
        this.configurationService = configurationService; 
    }

    @Override
    public void draw() throws Exception {
        pAppletWrapper.background(200);
        pAppletWrapper.fill(250);
        pAppletWrapper.textSize(25);
        drawCells();
        drawPieces();

    }

    private void drawPieces() {
        List<IPiece> pieces = board.getPieces();
        for (IPiece piece : pieces) {
            if (piece.isCaptured()) {
                continue;
            }
            PImage sprite = spriteManager.getSprite(piece, piece.getOwningPlayer().getColor());
            if (sprite == null) {
                System.out.println("Error: Sprite is null for piece: " + piece.getClass().getSimpleName());
            }
            ICoordinate coordinate = piece.getCoordinate();

            int pixelX = coordinate.getXInPixels();
            int pixelY = coordinate.getYInPixels();
            // Draw the image
            pAppletWrapper.image(sprite,pixelX , pixelY, configurationService.getAssetsSize(), configurationService.getAssetsSize());//Get From config
        }
    }

    private void drawCells() throws Exception {
        System.out.println(board.getCells().size());
        for (ICell cell : board.getCells()) {
            int cellSize = configurationService.getCellSize();
            
            decideAColor(cell.getCoordinate());
            pAppletWrapper.rect(cell.getCoordinate().getXInPixels(),
                                cell.getCoordinate().getYInPixels(),
                                cellSize,
                                cellSize);
        }
    }

    private void decideAColor(ICoordinate coordinate) throws Exception {
        ICell cell = board.getCell(coordinate);
        CellStatus cellStatus = cell.getStatus();
        switch (cellStatus) {
            case Option:
                pAppletWrapper.fill(0, 0, 255); // Blue
                break;
            case OptionWithCapture:
                pAppletWrapper.fill(255, 0, 0); // Red
                break;
            case Selected:
                pAppletWrapper.fill(0, 255, 0); // Green
                break;
            case None:
                if ((coordinate.getRow() + coordinate.getColumn()) % 2 == 0) {
                    pAppletWrapper.fill(255, 235, 205);
                } else {
                    pAppletWrapper.fill(200, 150, 50);
                }
                break;
            default:
                throw new Exception("Invalid cell status");
        }
    }
}
