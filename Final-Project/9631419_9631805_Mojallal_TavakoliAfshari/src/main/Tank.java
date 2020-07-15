package main;

import game.GameFrame;
import game.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * our tanks class!
 */
public class Tank {

    private BufferedImage tankBody1Image;
    private BufferedImage tankBody2Image;
    private BufferedImage tankGun1Image;
    private BufferedImage tankGun2Image;
    private BufferedImage tankGun3Image;
    private BufferedImage tankGun4Image;
    private BufferedImage targetImage;

    private GameFrame game;

    private AffineTransform gunTransform = new AffineTransform();

    private int health = 5;
    private int life = 2;
    private int cannonLevel = 1;
    private int machineGunLevel = 1;

    private int numOfCannonBullet = 50;
    private int numOfMachineGunBullet = 150;

    /**
     * our tanks constructor
     * @param game every tank needs a game frame
     */
    public Tank(GameFrame game){
        this.game = game;

        try{
            tankBody1Image = ImageIO.read(new File("src/pictures/tankBody1.png"));
            tankBody2Image = ImageIO.read(new File("src/pictures/tankBody2.png"));
            tankGun1Image = ImageIO.read(new File("src/pictures/tankGun1.png"));
            tankGun2Image = ImageIO.read(new File("src/pictures/tankGun2.png"));
            tankGun3Image = ImageIO.read(new File("src/pictures/tankGun3.png"));
            tankGun4Image = ImageIO.read(new File("src/pictures/tankGun4.png"));
            targetImage = ImageIO.read(new File("src/pictures/target.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * a method that returns the amount of distance the tank traveled in the y-axis in the map
     * @param state used to get the tanks coordinates
     * @return returns the distance in the y-axis in pixels
     */
    public int yChange(GameState state){
        return state.getTankLocY() - GameFrame.tankYStart;
    }
    /**
     * a method that returns the amount of distance the tank traveled in the x-axis in the map
     * @param state used to get the tanks coordinates
     * @return returns the distance in the x-axis in pixels
     */
    public int xChange(GameState state){
        return  state.getTankLocX() - GameFrame.tankXStart;
    }

    /**
     * the method that paints the tanks body
     * @param graphics2D the graphics required for our drawing!
     * @param state used to get the tanks coordinates
     */
    public void paintBody(Graphics2D graphics2D, GameState state){

        AffineTransform transform1 = new AffineTransform();
        transform1.setToTranslation((state.getTankLocX() + 50) - xChange(state),
                (state.getTankLocY() + 50) - yChange(state));
        transform1.rotate(Math.PI * state.getTankRotation());
        transform1.translate(-50, -50);

        if(state.isMoving()){
            if(state.getBodyNumber() % 2 == 1){
                graphics2D.drawImage(tankBody1Image, transform1, null);
            }
            else{
                graphics2D.drawImage(tankBody2Image, transform1, null);
            }
            state.setBodyNumber(state.getBodyNumber() + 1);
        }else{
            graphics2D.drawImage(tankBody1Image, transform1, null);
        }

    }

    /**
     * the method that paints the tanks gun
     * @param state used to get the tanks coordinates
     * @param mousePositionX used to see where our gun is pointing to
     * @param mousePositionY used to see where our gun is pointing to
     */
    public void paintGun(GameState state, int mousePositionX, int mousePositionY){
        gunTransform.setToTranslation(state.getTankLocX() + 50 - xChange(state),
                state.getTankLocY() + 50 - yChange(state));
        gunTransform.rotate(Math.atan2(mousePositionY - state.getTankLocY() - 50 + yChange(state),
                mousePositionX - state.getTankLocX() - 50 + xChange(state)));
        gunTransform.translate(-30, -30);
    }

    /**
     * the method that paints the tanks gun
     * * @param graphics2D the graphics required for our drawing!
     * @param state used to get the tanks coordinates
     * @param mousePositionX used to see where our gun is pointing to
     * @param mousePositionY used to see where our gun is pointing to
     */
    public void paintGun1(Graphics2D graphics2D, GameState state, int mousePositionX, int mousePositionY){

        paintGun(state, mousePositionX, mousePositionY);
        graphics2D.drawImage(tankGun1Image, gunTransform, null);
    }

    /**
     * the method that paints the tanks gun
     * @param graphics2D the graphics required for our drawing!
     * @param state used to get the tanks coordinates
     * @param mousePositionX used to see where our gun is pointing to
     * @param mousePositionY used to see where our gun is pointing to
     */
    public void paintGun2(Graphics2D graphics2D, GameState state, int mousePositionX, int mousePositionY){

        paintGun(state, mousePositionX, mousePositionY);
        graphics2D.drawImage(tankGun2Image, gunTransform, null);
    }

    /**
     * the method that paints the tanks gun
     * @param graphics2D the graphics required for our drawing!
     * @param state used to get the tanks coordinates
     * @param mousePositionX used to see where our gun is pointing to
     * @param mousePositionY used to see where our gun is pointing to
     */
    public void paintGun3(Graphics2D graphics2D, GameState state, int mousePositionX, int mousePositionY){
        paintGun(state, mousePositionX, mousePositionY);
        graphics2D.drawImage(tankGun3Image, gunTransform, null);
    }

    /**
     * the method that paints the tanks gun
     * @param graphics2D the graphics required for our drawing!
     * @param state used to get the tanks coordinates
     * @param mousePositionX used to see where our gun is pointing to
     * @param mousePositionY used to see where our gun is pointing to
     */
    public void paintGun4(Graphics2D graphics2D, GameState state, int mousePositionX, int mousePositionY){
        paintGun(state, mousePositionX, mousePositionY);
        graphics2D.drawImage(tankGun4Image, gunTransform, null);
    }

    /**
     * sees whether our enemies missiles have any collision with our tank or not
     * @param state used to get the tanks coordinates
     */
    public void enemyMissileCollision(GameState state){
        for (int i = 0; i < state.getEnemyMissiles().size(); i++) {
            if(state.getEnemyMissiles().get(i).getBounds(state).intersects(getBounds(state))){
                state.getEnemyMissiles().remove(state.getEnemyMissiles().get(i));
                if (!state.getInfiniteHealthCheatCode().isCheatCodeEntered()) {
                    this.health -= 1;
                    AudioPlayer enemyBulletToMyTankSFX=new AudioPlayer("sound effects/EnemyBulletToMyTank.wav",0);
                }
            }
        }
    }

    /**
     * sees whether our enemies bullets have any collision with our tank or not
     * @param state used to get the tanks coordinates
     */
    public void enemyBulletCollision(GameState state){
        for (int i = 0; i < state.getEnemyBullets().size(); i++) {
            if(state.getEnemyBullets().get(i).getBounds(state).intersects(getBounds(state))){
                state.getEnemyBullets().remove(state.getEnemyBullets().get(i));
                if (!state.getInfiniteHealthCheatCode().isCheatCodeEntered()) {
                    this.health -= 1;
                    AudioPlayer enemyBulletToMyTankSFX=new AudioPlayer("sound effects/EnemyBulletToMyTank.wav",0);
                }
            }
        }
    }

    /**
     * sees whether our enemies homing missiles have any collision with our tank or not
     * @param state used to get the tanks coordinates
     */
    public void enemyHomingMissileCollision(GameState state){
        for (int i = 0; i < state.getEnemyHomingMissiles().size(); i++) {
            if(state.getEnemyHomingMissiles().get(i).getBounds(state).intersects(getBounds(state))){
                state.getEnemyHomingMissiles().remove(state.getEnemyHomingMissiles().get(i));
                if (!state.getInfiniteHealthCheatCode().isCheatCodeEntered()) {
                    this.health -= 1;
                    AudioPlayer enemyBulletToMyTankSFX=new AudioPlayer("sound effects/EnemyBulletToMyTank.wav",0);
                }
            }
        }
    }

    /**
     * sees whether our enemies turret missiles have any collision with our tank or not
     * @param state used to get the tanks coordinates
     */
    public void enemyWallTurretMissileCollision(GameState state){
        for (int i = 0; i < state.getEnemyWallTurretMissiles().size(); i++) {
            if(state.getEnemyWallTurretMissiles().get(i).getBounds(state).intersects(getBounds(state))){
                state.getEnemyWallTurretMissiles().remove(state.getEnemyWallTurretMissiles().get(i));
                if (!state.getInfiniteHealthCheatCode().isCheatCodeEntered()) {
                    this.health -= 1;
                    AudioPlayer enemyBulletToMyTankSFX=new AudioPlayer("sound effects/EnemyBulletToMyTank.wav",0);
                }
            }
        }
    }

    /**
     * a method that paints the tanks target
     * @param graphics2D the graphics required for our drawing!
     * @param mousePositionX used to see where our gun is pointing to
     * @param mousePositionY used to see where our gun is pointing to
     */
    public void paintTarget(Graphics2D graphics2D, int mousePositionX, int mousePositionY){
        try {
            graphics2D.drawImage(targetImage, mousePositionX - 16, mousePositionY - 16, null);
        } catch (NullPointerException e) {
            System.out.println();
        }
    }

    /**
     * a method used to creat a rectangle bound around our tank
     * @param state used to get the tanks coordinates
     * @return returns the tanks rectangle
     */
    public Rectangle getBounds(GameState state){
        if (!state.getInvisibleTankCheatCode().isCheatCodeEntered())
            return new Rectangle(state.getTankLocX() + 10, state.getTankLocY() + 10,
                    tankBody1Image.getWidth() - 10, tankBody1Image.getHeight() - 10);
        else
            return new Rectangle(state.getTankLocX() + 10,state.getTankLocY() + 10, 0, 0);//This means that the tank is invisible!!(spooky:)) )
    }


    //bullets

    /**
     * gets the number of cannon bullets the tank has
     * @return returns the number of cannon bullets
     */
    public int getNumOfCannonBullet() {
        return numOfCannonBullet;
    }

    /**
     * sets the number of cannon bullets we desire
     * @param numOfCannonBullet the number of cannon bullets we desire to assign to our NumOfCannonBullet field
     */
    public void setNumOfCannonBullet(int numOfCannonBullet) {
        this.numOfCannonBullet = numOfCannonBullet;
    }
    /**
     * gets the number of machinegun bullets the tank has
     * @return returns the number of machinegun bullets
     */
    public int getNumOfMachineGunBullet() {
        return numOfMachineGunBullet;
    }
    /**
     * sets the number of machinegun bullets we desire
     * @param numOfMachineGunBullet the number of macihnegun bullets we desire to assign to our NumOfMachineGunBullet field
     */
    public void setNumOfMachineGunBullet(int numOfMachineGunBullet) {
        this.numOfMachineGunBullet = numOfMachineGunBullet;
    }


    //health

    /**
     * the amount of health the tank has
     * @return returns an int
     */
    public int getHealth() {
        return health;
    }

    /**
     * setting the amount of health we desire
     * @param health the amount of health we desire to assign
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * gives us the amount of lives we have
     * @return returns the amount of lives
     */
    public int getLife() {
        return life;
    }

    /**
     * sets the amount of lives we desire
     * @param life the amount of lives we desire
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * a method that gives us the tanks cannon level as an integer
     * @return the cannons level
     */
    public int getCannonLevel() {
        return cannonLevel;
    }

    /**
     * sets the cannons level the amount we desire
     * @param cannonLevel the cannons level we desire
     */
    public void setCannonLevel(int cannonLevel) {
        this.cannonLevel = cannonLevel;
    }

    /**
     * a method that gives us the tanks machinegun level as an integer
     * @return the machineguns level
     */
    public int getMachineGunLevel() {
        return machineGunLevel;
    }
    /**
     * sets the machineguns level the amount we desire
     * @param machineGunLevel the machineguns level we desire
     */
    public void setMachineGunLevel(int machineGunLevel) {
        this.machineGunLevel = machineGunLevel;
    }
}
