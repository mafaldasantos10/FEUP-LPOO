package lpoo312.Tests;

import lpoo312.Game.Pipe;
import lpoo312.Game.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class PipeTest {

    @Test
    public void pipeConstructor() {
        Position position = new Position(0, 0);
        Pipe pipe = new Pipe(position, 0, 0, 0);

        assertEquals(position, pipe.getPosition());
        assertEquals(0, pipe.getHeight());
        assertEquals(0, pipe.getWidth());
        assertEquals(0, pipe.getOption());
    }

    @Test
    public void pipeSets() {
        Position position = new Position(0, 0);
        Pipe pipe = new Pipe(position, 0, 0, 0);

        Position position1 = new Position(2, 2);
        pipe.setPosition(position1);
        assertEquals(position1, pipe.getPosition());

        assertTrue(pipe.isActive());
        pipe.setActive(false);
        assertFalse(pipe.isActive());

        Position position2 = new Position(5, 5);
        pipe.setPipe(position2, 5);
        assertEquals(position2, pipe.getPosition());
        assertEquals(5, pipe.getHeight());
    }
}
