package foods;

import game.GameFrame;
import game.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CannonFood {

    private BufferedImage cannonFoodImage;

    private GameFrame game;
    private int x;
    private int y;

    /**
     * constructor for the cannon food class
     * @param game game frame
     * @param x x
     * @param y y
     */
    public CannonFood(GameFrame game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;

        try {
            cannonFoodImage = ImageIO.read(new File("src/pictures/cannonFood.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter for image
     * @return image
     */
    public BufferedImage getImage() {
        return cannonFoodImage;
    }

    /**
     * getter for bounds
     * @return bounds
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y,
                cannonFoodImage.getWidth(), cannonFoodImage.getHeight());
    }

    /**
     * checks if cannon food had collision with our tank
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
