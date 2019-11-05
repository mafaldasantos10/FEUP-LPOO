package lpoo312.Lanterna;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import lpoo312.Game.DrawingElement;
import lpoo312.Game.Position;

public class GameOverDrawLanterna implements DrawingElement {
    TextGraphics graphics;
    Position position;
    int height;
    int width;

    public GameOverDrawLanterna(TextGraphics g) {
        graphics = g;
    }

    @Override
    public void draw() {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#74c3d7"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF10"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "   _____                         ____                 ");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 1), "  / ____|                       / __ \\                ");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 2), " | |                           | |  | |                 ");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 3), " | |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __ ");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 4), " | | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__| ");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 5), " | |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |   ");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 6), "  \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|  ");
    }

    @Override
    public void setDrawingParameters(Object... pars) {
        position = (Position) pars[0];
        width = (int) pars[1];
        height = (int) pars[2];
    }
}
