package components;

import game.GameFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Soil {

    private BufferedImage soilImage;

    private GameFrame game;
    private int x;
    private int y;

    /**
     * constructor for the Soil class
     * @param game game frame
     * @param x x
     * @param y y
     */
    public Soil(GameFrame game, int x, int y){
        this.game = game;
        this.x = x;
        this.y = y;

        try{
            soilImage = ImageIO.read(new File("src/pictures/soil1.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * getter for image
     * @return image
     */
    public BufferedImage getImage() {
        return soilImage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
