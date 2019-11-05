package lpoo312.Game;

public class Bird extends Element {
    private static final int FALL_PACE = 1;
    private static final int FLY_PACE = 4;

    public Bird(Position position) {
        super(position);
    }

    public static int getFlyPace() {
        return FLY_PACE;
    }

    public static int getFallPace() {
        return FALL_PACE;
    }

    public Position getPosition() {
        return super.getPosition();
    }

    public void setPosition(Position position) {
        super.setPosition(position);
    }

    /**
     * Simulates the gravity by decreasing the bird's position vertically by a given FALL_PACE value
     */
    public void dive() {
        Position newPosition = new Position(super.getPosition().getX(), super.getPosition().getY() + FALL_PACE);
        super.setPosition(newPosition);
    }

    /**
     * Simulates fly movement of the bird by increasing the bird's position vertically by a given FLY_PACE value
     */
    public void fly() {
        Position newPosition = new Position(super.getPosition().getX(), super.getPosition().getY() - FLY_PACE);
        super.setPosition(newPosition);
    }

    public void setDrawingParameters() {
        drawObject = FlappyBird.getInstance().getDrawingFactory().getBirdDrawingElement();
        drawObject.setDrawingParameters(position);
    }
}
