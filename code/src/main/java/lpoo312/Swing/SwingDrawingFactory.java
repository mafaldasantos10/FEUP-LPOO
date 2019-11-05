package lpoo312.Swing;

import lpoo312.Game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class SwingDrawingFactory implements AbstractDrawingFactory {

    Graphics graphics;
    Map map;
    GameOver gameOver;

    Image background;
    Image bird;
    Image downPipe;
    Image upPipe;
    Image limit;
    Image GO;
    Image coin;

    JFrame frame;

    boolean keyPressed = false;

    DrawingElement birdDE = null;
    DrawingElement pipeDE = null;
    DrawingElement limitDE = null;
    DrawingElement scoreDE = null;
    DrawingElement gameOverDE = null;
    DrawingElement backGroundDE = null;
    DrawingElement coinDE = null;

    public SwingDrawingFactory(Map m, GameOver go) {
        map = m;
        gameOver = go;
        background = new ImageIcon("src/main/java/Resources/background.png").getImage();
        bird = new ImageIcon("src/main/java/Resources/bird.png").getImage();
        downPipe = new ImageIcon("src/main/java/Resources/downPipe.png").getImage();
        upPipe = new ImageIcon("src/main/java/Resources/upPipe.png").getImage();
        limit = new ImageIcon("src/main/java/Resources/limit.png").getImage();
        GO = new ImageIcon("src/main/java/Resources/gameOver.png").getImage();
        coin = new ImageIcon("src/main/java/Resources/coin.png").getImage();

        frame = new JFrame("FlappyBird");
        //JLabel label = new JLabel("lpoo312.Game.FlappyBird");
        SwingGamePanel panel = new SwingGamePanel(this);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                processKey(e);
            }
        });
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics g) {
        graphics = g;
    }

    @Override
    public DrawingElement getBirdDrawingElement() {
        if (birdDE == null) birdDE = new BirdDrawSwing(this);

        return birdDE;
    }

    @Override
    public DrawingElement getPipeDrawingElement() {
        if (pipeDE == null) pipeDE = new PipeDrawSwing(this);

        return pipeDE;
    }

    @Override
    public DrawingElement getLimitDrawingElement() {
        if (limitDE == null) limitDE = new LimitDrawSwing(this);

        return limitDE;
    }

    @Override
    public DrawingElement getGameOverDrawingElement() {
        if (gameOverDE == null) gameOverDE = new GameOverDrawSwing(this);

        return gameOverDE;
    }

    @Override
    public DrawingElement getScoreDrawingElement() {
        if (scoreDE == null) scoreDE = new ScoreDrawSwing(this);

        return scoreDE;
    }

    @Override
    public DrawingElement getBackgroundDrawingElement() {
        if (backGroundDE == null) backGroundDE = new BackgroundDrawSwing(this);

        return backGroundDE;
    }

    @Override
    public DrawingElement getCoinDrawingElement() {
        if (coinDE == null) coinDE = new CoinDrawSwing(this);

        return coinDE;
    }

    @Override
    public void beginDrawing() {
    }

    @Override
    public void endDrawing() {
    }

    @Override
    public void draw() {
        map.draw();
        map.getScore().draw();
        frame.getContentPane().repaint();
    }

    @Override
    public void drawGameOver() {
        gameOver.draw();
        map.getScore().setPosition(new Position(map.getWidth() * 4, map.getHeight() * 12));
        map.getScore().draw();
        frame.getContentPane().repaint();
    }

    @Override
    public void endGame() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    private void processKey(KeyEvent key) {
        switch (key.getKeyChar()) {
            case '\n':
                keyPressed = true;
                break;
            default:
                break;
        }
    }

    @Override
    public boolean isKeyPressed() {
        return keyPressed;
    }

    @Override
    public void setKeyPressed(boolean keyPressed) {
        this.keyPressed = keyPressed;
    }

    public Image getBGImage() {
        return background;
    }

    public Image getUpPipeImage() {
        return upPipe;
    }

    public Image getDownPipeImage() {
        return downPipe;
    }

    public Image getLimitImage() {
        return limit;
    }

    public Image getBirdImage() {
        return bird;
    }

    public Image getGOImage() {
        return GO;
    }

    public Image getCoinImage() {
        return coin;
    }
}

