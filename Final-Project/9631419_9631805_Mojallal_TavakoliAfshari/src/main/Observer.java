package main;

import game.GameFrame;
import game.GameState;

public class Observer {

    /**
     * a class that checks our collisions & is referred to in each frame of the game frame
     */

    private GameFrame game;

    /**
     * the observers consstructor
     * @param game we need the game for our methods
     */
    public Observer(GameFrame game) {
        this.game = game;
    }

    /**
     * a method that does our class's only goal: observation!
     * @param state used to access our tanks attributes!
     */
    public void observe(GameState state) {
        for (int i = 0; i < Map.cannonFoods.size(); i++) {
            if (Map.cannonFoods.get(i).collision(state)) {
                game.getTankObject().setNumOfCannonBullet(game.getTankObject().getNumOfCannonBullet() + 10);
                Map.cannonFoods.remove(Map.cannonFoods.get(i));
                AudioPlayer upgradeSFX=new AudioPlayer("sound effects/upgrade.wav",0);
            }
        }
        for (int i = 0; i < Map.machineGunFoods.size(); i++) {
            if (Map.machineGunFoods.get(i).collision(state)) {
                game.getTankObject().setNumOfMachineGunBullet(game.getTankObject().getNumOfMachineGunBullet() + 20);
                Map.machineGunFoods.remove(Map.machineGunFoods.get(i));
                AudioPlayer upgradeSFX=new AudioPlayer("sound effects/upgrade.wav",0);
            }
        }
        for (int i = 0; i < Map.repairFoods.size(); i++) {
            if (Map.repairFoods.get(i).collision(state)) {
                game.getTankObject().setHealth(game.getTankObject().getHealth() + 1);
                Map.repairFoods.remove(Map.repairFoods.get(i));
                AudioPlayer upgradeSFX=new AudioPlayer("sound effects/upgrade.wav",0);
            }
        }
        for (int i = 0; i < Map.upgradeWeaponFoods.size(); i++) {
            if (!state.getUpgradeWeaponCheatCode().isCheatCodeEntered()) {
                if(Map.upgradeWeaponFoods.get(i).collision(state)) {
                    if (state.getGunType() % 2 == 1) {
                        Map.upgradeWeaponFoods.remove(Map.upgradeWeaponFoods.get(i));
                        AudioPlayer upgradeSFX=new AudioPlayer("sound effects/upgrade.wav",0);
                        game.getTankObject().setCannonLevel(2);
                    } else {
                        Map.upgradeWeaponFoods.remove(Map.upgradeWeaponFoods.get(i));
                        AudioPlayer upgradeSFX=new AudioPlayer("sound effects/upgrade.wav",0);
                        game.getTankObject().setMachineGunLevel(2);
                    }
                }
            }
            else{
                game.getTankObject().setCannonLevel(2);
                game.getTankObject().setMachineGunLevel(2);
            }
        }
        for (int i = 0; i < Map.mines.size(); i++) {
            if (Map.mines.get(i).collision(state)) {
                Map.mines.remove(Map.mines.get(i));
                AudioPlayer mineExplosionSFX=new AudioPlayer("sound effects/MineBoom.wav",0);
                game.getTankObject().setHealth(game.getTankObject().getHealth() - 1);

            }
        }
        for (int i = 0; i < Map.keys.size(); i++) {
            if (Map.keys.get(i).collision(state)) {
                Map.keys.remove(Map.keys.get(i));
                state.setGameOver(true);
                state.setGameWon(true);
            }
        }
        for (int i = 0; i < Map.shields.size(); i++) {
            if(Map.shields.get(i).collision(state)){
                Map.shields.remove(Map.shields.get(i));
                game.getTankObject().setLife(game.getTankObject().getLife() + 1);
            }
        }
        for (int i = 0; i < Map.blocks.size(); i++) {
            Map.blocks.get(i).missileCollision(state);
        }
        for (int i = 0; i < Map.blocks.size(); i++) {
            Map.blocks.get(i).bulletCollision(state);
        }
        for (int i = 0; i < Map.walls.size(); i++) {
            Map.walls.get(i).missileCollision(state);
        }
        for (int i = 0; i < Map.walls.size(); i++) {
            Map.walls.get(i).bulletCollision(state);
        }
        for (int i = 0; i < Map.walls.size(); i++) {
            Map.walls.get(i).enemyMissileCollision(state);
        }
        for (int i = 0; i < Map.walls.size(); i++) {
            Map.walls.get(i).enemyWallTurretMissileCollision(state);
        }
        for (int i = 0; i < Map.walls.size(); i++) {
            Map.walls.get(i).enemyHomingMissileCollision(state);
        }
        for (int i = 0; i < Map.walls.size(); i++) {
            Map.walls.get(i).enemyBulletCollision(state);
        }
        for (int i = 0; i < state.getEnemyCars().size(); i++) {
            state.getEnemyCars().get(i).missileCollision(state);
        }
        for (int i = 0; i < state.getEnemyCars().size(); i++) {
            state.getEnemyCars().get(i).bulletCollision(state);
        }
        for (int i = 0; i < state.getEnemyTanks().size(); i++) {
            state.getEnemyTanks().get(i).missileCollision(state);
        }
        for (int i = 0; i < state.getEnemyTanks().size(); i++) {
            state.getEnemyTanks().get(i).bulletCollision(state);
        }
        for (int i = 0; i < state.getEnemyTurrets().size(); i++) {
            state.getEnemyTurrets().get(i).missileCollision(state);
        }
        for (int i = 0; i < state.getEnemyTurrets().size(); i++) {
            state.getEnemyTurrets().get(i).bulletCollision(state);
        }
        for (int i = 0; i < state.getEnemyWallTurrets().size(); i++) {
            state.getEnemyWallTurrets().get(i).missileCollision(state);
        }
        for (int i = 0; i < state.getEnemyWallTurrets().size(); i++) {
            state.getEnemyWallTurrets().get(i).bulletCollision(state);
        }

        if (game.getTankObject().getHealth() == 0) {
            if (game.getTankObject().getLife() != 1) {
                game.getTankObject().setLife(game.getTankObject().getLife() - 1);
                game.getTankObject().setHealth(5);
            } else {
                state.setGameOver(true);
            }
        }
    }
}
