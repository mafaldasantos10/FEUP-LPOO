package lpoo312.Lanterna;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import lpoo312.Game.DrawingElement;
import lpoo312.Game.Position;

public class PipeDrawLanterna implements DrawingElement {
    TextGraphics graphics;
    Position position;
    private int height;
    private int width;
    private int option;

    public PipeDrawLanterna(TextGraphics g) {
        graphics = g;
    }

    @Override
    public void draw() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#78b029"));
        graphics.enableModifiers(SGR.BOLD);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                graphics.putString(new TerminalPosition(position.getX() + j, position.getY() + i * option), "█");
        }
    }

    @Override
    public void setDrawingParameters(Object... pars) {
        position = (Position) pars[0];
        height = (int) pars[1];
        width = (int) pars[2];
        option = (int) pars[3];
    }
}
