package lpoo312.Game;

public class Limit extends Element {

    private boolean active;

    public Limit(Position p) {
        super(p);
        this.active = true;
    }

    public Position getPosition() {
        return super.getPosition();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDrawingParameters() {
        drawObject = FlappyBird.getInstance().getDrawingFactory().getLimitDrawingElement();
        drawObject.setDrawingParameters(position);
    }
}