package components;

import game.GameFrame;
import game.GameState;
import main.AudioPlayer;
import main.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wall {

    private BufferedImage wallImage;

    private GameFrame game;
    private int x;
    private int y;

    public Wall(GameFrame game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;

        try {
            wallImage = ImageIO.read(new File("src/pictures/wall.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getImage() {
        return wallImage;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, wallImage.getWidth(), wallImage.getHeight());
    }

    public boolean collision(GameState state) {
        return game.getTankObject().getBounds(state).intersects(getBounds());
    }

    public void missileCollision(GameState state){
        for (int i = 0; i < state.getMissiles().size(); i++) {
            if (state.getMissiles().get(i).getBounds().intersects(getBounds())) {
                state.getMissiles().remove(state.getMissiles().get(i));
                AudioPlayer wallCollisionSFX=new AudioPlayer("sound effects/recosh.wav",0);
            }
        }
    }

    public void bulletCollision(GameState state) {
        for (int i = 0; i < state.getBullets().size(); i++) {
            if (state.getBullets().get(i).getBounds().intersects(getBounds())) {
                state.getBullets().remove(state.getBullets().get(i));
                AudioPlayer wallCollisionSFX=new AudioPlayer("sound effects/recosh.wav",0);
            }
        }
    }

    public void enemyMissileCollision(GameState state){
        for (int i = 0; i < state.getEnemyMissiles().size(); i++) {
            if (state.getEnemyMissiles().get(i).getBounds(state).intersects(getBounds())) {
                state.getEnemyMissiles().remove(state.getEnemyMissiles().get(i));
                AudioPlayer wallCollisionSFX=new AudioPlayer("sound effects/recosh.wav",0);
            }
        }
    }

    public void enemyBulletCollision(GameState state) {
        for (int i = 0; i < state.getEnemyBullets().size(); i++) {
            if (state.getEnemyBullets().get(i).getBounds(state).intersects(getBounds())) {
                state.getEnemyBullets().remove(state.getEnemyBullets().get(i));
                AudioPlayer wallCollisionSFX=new AudioPlayer("sound effects/recosh.wav",0);
            }
        }
    }

    public void enemyHomingMissileCollision(GameState state) {
        for (int i = 0; i < state.getEnemyHomingMissiles().size(); i++) {
            if (state.getEnemyHomingMissiles().get(i).getBounds(state).intersects(getBounds())) {
                state.getEnemyHomingMissiles().remove(state.getEnemyHomingMissiles().get(i));
                AudioPlayer wallCollisionSFX=new AudioPlayer("sound effects/recosh.wav",0);
            }
        }
    }

    public void enemyWallTurretMissileCollision(GameState state) {
        for (int i = 0; i < state.getEnemyWallTurretMissiles().size(); i++) {
            if (state.getEnemyWallTurretMissiles().get(i).getBounds(state).intersects(getBounds())) {
                state.getEnemyWallTurretMissiles().remove(state.getEnemyWallTurretMissiles().get(i));
                AudioPlayer wallCollisionSFX=new AudioPlayer("sound effects/recosh.wav",0);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
