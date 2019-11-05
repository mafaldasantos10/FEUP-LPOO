package lpoo312.Tests;

import lpoo312.Game.Background;
import lpoo312.Game.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BackGroundTest {

    @Test
    public void backgroundConstructor() {
        Position position = new Position(0, 0);
        int width = 80;
        int heigth = 24;
        Background background = new Background(position, width, heigth);

        assertEquals(position, background.getPosition());
        assertEquals(width, background.getWidth());
        assertEquals(heigth, background.getHeight());
    }
}
