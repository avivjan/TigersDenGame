package TigersDen.UI.DrawingService.BussinessLogic;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;

public class CustomButton extends JButton {
    private boolean isPressed = false;
    @Override
    protected void paintComponent(Graphics g) {
        // Perform default painting
        super.paintComponent(g);

        // Access the ButtonModel
        if (getModel().isPressed()) {
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
