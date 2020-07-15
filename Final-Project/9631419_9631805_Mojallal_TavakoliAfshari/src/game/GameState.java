package game;

import cheats.CheatCode;
import enemy.*;
import main.AudioPlayer;
import main.Bullet;
import main.Map;
import main.Missile;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class GameState {
    private GameFrame game;

    private int tankLocX, tankLocY;
    private double tankRotation;

    private int gunType;
    private int bodyNumber = 1;
    private boolean isMoving = false;

    private ArrayList<Missile> missiles;
    private ArrayList<Bullet> bullets;
    private ArrayList<EnemyTank> enemyTanks;
    private ArrayList<EnemyCar> enemyCars;
    private ArrayList<EnemyTurret> enemyTurrets;
    private ArrayList<EnemyWallTurret> enemyWallTurrets;
    private ArrayList<EnemyMissile> enemyMissiles;
    private ArrayList<EnemyHomingMissile> enemyHomingMissiles;//Testing
    private ArrayList<EnemyWallTurretMissile> enemyWallTurretMissiles;
    private ArrayList<EnemyBullet> enemyBullets;

    private boolean gameOver;
    private boolean gameWon;

    private int dis = 5;
    private int flag = 1;

    private int safeX = 0;
    private int safeY = 0;

    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private boolean mousePressed;
    private boolean mouseClicked;
    private int mouseX, mouseY;

    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    //cheat codes
    private CheatCode infiniteMissilesCheatCode = new CheatCode();//cheat code:"infinitemissiles"
    private CheatCode infiniteHealthCheatCode = new CheatCode();//cheat code:"infinitehealth"
    private CheatCode invisibleTankCheatCode = new CheatCode();//cheat code:"invisibletank"
    private CheatCode upgradeWeaponCheatCode = new CheatCode();//cheat code:"upgradeweapon"

    /**
     * constructor for the game state class
     * @param game game frame
     */
    public GameState(GameFrame game) {
        this.game = game;

        tankLocX = GameFrame.tankXStart;
        tankLocY = GameFrame.tankYStart;
        tankRotation = 0;

        missiles = new ArrayList<>();
        bullets = new ArrayList<>();
        enemyTanks = new ArrayList<>();
        enemyCars = new ArrayList<>();
        enemyTurrets = new ArrayList<>();
        enemyWallTurrets = new ArrayList<>();
        enemyMissiles = new ArrayList<>();
        enemyHomingMissiles = new ArrayList<>();//Testing
        enemyWallTurretMissiles = new ArrayList<>();
        enemyBullets = new ArrayList<>();

        createEnemies();

        gunType = 1;

        gameOver = false;
        gameWon = false;
        //
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        //
        mousePressed = false;
        mouseClicked = false;
        mouseX = 0;
        mouseY = 0;
        //
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
    }

    /**
     * creates all enemies
     */
    public void createEnemies() {
        if (GameFrame.difficulty == 1) {
            createEasyEnemies();
        }
        if (GameFrame.difficulty == 2) {
            createEasyEnemies();
            createMediumEnemies();
        }
        if (GameFrame.difficulty == 3) {
            createEasyEnemies();
            createMediumEnemies();
            createHardEnemies();
        }
    }

    /**
     * creates easy enemies
     */
    public void createEasyEnemies(){
        enemyTanks.add(new EnemyTank(game, this, 1700, 300, "v", 300));
        enemyTanks.add(new EnemyTank(game, this, 2000, 1100, "h", 200));
        enemyWallTurrets.add(new EnemyWallTurret(750, 2400));
        enemyCars.add(new EnemyCar(game, this, 1900, 2400, "h", 300));
        enemyTurrets.add(new EnemyTurret(750, 1950));
    }

    /**
     * creates medium enemies
     */
    public void createMediumEnemies(){
        enemyTurrets.add(new EnemyTurret(2000, 400));
        enemyWallTurrets.add(new EnemyWallTurret(2280, 1050));
        enemyWallTurrets.add(new EnemyWallTurret(800, 1300));
        enemyWallTurrets.add(new EnemyWallTurret(800, 1480));
    }

    /**
     * creates hard enemies
     */
    public void createHardEnemies(){
        enemyTanks.add(new EnemyTank(game, this, 1000, 2400, "v", 200));
        enemyTanks.add(new EnemyTank(game, this, 1100, 2400, "v", 200));
    }

    /**
     * updates the game state
     * @param game game frame
     */
    public void update(GameFrame game) {

        checkCollision(game);

        tankRotation();

    }

    /**
     * checks the collision of the tank with all components
     * @param game game frame
     */
    private void checkCollision(GameFrame game) {
        for (int i = 0; i < Map.walls.size(); i++) {
            if (Map.walls.get(i).collision(this)) {
                flag = 0;
            }
        }
        for (int i = 0; i < Map.teasels.size(); i++) {
            if (Map.teasels.get(i).collision(this)) {
                flag = 0;
            }
        }
        for (int i = 0; i < enemyTanks.size(); i++) {
            if (enemyTanks.get(i).tankCollision(game, this)) {
                flag = 0;
            }
        }
        for (int i = 0; i < enemyCars.size(); i++) {
            if (enemyCars.get(i).tankCollision(game, this)) {
                flag = 0;
            }
        }
        for (int i = 0; i < enemyTurrets.size(); i++) {
            if (enemyTurrets.get(i).tankCollision(game, this)) {
                flag = 0;
            }
        }
        for (int i = 0; i < Map.blocks.size(); i++) {
            if (Map.blocks.get(i).tankCollision(this)) {
                flag = 0;
            }
        }
        if (flag == 1) {
            safeX = tankLocX;
            safeY = tankLocY;
        }
        if (flag == 0) {
            tankLocX = safeX;
            tankLocY = safeY;
            flag = 1;
        }
    }

    /**
     * tank rotation
     */
    private void tankRotation() {
        if ((keyUP && keyLEFT) || (keyDOWN && keyRIGHT)) {
            if (keyUP && keyLEFT) {
                tankLocY -= 4 * Math.sqrt(2);
                tankLocX -= 4 * Math.sqrt(2);
            }
            if (keyDOWN && keyRIGHT) {
                tankLocY += 4 * Math.sqrt(2);
                tankLocX += 4 * Math.sqrt(2);
            }
            if (Math.abs(tankRotation - 1.25) < 0.03)
                tankRotation = 1.25;
            else if (tankRotation % 1 >= 0 && tankRotation % 1 < 0.25 - (double) 1 / 36) {
                tankRotation += (double) 1 / 36;
            } else if (tankRotation % 1 > 0.25 + (double) 1 / 36 && tankRotation % 1 < 1) {
                tankRotation -= (double) 1 / 36;
            } else if (tankRotation != 1.25) {
                tankRotation = 0.25;
            }
        } else if ((keyUP && keyRIGHT || (keyDOWN && keyLEFT))) {
            if (keyUP && keyRIGHT) {
                tankLocY -= 4 * Math.sqrt(2);
                tankLocX += 4 * Math.sqrt(2);
            }
            if (keyDOWN && keyLEFT) {
                tankLocY += 4 * Math.sqrt(2);
                tankLocX -= 4 * Math.sqrt(2);
            }
            if (Math.abs(tankRotation - 1.75) < 0.03)
                tankRotation = 1.75;
            else if (tankRotation % 1 >= 0 && tankRotation % 1 < 0.75 - (double) 1 / 36) {
                tankRotation += (double) 1 / 36;
            } else if (tankRotation % 1 > 0.75 + (double) 1 / 36 && tankRotation % 1 < 1) {
                tankRotation -= (double) 1 / 36;
            } else if (tankRotation != 1.75) {
                tankRotation = 0.75;
            }
        } else if (keyUP || keyDOWN) {
            if (keyUP) {
                tankLocY -= 8;
            }
            if (keyDOWN) {
                tankLocY += 8;
            }
            if (Math.abs(tankRotation - 1.5) < 0.03)
                tankRotation = 1.5;
            else if (tankRotation % 1 >= 0 && tankRotation % 1 < 0.5 - (double) 1 / 36) {
                tankRotation += (double) 1 / 36;
            } else if (tankRotation % 1 > 0.5 + (double) 1 / 36 && tankRotation % 1 < 1) {
                tankRotation -= (double) 1 / 36;
            } else if (tankRotation != 1.5) {
                tankRotation = 0.5;
            }
        } else if (keyLEFT || keyRIGHT) {
            if (keyLEFT) {
                tankLocX -= 8;
            }
            if (keyRIGHT) {
                tankLocX += 8;
            }
            if (Math.abs(tankRotation - 1) < 0.03)
                tankRotation = 1;
            else if (tankRotation % 1 < 1 && tankRotation % 1 > (double) 1 / 36) {
                tankRotation += (double) 1 / 36;
            } else if (tankRotation % 1 > -1 && tankRotation % 1 < -(double) 1 / 36) {
                tankRotation -= (double) 1 / 36;
            } else if (tankRotation != 1) {
                tankRotation = 0;
            }
        }
    }

    /**
     * key listener for game state
     * @return key handler
     */
    public KeyListener getKeyListener() {
        return keyHandler;
    }

    /**
     * mouse listener for game state
     * @return mouse handler
     */
    public MouseListener getMouseListener() {
        return mouseHandler;
    }

    /**
     * mouse motion listener for game state
     * @return mouse handler
     */
    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }

    /**
     * key handler
     */
    class KeyHandler extends KeyAdapter {

        /**
         * key handler
         * @param e key event
         */
        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    keyUP = true;
                    isMoving = true;
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = true;
                    isMoving = true;
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = true;
                    isMoving = true;
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = true;
                    isMoving = true;
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_ESCAPE:
                    gameOver = true;
                    break;
                case KeyEvent.VK_W:
                    keyUP = true;
                    isMoving = true;
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    if (upgradeWeaponCheatCode.getCheatCodeComboLevel() == 7)
                        upgradeWeaponCheatCode.upgradeCheatCodeComboLevel();
                    else
                        upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_A:
                    keyLEFT = true;
                    isMoving = true;
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    if (infiniteHealthCheatCode.getCheatCodeComboLevel() == 10)
                        infiniteHealthCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    if (invisibleTankCheatCode.getCheatCodeComboLevel() == 10)
                        invisibleTankCheatCode.upgradeCheatCodeComboLevel();
                    else
                        invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    if (upgradeWeaponCheatCode.getCheatCodeComboLevel() == 4 || upgradeWeaponCheatCode.getCheatCodeComboLevel() == 9)
                        upgradeWeaponCheatCode.upgradeCheatCodeComboLevel();
                    else
                        upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_S:
                    keyDOWN = true;
                    isMoving = true;
                    if (infiniteMissilesCheatCode.getCheatCodeComboLevel() == 10 || infiniteMissilesCheatCode.getCheatCodeComboLevel() == 11 || infiniteMissilesCheatCode.getCheatCodeComboLevel() == 15) {
                        infiniteMissilesCheatCode.upgradeCheatCodeComboLevel();
                        if (infiniteMissilesCheatCode.getCheatCodeComboLevel() == 16) {
                            infiniteMissilesCheatCode.setCheatCodeEntered(true);
                            System.out.println("infinte missiles");
                        }
                    } else
                        infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    if (invisibleTankCheatCode.getCheatCodeComboLevel() == 4)
                        invisibleTankCheatCode.upgradeCheatCodeComboLevel();
                    else
                        invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_D:
                    keyRIGHT = true;
                    isMoving = true;
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    if (upgradeWeaponCheatCode.getCheatCodeComboLevel() == 5)
                        upgradeWeaponCheatCode.upgradeCheatCodeComboLevel();
                    else
                        upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;

                case KeyEvent.VK_B:
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    if (invisibleTankCheatCode.getCheatCodeComboLevel() == 6)
                        invisibleTankCheatCode.upgradeCheatCodeComboLevel();
                    else
                        invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_E:
                    if (infiniteMissilesCheatCode.getCheatCodeComboLevel() == 7 || infiniteMissilesCheatCode.getCheatCodeComboLevel() == 14)
                        infiniteMissilesCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    if (infiniteHealthCheatCode.getCheatCodeComboLevel() == 7 || infiniteHealthCheatCode.getCheatCodeComboLevel() == 9)
                        infiniteHealthCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    if (invisibleTankCheatCode.getCheatCodeComboLevel() == 8)
                        invisibleTankCheatCode.upgradeCheatCodeComboLevel();
                    else
                        invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    if (upgradeWeaponCheatCode.getCheatCodeComboLevel() == 6 || upgradeWeaponCheatCode.getCheatCodeComboLevel() == 8)
                        upgradeWeaponCheatCode.upgradeCheatCodeComboLevel();
                    else
                        upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_F:
                    if (infiniteMissilesCheatCode.getCheatCodeComboLevel() == 2)
                        infiniteMissilesCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    if (infiniteHealthCheatCode.getCheatCodeComboLevel() == 2)
                        infiniteHealthCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_G:
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    if (upgradeWeaponCheatCode.getCheatCodeComboLevel() == 2)
                        upgradeWeaponCheatCode.upgradeCheatCodeComboLevel();
                    else
                        upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_H:
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    if (infiniteHealthCheatCode.getCheatCodeComboLevel() == 8 || infiniteHealthCheatCode.getCheatCodeComboLevel() == 13) {
                        infiniteHealthCheatCode.upgradeCheatCodeComboLevel();
                        if (infiniteHealthCheatCode.getCheatCodeComboLevel() == 14) {
                            infiniteHealthCheatCode.setCheatCodeEntered(true);
                            System.out.println("infinite health");
                        }
                    } else
                        infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_I:
                    if (infiniteMissilesCheatCode.getCheatCodeComboLevel() == 0 || infiniteMissilesCheatCode.getCheatCodeComboLevel() == 3 || infiniteMissilesCheatCode.getCheatCodeComboLevel() == 5 || infiniteMissilesCheatCode.getCheatCodeComboLevel() == 9 || infiniteMissilesCheatCode.getCheatCodeComboLevel() == 12)
                        infiniteMissilesCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    if (infiniteHealthCheatCode.getCheatCodeComboLevel() == 0 || infiniteHealthCheatCode.getCheatCodeComboLevel() == 3 || infiniteHealthCheatCode.getCheatCodeComboLevel() == 5)
                        infiniteHealthCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    if (invisibleTankCheatCode.getCheatCodeComboLevel() == 0 || invisibleTankCheatCode.getCheatCodeComboLevel() == 3 || invisibleTankCheatCode.getCheatCodeComboLevel() == 5)
                        invisibleTankCheatCode.upgradeCheatCodeComboLevel();
                    else
                        invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_K:
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    if (invisibleTankCheatCode.getCheatCodeComboLevel() == 12) {
                        invisibleTankCheatCode.upgradeCheatCodeComboLevel();
                        invisibleTankCheatCode.setCheatCodeEntered(true);
                        System.out.println("invisible tank");
                    } else
                        invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_L:
                    if (infiniteMissilesCheatCode.getCheatCodeComboLevel() == 13)
                        infiniteMissilesCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    if (infiniteHealthCheatCode.getCheatCodeComboLevel() == 11)
                        infiniteHealthCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    if (invisibleTankCheatCode.getCheatCodeComboLevel() == 7)
                        invisibleTankCheatCode.upgradeCheatCodeComboLevel();
                    else
                        invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_M:
                    if (infiniteMissilesCheatCode.getCheatCodeComboLevel() == 8)
                        infiniteMissilesCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_N:
                    if (infiniteMissilesCheatCode.getCheatCodeComboLevel() == 1 || infiniteMissilesCheatCode.getCheatCodeComboLevel() == 4)
                        infiniteMissilesCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    if (infiniteHealthCheatCode.getCheatCodeComboLevel() == 1 || infiniteHealthCheatCode.getCheatCodeComboLevel() == 4)
                        infiniteHealthCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    if (invisibleTankCheatCode.getCheatCodeComboLevel() == 1 || invisibleTankCheatCode.getCheatCodeComboLevel() == 11)
                        invisibleTankCheatCode.upgradeCheatCodeComboLevel();
                    else
                        invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    if (upgradeWeaponCheatCode.getCheatCodeComboLevel() == 12) {
                        upgradeWeaponCheatCode.upgradeCheatCodeComboLevel();
                        upgradeWeaponCheatCode.setCheatCodeEntered(true);
                        System.out.println("upgrade weapon");
                    }
                    break;
                case KeyEvent.VK_O:
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    if (upgradeWeaponCheatCode.getCheatCodeComboLevel() == 11)
                        upgradeWeaponCheatCode.upgradeCheatCodeComboLevel();
                    else
                        upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_P:
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    if (upgradeWeaponCheatCode.getCheatCodeComboLevel() == 1 || upgradeWeaponCheatCode.getCheatCodeComboLevel() == 10)
                        upgradeWeaponCheatCode.upgradeCheatCodeComboLevel();
                    else
                        upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_R:
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    if (upgradeWeaponCheatCode.getCheatCodeComboLevel() == 3)
                        upgradeWeaponCheatCode.upgradeCheatCodeComboLevel();
                    else
                        upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_T:
                    if (infiniteMissilesCheatCode.getCheatCodeComboLevel() == 6)
                        infiniteMissilesCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    if (infiniteHealthCheatCode.getCheatCodeComboLevel() == 6 || infiniteHealthCheatCode.getCheatCodeComboLevel() == 12)
                        infiniteHealthCheatCode.upgradeCheatCodeComboLevel();
                    else
                        infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    if (invisibleTankCheatCode.getCheatCodeComboLevel() == 9)
                        invisibleTankCheatCode.upgradeCheatCodeComboLevel();
                    else
                        invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_U:
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    if (upgradeWeaponCheatCode.getCheatCodeComboLevel() == 0)
                        upgradeWeaponCheatCode.upgradeCheatCodeComboLevel();
                    else
                        upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
                case KeyEvent.VK_V:
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    if (invisibleTankCheatCode.getCheatCodeComboLevel() == 2)
                        invisibleTankCheatCode.upgradeCheatCodeComboLevel();
                    else
                        invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;

                default:
                    infiniteMissilesCheatCode.setCheatCodeComboLevel(0);
                    infiniteHealthCheatCode.setCheatCodeComboLevel(0);
                    invisibleTankCheatCode.setCheatCodeComboLevel(0);
                    upgradeWeaponCheatCode.setCheatCodeComboLevel(0);
                    break;
            }
        }

        /**
         * key handler
         * @param e key event
         */
        @Override
        public void keyReleased(KeyEvent e) {

            isMoving = false;

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    keyUP = false;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = false;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = false;
                    break;
                case KeyEvent.VK_W:
                    keyUP = false;
                    break;
                case KeyEvent.VK_A:
                    keyLEFT = false;
                    break;
                case KeyEvent.VK_S:
                    keyDOWN = false;
                    break;
                case KeyEvent.VK_D:
                    keyRIGHT = false;
                    break;
            }
        }


    }

    /**
     * mouse handler
     */
    class MouseHandler extends MouseAdapter {

        /**
         * mouse pressed
         * @param e key event
         */
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == 1) {
                mouseX = e.getX();
                mouseY = e.getY();
                mousePressed = true;
                mouseClicked = true;
            }

            if (e.getButton() == 3) {
                gunType++;
            }
        }

        /**
         * mouse released
         * @param e key event
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            mousePressed = false;
        }

        /**
         * mouse dragged
         * @param e key event
         */
        @Override
        public void mouseDragged(MouseEvent e) {

        }

    }

    public int getTankLocX() {
        return tankLocX;
    }
    public int getTankLocY() {
        return tankLocY;
    }

    public double getTankRotation() {
        return tankRotation;
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public ArrayList<EnemyCar> getEnemyCars() {
        return enemyCars;
    }

    public ArrayList<EnemyTurret> getEnemyTurrets() {
        return enemyTurrets;
    }

    public ArrayList<EnemyWallTurret> getEnemyWallTurrets() {
        return enemyWallTurrets;
    }

    public ArrayList<EnemyMissile> getEnemyMissiles() {
        return enemyMissiles;
    }

    public ArrayList<EnemyHomingMissile> getEnemyHomingMissiles() {
        return enemyHomingMissiles;
    }

    public ArrayList<EnemyWallTurretMissile> getEnemyWallTurretMissiles() {
        return enemyWallTurretMissiles;
    }

    public ArrayList<EnemyBullet> getEnemyBullets() {
        return enemyBullets;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isMouseClicked() {
        return mouseClicked;
    }

    public void setMouseClicked(boolean mouseClicked) {
        this.mouseClicked = mouseClicked;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public int getGunType() {
        return gunType;
    }

    public int getBodyNumber() {
        return bodyNumber;
    }

    public void setBodyNumber(int bodyNumber) {
        this.bodyNumber = bodyNumber;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public CheatCode getInfiniteMissilesCheatCode() {
        return infiniteMissilesCheatCode;
    }

    public CheatCode getInfiniteHealthCheatCode() {
        return infiniteHealthCheatCode;
    }

    public CheatCode getInvisibleTankCheatCode() {
        return invisibleTankCheatCode;
    }

    public CheatCode getUpgradeWeaponCheatCode() {
        return upgradeWeaponCheatCode;
    }
}

