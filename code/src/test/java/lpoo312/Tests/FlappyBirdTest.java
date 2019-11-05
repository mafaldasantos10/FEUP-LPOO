package lpoo312.Tests;

import lpoo312.Game.FlappyBird;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FlappyBirdTest {

    @Test
    public void flappyBirdLanterna() {
        FlappyBird.setFactory("L");
        FlappyBird.getInstance();
        assertEquals(100F, FlappyBird.getBirdFallingSpeed(), 1F);
    }

    @Test
    public void flappyBirdSwing() {
        FlappyBird.setFactory("S");
        FlappyBird.getInstance();
        assertEquals(100F, FlappyBird.getBirdFallingSpeed(), 1F);
    }
}
