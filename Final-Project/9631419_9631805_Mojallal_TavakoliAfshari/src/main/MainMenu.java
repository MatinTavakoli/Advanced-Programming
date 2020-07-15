package main;

import game.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainMenu {

    private JFrame mainMenuJFrame;
    private JButton[] difficultyButtons = new JButton[3];
    private JButton[] levelsButtons = new JButton[2];

    private JButton easyButton = new JButton("EASY");
    private JButton normalButton = new JButton("NORMAL");
    private JButton hardButton = new JButton("HARD");
    private JButton mapEditorButton = new JButton("MAP EDITOR");
    private JButton defaultMapButton = new JButton("DEFAULT MAP");
    private JButton level2Button = new JButton("LEVEL 2");
    private JButton level3Button = new JButton("LEVEL 3");

    /**
     * constructor for the main menu class
     *
     * @throws IOException exception
     */
    public MainMenu() throws IOException {

        mainMenuJFrame = new JFrame("Main Menu");
        mainMenuJFrame.setSize(1200, 700);
        mainMenuJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainMenuJFrame.setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);

        difficultyButtons[0] = easyButton;
        difficultyButtons[1] = normalButton;
        difficultyButtons[2] = hardButton;

        levelsButtons[0] = level2Button;
        levelsButtons[1] = level3Button;

        mapEditorButton.setBounds(new Rectangle(220, 225, 175, 30));
        defaultMapButton.setBounds(new Rectangle(220, 270, 200, 30));
        easyButton.setBounds(new Rectangle(220, 315, 85, 30));
        normalButton.setBounds(new Rectangle(220, 360, 135, 30));
        hardButton.setBounds(new Rectangle(220, 405, 90, 30));
        level2Button.setBounds(new Rectangle(220, 450, 200, 30));
        level3Button.setBounds(new Rectangle(220, 495, 200, 30));

        easyButton.setFont(new Font("Arial", Font.BOLD, 15));
        normalButton.setFont(new Font("Arial", Font.BOLD, 15));
        hardButton.setFont(new Font("Arial", Font.BOLD, 15));
        mapEditorButton.setFont(new Font("Arial", Font.BOLD, 15));
        defaultMapButton.setFont(new Font("Arial", Font.BOLD, 15));
        level2Button.setFont(new Font("Arial", Font.BOLD, 15));
        level3Button.setFont(new Font("Arial", Font.BOLD, 15));

        easyButton.setForeground(Color.WHITE);
        easyButton.setBackground(Color.BLACK);

        normalButton.setForeground(Color.WHITE);
        normalButton.setBackground(Color.BLACK);

        hardButton.setForeground(Color.WHITE);
        hardButton.setBackground(Color.BLACK);

        mapEditorButton.setForeground(Color.WHITE);
        mapEditorButton.setBackground(Color.BLACK);

        defaultMapButton.setForeground(Color.WHITE);
        defaultMapButton.setBackground(Color.BLACK);

        level2Button.setForeground(Color.WHITE);
        level2Button.setBackground(Color.BLACK);

        level3Button.setForeground(Color.WHITE);
        level3Button.setBackground(Color.BLACK);

        JLabel mainMenuLabel = new JLabel(new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/Startup2.png"))
                .getScaledInstance(1200, 700, Image.SCALE_AREA_AVERAGING)));

        mainMenuLabel.setBounds(0, 0, 1200, 700);

        backgroundPanel.add(easyButton);
        backgroundPanel.add(normalButton);
        backgroundPanel.add(hardButton);
        backgroundPanel.add(mapEditorButton);
        backgroundPanel.add(defaultMapButton);
        backgroundPanel.add(level2Button);
        backgroundPanel.add(level3Button);
        backgroundPanel.add(mainMenuLabel);

        mainMenuJFrame.add(backgroundPanel);
        mainMenuJFrame.setVisible(true);
    }

    /**
     * getter for frame
     *
     * @return frame
     */
    public JFrame getMainMenuJFrame() {
        return mainMenuJFrame;
    }

    /**
     * getter for buttons
     *
     * @return button
     */
    public JButton[] getDifficultyButtons() {
        return difficultyButtons;
    }

    /**
     * getter for map editor button
     *
     * @return button
     */
    public JButton getMapEditorButton() {
        return mapEditorButton;
    }

    /**
     * getter for default map button
     *
     * @return button
     */
    public JButton getDefaultMapButton() {
        return defaultMapButton;
    }

    public JButton[] getLevelsButtons() {
        return levelsButtons;
    }
}
