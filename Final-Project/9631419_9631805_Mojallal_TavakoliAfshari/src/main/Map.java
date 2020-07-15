package main;

import components.*;
import foods.CannonFood;
import foods.MachineGunFood;
import foods.RepairFood;
import foods.UpgradeWeaponFood;
import game.GameFrame;
import game.GameState;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {

    private ArrayList<ArrayList<Integer>> tiles = new ArrayList<>();

    public static ArrayList<Key> keys = new ArrayList<>();
    public static ArrayList<Shield> shields = new ArrayList<>();
    public static ArrayList<Destroyed> destroyedArrayList = new ArrayList<>();
    public static ArrayList<Wall> walls = new ArrayList<>();
    public static ArrayList<Soil> soils = new ArrayList<>();
    public static ArrayList<Teasel> teasels = new ArrayList<>();
    public static ArrayList<Plant> plants = new ArrayList<>();
    public static ArrayList<Block> blocks = new ArrayList<>();
    public static ArrayList<Mine> mines = new ArrayList<>();

    public static ArrayList<CannonFood> cannonFoods = new ArrayList<>();
    public static ArrayList<MachineGunFood> machineGunFoods = new ArrayList<>();
    public static ArrayList<RepairFood> repairFoods = new ArrayList<>();
    public static ArrayList<UpgradeWeaponFood> upgradeWeaponFoods = new ArrayList<>();

    private GameFrame game;
    private int x = 0;
    private int y = 0;


    /**
     * constructor for the map class
     * @param fileName file name
     * @param game game frame
     */
    public Map(String fileName, GameFrame game){

        this.game = game;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String currentLine;

            while((currentLine = br.readLine()) != null){
                if(currentLine.isEmpty()) {
                    continue;
                }

                ArrayList<Integer> row = new ArrayList<>();

                String values[] = currentLine.trim().split(" ");

                for(String string : values)
                {
                    if(!string.isEmpty())
                    {
                        int id = Integer.parseInt(string);

                        row.add(id);
                    }
                }
                tiles.add(row);

            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * creates the components
     */
    public void semiDraw(){

        keys.add(new Key(game, 400, 2400));
        shields.add(new Shield(game, 2200, 1500));
        cannonFoods.add(new CannonFood(game, 400, 800));

        for (int i = 0; i < tiles.size(); i++)
        {
            for (int j = 0; j < tiles.get(0).size(); j++)
            {

                if(tiles.get(i).get(j) == 0)
                {
                    soils.add(new Soil(game, x, y));
                    x += 100;
                }
                if(tiles.get(i).get(j) == 1)
                {
                    walls.add(new Wall(game, x, y));
                    x += 100;
                }
                if(tiles.get(i).get(j) == 2)
                {
                    soils.add(new Soil(game, x, y));
                    teasels.add(new Teasel(game, x, y));
                    x += 100;
                }
                if(tiles.get(i).get(j) == 3)
                {
                    soils.add(new Soil(game, x, y));
                    plants.add(new Plant(game, x, y));
                    x += 100;
                }
                if(tiles.get(i).get(j) == 4)
                {
                    soils.add(new Soil(game, x, y));
                    cannonFoods.add(new CannonFood(game, x, y));
                    x += 100;
                }
                if(tiles.get(i).get(j) == 5)
                {
                    soils.add(new Soil(game, x, y));
                    machineGunFoods.add(new MachineGunFood(game, x, y));
                    x += 100;
                }
                if(tiles.get(i).get(j) == 6)
                {
                    soils.add(new Soil(game, x, y));
                    repairFoods.add(new RepairFood(game, x, y));
                    x += 100;
                }
                if(tiles.get(i).get(j) == 7)
                {
                    soils.add(new Soil(game, x, y));
                    blocks.add(new Block(game, x, y));
                    x += 100;
                }
                if(tiles.get(i).get(j) == 8)
                {
                    soils.add(new Soil(game, x, y));
                    upgradeWeaponFoods.add(new UpgradeWeaponFood(game, x, y));
                    x += 100;
                }
                if(tiles.get(i).get(j) == 9){
                    soils.add(new Soil(game, x, y));
                    mines.add(new Mine(game, x, y));
                    x += 100;
                }

            }
            x = 0;
            y += 100;
        }
        x = 0;
        y = 0;

    }

    /**
     * tank distance from starting point
     * @param state game state
     * @return yChange
     */
    public int yChange(GameState state){
        return state.getTankLocY() - GameFrame.tankYStart;
    }

    /**
     * tank distance from starting point
     * @param state game state
     * @return xChange
     */
    public int xChange(GameState state){
        return  state.getTankLocX() - GameFrame.tankXStart;
    }

    /**
     * draws the components
     * @param graphics2D graphics2D
     * @param state game state
     */
    public void draw(Graphics2D graphics2D, GameState state){

        for (int i = 0; i < soils.size(); i++) {
            if(soils.get(i).getY() < state.getTankLocY() + 500 && soils.get(i).getY() > state.getTankLocY() - 400 &&
                    soils.get(i).getX() < state.getTankLocX() + 800 && soils.get(i).getX() > state.getTankLocX() - 500){
                graphics2D.drawImage(soils.get(i).getImage(),
                        soils.get(i).getX() - xChange(state), soils.get(i).getY() - yChange(state), null);
            }
        }

        for (int i = 0; i < destroyedArrayList.size(); i++) {
            if(destroyedArrayList.get(i).getY() < state.getTankLocY() + 500 && destroyedArrayList.get(i).getY() > state.getTankLocY() - 400 &&
                    destroyedArrayList.get(i).getX() < state.getTankLocX() + 800 && destroyedArrayList.get(i).getX() > state.getTankLocX() - 500){
                graphics2D.drawImage(destroyedArrayList.get(i).getImage(),
                        destroyedArrayList.get(i).getX() - xChange(state), destroyedArrayList.get(i).getY() - yChange(state), null);
            }
        }

        for (int i = 0; i < walls.size(); i++) {
            if(walls.get(i).getY() < state.getTankLocY() + 500 && walls.get(i).getY() > state.getTankLocY() - 400 &&
                    walls.get(i).getX() < state.getTankLocX() + 800 && walls.get(i).getX() > state.getTankLocX() - 500){
                graphics2D.drawImage(walls.get(i).getImage(),
                        walls.get(i).getX() - xChange(state), walls.get(i).getY() - yChange(state), null);
            }
        }

        for (int i = 0; i < teasels.size(); i++) {
            if(teasels.get(i).getY() < state.getTankLocY() + 500 && teasels.get(i).getY() > state.getTankLocY() - 400 &&
                    teasels.get(i).getX() < state.getTankLocX() + 800 && teasels.get(i).getX() > state.getTankLocX() - 500){
                graphics2D.drawImage(teasels.get(i).getImage(),
                        teasels.get(i).getX() - xChange(state), teasels.get(i).getY() - yChange(state), null);
            }
        }

        for (int i = 0; i < mines.size(); i++) {
            if(mines.get(i).getY() < state.getTankLocY() + 500 && mines.get(i).getY() > state.getTankLocY() - 400 &&
                    mines.get(i).getX() < state.getTankLocX() + 800 && mines.get(i).getX() > state.getTankLocX() - 500){
                graphics2D.drawImage(mines.get(i).getImage(),
                        mines.get(i).getX() - xChange(state), mines.get(i).getY() - yChange(state), null);
            }
        }

        for (int i = 0; i < cannonFoods.size(); i++) {
            if(cannonFoods.get(i).getY() < state.getTankLocY() + 500 && cannonFoods.get(i).getY() > state.getTankLocY() - 400 &&
                    cannonFoods.get(i).getX() < state.getTankLocX() + 800 && cannonFoods.get(i).getX() > state.getTankLocX() - 500){
                graphics2D.drawImage(cannonFoods.get(i).getImage(),
                        cannonFoods.get(i).getX() - xChange(state), cannonFoods.get(i).getY() - yChange(state), null);
            }
        }

        for (int i = 0; i < blocks.size(); i++) {
            if(blocks.get(i).getY() < state.getTankLocY() + 500 && blocks.get(i).getY() > state.getTankLocY() - 400 &&
                    blocks.get(i).getX() < state.getTankLocX() + 800 && blocks.get(i).getX() > state.getTankLocX() - 500){
                graphics2D.drawImage(blocks.get(i).getImage(),
                        blocks.get(i).getX() - xChange(state), blocks.get(i).getY() - yChange(state), null);
            }
        }

        for (int i = 0; i < machineGunFoods.size(); i++) {
            if(machineGunFoods.get(i).getY() < state.getTankLocY() + 500 && machineGunFoods.get(i).getY() > state.getTankLocY() - 400 &&
                    machineGunFoods.get(i).getX() < state.getTankLocX() + 800 && machineGunFoods.get(i).getX() > state.getTankLocX() - 500){
                graphics2D.drawImage(machineGunFoods.get(i).getImage(),
                        machineGunFoods.get(i).getX() - xChange(state), machineGunFoods.get(i).getY() - yChange(state), null);
            }
        }

        for (int i = 0; i < repairFoods.size(); i++) {
            if(repairFoods.get(i).getY() < state.getTankLocY() + 500 && repairFoods.get(i).getY() > state.getTankLocY() - 400 &&
                    repairFoods.get(i).getX() < state.getTankLocX() + 800 && repairFoods.get(i).getX() > state.getTankLocX() - 500){
                graphics2D.drawImage(repairFoods.get(i).getImage(),
                        repairFoods.get(i).getX() - xChange(state), repairFoods.get(i).getY() - yChange(state), null);
            }
        }

        for (int i = 0; i < upgradeWeaponFoods.size(); i++) {
            if(upgradeWeaponFoods.get(i).getY() < state.getTankLocY() + 500 && upgradeWeaponFoods.get(i).getY() > state.getTankLocY() - 400 &&
                    upgradeWeaponFoods.get(i).getX() < state.getTankLocX() + 800 && upgradeWeaponFoods.get(i).getX() > state.getTankLocX() - 500){
                graphics2D.drawImage(upgradeWeaponFoods.get(i).getImage(),
                        upgradeWeaponFoods.get(i).getX() - xChange(state), upgradeWeaponFoods.get(i).getY() - yChange(state), null);
            }
        }

        for (int i = 0; i < keys.size(); i++) {
            if(keys.get(i).getY() < state.getTankLocY() + 500 && keys.get(i).getY() > state.getTankLocY() - 400 &&
                    keys.get(i).getX() < state.getTankLocX() + 800 && keys.get(i).getX() > state.getTankLocX() - 500){
                graphics2D.drawImage(keys.get(i).getImage(),
                        keys.get(i).getX() - xChange(state), keys.get(i).getY() - yChange(state), null);
            }
        }

        for (int i = 0; i < shields.size(); i++) {
            if(shields.get(i).getY() < state.getTankLocY() + 500 && shields.get(i).getY() > state.getTankLocY() - 400 &&
                    shields.get(i).getX() < state.getTankLocX() + 800 && shields.get(i).getX() > state.getTankLocX() - 500){
                graphics2D.drawImage(shields.get(i).getImage(),
                        shields.get(i).getX() - xChange(state), shields.get(i).getY() - yChange(state), null);
            }
        }
    }
}
