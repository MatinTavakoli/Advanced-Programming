package components;

import game.GameFrame;
import game.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Teasel {

    private BufferedImage teaselImage;

    private GameFrame game;
    private int x;
    private int y;

    /**
     * constructor for the Teasel class
     * @param game
     * @param x
     * @param y
     */
    public Teasel(GameFrame game, int x, int y){
        this.game = game;
        this.x = x;
        this.y = y;

        try{
            teaselImage = ImageIO.read(new File("src/pictures/teasel.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter for image
     * @return
     */
    public BufferedImage getImage() {
        return teaselImage;
    }

    /**
     * getter for bounds
     * @return
     */
    public Rectangle getBounds(){
        return new Rectangle(x, y,
                teaselImage.getWidth(), teaselImage.getHeight());
    }

    /**
     * checks if tank had collision with teasel
     * @param state game state
     * @return boolean
     */
    public boolean collision(GameState state){
        return game.getTankObject().getBounds(state).intersects(getBounds());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
