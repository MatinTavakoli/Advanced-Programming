package main;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * a button that extends jbutton and has a button state that decides what image icon is on the button!
 * @author Matin Tavakoli
 */
public class MapJButton extends JButton {
    private int buttonState;

    /**
     * the buttons simplest constructor
     */
    public MapJButton() {
        buttonState = 0;
    }

    /**
     * the buttons constructor with an image as its parameter
     * @param icon the image we give to the constructor!
     */
    public MapJButton(Icon icon) {
        super(icon);
        buttonState = 0;
    }

    /**
     * buttonStates getter
     * @return returns buttonState
     */
    public int getButtonState() {
        return buttonState;
    }

    /**
     * buttonStates setter
     * @param buttonState the buttonState we want our button to be assigned to
     */
    public void setButtonState(int buttonState) {
        this.buttonState = buttonState;
    }

    /**
     * a method that sets an image on the button corresponding to the vutton state it has
     * @param buttonState the buttons state
     * @throws IOException in case the image isn't there or can't read it etc
     */
    public void setIcon(int buttonState) throws IOException {
        switch (buttonState) {
            case 0:
                super.setIcon(new ImageIcon(ImageIO.read(getClass()
                        .getResource("/pictures/soil1.png"))
                        .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING)));
                break;
            case 1:
                super.setIcon(new ImageIcon(ImageIO.read(getClass()
                        .getResource("/pictures/wall.png"))
                        .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING)));
                break;
            case 2:
                super.setIcon(new ImageIcon(ImageIO.read(getClass()
                        .getResource("/pictures/teasel.png"))
                        .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING)));
                break;
            case 3:
                super.setIcon(new ImageIcon(ImageIO.read(getClass()
                        .getResource("/pictures/plant.png"))
                        .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING)));
                break;
            case 4:
                super.setIcon(new ImageIcon(ImageIO.read(getClass()
                        .getResource("/pictures/cannonFood.png"))
                        .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING)));
                break;
            case 5:
                super.setIcon(new ImageIcon(ImageIO.read(getClass()
                        .getResource("/pictures/machineGunFood.png"))
                        .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING)));
                break;
            case 6:
                super.setIcon(new ImageIcon(ImageIO.read(getClass()
                        .getResource("/pictures/repairFood.png"))
                        .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING)));
                break;
            case 7:
                super.setIcon(new ImageIcon(ImageIO.read(getClass()
                        .getResource("/pictures/block1.png"))
                        .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING)));
                break;
            case 8:
                super.setIcon(new ImageIcon(ImageIO.read(getClass()
                        .getResource("/pictures/star.png"))
                        .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING)));
                break;
            case 9:
                super.setIcon(new ImageIcon(ImageIO.read(getClass()
                        .getResource("/pictures/mine.png"))
                        .getScaledInstance(15, 20, Image.SCALE_AREA_AVERAGING)));
                break;
        }
    }
}

