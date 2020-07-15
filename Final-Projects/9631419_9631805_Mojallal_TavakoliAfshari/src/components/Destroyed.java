package components;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Destroyed {

    private BufferedImage destroyedImage;

    private int x;
    private int y;

    /**
     * constructor for the class Destroyed
     * @param x x
     * @param y y
     */
    public Destroyed(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            destroyedImage = ImageIO.read(new File("src/pictures/destroyed.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * getter for image
     * @return image
     */
    public BufferedImage getImage() {
        return destroyedImage;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
