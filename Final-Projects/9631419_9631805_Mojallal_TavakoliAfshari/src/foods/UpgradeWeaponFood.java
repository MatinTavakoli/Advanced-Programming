package foods;

import game.GameFrame;
import game.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UpgradeWeaponFood {

    private BufferedImage upgradeWeaponFoodImage;

    private GameFrame game;
    private int x;
    private int y;

    /**
     * constructor for the upgrade weapon class
     * @param game game frame
     * @param x x
     * @param y y
     */
    public UpgradeWeaponFood(GameFrame game, int x, int y){
        this.game = game;
        this.x = x;
        this.y = y;

        try{
            upgradeWeaponFoodImage = ImageIO.read(new File("src/pictures/star.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter for image
     * @return image
     */
    public BufferedImage getImage() {
        return upgradeWeaponFoodImage;
    }

    /**
     * getter for bounds
     * @return bounds
     */
    public Rectangle getBounds(){
        return new Rectangle(x, y,
                upgradeWeaponFoodImage.getWidth(), upgradeWeaponFoodImage.getHeight());
    }

    /**
     * checks if upgrade weapon food had collision with our tank
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
