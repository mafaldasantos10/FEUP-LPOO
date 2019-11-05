package lpoo312.Tests;

import lpoo312.Game.Map;
import org.junit.Test;

import static org.junit.Assert.*;


public class ObjectPoolTest {

    @Test
    public void screenPipes() {
        Map m = new Map(80, 24);
        m.computePipes();

        /* goes through entire map */
        for (int i = 0; i < 80; i++)
            m.updatePipes();

        assertTrue(m.getPipes().get(0).isActive());
        assertEquals(2, m.getPipes().size());

        for (int i = 0; i < Map.getPipeWidth(); i++)
            m.updatePipes();

        assertFalse(m.getPipes().get(0).isActive());

        m.computePipes();
        assertEquals(2, m.getPipes().size());
        assertTrue(m.getPipes().get(0).isActive());
    }

    @Test
    public void screenLimit() {
        Map m = new Map(80, 24);
        m.createLimits();

        assertEquals(80, m.getLimits().size());

        assertTrue(m.getLimits().get(0).isActive());
        m.moveLimits();
        m.deactivateInvalidLimits();
        assertFalse(m.getLimits().get(0).isActive());

        m.updateLimits();
        assertTrue(m.getLimits().get(0).isActive());

        assertEquals(80, m.getLimits().size());
    }

    @Test
    public void screenCoin() {
        Map m = new Map(80, 24);
        m.computeCoins();

        /* goes through just the map */
        for (int i = 0; i < 80; i++)
            m.updateCoins();

        assertTrue(m.getCoins().get(0).isActive());
        assertEquals(1, m.getCoins().size());

        /* completes map */
        for (int i = 0; i < Map.getPipeMinDistance(); i++)
            m.updateCoins();

        assertFalse(m.getCoins().get(0).isActive());

        m.computeCoins();
        assertEquals(1, m.getCoins().size());
        assertTrue(m.getCoins().get(0).isActive());

        m.computeCoins();
        assertEquals(2, m.getCoins().size());

        /* goes through entire map */
        for (int i = 0; i < 80 + Map.getPipeMinDistance(); i++)
            m.updateCoins();

        assertFalse(m.getCoins().get(0).isActive());
        assertFalse(m.getCoins().get(1).isActive());
    }
}
