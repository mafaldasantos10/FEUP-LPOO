package lpoo312.Game;

public class Score extends Element {
    private static final int SCORE_PER_PIPE = 5;

    private static final int SCORE_PER_COIN = 5;
    private int scoreTracker;

    public Score(Position p) {
        super(p);
    }

    public static int getScorePerCoin() {
        return SCORE_PER_COIN;
    }

    public static int getScorePerPipe() {
        return SCORE_PER_PIPE;
    }

    public int getScoreTracker() {
        return scoreTracker;
    }

    /**
     * Increases the current score by a defined SCORE_PER_PIPE value
     */
    public void increaseScore() {
        scoreTracker += SCORE_PER_PIPE;
    }

    @Override
    public void setDrawingParameters() {
        drawObject = FlappyBird.getInstance().getDrawingFactory().getScoreDrawingElement();
        drawObject.setDrawingParameters(position, scoreTracker);
    }
}
