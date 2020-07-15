import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class JDMSettings {
    private JFrame jDMSettingsFrame;
    private static JDMSettings jDMSettingsFrameInstance;
    private JComboBox<String> jDMDesiredLookAndFeelComboBox;

    public JDMSettings() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        jDMSettingsFrame = new JFrame("Settings");
        jDMSettingsFrame.setSize(420, 300);
        jDMSettingsFrame.setLocationRelativeTo(null);
        jDMSettingsFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JPanel jDMSettingsPanel = new JPanel(new BorderLayout());
        JPanel jDMSettingsTopPanel = new JPanel(new GridLayout(1, 3));
        jDMDesiredLookAndFeelComboBox = new JComboBox<>();
        jDMDesiredLookAndFeelComboBox.addItem("Windows");
        jDMDesiredLookAndFeelComboBox.addItem("Windows Classic");
        jDMDesiredLookAndFeelComboBox.addItem("Nimbus");
        jDMDesiredLookAndFeelComboBox.addItem("Metal");
        jDMDesiredLookAndFeelComboBox.addItem("CDE/Motif");
        JLabel jDMSimultaneousDownloadsLabel = new JLabel("  Simultaneous Downloads : ");
        SpinnerModel simultaneousDownloadsModel = new SpinnerNumberModel(10, 0, 10, 1);
        JSpinner jDMSimultaneousDownloadsSpinner = new JSpinner(simultaneousDownloadsModel);
        JFileChooser jDMSettingsFileChooser=new JFileChooser();
        //Setting tool tops for our components!
        jDMSimultaneousDownloadsLabel.setToolTipText("The number of downloads you want to run simultaneously.");
        jDMSimultaneousDownloadsSpinner.setToolTipText("A non-negative integer.");
        jDMSettingsFileChooser.setToolTipText("Browsing our destination.");
        //Adding our components!
        jDMSettingsTopPanel.add(jDMDesiredLookAndFeelComboBox);
        jDMSettingsTopPanel.add(jDMSimultaneousDownloadsLabel);
        jDMSettingsTopPanel.add(jDMSimultaneousDownloadsSpinner);
        jDMSettingsPanel.add(jDMSettingsFileChooser,BorderLayout.CENTER);
        jDMSettingsPanel.add(jDMSettingsTopPanel, BorderLayout.NORTH);
        jDMSettingsFrame.add(jDMSettingsPanel);
        jDMSettingsFrame.setVisible(true);
    }

    public static JDMSettings getJDMSettingsInstance() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {//To make sure we make only one of these frames!
        if (jDMSettingsFrameInstance == null) {
            jDMSettingsFrameInstance = new JDMSettings();
        }
        return jDMSettingsFrameInstance;
    }

    public void setDesiredLookAndFeel(String desiredLookAndFeel) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (desiredLookAndFeel.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, fall back to cross-platform
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // not worth my time
            }
        }
    }

    public JFrame getJDMSettingsFrame() {
        return jDMSettingsFrame;
    }

    public JComboBox<String> getJDMDesiredLookAndFeelComboBox() {
        return jDMDesiredLookAndFeelComboBox;
    }
}