package lpoo312.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private static final int PIPE_WIDTH = 4;
    private static final int GAME_SPEED = 1;
    private static final int LIMIT_HEIGHT = 1;
    private static final int FIXED_GAP_HEIGHT = 9;
    private static final int RANDOM_GAP_HEIGHT = 4;
    private static final int FIXED_UPPIPE_HEIGHT = 2;
    private static final int RANDOM_UPPIPE_HEIGHT = 12;
    private static final int PIPE_MIN_DISTANCE = 20;
    private static final int COIN_MIN_HEIGHT = 5;

    Position position = new Position(11, 10);
    Bird bird = new Bird(position);
    Score score = new Score(new Position(0, 0));
    Background background;

    private int width;
    private int height;
    private List<Limit> limits;
    private List<Pipe> pipes = new ArrayList<>();
    private List<Coin> coins = new ArrayList<>();
    private boolean[][] occupiedSpace;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.limits = createLimits();
        occupiedSpace = new boolean[height][width];
        background = new Background(new Position(0, 0), width, height);

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                occupiedSpace[i][j] = false;
    }

    public static int getGameSpeed() {
        return GAME_SPEED;
    }

    public static int getPipeMinDistance() {
        return PIPE_MIN_DISTANCE;
    }

    public static int getPipeWidth() { return PIPE_WIDTH; }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int arrayPipeSize() {
        return pipes.size();
    }

    public int arrayLimitSize() {
        return limits.size();
    }

    public int arrayCoinSize() {
        return coins.size();
    }

    public Position getPosition() {
        return position;
    }

    public Bird getBird() {
        return bird;
    }

    public Score getScore() {
        return score;
    }

    /**
     * Creates the map movement by moving the pipes on the screen to the left
     */
    public void moveMap() {
        updatePipes();
        updateLimits();
        updateCoins();
    }

    public void updatePipes() {
        movePipes();
        deactivateInvalidPipes();
    }

    public void updateCoins() {
        moveCoins();
        deactivateInvalidCoins();
    }

    public void updateLimits() {
        moveLimits();
        deactivateInvalidLimits();
        updateExistingLimits();
    }

    public void movePipes() {
        for (int i = 0; i < pipes.size(); i++) {
            int newX = pipes.get(i).getPosition().getX() - GAME_SPEED;

            Position position = new Position(newX, pipes.get(i).getPosition().getY());
            pipes.get(i).setPosition(position);

            if (pipes.get(i).getPosition().getX() == PIPE_WIDTH) {
                score.increaseScore();
            }
        }
    }

    /**
     * Removes pipes that are longer visible
     */
    public void deactivateInvalidPipes() {
        for (int i = 0; i < pipes.size(); i++)
            if (pipes.get(i).getPosition().getX() < -PIPE_WIDTH) {
                pipes.get(i).setActive(false);
            }
    }

    public void moveCoins() {
        for (int i = 0; i < coins.size(); i++) {
            int newX = coins.get(i).getPosition().getX() - GAME_SPEED;

            Position position = new Position(newX, coins.get(i).getPosition().getY());
            coins.get(i).setPosition(position);
        }
    }

    public void deactivateInvalidCoins() {
        for (int i = 0; i < coins.size(); i++)
            if (coins.get(i).getPosition().getX() < 0) {
                coins.get(i).setActive(false);
            }
    }

    /**
     * Creates the map movement by moving the limits on the screen to the left
     */
    public void moveLimits() {
        for (int i = 0; i < limits.size(); i++) {
            int newX = limits.get(i).getPosition().getX() - GAME_SPEED;

            Position position = new Position(newX, limits.get(i).getPosition().getY());
            limits.get(i).setPosition(position);
        }
    }

    /**
     * Removes limits that are longer visible
     */
    public void deactivateInvalidLimits() {
        for (int i = 0; i < limits.size(); i++)
            if (limits.get(i).getPosition().getX() < 0) {
                limits.get(i).setActive(false);
            }
    }

    /**
     * For each possible map coordinate, a 2D array is filled to know whether a certain coordinate is occupied
     *
     * @return
     */
    public boolean entersOccupiedSpace() {
        resetMapOccupation();
        setPipesOccupation();
        setLimitsOccupation();

        return setBirdOccupation();
    }

    private boolean setBirdOccupation() {
        int bX = bird.getPosition().getX();
        int bY = bird.getPosition().getY() + 1;

        for (int i = bY; i < bY + 2; i++)           //     p
            for (int j = bX - 3; j < bX + 2; j++)   // # # # #  the bird is a rectangle
                if (!occupiedSpace[i][j]) {         // # # # #  and 'p' as its position
                    occupiedSpace[i][j] = true;
                } else {
                    return true;
                }
        return false;
    }

    private void setPipesOccupation() {
        for (Pipe pipe : pipes)
            for (int i = 0; i < pipe.getHeight(); i++)
                for (int j = 0; j < pipe.getWidth(); j++)
                    if (pipe.getPosition().getX() >= 0)
                        occupiedSpace[pipe.getPosition().getY() + i * pipe.getOption()][pipe.getPosition().getX() + j] = true;
    }

    private void setLimitsOccupation() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                occupiedSpace[0][i] = true;
                occupiedSpace[height - 1][i] = true;
            }
    }

    private void resetMapOccupation() {
        for (int i = 1; i < height - 1; i++)
            for (int j = 0; j < width; j++)
                occupiedSpace[i][j] = false;
    }

    public void gravityBird() {
        bird.dive();
    }

    public void moveBird() {
        bird.fly();
    }

    public void computePipes() {
        Random random = new Random();
        int upPipe, downPipe, gap;

        gap = random.nextInt(RANDOM_GAP_HEIGHT) + FIXED_GAP_HEIGHT;
        upPipe = random.nextInt(RANDOM_UPPIPE_HEIGHT) + FIXED_UPPIPE_HEIGHT;
        downPipe = height - upPipe - gap;
        Position p = new Position(width - PIPE_WIDTH, upPipe);
        Position p1 = new Position(width - PIPE_WIDTH, height - downPipe);

        if (updateExistingPipes(upPipe, downPipe, p, p1)) return;
        createNewPipes(upPipe, downPipe, p, p1);
    }

    private boolean updateExistingPipes(int upPipe, int downPipe, Position p, Position p1) {
        for (int i = 0; i < pipes.size(); i++) {
            if (!pipes.get(i).isActive()) {
                pipes.get(i).setPipe(p, upPipe);
                pipes.get(i).setActive(true); // top limit
                pipes.get(i + 1).setPipe(p1, downPipe - 1);
                pipes.get(i + 1).setActive(true); // bot limit
                return true;
            }
        }
        return false;
    }

    private void createNewPipes(int upPipe, int downPipe, Position p, Position p1) {
        pipes.add(new Pipe(p, upPipe, PIPE_WIDTH, -1)); // up
        pipes.add(new Pipe(p1, downPipe - 1, PIPE_WIDTH, 1)); // down
    }

    public void caughtCoin() {
        int bX = bird.getPosition().getX();
        int bY = bird.getPosition().getY() + 1;         //     p
        for (int i = bY; i < bY + 2; i++) {             // # # # #  the bird is a rectangle
            for (int j = bX - 3; j < bX + 2; j++) {     // # # # #  the bird is a rectangle
                Position birdP = new Position(j, i);
                for (int k = 0; k < coins.size(); k++) {
                    if (coins.get(k).isActive()) {
                        if (coins.get(k).getPosition().equals(birdP)) {
                            coins.get(k).setActive(false);
                            score.increaseScore();
                            return;
                        }
                        break;
                    }
                }
            }
        }
    }

    public void computeCoins() {
        Random random = new Random();
        int posX = random.nextInt(PIPE_MIN_DISTANCE) + width;
        int posY = random.nextInt(height - 2 * COIN_MIN_HEIGHT) + COIN_MIN_HEIGHT;
        Position p = new Position(posX, posY);

        if (updateExistingCoins(p)) return;

        coins.add(new Coin(p));
    }

    private boolean updateExistingCoins(Position p) {
        for (int i = 0; i < coins.size(); i++) {
            if (!coins.get(i).isActive()) {
                coins.get(i).setActive(true);
                coins.get(i).setPosition(p);
                return true;
            }
        }

        return false;
    }

    public void updateExistingLimits() {
        for (int i = 0; i < limits.size(); i++) {
            if (!limits.get(i).isActive()) {
                limits.get(i).setPosition(new Position(width - 1, 0));
                limits.get(i).setActive(true); // top Limit
                limits.get(i + 1).setPosition(new Position(width - 1, height - 1));
                limits.get(i + 1).setActive(true); // bot Limit
                return;
            }
        }
    }

    public List<Limit> createLimits() {
        List<Limit> limits = new ArrayList<>();

        for (int c = 0; c < width; c += 2) {
            Position topPosition = new Position(c, 0);
            Position botPosition = new Position(c, height - LIMIT_HEIGHT);
            limits.add(new Limit(topPosition));
            limits.add(new Limit(botPosition));
        }

        return limits;
    }


    public void addPipe(Pipe pipe) {
        pipes.add(pipe);
    }

    public void addCoin(Coin coin) {
        coins.add(coin);
    }

    public void addLimit(Limit limit) {
        limits.add(limit);
    }

    public List<Pipe> getPipes() {
        return pipes;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public List<Limit> getLimits() {
        return limits;
    }

    public void draw() {
        background.draw();

        for (int i = 0; i < limits.size(); i++)
            if (limits.get(i).isActive()) {
                limits.get(i).draw();
            }

        for (int i = 0; i < pipes.size(); i++)
            if (pipes.get(i).isActive()) {
                pipes.get(i).draw();
            }

        for (int i = 0; i < coins.size(); i++)
            if (coins.get(i).isActive()) {
                coins.get(i).draw();
            }

        //score.draw();
        bird.draw();
    }
}

