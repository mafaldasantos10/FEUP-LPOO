package lpoo312.Tests;

import lpoo312.Game.Position;
import lpoo312.Game.Score;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ScoreTest {

    @Test
    public void scoreConstructor() {
        Position position = new Position(10, 10);
        Score score = new Score(position);

        assertEquals(position, score.getPosition());
    }

    @Test
    public void scoreLogic() {
        Position position = new Position(10, 10);
        Score score = new Score(position);

        assertEquals(5, Score.getScorePerPipe());
        assertEquals(0, score.getScoreTracker());

        score.increaseScore();

        assertEquals(5, score.getScoreTracker());
        score.increaseScore();
        assertNotEquals(5, score.getScoreTracker());
    }
}
