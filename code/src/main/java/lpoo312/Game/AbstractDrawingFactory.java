package lpoo312.Game;

import java.io.IOException;

public interface AbstractDrawingFactory {

    DrawingElement getBirdDrawingElement();

    DrawingElement getPipeDrawingElement();

    DrawingElement getLimitDrawingElement();

    DrawingElement getGameOverDrawingElement();

    DrawingElement getScoreDrawingElement();

    DrawingElement getBackgroundDrawingElement();

    DrawingElement getCoinDrawingElement();

    void beginDrawing();

    void endDrawing();

    void draw();

    void drawGameOver();

    void endGame();

    boolean isKeyPressed() throws IOException;

    void setKeyPressed(boolean keyPressed);
}
