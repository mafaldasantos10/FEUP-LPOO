package lpoo312.Game;

public class GameOver extends Element {

    private int width;
    private int height;

    public GameOver(Position p, int width, int height) {
        super(p);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setDrawingParameters() {
        drawObject = FlappyBird.getInstance().getDrawingFactory().getGameOverDrawingElement();
        drawObject.setDrawingParameters(position, width, height);
    }
}