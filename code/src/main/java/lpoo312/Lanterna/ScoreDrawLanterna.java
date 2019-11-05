package lpoo312.Lanterna;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import lpoo312.Game.DrawingElement;
import lpoo312.Game.Position;

public class ScoreDrawLanterna implements DrawingElement {

    TextGraphics graphics;
    Position position;
    private int scoreTracker;

    public ScoreDrawLanterna(TextGraphics g) {
        graphics = g;
    }

    @Override
    public void draw() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF1110"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "Score: " + scoreTracker + " ");
    }

    @Override
    public void setDrawingParameters(Object... pars) {
        position = (Position) pars[0];
        scoreTracker = (int) pars[1];
    }
}
