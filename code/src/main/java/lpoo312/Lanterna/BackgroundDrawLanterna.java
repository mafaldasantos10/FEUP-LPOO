package lpoo312.Lanterna;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import lpoo312.Game.DrawingElement;
import lpoo312.Game.Position;

public class BackgroundDrawLanterna implements DrawingElement {
    TextGraphics graphics;
    Position position;
    int height;
    int width;

    public BackgroundDrawLanterna(TextGraphics g) {
        graphics = g;
    }

    @Override
    public void draw() {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#71c5cf"));
        graphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(width, height), ' ');
    }

    @Override
    public void setDrawingParameters(Object... pars) {
        position = (Position) pars[0];
        width = (int) pars[1];
        height = (int) pars[2];
    }
}
