package lpoo312.Swing;

import lpoo312.Game.FlappyBird;

import javax.swing.*;
import java.awt.*;

public class SwingGamePanel extends JPanel {
    SwingDrawingFactory factory;

    public SwingGamePanel(SwingDrawingFactory f) {
        factory = f;
    }

    @Override
    public void paintComponent(Graphics g) {
        factory.setGraphics(g);
        factory.beginDrawing();
        factory.draw();
        factory.endDrawing();

        if (FlappyBird.getInstance().getGameState() == FlappyBird.gameState.GameOver) {
            factory.drawGameOver();
        }

    }
}
