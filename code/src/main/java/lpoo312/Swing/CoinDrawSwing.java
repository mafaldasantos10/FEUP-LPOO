package lpoo312.Swing;

import lpoo312.Game.DrawingElement;
import lpoo312.Game.Position;

import java.awt.*;

public class CoinDrawSwing implements DrawingElement {

    SwingDrawingFactory factory;
    Position position;

    public CoinDrawSwing(SwingDrawingFactory factory) {
        this.factory = factory;
    }

    public void draw() {
        Graphics2D g2d = (Graphics2D) factory.getGraphics();
        g2d.drawImage(factory.getCoinImage(), position.getX() * 20, position.getY() * 20, null);
    }

    @Override
    public void setDrawingParameters(Object... pars) {
        position = (Position) pars[0];
    }
}
