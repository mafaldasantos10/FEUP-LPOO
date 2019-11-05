package lpoo312.Tests;

import lpoo312.Game.*;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class MapTest {

    @Test
    public void mapConstructor() {
        int width = 80;
        int height = 24;
        Map map = new Map(width, height);

        assertEquals(width, map.getWidth());
        assertEquals(height, map.getHeight());
        assertEquals(0, map.arrayPipeSize());
        //assertEquals(0, map.getScore());
        assertEquals(width, map.arrayLimitSize());
        //assertEquals(map.getPosition(), map.getBird().getPosition());
        //assertEquals(map.getPosition(), map.getScore().getPosition());
    }

    @Test
    public void birdMovement() {
        Map map = new Map(80, 24);

        assertEquals(10, map.getBird().getPosition().getY());
        map.gravityBird();
        assertEquals(10 + Bird.getFallPace(), map.getBird().getPosition().getY());
        map.moveBird();
        assertEquals(10 + Bird.getFallPace() - Bird.getFlyPace(), map.getBird().getPosition().getY());
    }

    @Test
    public void removeInvalidPipes() {
        Position position = new Position(-5, 0);
        Position position2 = new Position(10, 0);
        Pipe pipe = new Pipe(position, 0, 0, 0);
        Pipe pipe2 = new Pipe(position2, 0, 0, 0);
        Map map = new Map(80, 24);

        map.addPipe(pipe);
        map.deactivateInvalidPipes();

        assertEquals(false, pipe.isActive());

        map.addPipe(pipe2);
        map.deactivateInvalidPipes();

        assertEquals(true, pipe2.isActive());

        pipe2.setPosition(new Position(-6, 0));
        map.updatePipes();
        assertEquals(false, pipe2.isActive());

    }

    @Test
    public void removeInvalidLimits() {
        Position position = new Position(-1, 0);
        Position position2 = new Position(10, 0);
        Limit limit = new Limit(position);
        Limit limit2 = new Limit(position2);
        Map map = new Map(80, 24);

        map.addLimit(limit);
        map.deactivateInvalidLimits();

        assertEquals(false, limit.isActive());

        map.addLimit(limit2);
        map.deactivateInvalidLimits();

        assertEquals(true, limit2.isActive());

        limit2.setPosition(new Position(-4, 0));
        map.updateLimits();

        assertEquals(false, limit2.isActive());
    }

    @Test
    public void movePipe() {
        Position position = new Position(20, 0);
        Position position2 = new Position(30, 0);
        Pipe pipe = new Pipe(position, 0, 0, 0);
        Pipe pipe2 = new Pipe(position2, 0, 0, 0);
        Position p = new Position(pipe.getPosition().getX() - Map.getGameSpeed(), 0);
        Map map = new Map(80, 24);
        map.addPipe(pipe);
        map.movePipes();

        assertEquals(p.getX(), pipe.getPosition().getX());

        Position p2 = new Position(pipe2.getPosition().getX() - Map.getGameSpeed(), 0);
        map.addPipe(pipe2);
        map.moveMap();

        assertEquals(p2.getX(), pipe2.getPosition().getX());
    }

    @Test
    public void moveLimits() {
        Position position = new Position(-1, 0);
        Position position2 = new Position(10, 0);
        Limit limit = new Limit(position);
        Limit limit2 = new Limit(position2);
        Map map = new Map(80, 24);
        Position p = new Position(limit.getPosition().getX() - Map.getGameSpeed(), 0);


        map.addLimit(limit);
        map.moveLimits();

        assertEquals(p.getX(), limit.getPosition().getX());

        Position p2 = new Position(limit2.getPosition().getX() - Map.getGameSpeed(), 0);
        map.addLimit(limit2);
        map.moveMap();

        assertEquals(p2.getX(), limit2.getPosition().getX());
    }

    @Test
    public void moveCoins() {
        Position position = new Position(-1, 0);
        Position position2 = new Position(10, 0);
        Coin coin = new Coin(position);
        Coin coin2 = new Coin(position2);
        Map map = new Map(80, 24);
        Position p = new Position(coin.getPosition().getX() - Map.getGameSpeed(), 0);


        map.addCoin(coin);
        map.moveCoins();

        assertEquals(p.getX(), coin.getPosition().getX());

        Position p2 = new Position(coin2.getPosition().getX() - Map.getGameSpeed(), 0);
        map.addCoin(coin2);
        map.moveMap();

        assertEquals(p2.getX(), coin2.getPosition().getX());
    }

    @Test
    public void removeInvalidCoins() {
        Position position = new Position(-1, 0);
        Position position2 = new Position(10, 0);
        Coin coin = new Coin(position);
        Coin coin2 = new Coin(position2);
        Map map = new Map(80, 24);

        map.addCoin(coin);
        map.updateCoins();

        assertEquals(false, coin.isActive());

        map.addCoin(coin2);
        map.deactivateInvalidCoins();

        assertEquals(true, coin2.isActive());
        coin2.setPosition(new Position(-4, 0));
        map.updateCoins();

        assertEquals(false, coin2.isActive());
    }

    @Test
    public void computePipes() {
        Map map = new Map(80, 24);
        map.computePipes();

        assertTrue(map.arrayPipeSize() == 2);
        //width - PIPE_WIDTH
        assertTrue(map.getPipes().get(0).getPosition().getX() < map.getWidth());

        assertTrue(map.getPipes().get(1).getPosition().getX() < map.getWidth());
    }

    @Test
    public void computeCoins() {
        Map map = new Map(80, 24);
        map.computeCoins();

        assertTrue(map.arrayCoinSize() == 1);
        //width - PIPE_WIDTH
        assertTrue(map.getCoins().get(0).getPosition().getX() < map.getWidth() + Map.getPipeMinDistance());
    }

    @Test
    public void catchingCoins() {
        Map map = new Map(80, 24);
        Position p = new Position(10, 10);
        Coin coin = new Coin(p);

        map.addCoin(coin);
        map.getBird().setPosition(p);
        assertEquals(coin.getPosition(), map.getBird().getPosition());
        int score = map.getScore().getScoreTracker();

        map.caughtCoin();

        assertEquals(true, coin.isActive()); //     p
        // # # # #  the bird is a rectangle
        // # # # #  and 'p' as its position
        map.getBird().setPosition(new Position(10, 9));

        map.caughtCoin();
        assertEquals(false, coin.isActive());

        assertEquals(score + Score.getScorePerCoin(), map.getScore().getScoreTracker());
    }

    @Test
    public void mapInteractions() {
        Map mockMap;
        mockMap = Mockito.mock(Map.class);

        verifyZeroInteractions(mockMap);

        mockMap.computePipes();

        /* goes through entire map */
        for (int i = 0; i < 80; i++)
            mockMap.updatePipes();

        mockMap.movePipes();
        mockMap.deactivateInvalidPipes();

        Mockito.verify(mockMap, times(80)).updatePipes();
        Mockito.verify(mockMap, times(1)).movePipes();
        Mockito.verify(mockMap, times(1)).deactivateInvalidPipes();
    }
}
