package TigersDen.UI.DrawingService.BussinessLogic;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import TigersDen.BL.BoardService.Contract.IPiece;
import TigersDen.UI.DrawingService.Contract.ISpriteManager;

public class SpriteManager implements ISpriteManager {
    private ImageIcon whitePawn;
    private ImageIcon blackpawn;
    private boolean inisialized = false;

    @Override
    public ImageIcon getSprite(IPiece piece) throws IOException {
        if (!inisialized) {
            inisialize();
            inisialized = true;
        }
        return whitePawn;
    }

    private void inisialize() throws IOException {
        Image whitePawnImage = ImageIO.read(new File("src/main/resources/TigersDen/w-pawn.png"));
        Image blackPawnImage = ImageIO.read(new File("src/main/resources/TigersDen/b-pawn.png"));

        Image scaledWhitePawnImage = whitePawnImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);//TODO: handle hardCoded!
        Image scaledBlackPawnImage = blackPawnImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);


        whitePawn = new ImageIcon(scaledWhitePawnImage);
        whitePawn = new ImageIcon(scaledBlackPawnImage);
        inisialized = true;
    }

}