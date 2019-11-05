package lpoo312.Swing;

import lpoo312.Game.DrawingElement;
import lpoo312.Game.Position;

import java.awt.*;

public class LimitDrawSwing implements DrawingElement {

    SwingDrawingFactory factory;
    Position position;

    public LimitDrawSwing(SwingDrawingFactory factory) {
        this.factory = factory;
    }

    public void draw() {
        Graphics2D g2d = (Graphics2D) factory.getGraphics();
        g2d.drawImage(factory.getLimitImage(), position.getX() * 20 - 40, position.getY() * 20, null);
    }

    @Override
    public void setDrawingParameters(Object... pars) {
        position = (Position) pars[0];
    }
}
