package lpoo312.Swing;

import lpoo312.Game.DrawingElement;
import lpoo312.Game.Position;

import java.awt.*;

public class GameOverDrawSwing implements DrawingElement {

    SwingDrawingFactory factory;
    private Position position;
    private int height;
    private int width;

    public GameOverDrawSwing(SwingDrawingFactory factory) {
        this.factory = factory;
    }

    public void draw() {
        Graphics2D g2d = (Graphics2D) factory.getGraphics();

        g2d.drawImage(factory.getGOImage(), position.getX() * 10, position.getY() * 10, null);

        /*graphics.setBackgroundColor(TextColor.Factory.fromString("#74c3d7"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF10"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "   _____                         ____                 ");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 1), "  / ____|                       / __ \\                ");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 2), " | |                           | |  | |                 ");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 3), " | |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __ ");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 4), " | | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__| ");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 5), " | |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |   ");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 6), "  \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|  ");*/
    }

    @Override
    public void setDrawingParameters(Object... pars) {
        position = (Position) pars[0];
        width = (int) pars[1];
        height = (int) pars[2];
    }
}
