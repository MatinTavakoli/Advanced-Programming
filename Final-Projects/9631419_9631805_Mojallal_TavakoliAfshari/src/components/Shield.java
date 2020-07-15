package components;

import game.GameFrame;
import game.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Shield {

    private BufferedImage shieldImage;

    private GameFrame game;
    private int x;
    private int y;

    /**
     * constructor for the shield class
     * @param game game frame
     * @param x x
     * @param y y
     */
    public Shield(GameFrame game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;

        try {
            shieldImage = ImageIO.read(new File("src/pictures/shield.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * getter for image
     * @return image
     */
    public BufferedImage getImage() {
        return shieldImage;
    }

    /**
     * getter for bounds
     * @return bounds
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, shieldImage.getWidth(), shieldImage.getHeight());
    }

    /**
     * checks if tank had collision with shield
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
