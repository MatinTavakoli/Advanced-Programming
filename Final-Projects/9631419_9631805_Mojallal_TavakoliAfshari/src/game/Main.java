/**
 * @author Kasra Mojallal & Matin Tavakoli
 * @since 7.14.2018
 */

package game;

import main.AudioPlayer;
import main.HowToPlayFrame;
import main.MainMenu;
import main.MapEditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Initialize the global thread-pool
        ThreadPool.init();

        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor
                (cursorImg, new Point(0, 0), "blank cursor");

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainMenu mainMenu = new MainMenu();
                    AudioPlayer menuSFX = new AudioPlayer("sound effects/startup.wav", 0);
                    for (int i = 0; i < 3; i++) {
                        int finalI = i;
                        mainMenu.getDifficultyButtons()[i].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    menuSFX.stop();
                                    AudioPlayer optionSelectedSFX = new AudioPlayer("sound effects/agree.wav", 0);
                                    mainMenu.getMainMenuJFrame().dispose();
                                    HowToPlayFrame howToPlayFrame = new HowToPlayFrame();
                                    howToPlayFrame.getHowToPlayJFrame().addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            howToPlayFrame.getHowToPlayJFrame().dispose();
                                            AudioPlayer mainSFX = new AudioPlayer("sound effects/Metal Fox.wav", 2);
                                            GameFrame frame = new GameFrame("JTanks!", finalI + 1, "map.txt");
                                            frame.setLocationRelativeTo(null); // put frame at center of screen
                                            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                                            frame.setVisible(true);
                                            frame.initBufferStrategy();
                                            frame.getContentPane().setCursor(blankCursor);

                                            // Create and execute the game-loop
                                            GameLoop game = new GameLoop(frame);
                                            game.init();
                                            ThreadPool.execute(game);
                                            // and the game starts ...
                                        }
                                    });
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    }
                    for (int i = 0; i < 2; i++) {
                        int finalI = i;
                        mainMenu.getLevelsButtons()[i].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    menuSFX.stop();
                                    AudioPlayer optionSelectedSFX = new AudioPlayer("sound effects/agree.wav", 0);
                                    mainMenu.getMainMenuJFrame().dispose();
                                    HowToPlayFrame howToPlayFrame = new HowToPlayFrame();
                                    howToPlayFrame.getHowToPlayJFrame().addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            howToPlayFrame.getHowToPlayJFrame().dispose();
                                            AudioPlayer mainSFX = new AudioPlayer("sound effects/Metal Fox.wav", 2);
                                            GameFrame frame = new GameFrame("JTanks!", 2, "map" + (finalI + 2) + ".txt");
                                            frame.setLocationRelativeTo(null); // put frame at center of screen
                                            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                                            frame.setVisible(true);
                                            frame.initBufferStrategy();
                                            frame.getContentPane().setCursor(blankCursor);

                                            // Create and execute the game-loop
                                            GameLoop game = new GameLoop(frame);
                                            game.init();
                                            ThreadPool.execute(game);
                                            // and the game starts ...
                                        }
                                    });
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });
                    }
                    mainMenu.getMapEditorButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                MapEditor mapEditor = new MapEditor();
                                mapEditor.showGUI();

                                mapEditor.getSaveAndPlayButton().addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        try {
                                            FileWriter mapWriter = new FileWriter(mapEditor.getMapEditorFile());
                                            StringBuilder mapStringBuilder = new StringBuilder();
                                            for (int i = 0; i < 30; i++) {
                                                for (int j = 0; j < 30; j++) {
                                                    switch (mapEditor.getMapCells()[i][j].getButtonState()) {
                                                        case 0:
                                                            if (j != 29)
                                                                mapStringBuilder.append("0 ");
                                                            else {
                                                                mapStringBuilder.append("0\r\n");
                                                            }
                                                            break;
                                                        case 1:
                                                            if (j != 29)
                                                                mapStringBuilder.append("1 ");
                                                            else {
                                                                mapStringBuilder.append("1\r\n");
                                                            }
                                                            break;
                                                        case 2:
                                                            if (j != 29)
                                                                mapStringBuilder.append("2 ");
                                                            else {
                                                                mapStringBuilder.append("2\r\n");
                                                            }
                                                            break;
                                                        case 3:
                                                            if (j != 29)
                                                                mapStringBuilder.append("3 ");
                                                            else {
                                                                mapStringBuilder.append("3\r\n");
                                                            }
                                                            break;
                                                        case 4:
                                                            if (j != 29)
                                                                mapStringBuilder.append("4 ");
                                                            else {
                                                                mapStringBuilder.append("4\r\n");
                                                            }
                                                            break;
                                                        case 5:
                                                            if (j != 29)
                                                                mapStringBuilder.append("5 ");
                                                            else {
                                                                mapStringBuilder.append("5\r\n");
                                                            }
                                                            break;
                                                        case 6:
                                                            if (j != 29)
                                                                mapStringBuilder.append("6 ");
                                                            else {
                                                                mapStringBuilder.append("6\r\n");
                                                            }
                                                            break;
                                                        case 7:
                                                            if (j != 29)
                                                                mapStringBuilder.append("7 ");
                                                            else {
                                                                mapStringBuilder.append("7\r\n");
                                                            }
                                                            break;
                                                        case 8:
                                                            if (j != 29)
                                                                mapStringBuilder.append("8 ");
                                                            else {
                                                                mapStringBuilder.append("8\r\n");
                                                            }
                                                            break;
                                                        case 9:
                                                            if (j != 29)
                                                                mapStringBuilder.append("9 ");
                                                            else {
                                                                mapStringBuilder.append("9\r\n");
                                                            }
                                                            break;
                                                    }
                                                }
                                            }
                                            mapWriter.write(String.valueOf(mapStringBuilder));
                                            mapWriter.close();
                                            mapEditor.getMapEditorFrame().dispose();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                });
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    mainMenu.getDefaultMapButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            AudioPlayer optionSelectedSFX = new AudioPlayer("sound effects/agree.wav", 0);
                            menuSFX.stop();
                            mainMenu.getMainMenuJFrame().dispose();
                            HowToPlayFrame howToPlayFrame = null;
                            try {
                                howToPlayFrame = new HowToPlayFrame();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            HowToPlayFrame finalHowToPlayFrame = howToPlayFrame;
                            howToPlayFrame.getHowToPlayJFrame().addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    finalHowToPlayFrame.getHowToPlayJFrame().dispose();
                                    AudioPlayer mainSFX = new AudioPlayer("sound effects/Metal Fox.wav", 2);
                                    GameFrame frame = new GameFrame("JTanks!", 1, "defaultMap.txt");
                                    frame.setLocationRelativeTo(null); // put frame at center of screen
                                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                                    frame.setVisible(true);
                                    frame.initBufferStrategy();
                                    frame.getContentPane().setCursor(blankCursor);

                                    // Create and execute the game-loop
                                    GameLoop game = new GameLoop(frame);
                                    game.init();
                                    ThreadPool.execute(game);
                                    // and the game starts ...
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
