package TigersDen.UI.DrawingService.Contract;

import TigersDen.BL.BoardService.Contract.IPiece;
import processing.core.PImage;


public interface ISpriteManager {
    PImage getSprite(IPiece piece, String color);
}