package components;

import game.GameFrame;
import game.GameState;
import main.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Key {

    private BufferedImage keyImage;

    private GameFrame game;
    private int x;
    private int y;

    /**
     * constructor for the class Key
     * @param game game frame
     * @param x x
     * @param y y
     */
    public Key(GameFrame game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;

        try {
            keyImage = ImageIO.read(new File("src/pictures/key.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * getter for image
     * @return image
     */
    public BufferedImage getImage() {
        return keyImage;
    }

    /**
     * getter for bounds
     * @return bounds
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, keyImage.getWidth(), keyImage.getHeight());
    }

    /**
     * checks if key had collision with tank
     * @param state game state
     * @return boolean
     */
    public boolean collision(GameState state) {
        return game.getTankObject().getBounds(state).intersects(getBounds());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

