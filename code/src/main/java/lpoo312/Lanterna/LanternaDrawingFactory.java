package lpoo312.Lanterna;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import lpoo312.Game.*;

import java.io.IOException;

public class LanternaDrawingFactory implements AbstractDrawingFactory {

    TextGraphics tg;
    Map map;
    GameOver gameOver;

    DrawingElement birdDE = null;
    DrawingElement pipeDE = null;
    DrawingElement limitDE = null;
    DrawingElement scoreDE = null;
    DrawingElement gameOverDE = null;
    DrawingElement backGroundDE = null;
    DrawingElement coinDE = null;
    private Screen screen;

    public LanternaDrawingFactory(Map m, GameOver go) {
        map = m;
        gameOver = go;
        Terminal terminal = null;
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
            tg = screen.newTextGraphics();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DrawingElement getBirdDrawingElement() {
        if (birdDE == null) birdDE = new BirdDrawLanterna(tg);

        return birdDE;
    }

    @Override
    public DrawingElement getPipeDrawingElement() {
        if (pipeDE == null) pipeDE = new PipeDrawLanterna(tg);

        return pipeDE;
    }

    @Override
    public DrawingElement getLimitDrawingElement() {
        if (limitDE == null) limitDE = new LimitDrawLanterna(tg);

        return limitDE;
    }

    @Override
    public DrawingElement getGameOverDrawingElement() {
        if (gameOverDE == null) gameOverDE = new GameOverDrawLanterna(tg);

        return gameOverDE;
    }

    @Override
    public DrawingElement getScoreDrawingElement() {
        if (scoreDE == null) scoreDE = new ScoreDrawLanterna(tg);

        return scoreDE;
    }

    @Override
    public DrawingElement getBackgroundDrawingElement() {
        if (backGroundDE == null) backGroundDE = new BackgroundDrawLanterna(tg);

        return backGroundDE;
    }

    @Override
    public DrawingElement getCoinDrawingElement() {
        if (coinDE == null) coinDE = new CoinDrawLanterna(tg);

        return coinDE;
    }

    @Override
    public void beginDrawing() {
        screen.clear();
    }

    @Override
    public void endDrawing() {
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {
        map.draw();
        map.getScore().draw();
    }

    @Override
    public void drawGameOver() {
        gameOver.draw();
        map.getScore().setPosition(new Position(map.getWidth() / 2 - 6, map.getHeight() - 8));
        map.getScore().draw();
    }

    @Override
    public void endGame() {
        try {
            screen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case Enter:
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean isKeyPressed() throws IOException {
        KeyStroke key;
        key = screen.pollInput();

        if (key != null && (key.getKeyType() == KeyType.Escape || key.getKeyType() == KeyType.EOF)) {
            endGame();
            return true;
        } else if (key != null) {
            return processKey(key);
        }
        return false;
    }

    @Override
    public void setKeyPressed(boolean keyPressed) {
    }
}
