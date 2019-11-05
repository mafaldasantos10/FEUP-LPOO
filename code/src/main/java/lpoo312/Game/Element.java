package lpoo312.Game;

public abstract class Element implements Drawable {

    protected DrawingElement drawObject;
    protected Position position;

    public Element(Position p) {
        this.position = p;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void draw() {
        setDrawingParameters();
        if (drawObject == null) return;
        drawObject.draw();
    }
}
