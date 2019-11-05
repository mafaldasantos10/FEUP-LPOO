package lpoo312.Swing;

import lpoo312.Game.DrawingElement;

import java.awt.*;

public class BackgroundDrawSwing implements DrawingElement {
    SwingDrawingFactory factory;

    public BackgroundDrawSwing(SwingDrawingFactory factory) {
        this.factory = factory;
    }

    public void draw() {
        Graphics2D g2d = (Graphics2D) factory.getGraphics();

        g2d.drawImage(factory.getBGImage(), 0, 0, null);
    }

    @Override
    public void setDrawingParameters(Object... pars) {
    }
}
