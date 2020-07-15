import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class JDMProgram extends JFrame {
    private JFrame jDMProgramFrame;
    //For Adding downloads to an ArrayList!
    private JPanel jDMProgramMainPanel;
    private JPanel downloadItemsPanel;
    private JPanel jDMProgramScrollPanel = new JPanel(new BorderLayout());
    private JScrollPane jDMProgramScrollPane = new JScrollPane(jDMProgramScrollPanel);
    private JLabel processingLabel;
    private JLabel completedLabel;


    //Array List parameters!!
    private ArrayList<DownloadItem> downloads = new ArrayList<>();//All the downloads!
    private ArrayList<DownloadItem> queueDownloads = new ArrayList<>();//All the downloads in the queue!
    private ArrayList<DownloadItem> processingDownloads = new ArrayList<>();//The list of downloads that are running!
    private static ArrayList<DownloadItem> completedDownloads = new ArrayList<>();//The list of downloads that are completed!
    private ArrayList<DownloadItem> removedDownloads = new ArrayList<>();//The list of downloads that have been removed from the processing downloads!


    //File parameters!!
    private File listDotJdm = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\list.jdm");//A file that is going to give us the downloads details!
    private File queueDotJdm = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\queue.jdm");//A file that is going to give us the downloads details!
    private File removedDotJdm = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\removed.jdm");//A file that is going to give us the downloads details!
    private File filterDotJdm = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\filter.jdm");//A file that is going to give us the downloads details!
    private File settingsDotJdm = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\settings.jdm");//A file that is going to give us the downloads details!
    private File englishWordsDotJdm = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\EnglishWords.jdm");//A file that is going to give us the downloads details!
    private File persianWordsDotJdm = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\PersianWords.jdm");//A file that is going to give us the downloads details!
    private BufferedReader settingsReader = new BufferedReader(new FileReader(settingsDotJdm));//Defining a buffer reader that gives us the key information!
    private String previousLookAndFeelLine = settingsReader.readLine();//The first line of settings.jdm shows us our look and feel
    private String previousLookAndFeel = previousLookAndFeelLine.substring(0, previousLookAndFeelLine.indexOf(','));//For example for Nimbus,5 it gives Nimbus!
    private String languageIdentifierLine = settingsReader.readLine();//The second line of settings.jdm shows us our language!
    private String languageIdentifier = languageIdentifierLine.substring(0, languageIdentifierLine.indexOf(','));//Fro example for Persian,2 it gives Persian!
    private String simultaneousDownloads = settingsReader.readLine();//See how much downloads we can handle!
    private String defaultPath = settingsReader.readLine();//See how much downloads we can handle!
    private String[] englishWords = showEnglishWords();
    private String[] persianWords = showPersianWords();

    public JDMProgram() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException, AWTException {
        jDMProgramFrame = new JFrame(wordLanguageSelector(englishWords[0], persianWords[0], languageIdentifier));
        jDMProgramFrame.setSize(800, 600);
        jDMProgramFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jDMProgramFrame.setLocationRelativeTo(null);
        UIManager.setLookAndFeel(convertLookAndFeelNameToClassName(previousLookAndFeel));//Setting our look and feel to the last look and feel used in the jdm!
        JPanel jDMBackgroundPanel = new JPanel(new BorderLayout());
        jDMProgramMainPanel = new JPanel(new BorderLayout());
        JPanel jDMTopMainPanel = new JPanel(new GridLayout(2, 1, 5, 0));
        JPanel jDMSidePanel = new JPanel(new BorderLayout());


        //Defining the left hand side of the jDMFrame's contents!
        JPanel jDMSideCentralPanel = new JPanel(new BorderLayout());
        JPanel jDMSideOptionsPanel = new JPanel(new GridLayout(6, 1));//The panel for the side options!
        JPanel processingPanel = new JPanel(new BorderLayout());//A panel for the processing section!
        JButton processingIconButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/processing.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        processingLabel = new JLabel(wordLanguageSelector(englishWords[12], persianWords[12], languageIdentifier) + " (" + processingDownloads.size() + "/" + downloads.size() + ")");//*/
        processingPanel.add(processingIconButton, BorderLayout.WEST);
        processingPanel.add(processingLabel, BorderLayout.CENTER);
        JPanel completedPanel = new JPanel(new BorderLayout());
        JButton completedIconButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/completed.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        completedLabel = new JLabel(wordLanguageSelector(englishWords[13], persianWords[13], languageIdentifier) + "(" + completedDownloads.size() + ")");
        JButton settingsButton1 = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/settings2.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        JButton settingsButton2 = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/settings2.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        completedPanel.add(completedIconButton, BorderLayout.WEST);
        completedPanel.add(completedLabel, BorderLayout.CENTER);
        completedPanel.add(settingsButton1, BorderLayout.EAST);
        JPanel queuesPanel = new JPanel(new BorderLayout());
        JButton queuesIconButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/queue.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        JLabel queuesLabel = new JLabel(wordLanguageSelector(englishWords[14], persianWords[14], languageIdentifier));
        queuesPanel.add(queuesIconButton, BorderLayout.WEST);
        queuesPanel.add(queuesLabel, BorderLayout.CENTER);
        queuesPanel.add(settingsButton2, BorderLayout.EAST);
        JPanel searchPanel = new JPanel(new BorderLayout());
        JButton searchIconButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/magnifying glass.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        JLabel searchLabel = new JLabel(wordLanguageSelector(englishWords[15], persianWords[15], languageIdentifier));
        searchPanel.add(searchIconButton, BorderLayout.WEST);
        searchPanel.add(searchLabel, BorderLayout.CENTER);
        JPanel sortPanel = new JPanel(new BorderLayout());
        JButton sortIconButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/sort.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        JLabel sortLabel = new JLabel(wordLanguageSelector(englishWords[17], persianWords[17], languageIdentifier));
        sortPanel.add(sortIconButton, BorderLayout.WEST);
        sortPanel.add(sortLabel, BorderLayout.CENTER);


        //Defining our tools!
        JButton newDownloadToolButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/add.png")).getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        JButton pauseToolButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/pause.png")).getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        JButton resumeToolButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/play.png")).getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        JButton cancelToolButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/cancel.png")).getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        JButton removeToolButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/remove.png")).getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));


        //Defining settings!
        JButton settingsToolButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/settings.png")).getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        settingsToolButton.addActionListener(new SettingsListener());//Defined at the bottom of the class as a specific action listener!


        //Setting tooltip texts for our buttons!
        newDownloadToolButton.setToolTipText("Start a new download.");
        pauseToolButton.setToolTipText("Pause an active download.");
        resumeToolButton.setToolTipText("Resuming a previously paused download.");
        cancelToolButton.setToolTipText("Cancelling a Running Download.");
        removeToolButton.setToolTipText("Eliminating a Download from our Catalog.");
        settingsToolButton.setToolTipText("Controlling simultaneous downloads, Defining download paths & Selecting the Desired Look-and-Feel.");
        processingIconButton.setToolTipText("The downloads that are running.");
        completedIconButton.setToolTipText("The downloads that have been completed.");
        queuesIconButton.setToolTipText("Queues.");
        searchIconButton.setToolTipText("Searching a file name or url");
        sortIconButton.setToolTipText("sorting our downloads by time, size & file name");
        JLabel eagleLabel = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/logo.png")).getScaledInstance(170, 100, Image.SCALE_AREA_AVERAGING)));
        JToolBar jDMToolBar = new JToolBar();


        //Setting our buttons action listeners!
        settingsButton1.addActionListener(new SettingsListener());//Declared at the bottom of the class!
        settingsButton2.addActionListener(new SettingsListener());//Declared at the bottom of the class!
        pauseToolButton.addActionListener(new PauseListener());//Declared at the bottom of the class!
        resumeToolButton.addActionListener(new ResumeListener());//Declared at the bottom of the class!
        cancelToolButton.addActionListener(new CancelListener());//Declared at the bottom of the class!
        removeToolButton.addActionListener(new RemoveListener());//Declared at the bottom of the class!
        newDownloadToolButton.addActionListener(new NewDownloadListener());//Declared at the bottom of the class!
        completedIconButton.addActionListener(new CompletedListener());
        queuesIconButton.addActionListener(new QueueListener());
        searchIconButton.addActionListener(new SearchListener());
        sortIconButton.addActionListener(new SortListener());


        //Adding our buttons to the toolbar!
        jDMToolBar.add(newDownloadToolButton);
        jDMToolBar.addSeparator(new Dimension(20, 25));
        jDMToolBar.add(resumeToolButton);
        jDMToolBar.add(pauseToolButton);
        jDMToolBar.add(cancelToolButton);
        jDMToolBar.addSeparator(new Dimension(20, 25));
        jDMToolBar.add(removeToolButton);
        jDMToolBar.add(settingsToolButton);
        JMenu downloadMenu = new JMenu(wordLanguageSelector(englishWords[1], persianWords[1], languageIdentifier));
        JMenu helpMenu = new JMenu(wordLanguageSelector(englishWords[10], persianWords[10], languageIdentifier));


        //Defining our menu items!
        JMenuItem newDownloadItem = new JMenuItem(wordLanguageSelector(englishWords[2], persianWords[2], languageIdentifier));
        JMenuItem pauseItem = new JMenuItem(wordLanguageSelector(englishWords[3], persianWords[3], languageIdentifier));
        JMenuItem resumeItem = new JMenuItem(wordLanguageSelector(englishWords[4], persianWords[4], languageIdentifier));
        JMenuItem cancelItem = new JMenuItem(wordLanguageSelector(englishWords[5], persianWords[5], languageIdentifier));
        JMenuItem removeItem = new JMenuItem(wordLanguageSelector(englishWords[6], persianWords[6], languageIdentifier));
        JMenuItem settingsItem = new JMenuItem(wordLanguageSelector(englishWords[7], persianWords[7], languageIdentifier));
        JMenuItem exitItem = new JMenuItem(wordLanguageSelector(englishWords[8], persianWords[8], languageIdentifier));
        JMenuItem exportItem = new JMenuItem(wordLanguageSelector(englishWords[9], persianWords[9], languageIdentifier));
        JMenuItem aboutItem = new JMenuItem(wordLanguageSelector(englishWords[11], persianWords[11], languageIdentifier));


        //Setting Mnemonics for our menus & menu items!
        downloadMenu.setMnemonic('D');
        helpMenu.setMnemonic('H');
        newDownloadItem.setMnemonic('N');
        pauseItem.setMnemonic('P');
        resumeItem.setMnemonic('R');
        cancelItem.setMnemonic('C');
        removeItem.setMnemonic('E');
        settingsItem.setMnemonic('S');
        exitItem.setMnemonic('X');
        exportItem.setMnemonic('O');
        aboutItem.setMnemonic('A');


        //Setting Accelerators for our menus & menu items!
        newDownloadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        pauseItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        resumeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        cancelItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        removeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        settingsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        exportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));


        //Setting action listeners for our components!
        newDownloadItem.addActionListener(new NewDownloadListener());
        pauseItem.addActionListener(new PauseListener());
        resumeItem.addActionListener(new ResumeListener());
        cancelItem.addActionListener(new CancelListener());
        removeItem.addActionListener(new RemoveListener());
        settingsItem.addActionListener(new SettingsListener());
        exitItem.addActionListener(new ExitListener());
        exportItem.addActionListener(new ExportListener());
        aboutItem.addActionListener(new AboutListener());


        //Adding our menu items to the menu!
        downloadMenu.add(newDownloadItem);
        downloadMenu.add(pauseItem);
        downloadMenu.add(resumeItem);
        downloadMenu.add(cancelItem);
        downloadMenu.add(removeItem);
        downloadMenu.add(settingsItem);
        downloadMenu.add(exitItem);
        downloadMenu.add(exportItem);
        helpMenu.add(aboutItem);


        //Defining our download items panel & opening them from our inventory!
        downloadItemsPanel = new JPanel(new GridLayout(1, 1, 5, 5));//The panel that consists of all the download items!
        restoreFromInventory();//Restoring the downloads from the last time the download manager was open!
        checkDownloads(downloads);//See whether there is a forbidden link or not?

        //Adding the components!
        JMenuBar jDMMenuBar = new JMenuBar();
        jDMMenuBar.add(downloadMenu);
        jDMMenuBar.add(helpMenu);
        jDMTopMainPanel.add(jDMMenuBar);
        jDMTopMainPanel.add(jDMToolBar);
        jDMProgramMainPanel.add(jDMTopMainPanel, BorderLayout.NORTH);
        jDMSidePanel.add(eagleLabel, BorderLayout.NORTH);
        jDMSideOptionsPanel.add(processingPanel);
        jDMSideOptionsPanel.add(completedPanel);
        jDMSideOptionsPanel.add(queuesPanel);
        jDMSideOptionsPanel.add(searchPanel);
        jDMSideOptionsPanel.add(sortPanel);
        jDMSideCentralPanel.add(jDMSideOptionsPanel, BorderLayout.NORTH);
        jDMSidePanel.add(jDMSideCentralPanel, BorderLayout.CENTER);
        jDMBackgroundPanel.add(jDMProgramMainPanel, BorderLayout.CENTER);
        jDMBackgroundPanel.add(jDMSidePanel, BorderLayout.WEST);
        jDMProgramFrame.add(jDMBackgroundPanel);


        //Declaring the system tray!
        SystemTray systemTray = SystemTray.getSystemTray();
        Image image = ImageIO.read(getClass().getResource("/Icons/ps/Icon.png"));//This shall be changed!
        PopupMenu popupMenu = new PopupMenu();
        TrayIcon icon = new TrayIcon(image, "JDM Program", popupMenu);
        MenuItem openItem = new MenuItem("Open JDM");
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDMProgramFrame.setVisible(true);
            }
        });
        popupMenu.add(openItem);

        MenuItem closeItem = new MenuItem("Close JDM");
        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemTray.getSystemTray().remove(icon);
                System.exit(0);
            }
        });
        popupMenu.add(closeItem);
        icon.setImageAutoSize(true);
        systemTray.add(icon);


        //Falsing our focuses!
        processingIconButton.setFocusable(false);//Testing!
        completedIconButton.setFocusable(false);//Testing!
        queuesIconButton.setFocusable(false);//Testing!
        searchIconButton.setFocusable(false);//Testing!
        sortIconButton.setFocusable(false);//Testing!
        settingsButton1.setFocusable(false);//Testing!
        settingsButton2.setFocusable(false);//Testing!
        newDownloadToolButton.setFocusable(false);//Testing!
        resumeToolButton.setFocusable(false);//Testing!
        pauseToolButton.setFocusable(false);//Testing!
        cancelToolButton.setFocusable(false);//Testing!
        removeToolButton.setFocusable(false);//Testing!
        settingsToolButton.setFocusable(false);//Testing!

    }

    /**
     * Updating our processing label!
     */
    public void updateProcessingLabelText() {
        processingLabel.setText(wordLanguageSelector(englishWords[12], persianWords[12], languageIdentifier) + "(" + processingDownloads.size() + "/" + downloads.size() + ")");
    }

    /**
     * Updating our completed label!
     */

    public void updateCompletedLabelText() {
        completedLabel.setText(wordLanguageSelector(englishWords[13], persianWords[13], languageIdentifier) + "(" + completedDownloads.size() + ")");
    }

    public void showGUI() {
        jDMProgramFrame.setVisible(true);
    }

    /**
     * An action listener that opens the settings panel!
     *
     * @Override
     */
    public class SettingsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JDMSettings jDMSettings = JDMSettings.getJDMSettingsInstance();//So I Make only one JDMSettingsFrame!
                jDMSettings.getJDMSettingsFrame().setVisible(true);
                jDMSettings.getJDMDesiredLookAndFeelComboBox().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String desiredLookAndFeel = jDMSettings.getJDMDesiredLookAndFeelComboBox().getSelectedItem().toString();//Getting our desired look&feel from the settings combo box!
                        jDMSettings.setDesiredLookAndFeel(desiredLookAndFeel);//Setting our look&feel to the desired look&feel
                        SwingUtilities.updateComponentTreeUI(jDMProgramFrame);
                    }
                });
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (UnsupportedLookAndFeelException e1) {
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class NewDownloadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame newDownloadFrame = new JFrame(wordLanguageSelector(englishWords[2], persianWords[2], languageIdentifier));
            newDownloadFrame.setSize(400, 400);
            newDownloadFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            newDownloadFrame.setLocationRelativeTo(null);
            newDownloadFrame.setVisible(true);
            JPanel newDownloadPanel = new JPanel(new BorderLayout());
            JPanel topPanel = new JPanel(new GridLayout(4, 1));//A panel for the url and file decleration!
            JPanel uRLPanel = new JPanel(new BorderLayout());//A panel for the url textField!
            JPanel filePanel = new JPanel(new BorderLayout());
            JPanel fileDestinationPanel = new JPanel(new BorderLayout());//A panel that shows us where to save the download!
            JPanel downloadMethodsPanel = new JPanel(new GridLayout(1, 3, 5, 5));//A panel that we select our download method from!
            JPanel timePanel = new JPanel(new BorderLayout());
            JPanel timeParametersPanel = new JPanel(new GridLayout(2, 2));//the things that are in the time panel(JRadioButton, JSpinner etc.)
            timeParametersPanel.setVisible(false);//It is false until the user selects the scheduling download method
            JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
            JLabel uRLIcon = new JLabel();
            JLabel fileIcon = new JLabel();
            JLabel fileDestinationLabel = new JLabel(wordLanguageSelector(englishWords[18], persianWords[18], languageIdentifier) + " : ");
            JRadioButton downloadNowRadioButton = new JRadioButton(wordLanguageSelector(englishWords[19], persianWords[19], languageIdentifier));
            JRadioButton scheduleDownloadRadioButton = new JRadioButton(wordLanguageSelector(englishWords[20], persianWords[20], languageIdentifier));
            JRadioButton addToQueueRadioButton = new JRadioButton(wordLanguageSelector(englishWords[21], persianWords[21], languageIdentifier));
            ButtonGroup downloadMethodsGroupButton = new ButtonGroup();
            downloadMethodsGroupButton.add(downloadNowRadioButton);
            downloadMethodsGroupButton.add(scheduleDownloadRadioButton);
            downloadMethodsGroupButton.add(addToQueueRadioButton);
            JButton cancelButton = new JButton(wordLanguageSelector(englishWords[5], persianWords[5], languageIdentifier));
            JButton oKButton = new JButton(wordLanguageSelector(englishWords[22], persianWords[22], languageIdentifier));
            JTextField uRLTextField = new JTextField();
            JTextField fileNameTextField = new JTextField();
            JTextField fileDestinationTextField = new JTextField(defaultPath);
            JButton fileBrowserButton = null;//Just so it doesn't give us errors!
            try {
                fileBrowserButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/browse.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            JFileChooser fileChooser = new JFileChooser();
            JRadioButton downloadStartsAtaSpecifiedTimeRadioButton = new JRadioButton(wordLanguageSelector(englishWords[23], persianWords[23], languageIdentifier));
            JPanel downloadStartsAtaSpecifiedTimePanel = new JPanel(new GridLayout(1, 3));
            JRadioButton downloadStartsAfterSpecifiedSecondsRadioButton = new JRadioButton(wordLanguageSelector(englishWords[24], persianWords[24], languageIdentifier));


            //Setting tool tips for our components!
            uRLTextField.setToolTipText("The downloads URL");
            fileNameTextField.setToolTipText("The name of the file");
            fileDestinationTextField.setToolTipText("The file's destination");
            downloadStartsAtaSpecifiedTimeRadioButton.setToolTipText("For example, the download starts at 13:12:34");
            downloadStartsAfterSpecifiedSecondsRadioButton.setToolTipText("For example, the download starts after 5 seconds");


            //Defining our time spinners!
            SpinnerModel secondsFromNowSpinnerModel = new SpinnerNumberModel(0, 0, 2147483647, 1);//Why 2147483647?Because it is the biggest integer i can give to my spinner!
            JSpinner downloadStartsAfterSpecifiedSecondsSpinner = new JSpinner(secondsFromNowSpinnerModel);//After how many seconds you want to start?
            SpinnerModel hoursTimerSpinnerModel = new SpinnerNumberModel(0, 0, 24, 1);
            SpinnerModel minutesTimerSpinnerModel = new SpinnerNumberModel(0, 0, 60, 1);
            SpinnerModel secondsTimerSpinnerModel = new SpinnerNumberModel(0, 0, 60, 1);
            JSpinner hoursTimerSpinner = new JSpinner(hoursTimerSpinnerModel);//A spinner that defines in which hour you want to start the download!
            JSpinner minutesTimerSpinner = new JSpinner(minutesTimerSpinnerModel);//A spinner that defines in which minute you want to start the download!
            JSpinner secondsTimerSpinner = new JSpinner(secondsTimerSpinnerModel);//A spinner that defines in which second you want to start the download!


            //Setting tool tips for our spinners!
            hoursTimerSpinner.setToolTipText("Hour");
            minutesTimerSpinner.setToolTipText("Minutes");
            secondsTimerSpinner.setToolTipText("Seconds");


            //Adding the time spinners to it's panel!
            downloadStartsAtaSpecifiedTimePanel.add(hoursTimerSpinner);
            downloadStartsAtaSpecifiedTimePanel.add(minutesTimerSpinner);
            downloadStartsAtaSpecifiedTimePanel.add(secondsTimerSpinner);
            ButtonGroup timeGroupButton = new ButtonGroup();
            timeGroupButton.add(downloadStartsAfterSpecifiedSecondsRadioButton);
            timeGroupButton.add(downloadStartsAtaSpecifiedTimeRadioButton);


            //Adding our download methods buttons!
            downloadMethodsPanel.add(downloadNowRadioButton);
            downloadMethodsPanel.add(scheduleDownloadRadioButton);
            downloadMethodsPanel.add(addToQueueRadioButton);


            //Adding our time components!
            timeParametersPanel.add(downloadStartsAtaSpecifiedTimeRadioButton);
            timeParametersPanel.add(downloadStartsAtaSpecifiedTimePanel);
            timeParametersPanel.add(downloadStartsAfterSpecifiedSecondsRadioButton);
            timeParametersPanel.add(downloadStartsAfterSpecifiedSecondsSpinner);
            timePanel.add(timeParametersPanel, BorderLayout.NORTH);


            //Setting action listener for our components!
            fileBrowserButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fileChooser.showDialog(newDownloadFrame, "OK");
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    if (fileChooser.getSelectedFile().toString() != null) {
                        fileDestinationTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                }
            });
            downloadNowRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeParametersPanel.setVisible(false);
                }
            });
            scheduleDownloadRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeParametersPanel.setVisible(true);
                }
            });
            addToQueueRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeParametersPanel.setVisible(false);
                }
            });
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    newDownloadFrame.dispose();
                }
            });
            oKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (downloadNowRadioButton.isSelected() || scheduleDownloadRadioButton.isSelected() || addToQueueRadioButton.isSelected()) {
                        if (downloadNowRadioButton.isSelected()) {
                            DownloadItem downloadItem = null;
                            try {
                                downloadItem = new DownloadItem(fileNameTextField.getText(), 0, 50, 5, uRLTextField.getText(), fileDestinationTextField.getText(), 0);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            downloadItem.getFileModeButton().setFocusable(false);
                            downloadItem.getDownloadItemDetailsButton().setFocusable(false);
                            if (processingDownloads.size() < Integer.parseInt(simultaneousDownloads)) {
                                downloads.add(downloadItem);
                                try {
                                    int b = 4;
                                    if (isDownloadValid(downloadItem)) {//If the download is not a filtered link
                                        processingDownloads.add(downloadItem);
                                        JOptionPane.showMessageDialog(newDownloadFrame, "Your input has been successfully saved!", "Added Download", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(newDownloadFrame, "Sorry! We are not allowed to download filtered links!", "Forbidden Link", JOptionPane.ERROR_MESSAGE);
                                        downloads.remove(downloadItem);
                                    }

                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(newDownloadFrame, "Sorry! Your Download Must Wait.", "No More Downloads!", JOptionPane.ERROR_MESSAGE);
                            }
                            sortDownloadsByTime(downloads);//First we sort them!
                            setDownloadsOnTheList();//Revalidating the downloads in the downloads list!
                            SwingUtilities.updateComponentTreeUI(jDMProgramFrame);
                            newDownloadFrame.dispose();
                            int un = 5;//Just so it doesn't give us duplication code errors!
                        }
                        if (scheduleDownloadRadioButton.isSelected()) {
                            int time1 = ((int) hoursTimerSpinner.getValue() * 3600) + ((int) minutesTimerSpinner.getValue() * 60) + ((int) secondsTimerSpinner.getValue());
                            int time2 = (int) downloadStartsAfterSpecifiedSecondsSpinner.getValue();
                            if (downloadStartsAtaSpecifiedTimeRadioButton.isSelected()) {
                                if (time1 != 0 && time2 != 0)
                                    JOptionPane.showMessageDialog(newDownloadFrame, "Please fill in only one of the scheduling options", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                                else if (time1 == 0 && time2 != 0)
                                    JOptionPane.showMessageDialog(newDownloadFrame, "Please determine your time scheduling in the correct box!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                                else {
                                    JOptionPane.showMessageDialog(newDownloadFrame, "Your input has been successfully saved!", "Successful Scheduling", JOptionPane.INFORMATION_MESSAGE);
                                    DownloadItem downloadItem = null;
                                    try {
                                        downloadItem = new DownloadItem(fileNameTextField.getText(), 0, 50, 5, uRLTextField.getText(), fileDestinationTextField.getText(), time1);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    downloadItem.getFileModeButton().setFocusable(false);
                                    downloadItem.getDownloadItemDetailsButton().setFocusable(false);
                                    if (processingDownloads.size() < Integer.parseInt(simultaneousDownloads)) {
                                        downloads.add(downloadItem);
                                        try {
                                            if (isDownloadValid(downloadItem)) {//If the download is not a filtered link
                                                processingDownloads.add(downloadItem);
                                                JOptionPane.showMessageDialog(newDownloadFrame, "Your input has been successfully saved!", "Added Download", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(newDownloadFrame, "Sorry! We are not allowed to download filtered links!", "Forbidden Link", JOptionPane.ERROR_MESSAGE);
                                                downloads.remove(downloadItem);
                                                String a = "now";
                                            }
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(newDownloadFrame, "Sorry! Your Download Must Wait.", "No More Downloads!", JOptionPane.ERROR_MESSAGE);
                                    }
                                    sortDownloadsByTime(downloads);//First we sort them!
                                    setDownloadsOnTheList();//Revalidating the downloads in the downloads list!
                                    SwingUtilities.updateComponentTreeUI(jDMProgramFrame);
                                    newDownloadFrame.dispose();
                                    int unnecessaryInt = 0;//Just so it doesn't give us duplication code warnings!
                                }
                            } else if (downloadStartsAfterSpecifiedSecondsRadioButton.isSelected()) {
                                if (time2 != 0 && time1 != 0)
                                    JOptionPane.showMessageDialog(newDownloadFrame, "Please fill in only one of the scheduling options", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                                else if (time2 == 0 && time1 != 0) {
                                    JOptionPane.showMessageDialog(newDownloadFrame, "Please determine your time scheduling in the correct box!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(newDownloadFrame, "Your input has been successfully saved!", "Successful Scheduling", JOptionPane.INFORMATION_MESSAGE);
                                    DownloadItem downloadItem = null;
                                    try {
                                        downloadItem = new DownloadItem(fileNameTextField.getText(), 0, 50, 5, uRLTextField.getText(), fileDestinationTextField.getText(), time2);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    downloadItem.getFileModeButton().setFocusable(false);
                                    downloadItem.getDownloadItemDetailsButton().setFocusable(false);
                                    if (processingDownloads.size() < Integer.parseInt(simultaneousDownloads)) {
                                        downloads.add(downloadItem);
                                        try {
                                            int c = 6;
                                            if (isDownloadValid(downloadItem)) {//If the download is not a filtered link
                                                processingDownloads.add(downloadItem);
                                                JOptionPane.showMessageDialog(newDownloadFrame, "Your input has been successfully saved!", "Added Download", JOptionPane.INFORMATION_MESSAGE);
                                            } else {
                                                JOptionPane.showMessageDialog(newDownloadFrame, "Sorry! We are not allowed to download filtered links!", "Forbidden Link", JOptionPane.ERROR_MESSAGE);
                                                downloads.remove(downloadItem);
                                                long time = 23;
                                            }
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(newDownloadFrame, "Sorry! Your Download Must Wait.", "No More Downloads!", JOptionPane.ERROR_MESSAGE);
                                    }
                                    sortDownloadsByTime(downloads);//First we sort them!
                                    setDownloadsOnTheList();//Revalidating the downloads in the downloads list!
                                    SwingUtilities.updateComponentTreeUI(jDMProgramFrame);
                                    newDownloadFrame.dispose();
                                }
                            } else {
                                JOptionPane.showMessageDialog(newDownloadFrame, "You didn't schedule your download!", "No Input", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        if (addToQueueRadioButton.isSelected()) {
                            DownloadItem downloadItem = null;
                            try {
                                downloadItem = new DownloadItem(fileNameTextField.getText(), 0, 50, 5, uRLTextField.getText(), fileDestinationTextField.getText(), 0);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            downloadItem.getFileModeButton().setFocusable(false);
                            downloadItem.getDownloadItemDetailsButton().setFocusable(false);
                            try {
                                if (isDownloadValid(downloadItem)) {//If the download is not a filtered link
                                    queueDownloads.add(downloadItem);
                                    JOptionPane.showMessageDialog(newDownloadFrame, "Your input has been successfully saved!", "Added Download", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(newDownloadFrame, "Sorry! We are not allowed to download filtered links!", "Forbidden Link", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            try {
                                checkDownloads(queueDownloads);//See whether there is a forbidden link or not?
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            sortDownloadsByTime(queueDownloads);//First we sort them!
                            setDownloadsOnTheList();//Revalidating the downloads in the downloads list!
                            SwingUtilities.updateComponentTreeUI(jDMProgramFrame);
                            newDownloadFrame.dispose();
                            int un = 5;//Just so it doesn't give us duplication code errors!
                        }
                    } else {
                        JOptionPane.showMessageDialog(newDownloadFrame, "You didn't select a downloading method", "No Download Method", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            try {
                uRLIcon = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/url.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
                fileIcon = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/file.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e1) {
                e1.printStackTrace();
            }


            //Adding our components!
            uRLPanel.add(uRLIcon, BorderLayout.WEST);
            uRLPanel.add(uRLTextField, BorderLayout.CENTER);
            filePanel.add(fileIcon, BorderLayout.WEST);
            filePanel.add(fileNameTextField, BorderLayout.CENTER);
            fileDestinationPanel.add(fileDestinationLabel, BorderLayout.WEST);
            fileDestinationPanel.add(fileDestinationTextField, BorderLayout.CENTER);
            fileDestinationPanel.add(fileBrowserButton, BorderLayout.EAST);
            topPanel.add(uRLPanel);
            topPanel.add(filePanel);
            topPanel.add(fileDestinationPanel);
            topPanel.add(downloadMethodsPanel);
            newDownloadPanel.add(topPanel, BorderLayout.NORTH);
            newDownloadPanel.add(timePanel, BorderLayout.CENTER);
            bottomPanel.add(cancelButton);
            bottomPanel.add(oKButton);
            newDownloadPanel.add(bottomPanel, BorderLayout.SOUTH);
            newDownloadFrame.add(newDownloadPanel);
        }
    }

    public class CompletedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            completedDownloads.clear();//Clearing our completed downloads!
            for (DownloadItem downloadItem : downloads) {
                if (downloadItem.getFileModeButton().getBackground().equals(Color.GREEN))
                    completedDownloads.add(downloadItem);
                if (processingDownloads.contains(downloadItem))
                    processingDownloads.remove(downloadItem);//This means that that download is no longer a processing download!
            }
            JFrame completedDownloadsFrame = new JFrame("Completed Downloads");
            completedDownloadsFrame.setSize(400, 400);
            completedDownloadsFrame.setLocationRelativeTo(null);
            completedDownloadsFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            completedDownloadsFrame.setVisible(true);
            JPanel backGroundPanel = new JPanel(new BorderLayout());
            JPanel completedDownloadsPanel = new JPanel(new GridLayout(completedDownloads.size(), 1));
            for (DownloadItem downloadItem : completedDownloads)
                completedDownloadsPanel.add(downloadItem.getDownloadItemPanel());
            JScrollPane completedDownloadsScrollPane = new JScrollPane(completedDownloadsPanel);
            backGroundPanel.add(completedDownloadsScrollPane, BorderLayout.NORTH);
            completedDownloadsFrame.add(backGroundPanel);
            completedDownloadsFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    super.windowClosed(e);
                    updateProcessingLabelText();
                    updateCompletedLabelText();
                    setDownloadsOnTheList();
                }
            });
        }
    }

    public class QueueListener implements ActionListener {

        JPanel queueDownloadListPanel;

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame queueFrame = new JFrame(wordLanguageSelector(englishWords[14], persianWords[14], languageIdentifier));
            queueFrame.setSize(400, 400);
            queueFrame.setLocationRelativeTo(null);
            queueFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            queueFrame.setVisible(true);
            JPanel menuPanel = new JPanel(new BorderLayout());
            JMenuBar queueMenuBar = new JMenuBar();
            JMenu queueMenu = new JMenu("Menu");
            JMenuItem startMenuItem = new JMenuItem("Start");
            queueMenu.add(startMenuItem);
            JPanel queueBackgroundPanel = new JPanel(new BorderLayout());//A background panel
            queueDownloadListPanel = new JPanel(new GridLayout(queueDownloads.size() + 2, 1, 5, 5));//A panel for the list of downloads!
            JButton addButton = new JButton();
            JButton removeButton = new JButton();
            JPanel addAndRemovePanel = new JPanel(new GridLayout(1, 2));
            try {
                addButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/add.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
                removeButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/remove.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e1) {
                e1.printStackTrace();
            }


            //Setting mnemonics and accelerators for our menu items
            queueMenu.setMnemonic('M');
            startMenuItem.setMnemonic('S');
            startMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));


            //Setting action listeners for our menu items!
            startMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (queueDownloads.size() != 0) {
                        if (processingDownloads.size() < Integer.parseInt(simultaneousDownloads)) {
                            queueDownloads.get(0).getDownloadWorker().execute();//This means that that download will be added to the processing downloads!
                            downloads.add(queueDownloads.get(0));
                            processingDownloads.add(queueDownloads.get(0));
                            queueDownloads.remove(0);
                            updateProcessingLabelText();
                            updateCompletedLabelText();
                            setDownloadsOnTheList();
                            queueFrame.dispose();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(queueFrame,"You don't have any donwloads in your queue!","No Downloads in Queue",JOptionPane.ERROR_MESSAGE);
                    }

                }
            });


            //Falsing our focuses!
            addButton.setFocusable(false);
            removeButton.setFocusable(false);
            addButton.addActionListener(new NewDownloadListener());
            removeButton.addActionListener(new RemoveListener());
            addAndRemovePanel.add(addButton);
            addAndRemovePanel.add(removeButton);
            queueMenuBar.add(queueMenu);
            menuPanel.add(queueMenuBar, BorderLayout.WEST);
            queueDownloadListPanel.add(menuPanel);
            for (DownloadItem downloadItem : queueDownloads) {
                if (!downloadItem.getDownloadItemPanel().getBackground().equals(Color.RED) && !downloadItem.getDownloadItemPanel().getBackground().equals(Color.GREEN)) {
                    downloadItem.getDownloadItemPanel().setBackground(null);//So none of them are considered selected!!
                    queueDownloadListPanel.add(downloadItem.getDownloadItemPanel());
                    downloadItem.getDownloadItemPanel().addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            if (SwingUtilities.isLeftMouseButton(e)) {
                                downloadItem.getFileModeButton().setBackground(Color.BLUE);
                                for (DownloadItem downloadItemSample : queueDownloads)
                                    if (!downloadItem.equals(downloadItemSample) && !downloadItemSample.getFileModeButton().getBackground().equals(Color.RED) && !downloadItemSample.getFileModeButton().getBackground().equals(Color.GREEN)) {//If it is neither the selected download nor a forbidden or completed download...
                                        downloadItemSample.getFileModeButton().setBackground(null);
                                    }
                                //SwingUtilities.updateComponentTreeUI(queueFrame);
                                int unnecessary = 1;//Just so it doesn't give us code duplication warnings!
                            }
                        }
                    });
                }
            }
            queueDownloadListPanel.add(addAndRemovePanel);
            JScrollPane queueScrollPane = new JScrollPane(queueDownloadListPanel);
            queueBackgroundPanel.add(queueScrollPane, BorderLayout.NORTH);
            queueFrame.add(queueBackgroundPanel);
        }
    }

    public class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame searchFrame = new JFrame(wordLanguageSelector(englishWords[15], persianWords[15], languageIdentifier));
            searchFrame.setSize(450, 100);
            searchFrame.setLocationRelativeTo(null);
            searchFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            searchFrame.setVisible(true);
            JPanel backGroundPanel = new JPanel(new GridLayout(2, 2, 10, 5));
            JTextField fileNameSearchTextField = new JTextField(wordLanguageSelector(englishWords[25], persianWords[25], languageIdentifier));
            JTextField uRLSearchTextField = new JTextField(wordLanguageSelector(englishWords[26], persianWords[26], languageIdentifier));
            JButton fileNameSearchButton = new JButton(wordLanguageSelector(englishWords[15], persianWords[15], languageIdentifier));
            JButton uRLSearchButton = new JButton(wordLanguageSelector(englishWords[15], persianWords[15], languageIdentifier));
            fileNameSearchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<DownloadItem> searchedByNameDownloads = searchFileNames(fileNameSearchTextField.getText());
                    SearchResult searchResult = new SearchResult(searchedByNameDownloads, wordLanguageSelector(englishWords[16], persianWords[16], languageIdentifier));//Opening a frame that shows us the result downloads!
                    searchFrame.dispose();
                }
            });
            uRLSearchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<DownloadItem> searchedByURLDownloads = searchURLs(uRLSearchTextField.getText());
                    SearchResult searchResult = new SearchResult(searchedByURLDownloads, wordLanguageSelector(englishWords[16], persianWords[16], languageIdentifier));//Opening a frame that shows us the result downloads!
                    searchFrame.dispose();
                }
            });
            backGroundPanel.add(fileNameSearchTextField);
            backGroundPanel.add(uRLSearchTextField);
            backGroundPanel.add(fileNameSearchButton);
            backGroundPanel.add(uRLSearchButton);
            searchFrame.add(backGroundPanel);
        }
    }

    public class SortListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Sort sort;
            try {
                sort = new Sort(wordLanguageSelector(englishWords[17], persianWords[17], languageIdentifier));
                sort.getoKButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (sort.getTimeRadioButton().isSelected())
                            sortDownloadsByTime(sort, downloads);
                        else if (sort.getFileSizeRadioButton().isSelected())
                            sortDownloadsByFileSize(sort, downloads);
                        else if (sort.getFileNameRadioButton().isSelected())
                            sortDownloadsByFileName(downloads);
                        sort.getSortFrame().dispose();//Closing the program
                    }
                });
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class PauseListener implements ActionListener {

        /**
         * defining a listener for our pause buttons!
         *
         * @Override
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (downloads.size() != 0) {
                boolean isAnyButtonSelected = false;//To determine whether the user selected a download or not!
                for (DownloadItem downloadItem : processingDownloads)
                    if (downloadItem.getFileModeButton().getBackground().equals(Color.BLUE)) {
                        isAnyButtonSelected = true;//This means that we detected a selected download!
                        if (!downloadItem.getStatusLabel().getText().equals("Status : Paused")) {
                            downloadItem.setStatusLabel(new JLabel("Status : Paused", SwingConstants.CENTER));
                            downloadItem.setDownloadSpeed(0);
                            downloadItem.getDownloadWorker().cancel(true);
                            JOptionPane.showMessageDialog(jDMProgramFrame, downloadItem.getFileName() + " is paused", "Download Paused", JOptionPane.INFORMATION_MESSAGE);
                            downloadItem.getFileModeButton().setBackground(null);//Back to normal!
                            downloadItem.getFileModeButton().setFocusPainted(false);//Because we aren't focusing on it anymore!
                            //setDownloadsOnTheList();//*/
                            SwingUtilities.updateComponentTreeUI(jDMProgramFrame);
                        } else {
                            JOptionPane.showMessageDialog(jDMProgramFrame, "That download is already paused!", "Redundant Pausing", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                if (!isAnyButtonSelected)//This means that the user didn't select a download to pause it!
                    JOptionPane.showMessageDialog(jDMProgramFrame, "You didn't select a download!", "No downloads selected", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(jDMProgramFrame, "You don't have any downloads!", "Empty Download List", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public class ResumeListener implements ActionListener {

        /**
         * defining a listener for our resume buttons!
         *
         * @Override
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (downloads.size() != 0) {
                boolean isAnyButtonSelected = false;//To determine whether the user selected a download or not!
                for (DownloadItem downloadItem : processingDownloads)
                    if (downloadItem.getFileModeButton().getBackground().equals(Color.BLUE)) {
                        isAnyButtonSelected = true;//This means that we detected a selected download!
                        if (!downloadItem.getStatusLabel().getText().equals("Status : Downloading") && !downloadItem.getStatusLabel().getText().equals("Status : Canceled")) {//If it isn't already resumed or canceled!
                            downloadItem.setStatusLabel(new JLabel("Status : Downloading", SwingConstants.CENTER));
                            downloadItem.setDownloadSpeed(5);
                            JOptionPane.showMessageDialog(jDMProgramFrame, downloadItem.getFileName() + " is resumed", "Download Resumed", JOptionPane.INFORMATION_MESSAGE);
                            downloadItem.getFileModeButton().setBackground(null);//Back to normal!
                            //Executing our SwingWorker!
                            if (!downloadItem.getFileModeButton().getBackground().equals(Color.RED)) {
                                /*if(downloadItem.getDownloadWorker().isCancelled())
                                    downloadItem.getDownloadWorker().cancel(false);*/
                                downloadItem.getDownloadWorker().execute();
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            SwingUtilities.updateComponentTreeUI(jDMProgramFrame);
                        } else if (downloadItem.getStatusLabel().getText().equals("Status : Downloading")) {
                            JOptionPane.showMessageDialog(jDMProgramFrame, "That download is already running!", "Redundant Resuming", JOptionPane.WARNING_MESSAGE);
                        } else if (downloadItem.getStatusLabel().getText().equals("Status : Canceled")) {
                            JOptionPane.showMessageDialog(jDMProgramFrame, "Sorry! Were not allowed to resume a canceled download!", "Download Canceled", JOptionPane.WARNING_MESSAGE);

                        }
                    }
                if (!isAnyButtonSelected)//This means that the user didn't select a download to pause it!
                    JOptionPane.showMessageDialog(jDMProgramFrame, "You didn't select a download!", "No downloads selected", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(jDMProgramFrame, "You don't have any downloads!", "Empty Download List", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public class CancelListener implements ActionListener {

        /**
         * defining a listener for our cancel buttons!
         *
         * @Override
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (downloads.size() != 0) {
                boolean isAnyButtonSelected = false;//To determine whether the user selected a download or not!
                for (DownloadItem downloadItem : processingDownloads)
                    if (downloadItem.getFileModeButton().getBackground().equals(Color.BLUE)) {
                        isAnyButtonSelected = true;//This means that we detected a selected download!
                        if (!downloadItem.getStatusLabel().getText().equals("Status : Canceled")) {//If it isn't already resumed!
                            downloadItem.setStatusLabel(new JLabel("Status : Canceled", SwingConstants.CENTER));
                            downloadItem.setDownloadSpeed(0);
                            downloadItem.setProgressSize(0);//Because we canceled it!
                            JOptionPane.showMessageDialog(jDMProgramFrame, downloadItem.getFileName() + " is canceled", "Download Canceled", JOptionPane.INFORMATION_MESSAGE);
                            downloadItem.getFileModeButton().setBackground(null);//Back to normal!
                            //downloadItem.getDownloadItemPanel().repaint();//*/
                            SwingUtilities.updateComponentTreeUI(jDMProgramFrame);
                        } else {
                            JOptionPane.showMessageDialog(jDMProgramFrame, "That download is already running!", "Redundant Cancelling", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                if (!isAnyButtonSelected)//This means that the user didn't select a download to pause it!
                    JOptionPane.showMessageDialog(jDMProgramFrame, "You didn't select a download!", "No downloads selected", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(jDMProgramFrame, "You don't have any downloads!", "Empty Download List", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public class RemoveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (downloads.size() != 0) {
                boolean isAnyButtonSelected = false;//To determine whether the user selected a download or not!
                for (DownloadItem downloadItem : processingDownloads)
                    if (downloadItem.getFileModeButton().getBackground().equals(Color.BLUE)) {
                        isAnyButtonSelected = true;//This means that we detected a selected download!
                        downloads.remove(downloadItem);
                        processingDownloads.remove(downloadItem);
                        removedDownloads.add(downloadItem);//Adding it to the deleted downloads list!
                        JOptionPane.showMessageDialog(jDMProgramFrame, "You successfully removed " + downloadItem.getFileName(), "Removed", JOptionPane.INFORMATION_MESSAGE);
                        downloadItem.getFileModeButton().setBackground(null);//Back to normal!
                        setDownloadsOnTheList();//Revalidating the downloads in the downloads list!
                        // SwingUtilities.updateComponentTreeUI(jDMFrame);

                        break;
                    }
                if (!isAnyButtonSelected)//This means that the user didn't select a download to pause it!
                    JOptionPane.showMessageDialog(jDMProgramFrame, "You didn't select a download!", "No downloads selected", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(jDMProgramFrame, "You don't have any downloads!", "Empty Download List", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("You have successfully closed your program! (at " + Date.from(Instant.now()).toString() + ")");
            try (FileWriter downloadsWriter = new FileWriter(listDotJdm); FileWriter queueWriter = new FileWriter(queueDotJdm); FileWriter removedDownloadsWriter = new FileWriter(removedDotJdm)) {
                downloadsWriter.write(saveDownloads());
                queueWriter.write(saveQueuedDownloads());
                removedDownloadsWriter.write(saveRemovedDownloads());
                downloadsWriter.close();
                queueWriter.close();
                removedDownloadsWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }
    }

    public class ExportListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String zipFileName = ("inventory.zip");
                FileOutputStream fos = new FileOutputStream(zipFileName);
                ZipOutputStream zos = new ZipOutputStream(fos);
                zos.putNextEntry(new ZipEntry(listDotJdm.getName()));
                byte[] listBytes = Files.readAllBytes(Paths.get(listDotJdm.getPath()));
                zos.write(listBytes, 0, listBytes.length);
                zos.closeEntry();
                zos.putNextEntry(new ZipEntry(queueDotJdm.getName()));
                byte[] queueBytes = Files.readAllBytes(Paths.get(queueDotJdm.getPath()));
                zos.write(queueBytes, 0, queueBytes.length);
                zos.closeEntry();
                zos.putNextEntry(new ZipEntry(removedDotJdm.getName()));
                byte[] removedBytes = Files.readAllBytes(Paths.get(removedDotJdm.getPath()));
                zos.write(removedBytes, 0, removedBytes.length);
                zos.closeEntry();
                zos.putNextEntry(new ZipEntry(filterDotJdm.getName()));
                byte[] filterBytes = Files.readAllBytes(Paths.get(filterDotJdm.getPath()));
                zos.write(filterBytes, 0, filterBytes.length);
                zos.closeEntry();
                zos.putNextEntry(new ZipEntry(settingsDotJdm.getName()));
                byte[] settingsBytes = Files.readAllBytes(Paths.get(settingsDotJdm.getPath()));
                zos.write(settingsBytes, 0, settingsBytes.length);
                zos.closeEntry();
                zos.putNextEntry(new ZipEntry(englishWordsDotJdm.getName()));
                byte[] englishWordsBytes = Files.readAllBytes(Paths.get(englishWordsDotJdm.getPath()));
                zos.write(englishWordsBytes, 0, englishWordsBytes.length);
                zos.closeEntry();
                zos.putNextEntry(new ZipEntry(persianWordsDotJdm.getName()));
                byte[] persianWordsBytes = Files.readAllBytes(Paths.get(persianWordsDotJdm.getPath()));
                zos.write(persianWordsBytes, 0, persianWordsBytes.length);
                zos.closeEntry();
                zos.close();

            } catch (FileNotFoundException ex) {
                System.err.format("The file %s does not exist", listDotJdm.getPath());
            } catch (IOException ex) {
                System.err.println("I/O error: " + ex);
            }

        }
    }

    public class AboutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame aboutFrame = new JFrame();//Making a frame for our "about"!
            aboutFrame.setSize(600, 125);
            aboutFrame.setLocationRelativeTo(null);
            aboutFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            aboutFrame.setResizable(false);
            aboutFrame.setVisible(true);
            JPanel aboutPanel = new JPanel(new BorderLayout());//Making a panel for our "about"!
            JTextArea aboutTheProgrammer = new JTextArea("Seyed Matin Tavakoli Afshari" + "\n" + "9631805" + "\n" + "2018-05-16 till 2018-06-01" + "\n" + "This is a small prototype of the real thing.Please use with caution!");
            JButton oKButton = new JButton("OK");
            aboutTheProgrammer.setEditable(false);
            aboutPanel.add(aboutTheProgrammer, BorderLayout.CENTER);
            aboutPanel.add(oKButton, BorderLayout.SOUTH);
            aboutFrame.add(aboutPanel);
            oKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    aboutFrame.dispose();
                }
            });
        }
    }

    public void restoreFromInventory() {
        try {
            BufferedReader listReader = new BufferedReader(new FileReader(listDotJdm));
            BufferedReader queueReader = new BufferedReader(new FileReader(queueDotJdm));
            String downloadDetailsString = listReader.readLine();
            String queueDownloadDetailsString = queueReader.readLine();
            while (downloadDetailsString != null) {
                String[] nonProcessedDownloadDetails = downloadDetailsString.split(",");
                DownloadItem downloadItem = new DownloadItem(nonProcessedDownloadDetails[0], Integer.parseInt(nonProcessedDownloadDetails[1]), Integer.parseInt(nonProcessedDownloadDetails[2]), Integer.parseInt(nonProcessedDownloadDetails[3]), nonProcessedDownloadDetails[4], nonProcessedDownloadDetails[5], Integer.parseInt(nonProcessedDownloadDetails[6]));//Recreating new Downloads by the details that we restored!
                downloads.add(downloadItem);
                if (downloadItem.getFileModeButton().getBackground().equals(Color.GREEN)) //It has been completed!
                    completedDownloads.add(downloadItem);
                else if (isDownloadValid(downloadItem))
                    processingDownloads.add(downloadItem);
                downloadDetailsString = listReader.readLine();//Increment!!
            }
            listReader.close();
            setDownloadsOnTheList();
            while (queueDownloadDetailsString != null) {
                String[] nonProcessedQueueDownloadDetails = queueDownloadDetailsString.split(",");
                DownloadItem queueDownloadItem = new DownloadItem(nonProcessedQueueDownloadDetails[0], Integer.parseInt(nonProcessedQueueDownloadDetails[1]), Integer.parseInt(nonProcessedQueueDownloadDetails[2]), Integer.parseInt(nonProcessedQueueDownloadDetails[3]), nonProcessedQueueDownloadDetails[4], nonProcessedQueueDownloadDetails[5], Integer.parseInt(nonProcessedQueueDownloadDetails[6]));//Recreating new Downloads by the details that we restored!
                queueDownloads.add(queueDownloadItem);
                queueDownloadDetailsString = queueReader.readLine();//Increment!!
            }
            queueReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a method that writes all the downloads details in a string!
     *
     * @return returns the String that contains all of the downloads and their details
     */
    public String saveDownloads() {
        String fileName = "";
        sortDownloadsByTime(downloads);//First we sort them!
        for (DownloadItem downloadItem : downloads) {
            fileName += (downloadItem.getFileName() + "," + downloadItem.getProgressSize() + "," + downloadItem.getFileSize() + "," + downloadItem.getDownloadSpeed() + "," + downloadItem.getDownloadURL() + "," + downloadItem.getDownloadDestination() + "," + downloadItem.getDownloadStartTime() + "\n");
        }
        return fileName;
    }

    public String saveRemovedDownloads() {
        String fileName = "";
        for (DownloadItem downloadItem : removedDownloads) {
            fileName += (downloadItem.getFileName() + "," + downloadItem.getProgressSize() + "," + downloadItem.getFileSize() + "," + downloadItem.getDownloadURL() + "," + downloadItem.getDownloadDestination() + "," + "\n");
        }
        return fileName;
    }

    public String saveQueuedDownloads() {
        String fileName = "";
        sortDownloadsByTime(downloads);//First we sort them!
        for (DownloadItem downloadItem : queueDownloads) {
            fileName += (downloadItem.getFileName() + "," + downloadItem.getProgressSize() + "," + downloadItem.getFileSize() + "," + downloadItem.getDownloadSpeed() + "," + downloadItem.getDownloadURL() + "," + downloadItem.getDownloadDestination() + "," + downloadItem.getDownloadStartTime() + "\n");
        }
        return fileName;
    }

    /**
     * putting the downloads in the scroll panel!
     */
    public void setDownloadsOnTheList() {
        if (downloadItemsPanel != null) {
            downloadItemsPanel.removeAll();
        }
        downloadItemsPanel.setLayout(new GridLayout(downloads.size(), 1, 5, 5));
        for (DownloadItem downloadItem : processingDownloads) {
            downloadItemsPanel.add(downloadItem.getDownloadItemPanel());//Adding our download Item's panel to the download items list Panel
            downloadItem.getFileModeButton().setFocusable(false);
            downloadItem.getDownloadItemDetailsButton().setFocusable(false);
            downloadItem.getDownloadItemPanel().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        if (!downloadItem.getFileModeButton().getBackground().equals(Color.RED) && !downloadItem.getFileModeButton().getBackground().equals(Color.GREEN)) {
                            downloadItem.getFileModeButton().setBackground(Color.BLUE);
                            for (DownloadItem downloadItemSample : processingDownloads)
                                if (!downloadItem.equals(downloadItemSample) && !downloadItemSample.getFileModeButton().getBackground().equals(Color.RED) && !downloadItem.getFileModeButton().getBackground().equals(Color.GREEN)) {//If it is neither the selected download nor a forbidden download...
                                    downloadItemSample.getFileModeButton().setBackground(null);
                                }
                        } else if (downloadItem.getFileModeButton().getBackground().equals(Color.RED)) {
                            JOptionPane.showMessageDialog(jDMProgramFrame, "Sorry!You can't download from that link due to filtering reasons!", "Forbidden Link", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            });
        }
        updateProcessingLabelText();//Updating our processing label!
        updateCompletedLabelText();//Updating our completed label!
        new JPanel(new BorderLayout());
        jDMProgramScrollPanel.add(downloadItemsPanel, BorderLayout.NORTH);
        jDMProgramMainPanel.add(jDMProgramScrollPane, BorderLayout.CENTER);
        jDMProgramScrollPanel.repaint();
        SwingUtilities.updateComponentTreeUI(jDMProgramFrame);
    }

    /**
     * a homemade(!) method that returns a look and feels class!
     *
     * @param Name the look and feels name!
     * @return it return its class!
     */
    public String convertLookAndFeelNameToClassName(String Name) {
        if (Name.equals("Metal"))
            return "javax.swing.plaf.metal.MetalLookAndFeel";
        else if (Name.equals("Nimbus"))
            return "javax.swing.plaf.nimbus.NimbusLookAndFeel";
        else if (Name.equals("CDE/Motif"))
            return "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        else if (Name.equals("Windows"))
            return "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        else if (Name.equals("Windows Classic"))
            return "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
        else
            return null;
    }

    public boolean isDownloadValid(DownloadItem downloadItem) throws IOException {
        File filterLinks = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\filter.jdm");
        BufferedReader filterReader = new BufferedReader(new FileReader(filterLinks));
        String filterLink = filterReader.readLine();
        while (filterLink != null) {
            if (downloadItem.getDownloadURL().contains(filterLink)) //If it was a sub-link of the forbidden link!
                return false;
            filterLink = filterReader.readLine();//Increment!
        }
        return true;//In case nothing wrong happens!
    }

    public void checkDownloads(ArrayList<DownloadItem> iteratedDownloads) throws IOException {
        File filterLinks = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\filter.jdm");
        BufferedReader filterReader = new BufferedReader(new FileReader(filterLinks));
        String filterLink = filterReader.readLine();
        while (filterLink != null) {
            for (DownloadItem downloadItem : iteratedDownloads)
                if (downloadItem.getDownloadURL().contains(filterLink)) {//If it was a sub-link of the forbidden link!
                    downloadItem.getFileModeButton().setBackground(Color.RED);//This means that the download is forbidden!
                }
            filterLink = filterReader.readLine();//Increment!
        }
    }

    /**
     * A method that shows us  which downloads file names have the target sequence in them!
     *
     * @param target the string that the user gives to search through the file names!
     */
    public ArrayList<DownloadItem> searchFileNames(String target) {
        ArrayList<DownloadItem> searchedByNameDownloads = new ArrayList<>();
        for (DownloadItem downloadItem : downloads)
            if (downloadItem.getFileName().contains(target)) {
                searchedByNameDownloads.add(downloadItem);
            }
        return searchedByNameDownloads;
    }

    /**
     * A method that shows us  which downloads urls have the target sequence in them!
     *
     * @param target the string that the user gives to search through the urls!
     */
    public ArrayList<DownloadItem> searchURLs(String target) {
        ArrayList<DownloadItem> searchedByURLDownloads = new ArrayList<>();
        for (DownloadItem downloadItem : downloads)
            if (downloadItem.getDownloadURL().contains(target))
                searchedByURLDownloads.add(downloadItem);
        return searchedByURLDownloads;
    }


    public void sortDownloadsByTime(ArrayList<DownloadItem> iteratedDownloads) {
        for (int pass = 0; pass < iteratedDownloads.size() - 1; pass++) {
            for (int i = 0; i < iteratedDownloads.size() - pass - 1; i++) {
                if (iteratedDownloads.get(i).getDownloadStartTime() > iteratedDownloads.get(i + 1).getDownloadStartTime()) {
                    swapDownloads(iteratedDownloads, i, i + 1);
                } else if (iteratedDownloads.get(i).getDownloadStartTime() == iteratedDownloads.get(i + 1).getDownloadStartTime()) {
                    if (iteratedDownloads.get(i).getFileSize() > iteratedDownloads.get(i + 1).getFileSize())
                        swapDownloads(iteratedDownloads, i, i + 1);
                    else if (iteratedDownloads.get(i).getFileSize() == iteratedDownloads.get(i + 1).getFileSize()) {
                        if (iteratedDownloads.get(i).getFileName().compareToIgnoreCase(iteratedDownloads.get(i + 1).getFileName()) > 0)
                            swapDownloads(iteratedDownloads, i, i + 1);
                    }
                }
            }
        }
        setDownloadsOnTheList();
    }

    /**
     * Sorting them with a very easy bubble sort!!
     */
    public void sortDownloadsByTime(Sort sort, ArrayList<DownloadItem> iteratedDownloads) {
        for (int pass = 0; pass < iteratedDownloads.size() - 1; pass++) {
            for (int i = 0; i < iteratedDownloads.size() - pass - 1; i++) {
                if (iteratedDownloads.get(i).getDownloadStartTime() > iteratedDownloads.get(i + 1).getDownloadStartTime()) {
                    swapDownloads(iteratedDownloads, i, i + 1);
                } else if (iteratedDownloads.get(i).getDownloadStartTime() == iteratedDownloads.get(i + 1).getDownloadStartTime()) {
                    if (sort.getFileSizeRadioButton().isSelected()) {//If the next priority is file size
                        if (iteratedDownloads.get(i).getFileSize() > iteratedDownloads.get(i + 1).getFileSize()) {
                            swapDownloads(iteratedDownloads, i, i + 1);
                        } else if (iteratedDownloads.get(i).getFileSize() == (iteratedDownloads.get(i + 1).getFileSize())) {
                            if (sort.getFileNameRadioButton().isSelected())//If the next priority is file name
                                if (iteratedDownloads.get(i).getFileName().compareToIgnoreCase(iteratedDownloads.get(i + 1).getFileName()) > 0)
                                    swapDownloads(iteratedDownloads, i, i + 1);
                        }
                    } else if (sort.getFileNameRadioButton().isSelected()) {//If the next priority is file size
                        if (iteratedDownloads.get(i).getFileName().compareToIgnoreCase(downloads.get(i + 1).getFileName()) > 0)
                            swapDownloads(iteratedDownloads, i, i + 1);
                    }
                }
            }
        }
        setDownloadsOnTheList();
    }

    public void sortDownloadsByFileSize(Sort sort, ArrayList<DownloadItem> iteratedDownloads) {
        for (int pass = 0; pass < iteratedDownloads.size() - 1; pass++) {
            for (int i = 0; i < iteratedDownloads.size() - pass - 1; i++) {
                if (iteratedDownloads.get(i).getFileSize() > iteratedDownloads.get(i + 1).getFileSize()) {
                    swapDownloads(iteratedDownloads, i, i + 1);
                } else if (iteratedDownloads.get(i).getFileSize() == iteratedDownloads.get(i + 1).getFileSize()) {
                    if (sort.getFileNameRadioButton().isSelected()) {//If the next priority is file size
                        if (iteratedDownloads.get(i).getFileName().compareToIgnoreCase(iteratedDownloads.get(i + 1).getFileName()) > 0)
                            swapDownloads(iteratedDownloads, i, i + 1);
                    }
                }
            }
        }
        setDownloadsOnTheList();
    }

    public void sortDownloadsByFileName(ArrayList<DownloadItem> iteratedDownloads) {
        for (int pass = 0; pass < iteratedDownloads.size() - 1; pass++) {
            for (int i = 0; i < iteratedDownloads.size() - pass - 1; i++) {
                if (iteratedDownloads.get(i).getFileName().compareToIgnoreCase(iteratedDownloads.get(i + 1).getFileName()) > 0) {//If the ith download is bigger than the (i+1)th download(regarding upper case and lower case)
                    swapDownloads(iteratedDownloads, i, i + 1);
                }
            }
        }
        setDownloadsOnTheList();
    }

    /**
     * A home made method that swaps 2 of the array lists elements
     *
     * @param downloadItems the array list that we're using!
     * @param i             the index that we select!
     * @param j             the index thatwe want to swap i wth!
     */
    public void swapDownloads(ArrayList<DownloadItem> downloadItems, int i, int j) {
        DownloadItem temp;
        temp = downloadItems.get(i);
        downloadItems.set(i, downloadItems.get(j));
        downloadItems.set(j, temp);
    }

    public String[] showEnglishWords() throws IOException {
        File englishWordsDotJdm = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\EnglishWords.jdm");
        BufferedReader englishWordsReader = new BufferedReader(new FileReader(englishWordsDotJdm));
        String englishWordsLine = englishWordsReader.readLine();
        String[] englishWords = englishWordsLine.split(",");
        return englishWords;
    }

    public String[] showPersianWords() throws IOException {
        File persianWordsDotJdm = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\PersianWords.jdm");
        BufferedReader persianWordsReader = new BufferedReader(new FileReader(persianWordsDotJdm));
        String persianWordsLine = persianWordsReader.readLine();
        String[] persianWords = persianWordsLine.split("،");
        return persianWords;
    }

    public String wordLanguageSelector(String englishWord, String persianWord, String languageIdentifier) {
        if (languageIdentifier.equals("English"))
            return englishWord;
        else if (languageIdentifier.equals("فارسی"))
            return persianWord;
        else return null;//Just so it does'nt give us errors
    }

}