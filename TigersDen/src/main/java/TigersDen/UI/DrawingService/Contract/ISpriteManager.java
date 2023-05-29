package TigersDen.UI.DrawingService.Contract;

import TigersDen.BL.BoardService.Contract.IPiece;

import javax.swing.ImageIcon;

public interface ISpriteManager {
    ImageIcon getSprite(IPiece piece) throws Exception;
}