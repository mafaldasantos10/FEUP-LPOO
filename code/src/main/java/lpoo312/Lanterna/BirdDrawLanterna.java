package lpoo312.Lanterna;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import lpoo312.Game.DrawingElement;
import lpoo312.Game.Position;

public class BirdDrawLanterna implements DrawingElement {

    TextGraphics graphics;
    Position position;

    public BirdDrawLanterna(TextGraphics g) {
        graphics = g;
    }

    @Override
    public void draw() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#fcff00"));
        graphics.enableModifiers(SGR.BOLD);

        graphics.putString(new TerminalPosition(position.getX() - 2, position.getY()), "_");
        graphics.putString(new TerminalPosition(position.getX() - 1, position.getY()), "_");
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "_");

        graphics.putString(new TerminalPosition(position.getX() - 3, position.getY() + 1), "/");
        graphics.putString(new TerminalPosition(position.getX() - 2, position.getY() + 1), "_");
        graphics.putString(new TerminalPosition(position.getX() - 1, position.getY() + 1), "_");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 1), "0");
        graphics.putString(new TerminalPosition(position.getX() + 1, position.getY() + 1), "\\");

        graphics.putString(new TerminalPosition(position.getX() - 3, position.getY() + 2), "\\");
        graphics.putString(new TerminalPosition(position.getX() - 2, position.getY() + 2), "^");
        graphics.putString(new TerminalPosition(position.getX() - 1, position.getY() + 2), "^");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 2), "_");
        graphics.putString(new TerminalPosition(position.getX() + 1, position.getY() + 2), "/");

        graphics.setForegroundColor(TextColor.Factory.fromString("#f8a515"));

        graphics.putString(new TerminalPosition(position.getX() + 2, position.getY() + 2), "-");
        graphics.putString(new TerminalPosition(position.getX() + 2, position.getY() + 1), "_");
        //graphics.setForegroundColor(TextColor.Factory.fromString("#FF1110"));
    }

    @Override
    public void setDrawingParameters(Object... pars) {
        position = (Position) pars[0];
    }
}
