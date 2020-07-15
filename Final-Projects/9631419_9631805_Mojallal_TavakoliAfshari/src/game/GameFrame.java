package game;

import enemy.EnemyBullet;
import enemy.EnemyHomingMissile;
import enemy.EnemyMissile;
import enemy.EnemyWallTurretMissile;
import main.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public static final int GAME_HEIGHT = 700;
    public static final int GAME_WIDTH = 1200;

    public static final int tankXStart = 400;
    public static final int tankYStart = 300;

    public static int difficulty;

    private BufferedImage gameOverImage;
    private BufferedImage gameWonImage;

    private long lastRender;
    private ArrayList<Float> fpsHistory;

    private BufferStrategy bufferStrategy;

    private BufferedImage plantImage;

    private int numOfRenderMove = 0;
    private int numOfRenderShoot = 0;
    private int numOfRenderBullet1 = 0;
    private int numOfRenderBullet2 = 0;

    //main objects
    private Tank tankObject = new Tank(this);
    private Observer observerObject = new Observer(this);
    private NumOfBullet numOfBulletObject = new NumOfBullet(this);
    private Map mapObject;

    private int flag = 1;

    /**
     * constructor for the game frame class
     * @param title game title
     * @param difficulty difficulty
     * @param mapFileName map file name
     */
    public GameFrame(String title, int difficulty, String mapFileName) {
        super(title);
        setResizable(false);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        this.difficulty = difficulty;
        mapObject = new Map(mapFileName, this);
        lastRender = -1;
        fpsHistory = new ArrayList<>(100);

        try {
            plantImage = ImageIO.read(new File("src/pictures/plant.png"));
            gameOverImage = ImageIO.read(new File("src/pictures/gameOver.png"));
            gameWonImage = ImageIO.read(new File("src/pictures/youWin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * initializes the buffer strategy
     */
    public void initBufferStrategy() {
        // Triple-buffering
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
    }

    /**
     * builds up the rendering
     * @param state game state
     */
    public void render(GameState state) {
        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    doRendering(graphics, state);
                } finally {
                    // Dispose the graphics
                    graphics.dispose();
                }
                // Repeat the rendering if the drawing buffer contents were restored
            } while (bufferStrategy.contentsRestored());

            // Display the buffer
            bufferStrategy.show();
            // Tell the system to do the drawing NOW;
            // otherwise it can take a few extra ms and will feel jerky!
            Toolkit.getDefaultToolkit().sync();

            // Repeat the rendering if the drawing buffer was lost
        } while (bufferStrategy.contentsLost());
    }

    /**
     * method for rendering
     * @param g2d graphics2D
     * @param state game state
     */
    private void doRendering(Graphics2D g2d, GameState state) {

        if (!state.isGameOver()) {

            try {

                if (numOfRenderMove == 5) {
                    numOfRenderMove = 0;
                    updateEnemies(state);
                }

                //map
                if (flag == 1) {
                    mapObject.semiDraw();
                    flag = 0;
                }
                mapObject.draw(g2d, state);

                //observer
                observerObject.observe(state);

                //paint tank body
                tankObject.paintBody(g2d, state);
                tankObject.enemyMissileCollision(state);
                tankObject.enemyBulletCollision(state);
                tankObject.enemyWallTurretMissileCollision(state);
                tankObject.enemyHomingMissileCollision(state);

                //paint tank guns
                paintTankGuns(g2d, state);

                //paint all enemies
                paintAllEnemies(g2d, state);

                //enemies will shoot
                enemiesWillShoot(g2d, state);

                //tank will shoot
                tankWillShoot(g2d, state);

                //plants
                paintPlants(g2d, state);

                //fps & target
                fps(g2d);
                tankObject.paintTarget(g2d, getMousePosition().x, getMousePosition().y);

                numOfRenderMove++;
                numOfRenderShoot++;

            } catch (NullPointerException e) {

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        if (state.isGameOver() && !state.isGameWon()) {
            g2d.drawImage(gameOverImage, 0, 0, null);
        }
        if (state.isGameOver() && state.isGameWon()) {
            g2d.drawImage(gameWonImage, 0, 0, null);
        }

    }

    /**
     * method to make enemies shoot
     * @param g2d graphics2D
     * @param state game state
     */
    private void enemiesWillShoot(Graphics2D g2d, GameState state) {
        if (numOfRenderShoot == 75) {
            numOfRenderShoot = 0;
            for (int i = 0; i < state.getEnemyTanks().size(); i++) {
                if (state.getEnemyTanks().get(i).getLocY() < state.getTankLocY() + 500 && state.getEnemyTanks().get(i).getLocY() > state.getTankLocY() - 400 &&
                        state.getEnemyTanks().get(i).getLocX() < state.getTankLocX() + 800 && state.getEnemyTanks().get(i).getLocX() > state.getTankLocX() - 500) {
                    state.getEnemyMissiles().add(new EnemyMissile(this, state, 10
                            , state.getEnemyTanks().get(i)));
                    state.getEnemyMissiles().get(state.getEnemyMissiles().size() - 1).missileDirection(state);
                    AudioPlayer enemyGunShotSFX = new AudioPlayer("sound effects/enemyshot.wav", 0);
                }
            }
            for (int i = 0; i < state.getEnemyCars().size(); i++) {
                if (state.getEnemyCars().get(i).getLocY() < state.getTankLocY() + 500 && state.getEnemyCars().get(i).getLocY() > state.getTankLocY() - 400 &&
                        state.getEnemyCars().get(i).getLocX() < state.getTankLocX() + 800 && state.getEnemyCars().get(i).getLocX() > state.getTankLocX() - 500) {
                    state.getEnemyBullets().add(new EnemyBullet(this, state, 20
                            , state.getEnemyCars().get(i)));
                    state.getEnemyBullets().get(state.getEnemyBullets().size() - 1).bulletDirection(state);
                    AudioPlayer enemyGunShotSFX = new AudioPlayer("sound effects/enemyshot.wav", 0);
                }
            }
            for (int i = 0; i < state.getEnemyWallTurrets().size(); i++) {
                if (state.getEnemyWallTurrets().get(i).getLocY() < state.getTankLocY() + 500 && state.getEnemyWallTurrets().get(i).getLocY() > state.getTankLocY() - 400 &&
                        state.getEnemyWallTurrets().get(i).getLocX() < state.getTankLocX() + 800 && state.getEnemyWallTurrets().get(i).getLocX() > state.getTankLocX() - 500) {
                    state.getEnemyWallTurretMissiles().add(new EnemyWallTurretMissile(this, state, 15
                            , state.getEnemyWallTurrets().get(i)));
                    state.getEnemyWallTurretMissiles().get(state.getEnemyWallTurretMissiles().size() - 1).missileDirection(state);
                    AudioPlayer enemyGunShotSFX = new AudioPlayer("sound effects/enemyshot.wav", 0);
                }
            }

            for (int i = 0; i < state.getEnemyTurrets().size(); i++) {
                if (state.getEnemyTurrets().get(i).getLocY() < state.getTankLocY() + 500 && state.getEnemyTurrets().get(i).getLocY() > state.getTankLocY() - 400 &&
                        state.getEnemyTurrets().get(i).getLocX() < state.getTankLocX() + 800 && state.getEnemyTurrets().get(i).getLocX() > state.getTankLocX() - 500) {
                    state.getEnemyHomingMissiles().add(new EnemyHomingMissile(this, state, 15
                            , state.getEnemyTurrets().get(i)));
                    state.getEnemyHomingMissiles().get(state.getEnemyHomingMissiles().size() - 1).missileDirection(state);
                    AudioPlayer enemyGunShotSFX = new AudioPlayer("sound effects/enemyshot.wav", 0);
                }
            }
        }
        for (EnemyMissile enemyMissile : state.getEnemyMissiles()) {
            enemyMissile.paint(g2d, state);
        }
        for (EnemyBullet enemyBullet : state.getEnemyBullets()) {
            enemyBullet.paint(g2d, state);
        }
        for (EnemyWallTurretMissile enemyWallTurretMissile : state.getEnemyWallTurretMissiles()) {
            enemyWallTurretMissile.paint(g2d, state);
        }
        for (EnemyHomingMissile enemyHomingMissile : state.getEnemyHomingMissiles()) {
            enemyHomingMissile.paint(g2d, state);
        }
    }

    /**
     * method for our tank to shoot
     * @param g2d graphics2D
     * @param state game state
     */
    private void tankWillShoot(Graphics2D g2d, GameState state) {
        if (state.isMouseClicked() && tankObject.getNumOfCannonBullet() > 0 && state.getGunType() % 2 == 1) {
            if (!state.getInfiniteMissilesCheatCode().isCheatCodeEntered())
                tankObject.setNumOfCannonBullet(tankObject.getNumOfCannonBullet() - 1);
            if (tankObject.getCannonLevel() == 1) {
                state.getMissiles().add(new Missile(state, 15));
                state.getMissiles().get(state.getMissiles().size() - 1).missileDirection(state);
                AudioPlayer tankMissileSFX = new AudioPlayer("sound effects/heavygun.wav", 0);
                state.setMouseClicked(false);
            }
            if (tankObject.getCannonLevel() == 2) {
                state.getMissiles().add(new Missile(state, 30));
                state.getMissiles().get(state.getMissiles().size() - 1).missileDirection(state);
                AudioPlayer tankMissileSFX = new AudioPlayer("sound effects/heavygun.wav", 0);
                state.setMouseClicked(false);
            }
        } else if (state.isMouseClicked() && tankObject.getNumOfCannonBullet() == 0 && state.getGunType() % 2 == 1) {
            AudioPlayer emptyGunSFX = new AudioPlayer("sound effects/emptyGun.wav", 0);
            state.setMouseClicked(false);
        }
        for (Missile missile : state.getMissiles()) {
           missile.paint(g2d, state);
        }
        if (state.isMousePressed() && tankObject.getNumOfMachineGunBullet() > 0 && state.getGunType() % 2 == 0) {
            if (numOfRenderBullet1 == 0 && tankObject.getMachineGunLevel() == 1) {
                if (!state.getInfiniteMissilesCheatCode().isCheatCodeEntered())
                    tankObject.setNumOfMachineGunBullet(tankObject.getNumOfMachineGunBullet() - 1);
                state.getBullets().add(new Bullet(state, 20));
                state.getBullets().get(state.getBullets().size() - 1).bulletDirection(state);
                AudioPlayer tankMissileSFX = new AudioPlayer("sound effects/lightgun.wav", 0);
                state.setMouseClicked(false);
            }
            if (numOfRenderBullet2 == 0 && tankObject.getMachineGunLevel() == 2) {
                if (!state.getInfiniteMissilesCheatCode().isCheatCodeEntered())
                    tankObject.setNumOfMachineGunBullet(tankObject.getNumOfMachineGunBullet() - 1);
                state.getBullets().add(new Bullet(state, 30));
                state.getBullets().get(state.getBullets().size() - 1).bulletDirection(state);
                AudioPlayer tankMissileSFX = new AudioPlayer("sound effects/lightgun.wav", 0);
                state.setMouseClicked(false);
            }
            if (numOfRenderBullet1 <= 7) {
                numOfRenderBullet1++;
            }
            if (numOfRenderBullet1 == 7) {
                numOfRenderBullet1 = 0;
            }
            if (numOfRenderBullet2 <= 3) {
                numOfRenderBullet2++;
            }
            if (numOfRenderBullet2 == 3) {
                numOfRenderBullet2 = 0;
            }
        }
        else if (state.isMousePressed() && tankObject.getNumOfMachineGunBullet() == 0 && state.getGunType() % 2 == 0) {
            AudioPlayer emptyGunSFX = new AudioPlayer("sound effects/emptyGun.wav", 0);
        }
        for (Bullet bullet : state.getBullets()) {
            bullet.paint(g2d, state);
        }
    }

    /**
     * paints all enemies
     * @param g2d graphics2D
     * @param state game state
     */
    private void paintAllEnemies(Graphics2D g2d, GameState state) {
        for (int i = 0; i < state.getEnemyTanks().size(); i++) {
            state.getEnemyTanks().get(i).paintBody(g2d, state);
            state.getEnemyTanks().get(i).paintGun1(g2d, state);
        }
        for (int i = 0; i < state.getEnemyCars().size(); i++) {
            state.getEnemyCars().get(i).paintBody(g2d, state);
        }
        for (int i = 0; i < state.getEnemyTurrets().size(); i++) {
            state.getEnemyTurrets().get(i).paintBody(g2d, state);
        }
        for (int i = 0; i < state.getEnemyWallTurrets().size(); i++) {
            state.getEnemyWallTurrets().get(i).paintBody(g2d, state);
        }
    }

    /**
     * paints our target
     * @param g2d graphics2D
     * @param state game state
     */
    private void paintTankGuns(Graphics2D g2d, GameState state) {
        if (state.getGunType() % 2 == 1) {
            if (tankObject.getCannonLevel() == 1) {
                tankObject.paintGun1(g2d, state, getMousePosition().x, getMousePosition().y);
            }
            if (tankObject.getCannonLevel() == 2) {
                tankObject.paintGun2(g2d, state, getMousePosition().x, getMousePosition().y);
            }
        } else {
            if (tankObject.getMachineGunLevel() == 1) {
                tankObject.paintGun3(g2d, state, getMousePosition().x, getMousePosition().y);
            }
            if (tankObject.getMachineGunLevel() == 2) {
                tankObject.paintGun4(g2d, state, getMousePosition().x, getMousePosition().y);
            }
        }
    }

    /**
     * updates enemies locations
     * @param state game state
     */
    public void updateEnemies(GameState state) {
        for (int i = 0; i < state.getEnemyTanks().size(); i++) {
            state.getEnemyTanks().get(i).update();
        }
        for (int i = 0; i < state.getEnemyCars().size(); i++) {
            state.getEnemyCars().get(i).update();
        }
    }

    /**
     * tank distance from starting point
     * @param state game state
     * @return yChange
     */
    public int yChange(GameState state) {
        return state.getTankLocY() - tankYStart;
    }

    /**
     * tank distance from starting point
     * @param state game state
     * @return xChange
     */
    public int xChange(GameState state) {
        return state.getTankLocX() - tankXStart;
    }

    /**
     * paints plants
     * @param graphics2D graphics2D
     * @param state game state
     */
    public void paintPlants(Graphics2D graphics2D, GameState state) {
        for (int i = 0; i < Map.plants.size(); i++) {
            if (Map.plants.get(i).getY() < state.getTankLocY() + 500 && Map.plants.get(i).getY() > state.getTankLocY() - 400 &&
                    Map.plants.get(i).getX() < state.getTankLocX() + 800 && Map.plants.get(i).getX() > state.getTankLocX() - 500) {
                graphics2D.drawImage(Map.plants.get(i).getImage(),
                        Map.plants.get(i).getX() - xChange(state), Map.plants.get(i).getY() - yChange(state), null);
            }
        }
    }

    /**
     * prints frame per second
     * @param g2d graphics2D
     */
    private void fps(Graphics2D g2d) {

        long currentRender = System.currentTimeMillis();
        if (lastRender > 0) {
            fpsHistory.add(1000.0f / (currentRender - lastRender));
            if (fpsHistory.size() > 100) {
                fpsHistory.remove(0); // remove oldest
            }
            float avg = 0.0f;
            for (float fps : fpsHistory) {
                avg += fps;
            }
            avg /= fpsHistory.size();
            String str = String.format("Average FPS = %.1f , Last Interval = %d ms",
                    avg, (currentRender - lastRender));
            g2d.setColor(Color.CYAN);
            g2d.setFont(g2d.getFont().deriveFont(18.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            int strHeight = g2d.getFontMetrics().getHeight();
            g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, strHeight + 50);
        }
        lastRender = currentRender;

        numOfBulletObject.paintCannonBullet(g2d);
        numOfBulletObject.paintMachineGunBullet(g2d);
        numOfBulletObject.paintHealth(g2d);
        numOfBulletObject.paintLife(g2d);
    }

    /**
     * getter for tank object
     * @return tank object
     */
    public Tank getTankObject() {
        return tankObject;
    }


}
