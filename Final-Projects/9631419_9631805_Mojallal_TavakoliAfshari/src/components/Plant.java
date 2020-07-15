package components;

import game.GameFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Plant{

    private BufferedImage plantImage;

    private GameFrame game;
    private int x;
    private int y;

    /**
     * constructor for the Plant class
     * @param game game frame
     * @param x x
     * @param y y
     */
    public Plant(GameFrame game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;

        try {
            plantImage = ImageIO.read(new File("src/pictures/plant.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter for image
     * @return image
     */
    public BufferedImage getImage() {
        return plantImage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
