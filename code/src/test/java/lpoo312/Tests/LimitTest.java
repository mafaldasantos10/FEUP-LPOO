package lpoo312.Tests;

import lpoo312.Game.Limit;
import lpoo312.Game.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class LimitTest {

    @Test
    public void limitConstructor() {
        Position position = new Position(0, 0);
        Limit limit = new Limit(position);

        assertEquals(position, limit.getPosition());
    }

    @Test
    public void limitSets() {
        Position position = new Position(0, 0);
        Limit limit = new Limit(position);

        Position position1 = new Position(2, 2);
        limit.setPosition(position1);
        assertEquals(position1, limit.getPosition());

        assertTrue(limit.isActive());
        limit.setActive(false);
        assertFalse(limit.isActive());
    }
}
