package lpoo312.Tests;

import lpoo312.Game.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class BirdTest {

    @Test
    public void birdFly() {
        Position birdPosition = new Position(10, 10);
        Bird b = new Bird(birdPosition);
        Position newPosition = new Position(10, 10 - Bird.getFlyPace());
        b.fly();
        assertEquals(b.getPosition(), newPosition);
    }

    @Test
    public void birdDive() {
        Position birdPosition = new Position(10, 10);
        Bird b = new Bird(birdPosition);
        Position newPosition = new Position(10, 10 - Bird.getFallPace());
        long start = System.currentTimeMillis();
        if (System.currentTimeMillis() - start > FlappyBird.getBirdFallingSpeed()) {
            assertEquals(b.getPosition(), newPosition);
        }
    }

    @Test
    public void birdLimitCollision() {
        Map m = new Map(80, 24);

        Position p = new Position(9, m.getHeight() - 2); // hits top limit
        m.getBird().setPosition(p);
        assertTrue(m.entersOccupiedSpace());

        Position p1 = new Position(9, -1);  // hits bot limit
        m.getBird().setPosition(p1);
        assertTrue(m.entersOccupiedSpace());

        Position p2 = new Position(9, 0);  // close to top limit, but doesn't hit it
        m.getBird().setPosition(p2);
        assertFalse(m.entersOccupiedSpace());

        Position p3 = new Position(9, m.getHeight() - 4);  // close to bot limit, but doesn't hit it
        m.getBird().setPosition(p3);
        assertFalse(m.entersOccupiedSpace());
    }

    @Test
    public void birdPipeCollision() {
        Map m = new Map(80, 24);

        assertFalse(m.entersOccupiedSpace());

        m.createLimits();
        m.computePipes();

        while (m.getPipes().get(0).getPosition().getX() > 10) {
            m.updatePipes();
        }

        Position birdPosition = new Position(m.getPipes().get(0).getPosition().getX(), m.getPipes().get(0).getPosition().getY() - 1);
        m.getBird().setPosition(birdPosition);

        assertTrue(m.entersOccupiedSpace());
    }

    @Test
    public void birdGets() {
        Position birdPosition = new Position(10, 10);
        Bird b = new Bird(birdPosition);

        assertEquals(10, b.getPosition().getX());
        assertEquals(10, b.getPosition().getY());
        assertEquals(1, Bird.getFallPace());
        assertEquals(4, Bird.getFlyPace());
    }
}
