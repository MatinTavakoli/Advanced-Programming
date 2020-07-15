package main;

import game.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

/**
 * a class that edits the games map
 *
 * @author Matin Tavakoli
 */
public class MapEditor {
    private JFrame mapEditorFrame;
    private int selectedItem;
    private File mapEditorFile;
    private JButton saveAndPlayButton;
    private MapJButton[][] mapCells;

    /**
     * the map editors constructor which creates the mak editros frame
     *
     * @throws IOException in case the image icons are not there or can't be opened etc
     */
    public MapEditor() throws IOException {
        mapEditorFrame = new JFrame("Map Editor");
        mapEditorFrame.setSize(907, 655);
        mapEditorFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mapEditorFrame.setLocationRelativeTo(null);
        mapEditorFrame.setResizable(false);

        selectedItem = 0;

        mapEditorFile = new File("map.txt");

        JPanel backgroundPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        JPanel southPanel = new JPanel(new GridLayout(1, 2));
        JPanel mapPanel = new JPanel(new GridLayout(30, 30));
        JPanel componentsPanel = new JPanel(new GridLayout(5, 2));

        //components images
        ImageIcon soilImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/soil1.png"))
                .getScaledInstance(150, 120, Image.SCALE_AREA_AVERAGING));
        ImageIcon wallImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/wall.png"))
                .getScaledInstance(150, 120, Image.SCALE_AREA_AVERAGING));
        ImageIcon teaselImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/teasel.png"))
                .getScaledInstance(150, 120, Image.SCALE_AREA_AVERAGING));
        ImageIcon plantImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/plant.png"))
                .getScaledInstance(150, 120, Image.SCALE_AREA_AVERAGING));
        ImageIcon cannonFoodImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/cannonFood.png"))
                .getScaledInstance(150, 120, Image.SCALE_AREA_AVERAGING));
        ImageIcon machineGunFoodImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/machineGunFood.png"))
                .getScaledInstance(150, 120, Image.SCALE_AREA_AVERAGING));
        ImageIcon repairFoodImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/repairFood.png"))
                .getScaledInstance(150, 120, Image.SCALE_AREA_AVERAGING));
        ImageIcon block1ImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/block1.png"))
                .getScaledInstance(150, 120, Image.SCALE_AREA_AVERAGING));
        ImageIcon starImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/star.png"))
                .getScaledInstance(150, 120, Image.SCALE_AREA_AVERAGING));
        ImageIcon mineImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/mine.png"))
                .getScaledInstance(150, 120, Image.SCALE_AREA_AVERAGING));
        //map cells images
        ImageIcon soilMiniImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/soil1.png"))
                .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING));
        ImageIcon wallMiniImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/wall.png"))
                .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING));
        ImageIcon teaselMiniImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/teasel.png"))
                .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING));
        ImageIcon plantMiniImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/plant.png"))
                .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING));
        ImageIcon cannonFoodMiniImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/cannonFood.png"))
                .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING));
        ImageIcon machineGunFoodMiniImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/machineGunFood.png"))
                .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING));
        ImageIcon repairFoodMiniImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/repairFood.png"))
                .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING));
        ImageIcon block1MiniImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/block1.png"))
                .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING));
        ImageIcon starMiniImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/star.png"))
                .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING));
        ImageIcon mineMiniImageIcon = new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/mine.png"))
                .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING));

        mapCells = new MapJButton[30][30];
        MapJButton[][] temporaryMapCells = new MapJButton[30][30];
        JButton[][] componentsCells = new JButton[5][2];
        componentsCells[0][0] = new JButton(soilImageIcon);
        componentsCells[0][1] = new JButton(wallImageIcon);
        componentsCells[1][0] = new JButton(teaselImageIcon);
        componentsCells[1][1] = new JButton(plantImageIcon);
        componentsCells[2][0] = new JButton(cannonFoodImageIcon);
        componentsCells[2][1] = new JButton(machineGunFoodImageIcon);
        componentsCells[3][0] = new JButton(repairFoodImageIcon);
        componentsCells[3][1] = new JButton(block1ImageIcon);
        componentsCells[4][0] = new JButton(starImageIcon);
        componentsCells[4][1] = new JButton(mineImageIcon);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                int finalJ = j;
                int finalI = i;
                componentsCells[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedItem = (2 * finalI) + (finalJ);
                    }
                });
                componentsPanel.add(componentsCells[i][j]);
            }
        }

        //reading from the map!

        BufferedReader mapReader = new BufferedReader(new FileReader(mapEditorFile));

        for (int i = 0; i < 30; i++) {
            String currentLine = mapReader.readLine();
            String[] currentLineCells = currentLine.split(" ");
            for (int j = 0; j < 30; j++) {
                mapCells[i][j] = new MapJButton();
                mapCells[i][j].setButtonState(Integer.parseInt(currentLineCells[j]));
                mapCells[i][j].setIcon(mapCells[i][j].getButtonState());
                temporaryMapCells[i][j] = new MapJButton();
            }
        }
        mapReader.close();

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                int finalI = i;
                int finalJ = j;
                mapCells[finalI][finalJ].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        switch (selectedItem) {
                            case 0:
                                if (mapCells[finalI][finalJ].getButtonState() != 0) {//This means that the user probably wants to change his/her cell's component
                                    mapCells[finalI][finalJ].setIcon(soilMiniImageIcon);
                                }
                                break;
                            case 1:
                                if (mapCells[finalI][finalJ].getButtonState() != 1) {//This means that the user probably wants to change his/her cell's component
                                    mapCells[finalI][finalJ].setIcon(wallMiniImageIcon);
                                }
                                break;
                            case 2:
                                if (mapCells[finalI][finalJ].getButtonState() != 2) {//This means that the user probably wants to change his/her cell's component{
                                    mapCells[finalI][finalJ].setIcon(teaselMiniImageIcon);
                                }
                                break;
                            case 3:
                                if (mapCells[finalI][finalJ].getButtonState() != 3) {//This means that the user probably wants to change his/her cell's component
                                    mapCells[finalI][finalJ].setIcon(plantMiniImageIcon);
                                }
                                break;
                            case 4:
                                if (mapCells[finalI][finalJ].getButtonState() != 4) {//This means that the user probably wants to change his/her cell's component
                                    mapCells[finalI][finalJ].setIcon(cannonFoodMiniImageIcon);
                                }
                                break;
                            case 5:
                                if (mapCells[finalI][finalJ].getButtonState() != 5) {//This means that the user probably wants to change his/her cell's component
                                    mapCells[finalI][finalJ].setIcon(machineGunFoodMiniImageIcon);
                                }
                                break;
                            case 6:
                                if (mapCells[finalI][finalJ].getButtonState() != 6) {//This means that the user probably wants to change his/her cell's component
                                    mapCells[finalI][finalJ].setIcon(repairFoodImageIcon);
                                }
                                break;
                            case 7:
                                if (mapCells[finalI][finalJ].getButtonState() != 7) {//This means that the user probably wants to change his/her cell's component
                                    mapCells[finalI][finalJ].setIcon(block1MiniImageIcon);
                                }
                                break;
                            case 8:
                                if (mapCells[finalI][finalJ].getButtonState() != 8) {//This means that the user probably wants to change his/her cell's component
                                    mapCells[finalI][finalJ].setIcon(starMiniImageIcon);
                                }
                                break;
                            case 9:
                                if (mapCells[finalI][finalJ].getButtonState() != 9) {//This means that the user probably wants to change his/her cell's component
                                    mapCells[finalI][finalJ].setIcon(mineMiniImageIcon);
                                }
                                break;
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (mapCells[finalI][finalJ].getButtonState() != selectedItem) {
                            try {
                                mapCells[finalI][finalJ].setIcon(mapCells[finalI][finalJ].getButtonState());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        switch (selectedItem) {
                            case 0:
                                mapCells[finalI][finalJ].setIcon(soilMiniImageIcon);
                                mapCells[finalI][finalJ].setButtonState(selectedItem);
                                temporaryMapCells[finalI][finalJ] = mapCells[finalI][finalJ];
                                break;
                            case 1:
                                mapCells[finalI][finalJ].setIcon(wallMiniImageIcon);
                                mapCells[finalI][finalJ].setButtonState(selectedItem);
                                temporaryMapCells[finalI][finalJ] = mapCells[finalI][finalJ];
                                break;
                            case 2:
                                mapCells[finalI][finalJ].setIcon(teaselMiniImageIcon);
                                mapCells[finalI][finalJ].setButtonState(selectedItem);
                                temporaryMapCells[finalI][finalJ] = mapCells[finalI][finalJ];
                                break;
                            case 3:
                                mapCells[finalI][finalJ].setIcon(plantMiniImageIcon);
                                mapCells[finalI][finalJ].setButtonState(selectedItem);
                                temporaryMapCells[finalI][finalJ] = mapCells[finalI][finalJ];
                                break;
                            case 4:
                                mapCells[finalI][finalJ].setIcon(cannonFoodMiniImageIcon);
                                mapCells[finalI][finalJ].setButtonState(selectedItem);
                                temporaryMapCells[finalI][finalJ] = mapCells[finalI][finalJ];
                                break;
                            case 5:
                                mapCells[finalI][finalJ].setIcon(machineGunFoodMiniImageIcon);
                                mapCells[finalI][finalJ].setButtonState(selectedItem);
                                temporaryMapCells[finalI][finalJ] = mapCells[finalI][finalJ];
                                break;
                            case 6:
                                mapCells[finalI][finalJ].setIcon(repairFoodMiniImageIcon);
                                mapCells[finalI][finalJ].setButtonState(selectedItem);
                                temporaryMapCells[finalI][finalJ] = mapCells[finalI][finalJ];
                                break;
                            case 7:
                                mapCells[finalI][finalJ].setIcon(block1MiniImageIcon);
                                mapCells[finalI][finalJ].setButtonState(selectedItem);
                                temporaryMapCells[finalI][finalJ] = mapCells[finalI][finalJ];
                                break;
                            case 8:
                                mapCells[finalI][finalJ].setIcon(starMiniImageIcon);
                                mapCells[finalI][finalJ].setButtonState(selectedItem);
                                temporaryMapCells[finalI][finalJ] = mapCells[finalI][finalJ];
                                break;
                            case 9:
                                mapCells[finalI][finalJ].setIcon(mineMiniImageIcon);
                                mapCells[finalI][finalJ].setButtonState(selectedItem);
                                temporaryMapCells[finalI][finalJ] = mapCells[finalI][finalJ];
                                break;
                        }
                    }
                });
            }
        }

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                mapPanel.add(mapCells[i][j]);
            }
        }

        saveAndPlayButton = new JButton("Save & Play");
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapEditorFrame.dispose();
            }
        });

        centerPanel.add(mapPanel);
        centerPanel.add(componentsPanel);
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);
        southPanel.add(saveAndPlayButton);
        southPanel.add(cancelButton);
        backgroundPanel.add(southPanel, BorderLayout.SOUTH);
        mapEditorFrame.add(backgroundPanel);
    }

    /**
     * displays the mak editors frame
     */
    public void showGUI() {
        mapEditorFrame.setVisible(true);
    }

    /**
     * the map editor frames getter
     *
     * @return returns mapEditorFrame
     */
    public JFrame getMapEditorFrame() {
        return mapEditorFrame;
    }

    /**
     * the save&play buttons getter
     *
     * @return returns save&playButon
     */
    public JButton getSaveAndPlayButton() {
        return saveAndPlayButton;
    }

    /**
     * the map editor files getter
     *
     * @return returns mapEditorFile
     */
    public File getMapEditorFile() {
        return mapEditorFile;
    }

    /**
     * the map editor's map cells getter
     *
     * @return returns mapCells
     */
    public MapJButton[][] getMapCells() {
        return mapCells;
    }
}