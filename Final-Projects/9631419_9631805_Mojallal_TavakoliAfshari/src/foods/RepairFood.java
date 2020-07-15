package foods;

import game.GameFrame;
import game.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RepairFood {

    private BufferedImage repairFoodImage;

    private GameFrame game;
    private int x;
    private int y;

    /**
     * constructor for the repair food class
     * @param game game frame
     * @param x x
     * @param y y
     */
    public RepairFood(GameFrame game, int x, int y){
        this.game = game;
        this.x = x;
        this.y = y;

        try{
            repairFoodImage = ImageIO.read(new File("src/pictures/repairFood.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter for image
     * @return image
     */
    public BufferedImage getImage() {
        return repairFoodImage;
    }

    /**
     * getter for bounds
     * @return bounds
     */
    public Rectangle getBounds(){
        return new Rectangle(x, y,
                repairFoodImage.getWidth(), repairFoodImage.getHeight());
    }

    /**
     * checks if repair food had collision with our tank
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
