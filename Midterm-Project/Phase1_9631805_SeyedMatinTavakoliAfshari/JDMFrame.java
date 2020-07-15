import com.sun.org.apache.xml.internal.security.utils.JDKXPathAPI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class JDMFrame extends JFrame {
    private JFrame jDMFrame;
    //For Adding downloads to an ArrayList!
    private ArrayList<DownloadItem> downloadItems = new ArrayList<>();

    public JDMFrame(String title) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException, AWTException {
        jDMFrame = new JFrame(title);
        jDMFrame.setSize(800, 600);
        jDMFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jDMFrame.setLocationRelativeTo(null);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JPanel jDMBackgroundPanel = new JPanel(new BorderLayout());
        JPanel jDMMainPanel = new JPanel(new BorderLayout());
        JPanel jDMTopMainPanel = new JPanel(new GridLayout(2, 1, 5, 0));
        JPanel jDMSidePanel = new JPanel(new BorderLayout());
        //Defining the left hand side of the jDMFrame's contents!
        JPanel jDMSideCentralPanel = new JPanel(new BorderLayout());
        JPanel jDMSideOptionsPanel = new JPanel(new GridLayout(3, 1));//The panel for the side options!
        int processingDownloads = 0;//The number of downloads that are running!
        int downloadsInQueue = 3;//The number of downloads that are either running or paused!
        int completedDownloads = 4;//The number of downloads that have been completed!
        JPanel processingPanel = new JPanel(new BorderLayout());//A panel for the processing section!
        JButton processingIconButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/processing.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        JLabel processingLabel = new JLabel("Processing (" + processingDownloads + "/" + downloadsInQueue + ")");
        processingPanel.add(processingIconButton, BorderLayout.WEST);
        processingPanel.add(processingLabel, BorderLayout.CENTER);
        JPanel completedPanel = new JPanel(new BorderLayout());
        JButton completedIconButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/completed.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        JLabel completedLabel = new JLabel("Completed(" + completedDownloads + ")");
        JButton settingsButton1 = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/settings2.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        JButton settingsButton2 = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/settings2.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        completedPanel.add(completedIconButton, BorderLayout.WEST);
        completedPanel.add(completedLabel, BorderLayout.CENTER);
        completedPanel.add(settingsButton1, BorderLayout.EAST);
        JPanel queuesPanel = new JPanel(new BorderLayout());
        JButton queuesIconButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/queue.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        JLabel queuesLabel = new JLabel("Queues");
        queuesPanel.add(queuesIconButton, BorderLayout.WEST);
        queuesPanel.add(queuesLabel, BorderLayout.CENTER);
        queuesPanel.add(settingsButton2, BorderLayout.EAST);
        queuesIconButton.addActionListener(new queueListener());
        //Defining our tools!
        JButton newDownloadToolButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/add.png")).getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        JButton pauseToolButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/pause.png")).getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        JButton resumeToolButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/play.png")).getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
        JButton cancelToolButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/delete.png")).getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING)));
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
        JLabel eagleLabel = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/logo.png")).getScaledInstance(170, 100, Image.SCALE_AREA_AVERAGING)));
        JToolBar jDMToolBar = new JToolBar();
        //Setting our buttons action listeners!
        settingsButton1.addActionListener(new SettingsListener());//Declared at the bottom of the class!
        settingsButton2.addActionListener(new SettingsListener());//Declared at the bottom of the class!
        pauseToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*for (DownloadItem downloadItem : downloadItems)
                    if(downloadItem.getFileModeButton().getBackground().equals(Color.GREEN)) {
                        try {
                            downloadItem=new DownloadItem(downloadItem.getFileName(),downloadItem.getFileModeButton().getText(),downloadItem.getFileSize(),downloadItem.getProgressSize(),0,downloadItem.getuRL(),downloadItem.getDownloadDestination(),downloadItem.getDownloadStartTime());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        SwingUtilities.updateComponentTreeUI(jDMFrame);
                        jDMFrame.invalidate();
                        jDMFrame.validate();
                    }*/
                JOptionPane.showMessageDialog(jDMFrame, "Coming Soon!", "Pause Button", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        resumeToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jDMFrame, "Coming Soon!", "Resume Button", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        cancelToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jDMFrame, "Coming Soon!", "Cancel Button", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        removeToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jDMFrame, "Coming Soon!", "Remove Button", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        newDownloadToolButton.addActionListener(new newDownloadListner());
        //Adding our buttons to the toolbar!
        jDMToolBar.add(newDownloadToolButton);
        jDMToolBar.addSeparator(new Dimension(20, 25));
        jDMToolBar.add(resumeToolButton);
        jDMToolBar.add(pauseToolButton);
        jDMToolBar.add(cancelToolButton);
        jDMToolBar.addSeparator(new Dimension(20, 25));
        jDMToolBar.add(removeToolButton);
        jDMToolBar.add(settingsToolButton);
        JMenu downloadMenu = new JMenu("Download");
        JMenu helpMenu = new JMenu("Help");
        //Defining our menu items!
        JMenuItem newDownloadItem = new JMenuItem("New Download");
        JMenuItem pauseItem = new JMenuItem("Pause");
        JMenuItem resumeItem = new JMenuItem("Resume");
        JMenuItem cancelItem = new JMenuItem("Cancel");
        JMenuItem removeItem = new JMenuItem("Remove");
        JMenuItem settingsItem = new JMenuItem("Settings");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem aboutItem = new JMenuItem("About");
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
        aboutItem.setMnemonic('A');
        //Setting Accelerators for our menus & menu items!
        newDownloadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        pauseItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        resumeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        cancelItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        removeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        settingsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        //Setting actions for our items!
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        aboutItem.addActionListener(new ActionListener() {
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
        });
        settingsItem.addActionListener(new SettingsListener());
        //Adding our menu items to the menu!
        downloadMenu.add(newDownloadItem);
        downloadMenu.add(pauseItem);
        downloadMenu.add(resumeItem);
        downloadMenu.add(cancelItem);
        downloadMenu.add(removeItem);
        downloadMenu.add(settingsItem);
        downloadMenu.add(exitItem);
        helpMenu.add(aboutItem);
        //Setting action listener for our components!
        pauseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*for (DownloadItem downloadItem : downloadItems)
                    if(downloadItem.getFileModeButton().getBackground().equals(Color.GREEN)) {
                        try {
                            downloadItem=new DownloadItem(downloadItem.getFileName(),downloadItem.getFileModeButton().getText(),downloadItem.getFileSize(),downloadItem.getProgressSize(),0,downloadItem.getuRL(),downloadItem.getDownloadDestination(),downloadItem.getDownloadStartTime());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        SwingUtilities.updateComponentTreeUI(jDMFrame);
                        jDMFrame.invalidate();
                        jDMFrame.validate();
                    }*/
                JOptionPane.showMessageDialog(jDMFrame, "Coming Soon!", "Pause Button", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        resumeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jDMFrame, "Coming Soon!", "Resume Button", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        cancelItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jDMFrame, "Coming Soon!", "Cancel Button", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*for (DownloadItem downloadItem : downloadItems)
                    if (downloadItem.getFileModeButton().getBackground().equals(Color.GREEN)) {
                        downloadItems.remove(downloadItem);
                        break;
                    }
                SwingUtilities.updateComponentTreeUI(jDMFrame);*/
                JOptionPane.showMessageDialog(jDMFrame, "Coming Soon!", "Remove Button", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        newDownloadItem.addActionListener(new newDownloadListner());

        //Defining our download Items!
        DownloadItem downloadItem1 = new DownloadItem("File1.txt", "Text", 25, 50, 5, "https.www.p30download.com/...", "Desktop", 0);
        DownloadItem downloadItem2 = new DownloadItem("File2.txt", "Text", 25, 50, 5, "https.www.p30download.com/...", "Desktop", 0);
        DownloadItem downloadItem3 = new DownloadItem("File3.txt", "Text", 25, 50, 5, "https.www.p30download.com/...", "Desktop", 0);
        downloadItems.add(downloadItem1);
        downloadItems.add(downloadItem2);
        downloadItems.add(downloadItem3);
        //Defining our scroll pane & it's belongings!
        JPanel downloadItemsPanel = new JPanel(new GridLayout(downloadItems.size(), 1, 5, 5));//The panel that consists of all the download items!
        for (DownloadItem downloadItem : downloadItems) {
            downloadItem.getDownloadItemPanel().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        downloadItem.getFileModeButton().setBackground(Color.GREEN);
                        for (DownloadItem downloadItemSample : downloadItems)
                            if (!downloadItem.equals(downloadItemSample))
                                downloadItemSample.getFileModeButton().setBackground(Color.lightGray);
                        SwingUtilities.updateComponentTreeUI(jDMFrame);
                    }
                }
            });
        }
        JPanel jDMScrollPanel = new JPanel(new BorderLayout());
        JScrollPane jDMScrollPane = new JScrollPane(jDMScrollPanel);
        for (DownloadItem downloadItem : downloadItems) {
            downloadItemsPanel.add(downloadItem.getDownloadItemPanel());//Adding our download Item's panel to the download items list Panel
        }
        //Adding the components!
        JMenuBar jDMMenuBar = new JMenuBar();
        jDMMenuBar.add(downloadMenu);
        jDMMenuBar.add(helpMenu);
        jDMTopMainPanel.add(jDMMenuBar);
        jDMTopMainPanel.add(jDMToolBar);
        jDMMainPanel.add(jDMTopMainPanel, BorderLayout.NORTH);
        jDMScrollPanel.add(downloadItemsPanel, BorderLayout.NORTH);
        jDMMainPanel.add(jDMScrollPane, BorderLayout.CENTER);
        jDMSidePanel.add(eagleLabel, BorderLayout.NORTH);
        jDMSideOptionsPanel.add(processingPanel);
        jDMSideOptionsPanel.add(completedPanel);
        jDMSideOptionsPanel.add(queuesPanel);
        jDMSideCentralPanel.add(jDMSideOptionsPanel, BorderLayout.NORTH);
        jDMSidePanel.add(jDMSideCentralPanel, BorderLayout.CENTER);
        jDMBackgroundPanel.add(jDMMainPanel, BorderLayout.CENTER);
        jDMBackgroundPanel.add(jDMSidePanel, BorderLayout.WEST);
        jDMFrame.add(jDMBackgroundPanel);
        //Declaring the system tray!
        /*SystemTray systemTray = SystemTray.getSystemTray();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(getClass().getResource("/IntelliJ IDEA.png"));//This shall be changed!
        PopupMenu popupMenu = new PopupMenu();
        TrayIcon icon = new TrayIcon(image, "JDM Program", popupMenu);
        MenuItem openItem = new MenuItem("Open JDownloadManager");
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDMFrame.setVisible(true);
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
        systemTray.add(icon);*/
    }

    public void showGUI() {
        jDMFrame.setVisible(true);
    }

    /*
    An action listener that opens the settings panel!
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
                        SwingUtilities.updateComponentTreeUI(jDMFrame);
                        jDMFrame.invalidate();
                        jDMFrame.validate();
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

    public class newDownloadListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame newDownloadFrame = new JFrame("New Download");
            newDownloadFrame.setSize(400, 400);
            newDownloadFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            newDownloadFrame.setLocationRelativeTo(null);
            newDownloadFrame.setVisible(true);
            JPanel newDownloadPanel = new JPanel(new BorderLayout());
            JPanel uRLPanel = new JPanel(new BorderLayout());//A panel for the url textField!
            JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
            JLabel uRLIcon = new JLabel();
            JButton cancelButton = new JButton("Cancel");
            JButton oKButton = new JButton("OK");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    newDownloadFrame.dispose();
                }
            });
            oKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        DownloadItem downloadItem = new DownloadItem("File" + downloadItems.size() + ".txt", "File", 25, 50, 5, "https.www.p30download.com/...", "Desktop", 0);
                        downloadItems.add(downloadItem);
                        JOptionPane.showMessageDialog(newDownloadFrame, "You're download has been successfully added to the download items.Further modifications are Coming Soon", "Adding a new download", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            try {
                uRLIcon = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/url.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            JTextField uRLTextField = new JTextField("C.Users.atiye.Desktop");
            uRLPanel.add(uRLIcon, BorderLayout.WEST);
            uRLPanel.add(uRLTextField, BorderLayout.CENTER);
            newDownloadPanel.add(uRLPanel, BorderLayout.NORTH);
            bottomPanel.add(cancelButton);
            bottomPanel.add(oKButton);
            newDownloadPanel.add(bottomPanel, BorderLayout.SOUTH);
            newDownloadFrame.add(newDownloadPanel);
        }
    }

    public class queueListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame queueFrame = new JFrame("Queue");
            queueFrame.setSize(400, 400);
            queueFrame.setLocationRelativeTo(null);
            queueFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            queueFrame.setVisible(true);
            JPanel queueBackgroundPanel = new JPanel(new BorderLayout());//A background panel
            JPanel queueDownloadListPanel = new JPanel(new GridLayout(downloadItems.size() + 1, 1));//A panel for the list of downloads!
            JButton addButton = new JButton();
            try {
                addButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/add.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            addButton.addActionListener(new newDownloadListner());
            for (DownloadItem downloadItem : downloadItems)
                queueDownloadListPanel.add(downloadItem.getDownloadItemPanel());
            queueDownloadListPanel.add(addButton);
            JScrollPane queueScrollPane = new JScrollPane(queueDownloadListPanel);
            queueBackgroundPanel.add(queueScrollPane, BorderLayout.NORTH);
            queueFrame.add(queueBackgroundPanel);
        }
    }
}

