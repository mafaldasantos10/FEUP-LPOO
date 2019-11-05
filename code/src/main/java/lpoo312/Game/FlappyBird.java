package lpoo312.Game;

import lpoo312.Lanterna.LanternaDrawingFactory;
import lpoo312.Swing.SwingDrawingFactory;

import java.io.IOException;
import java.util.Random;

public class FlappyBird {
    private static final float MAP_SPEED = 40F;
    private static final float BIRD_FALLING_SPEED = 100F;
    private static final float FIXED_PIPE_DISTANCE = 1100F;
    private static final int RANDOM_PIPE_DISTANCE = 600;
    private static final int GAME_OVER_WAITING_TIME = 1000;
    float randomTime = FIXED_PIPE_DISTANCE;

    static private String factory = null;
    static private FlappyBird instance = null;
    AbstractDrawingFactory adf;

    Map map = new Map(80, 24);
    GameOver gameOver = new GameOver(new Position(12, map.getHeight() / 2 - 8), map.getWidth(), map.getHeight());
    gameState gState = gameState.Start;

    private FlappyBird() {
        if (factory.equals("S") || factory.equals("s")) {
            adf = new SwingDrawingFactory(map, gameOver);
        } else {
            System.out.println("Factory: " + factory);
            adf = new LanternaDrawingFactory(map, gameOver);
        }
    }

    public static void setFactory(String factory) {
        FlappyBird.factory = factory;
    }

    static public FlappyBird getInstance() {
        if (instance == null)
            instance = new FlappyBird();
        return instance;
    }

    public static float getBirdFallingSpeed() {
        return BIRD_FALLING_SPEED;
    }

    public AbstractDrawingFactory getDrawingFactory() {
        return adf;
    }

    private void finishGame() {
        adf.beginDrawing();
        adf.draw();
        gState = gameState.GameOver;
        adf.drawGameOver();
        adf.endDrawing();

        try {
            Thread.sleep(GAME_OVER_WAITING_TIME);
        } catch (InterruptedException e) {
        }

        adf.endGame();
    }

    public void run() throws IOException {
        Random random = new Random();

        long startDraw = System.currentTimeMillis(),
                createPipe = System.currentTimeMillis(),
                moveBird = System.currentTimeMillis();

        do {
            startDraw = updateMap(startDraw);

            if (gState == gameState.Play) {
                moveBird = updateBird(moveBird);
                createPipe = updatePipes(random, createPipe);
                map.caughtCoin();
                if (map.entersOccupiedSpace()) {
                    finishGame();
                    break;
                }
            }

            if (adf.isKeyPressed()) {
                gState = gameState.Play;
                map.moveBird();
                adf.setKeyPressed(false);
            }

        } while (true);
    }

    public long updatePipes(Random random, long createPipe) {
        if (System.currentTimeMillis() - createPipe > randomTime) {
            map.computePipes();

            int rand = random.nextInt(2);
            if (rand == 0) {
                map.computeCoins();

            }
            createPipe = System.currentTimeMillis();
            randomTime = random.nextInt(RANDOM_PIPE_DISTANCE) + FIXED_PIPE_DISTANCE;
        }
        return createPipe;
    }

    public long updateBird(long moveBird) {
        if (System.currentTimeMillis() - moveBird > BIRD_FALLING_SPEED) {
            moveBird = System.currentTimeMillis();
            map.gravityBird();
        }
        return moveBird;
    }

    public long updateMap(long startDraw) {
        if (System.currentTimeMillis() - startDraw > MAP_SPEED) {
            startDraw = System.currentTimeMillis();
            draw();
            map.moveMap();
        }
        return startDraw;
    }

    private void beginDrawing() {
        adf.beginDrawing();
    }

    private void endDrawing() {
        adf.endDrawing();
    }

    private void draw() {
        beginDrawing();
        adf.draw();
        endDrawing();
    }

    public gameState getGameState() {
        return gState;
    }

    public enum gameState {
        Start,
        Play,
        GameOver
    }
}
