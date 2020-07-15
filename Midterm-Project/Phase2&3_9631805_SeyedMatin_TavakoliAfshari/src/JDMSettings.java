import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class JDMSettings {
    private JFrame jDMSettingsFrame;
    private static JDMSettings jDMSettingsFrameInstance;
    private JComboBox<String> jDMDesiredLookAndFeelComboBox;
    private File settingsDotJdm = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\settings.jdm");
    private BufferedReader settingsReader = new BufferedReader(new FileReader(settingsDotJdm));
    private String defaultLookAndFeelLine = settingsReader.readLine();
    private String defaultLookAndFeelIndex = defaultLookAndFeelLine.substring(defaultLookAndFeelLine.indexOf(',') + 1);//The index of the selected look and feel!
    private String defaultLanguageLine = settingsReader.readLine();
    private String defaultLanguageIndex = defaultLanguageLine.substring(defaultLanguageLine.indexOf(',') + 1);//The index of the selected language!
    private String defaultSimultaneousDownloadsSpinnerValue = settingsReader.readLine();
    private String defaultDirectory = settingsReader.readLine();

    public JDMSettings() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        jDMSettingsFrame = new JFrame("Settings");
        jDMSettingsFrame.setSize(420, 120);
        jDMSettingsFrame.setLocationRelativeTo(null);
        jDMSettingsFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JPanel jDMSettingsPanel = new JPanel(new BorderLayout());
        JPanel jDMSettingsTopPanel = new JPanel(new GridLayout(1, 3));
        JPanel jDMSettingsCentralPanel = new JPanel(new BorderLayout());
        JPanel jDMSettingsPathDirectorPanel = new JPanel(new BorderLayout());
        JPanel jDMSettingsBottomPanel = new JPanel(new GridLayout(1, 2));
        jDMDesiredLookAndFeelComboBox = new JComboBox<>();
        jDMDesiredLookAndFeelComboBox.addItem("Windows");
        jDMDesiredLookAndFeelComboBox.addItem("Windows Classic");
        jDMDesiredLookAndFeelComboBox.addItem("Nimbus");
        jDMDesiredLookAndFeelComboBox.addItem("Metal");
        jDMDesiredLookAndFeelComboBox.addItem("CDE/Motif");
        jDMDesiredLookAndFeelComboBox.setSelectedIndex(Integer.parseInt(defaultLookAndFeelIndex));
        JLabel jDMSimultaneousDownloadsLabel = new JLabel("  Simultaneous Downloads : ");
        SpinnerModel simultaneousDownloadsModel = new SpinnerNumberModel(Integer.parseInt(defaultSimultaneousDownloadsSpinnerValue), 0, 10, 1);
        JSpinner jDMSimultaneousDownloadsSpinner = new JSpinner(simultaneousDownloadsModel);
        JTextField filePathTextField = new JTextField(defaultDirectory);
        JButton fileBrowserButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/browse.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        JFileChooser jDMSettingsFileChooser = new JFileChooser();
        JButton filteredLinksButton = new JButton("Filtered Links");
        JComboBox<String> languageComboBox = new JComboBox<>();
        languageComboBox.addItem("English");
        languageComboBox.addItem("فارسی");
        languageComboBox.setSelectedIndex(Integer.parseInt(defaultLanguageIndex));


        //Setting action listeners for our components!
        fileBrowserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDMSettingsFileChooser.showDialog(jDMSettingsFrame, "OK");
                jDMSettingsFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (jDMSettingsFileChooser.getSelectedFile().toString()!=null) {
                    filePathTextField.setText(jDMSettingsFileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        filteredLinksButton.addActionListener(new FilterListener());
        jDMSettingsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                String defaultPath = "C:\\Users\\atiye\\Desktop";//In case nothing has been chosen!
                if (filePathTextField.getText() != null)
                    defaultPath = filePathTextField.getText();//Emtying the text field value in the default path!
                try {
                    FileWriter settingsWriter = new FileWriter(settingsDotJdm);
                    settingsWriter.write(jDMDesiredLookAndFeelComboBox.getSelectedItem().toString() + "," + jDMDesiredLookAndFeelComboBox.getSelectedIndex() + "\n");
                    settingsWriter.write(languageComboBox.getSelectedItem().toString() + "," + languageComboBox.getSelectedIndex() + "\n");
                    settingsWriter.write(String.valueOf(jDMSimultaneousDownloadsSpinner.getValue()) + "\n");
                    settingsWriter.write(defaultPath + "\n");
                    settingsWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        //Setting tool tips for our components!
        jDMSimultaneousDownloadsLabel.setToolTipText("The number of downloads you want to run simultaneously.");
        jDMSimultaneousDownloadsSpinner.setToolTipText("A non-negative integer.");
        jDMSettingsFileChooser.setToolTipText("Browsing our destination.");
        filteredLinksButton.setToolTipText("The list of downloads that are forbidden.");


        //Adding our components!
        jDMSettingsTopPanel.add(jDMDesiredLookAndFeelComboBox);
        jDMSettingsTopPanel.add(jDMSimultaneousDownloadsLabel);
        jDMSettingsTopPanel.add(jDMSimultaneousDownloadsSpinner);
        jDMSettingsBottomPanel.add(filteredLinksButton);
        jDMSettingsBottomPanel.add(languageComboBox);
        jDMSettingsPathDirectorPanel.add(filePathTextField, BorderLayout.CENTER);
        jDMSettingsPathDirectorPanel.add(fileBrowserButton, BorderLayout.EAST);
        jDMSettingsCentralPanel.add(jDMSettingsPathDirectorPanel, BorderLayout.NORTH);
        jDMSettingsPanel.add(jDMSettingsTopPanel, BorderLayout.NORTH);
        jDMSettingsPanel.add(jDMSettingsCentralPanel, BorderLayout.CENTER);
        jDMSettingsPanel.add(jDMSettingsBottomPanel, BorderLayout.SOUTH);
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

    public class FilterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                FilteredLinks filteredLinks = new FilteredLinks();
            } catch (IOException e1) {
                e1.printStackTrace();
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