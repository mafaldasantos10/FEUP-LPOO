package lpoo312.Tests;

import lpoo312.Game.GameOver;
import lpoo312.Game.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameOverTest {

    @Test
    public void gameOverConstructor() {
        Position position = new Position(0, 0);
        GameOver gameOver = new GameOver(position, 0, 0);

        assertEquals(position, gameOver.getPosition());
    }

    @Test
    public void gameOveParameters() {
        Position position = new Position(0, 0);
        GameOver gameOver = new GameOver(position, 0, 0);

        assertEquals(0, gameOver.getHeight());
        assertEquals(0, gameOver.getWidth());

        int newHeight = 20;
        int newWidth = 80;

        gameOver.setHeight(newHeight);
        gameOver.setWidth(newWidth);

        assertEquals(newHeight, gameOver.getHeight());
        assertEquals(newWidth, gameOver.getWidth());
    }
}
