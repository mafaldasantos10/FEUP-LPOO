package lpoo312.Swing;

import lpoo312.Game.DrawingElement;
import lpoo312.Game.Position;

import java.awt.*;

public class BirdDrawSwing implements DrawingElement {

    private static final int BIRD_POSITION_CORRECTION = 40;
    SwingDrawingFactory factory;
    Position position;

    public BirdDrawSwing(SwingDrawingFactory factory) {
        this.factory = factory;
    }

    public void draw() {
        Graphics2D g2d = (Graphics2D) factory.getGraphics();
        g2d.drawImage(factory.getBirdImage(), position.getX() * 20 - BIRD_POSITION_CORRECTION, position.getY() * 20, null);
    }

    @Override
    public void setDrawingParameters(Object... pars) {
        position = (Position) pars[0];
        //System.out.println("X = " + position.getX() + " Y = " + position.getY());
    }
}
