package main;

import game.GameFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *the number of bullets that is shown in the up-left corner of the frame!
 */
public class NumOfBullet {

    private BufferedImage cannonBulletImage;
    private BufferedImage machineGunBulletImage;

    private BufferedImage healthImage;
    private BufferedImage lifeImage;

    private GameFrame game;

    /**
     * numOfBullet's constructor
     * @param game we need a game for our constructor
     */
    public NumOfBullet(GameFrame game){
        this.game = game;

        try {
            cannonBulletImage = ImageIO.read(new File("src/pictures/numberOfCannonBullet.png"));
            machineGunBulletImage = ImageIO.read(new File("src/pictures/numberOfMachineGunBullet.png"));
            healthImage = ImageIO.read(new File("src/pictures/health.png"));
            lifeImage = ImageIO.read(new File("src/pictures/life.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * a method that paints our cannonBullets image!
     * @param graphics2D the graphics required for our drawing
     */
    public void paintCannonBullet(Graphics2D graphics2D) {

        graphics2D.drawImage(cannonBulletImage, 10, 30, null);

        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(graphics2D.getFont().deriveFont(18.0f));
        graphics2D.drawString(String.valueOf(game.getTankObject().getNumOfCannonBullet()), 40, 80);
    }

    /**
     * a method that paints our machinegunBullets image!
     * @param graphics2D the graphics required for our drawing
     */
    public void paintMachineGunBullet(Graphics2D graphics2D) {

        graphics2D.drawImage(machineGunBulletImage, 10, 100, null);

        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(graphics2D.getFont().deriveFont(18.0f));
        graphics2D.drawString(String.valueOf(game.getTankObject().getNumOfMachineGunBullet()), 40, 155);
    }

    /**
     * a method that pains the green health bars!
     * @param graphics2D the graphics required for our drawing
     */
    public void paintHealth(Graphics2D graphics2D){

        for (int i = 0; i < game.getTankObject().getHealth(); i++) {
            graphics2D.drawImage(healthImage, 480 + i * 40, 80, null);
        }
    }

    /**
     * a method that pains the green health lives!
     * @param graphics2D the graphics required for our drawing
     */
    public void paintLife(Graphics2D graphics2D){
        for (int i = 0; i < game.getTankObject().getLife(); i++) {
            graphics2D.drawImage(lifeImage, 1000 + i * 30, 50, null);
        }
    }
}
