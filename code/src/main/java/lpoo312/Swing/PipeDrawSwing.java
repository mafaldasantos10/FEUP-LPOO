package lpoo312.Swing;

import lpoo312.Game.DrawingElement;
import lpoo312.Game.Position;

import java.awt.*;

public class PipeDrawSwing implements DrawingElement {
    private final static int HEIGHT = 265;
    SwingDrawingFactory factory;
    private Position position;
    private int option;

    public PipeDrawSwing(SwingDrawingFactory factory) {
        this.factory = factory;
    }

    public void draw() {
        Graphics2D g2d = (Graphics2D) factory.getGraphics();

        if (option == 1) {
            g2d.drawImage(factory.getDownPipeImage(), position.getX() * 20, position.getY() * 20, null);
        } else if (option == -1) {
            g2d.drawImage(factory.getUpPipeImage(), position.getX() * 20, position.getY() * 20 - HEIGHT, null);
        }
    }

    @Override
    public void setDrawingParameters(Object... pars) {
        position = (Position) pars[0];
        option = (int) pars[3];
    }
}
