package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HowToPlayFrame {

    private JFrame howToPlayJFrame;

    /**
     * constructor for the how to play frame class
     * @throws IOException exception
     */
    public HowToPlayFrame() throws IOException {

        howToPlayJFrame = new JFrame("How to play");
        howToPlayJFrame.setSize(1200, 700);
        howToPlayJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        howToPlayJFrame.setLocationRelativeTo(null);
        JLabel howToPlayLabel = new JLabel(new ImageIcon(ImageIO.read(getClass()
                .getResource("/pictures/Startup.png"))
                .getScaledInstance(1200, 700, Image.SCALE_AREA_AVERAGING)));

        howToPlayJFrame.add(howToPlayLabel);
        howToPlayJFrame.setVisible(true);
    }

    /**
     * getter for frame
     * @return frame
     */
    public JFrame getHowToPlayJFrame() {
        return howToPlayJFrame;
    }


}
