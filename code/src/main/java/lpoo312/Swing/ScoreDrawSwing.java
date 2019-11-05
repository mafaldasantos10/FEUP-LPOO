package lpoo312.Swing;

import lpoo312.Game.DrawingElement;
import lpoo312.Game.Position;

import java.awt.*;

public class ScoreDrawSwing implements DrawingElement {
    SwingDrawingFactory factory;
    private Position position;
    private int scoreTracker;

    public ScoreDrawSwing(SwingDrawingFactory factory) {
        this.factory = factory;
    }

    public void draw() {
        Graphics2D g2d = (Graphics2D) factory.getGraphics();

        try {
            Font f = new Font("Monospaced", Font.BOLD | Font.ITALIC, 24);
            g2d.setFont(f);
            g2d.drawString("SCORE: " + scoreTracker, position.getX() + 10, position.getY() + 50);

        } catch (Exception e) {
        }
    }

    @Override
    public void setDrawingParameters(Object... pars) {
        position = (Position) pars[0];
        scoreTracker = (int) pars[1];
    }
}
