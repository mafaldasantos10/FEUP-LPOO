package lpoo312.Tests;

import lpoo312.Game.Position;
import lpoo312.Game.Score;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogicTest {

    @Test
    public void scoreIncrement() {
        Position p = new Position(0, 0);
        Score s = new Score(p);
        int iterations = 15;
        for (int i = 0; i < iterations; i++) {
            s.increaseScore();
        }
        assertEquals(s.getScoreTracker(), iterations * Score.getScorePerPipe());
    }

    @Test
    public void positionEquals() {
        Position p = new Position(10, 0);
        Position p1 = p;

        assertTrue(p.equals(p1));

        Position p2 = new Position(0, 0);

        assertTrue(!p2.equals(p));
    }
}
