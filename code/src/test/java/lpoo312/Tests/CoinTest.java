package lpoo312.Tests;

import lpoo312.Game.Coin;
import lpoo312.Game.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CoinTest {

    @Test
    public void coinConstructor() {
        Position position = new Position(0, 0);

        Coin coin = new Coin(position);

        assertEquals(position, coin.getPosition());

        Position position2 = new Position(10, 10);
        coin.setPosition(position2);

        assertEquals(position2, coin.getPosition());

    }

    @Test
    public void coinActive() {
        Position position = new Position(0, 0);

        Coin coin = new Coin(position);

        assertEquals(true, coin.isActive());
        coin.setActive(false);

        assertEquals(false, coin.isActive());

    }
}
