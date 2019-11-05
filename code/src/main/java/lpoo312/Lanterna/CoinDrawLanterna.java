package lpoo312.Lanterna;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import lpoo312.Game.DrawingElement;
import lpoo312.Game.Position;

public class CoinDrawLanterna implements DrawingElement {

    TextGraphics graphics;
    Position position;

    public CoinDrawLanterna(TextGraphics g) {
        graphics = g;
    }

    @Override
    public void draw() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#fff003"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "@");
    }

    @Override
    public void setDrawingParameters(Object... pars) {
        position = (Position) pars[0];
    }
}
