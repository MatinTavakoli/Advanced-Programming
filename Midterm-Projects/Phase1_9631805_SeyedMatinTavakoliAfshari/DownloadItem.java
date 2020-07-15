import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class DownloadItem {
    private JPanel downloadItemPanel;
    //Download Item Methods
    private String fileName;
    private JProgressBar jDMProgressBar;
    private int fileSize;
    private int progressSize;
    private int downloadSpeed;
    private String uRL;
    private String downloadDestination;
    private int downloadStartTime;
    private JButton fileModeButton;

    public DownloadItem(String fileName, String fileMode, int fileSize, int progressSize, int downloadSpeed, String uRL, String downloadDestination, int downloadStartTime) throws IOException {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.progressSize = progressSize;
        this.downloadSpeed = downloadSpeed;
        this.uRL = uRL;
        this.downloadDestination = downloadDestination;
        this.downloadStartTime = downloadStartTime;
        //Defining our Download Item(A panel!)
        downloadItemPanel = new JPanel(new BorderLayout());
        JPanel downloadItemsCentralPanel = new JPanel(new GridLayout(3, 1));
        JPanel downloadItemsCentralBottomPanel = new JPanel(new BorderLayout());//A more inside panel for the downloads statistics!
        JPanel downloadItemsEasternPanel = new JPanel(new GridLayout(1, 3));
        JPanel fileTypePanel = new JPanel(new BorderLayout());//Just so we can click the rest of the space!
        JLabel fileTypeLabel = new JLabel(fileName);
        jDMProgressBar = new JProgressBar();
        jDMProgressBar.setValue(50);
        JLabel fileProgressAndTotalSizeLabel = new JLabel(progressSize + "MB" + "/" + fileSize + "MB", SwingConstants.RIGHT);//The size of the file and the amount that has been downloaded!
        JLabel fileProgressionPercentageLabel = new JLabel(String.valueOf(jDMProgressBar.getPercentComplete() * 100) + "%");
        JButton downloadItemDetailsButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/details.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        JButton deleteButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/delete.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        JLabel fileProgressSpeed = new JLabel(downloadSpeed + "MB/s");
        fileProgressSpeed.setHorizontalAlignment(SwingConstants.CENTER);
        fileModeButton = new JButton(fileMode);
        //Setting tool tips for these components!
        fileTypeLabel.setToolTipText("The type of file that is being downloaded.");
        jDMProgressBar.setToolTipText("Displaying the percentage of the download's Progress.");
        fileProgressAndTotalSizeLabel.setToolTipText("The size of the download's Progression & download shown respectively.");
        fileProgressionPercentageLabel.setToolTipText("The percentage of the download's Progress.");
        fileModeButton.setToolTipText("The icon of the file that is being downloaded.");
        fileProgressSpeed.setToolTipText("The speed of the download(measured by Megabytes per Second)");
        //Setting action listeners for our components!
        downloadItemPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isRightMouseButton(e)) {
                    JFrame downloadItemDetailsFrame = new JFrame("DownloadItem Details (" + getFileName() + ")");//A frame for the particular item!
                    downloadItemDetailsFrame.setSize(400, 300);
                    downloadItemDetailsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    downloadItemDetailsFrame.setLocationRelativeTo(null);
                    downloadItemDetailsFrame.setVisible(true);
                    //Newing our frames contents(downloads details)!
                    JPanel downloadItemDetailsPanel = new JPanel(new GridLayout(8, 1));
                    JLabel fileNameLabel = new JLabel("File Name : " + getFileName(), SwingConstants.CENTER);
                    JLabel fileSizeLabel = new JLabel("File Size : " + String.valueOf(getFileSize()), SwingConstants.CENTER);
                    JLabel progressSizeLabel = new JLabel("File's Progress Size : " + String.valueOf(getProgressSize()), SwingConstants.CENTER);
                    JLabel downloadSpeedLabel = new JLabel("Download Speed : " + String.valueOf(getDownloadSpeed()), SwingConstants.CENTER);
                    JLabel downloadItemURLLabel = new JLabel("URL : " + uRL, SwingConstants.CENTER);
                    JLabel downloadDestinationLabel = new JLabel("Save to : " + downloadDestination, SwingConstants.CENTER);
                    //JLabel downloadStartTimeLabel = new JLabel("Started at (from zero) : " + String.valueOf(downloadStartTime), SwingConstants.CENTER);
                    JPanel timePanel = new JPanel(new GridLayout(2, 2));
                    JPanel cancelAndOKPanel=new JPanel(new GridLayout(1,2));
                    JRadioButton downloadStartsAtASpecifiedTimeRadioButton = new JRadioButton("Start at a specifiedTime");
                    JTextField downloadStartsAtASpecifiedTimeTextField = new JTextField("");//When you want to start!
                    JRadioButton downloadStartsAfterSpecifiedSecondsRadioButton = new JRadioButton("Start after n seconds");
                    JTextField downloadStartsAfterSpecifiedSecondsTextField = new JTextField("");//After how many seconds you want to start?
                    ButtonGroup timeGroupButton = new ButtonGroup();
                    timeGroupButton.add(downloadStartsAfterSpecifiedSecondsRadioButton);
                    timeGroupButton.add(downloadStartsAtASpecifiedTimeRadioButton);
                    JButton cancelButton=new JButton("Cancel");
                    JButton oKButton=new JButton("OK");
                    downloadStartsAtASpecifiedTimeRadioButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (downloadStartsAtASpecifiedTimeRadioButton.isSelected()) {
                                downloadStartsAtASpecifiedTimeTextField.setEditable(true);//When the user chose it,we let him change the text!
                                downloadStartsAfterSpecifiedSecondsTextField.setEditable(false);
                            }
                            else {
                                downloadStartsAtASpecifiedTimeTextField.setEditable(false);
                                downloadStartsAfterSpecifiedSecondsTextField.setEditable(true);
                                int unnecessary=1;//Just so it doesn't gives us duplication codes!
                            }
                        }
                    });
                    cancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            downloadItemDetailsFrame.dispose();
                        }
                    });
                    oKButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String time1=downloadStartsAtASpecifiedTimeTextField.getText();
                            String time2=downloadStartsAfterSpecifiedSecondsTextField.getText();
                            JOptionPane.showMessageDialog(downloadItemDetailsFrame,"You're input has been successfully saved!Further modifications are Coming soon!","Successful Scheduling",JOptionPane.INFORMATION_MESSAGE);
                            downloadItemDetailsFrame.dispose();
                        }
                    });
                    cancelAndOKPanel.add(cancelButton);
                    cancelAndOKPanel.add(oKButton);
                    downloadStartsAfterSpecifiedSecondsRadioButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (downloadStartsAfterSpecifiedSecondsRadioButton.isSelected()) {
                                downloadStartsAfterSpecifiedSecondsTextField.setEditable(false);//When the user chose it,we let him change the text!
                                downloadStartsAtASpecifiedTimeTextField.setEditable(true);
                            }
                            else {
                                downloadStartsAfterSpecifiedSecondsTextField.setEditable(true);
                                downloadStartsAtASpecifiedTimeTextField.setEditable(false);
                            }
                        }
                    });
                    //Adding our time components!
                    timePanel.add(downloadStartsAtASpecifiedTimeRadioButton);
                    timePanel.add(downloadStartsAtASpecifiedTimeTextField);
                    timePanel.add(downloadStartsAfterSpecifiedSecondsRadioButton);
                    timePanel.add(downloadStartsAfterSpecifiedSecondsTextField);
                    //Adding our components!
                    downloadItemDetailsPanel.add(fileNameLabel);
                    downloadItemDetailsPanel.add(fileSizeLabel);
                    downloadItemDetailsPanel.add(progressSizeLabel);
                    downloadItemDetailsPanel.add(downloadSpeedLabel);
                    downloadItemDetailsPanel.add(downloadItemURLLabel);
                    downloadItemDetailsPanel.add(downloadDestinationLabel);
                    downloadItemDetailsPanel.add(timePanel);
                    downloadItemDetailsPanel.add(cancelAndOKPanel);
                    downloadItemDetailsFrame.add(downloadItemDetailsPanel);
                    downloadItemDetailsFrame.add(downloadItemDetailsPanel);
                } else if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(downloadItemPanel, "Coming Soon (Though it seems to be Desktop.getDesktop().open(File file))", "", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(downloadItemPanel, "Deleting this item is coming soon!", "Deleting a download", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        downloadItemDetailsButton.addActionListener(new DetailsListener());//Defined at the bottom of the class as a specific action listener!
        //Adding them to the downloadItemPanel!
        fileTypePanel.add(fileTypeLabel, BorderLayout.WEST);
        downloadItemsCentralPanel.add(fileTypePanel);
        downloadItemsCentralPanel.add(jDMProgressBar);
        downloadItemsCentralBottomPanel.add(fileProgressSpeed, BorderLayout.CENTER);
        downloadItemsCentralBottomPanel.add(fileProgressAndTotalSizeLabel, BorderLayout.EAST);
        downloadItemsCentralPanel.add(downloadItemsCentralBottomPanel);
        downloadItemsEasternPanel.add(fileProgressionPercentageLabel);
        downloadItemsEasternPanel.add(downloadItemDetailsButton);
        downloadItemsEasternPanel.add(deleteButton);
        downloadItemPanel.add(fileModeButton, BorderLayout.WEST);
        downloadItemPanel.add(downloadItemsCentralPanel, BorderLayout.CENTER);
        downloadItemPanel.add(downloadItemsEasternPanel, BorderLayout.EAST);
    }

    public JPanel getDownloadItemPanel() {
        return downloadItemPanel;
    }

    public String getFileName() {
        return fileName;
    }

    public JProgressBar getjDMProgressBar() {
        return jDMProgressBar;
    }

    public int getFileSize() {
        return fileSize;
    }

    public int getProgressSize() {
        return progressSize;
    }

    public int getDownloadSpeed() {
        return downloadSpeed;
    }

    public String getuRL() {
        return uRL;
    }

    public String getDownloadDestination() {
        return downloadDestination;
    }

    public int getDownloadStartTime() {
        return downloadStartTime;
    }

    public JButton getFileModeButton() {
        return fileModeButton;
    }

    /*
        An action listener that shows the details of a download!
         */
    public class DetailsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame downloadItemDetailsFrame = new JFrame("DownloadItem Details (" + getFileName() + ")");//A frame for the particular item!
            downloadItemDetailsFrame.setSize(400, 300);
            downloadItemDetailsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            downloadItemDetailsFrame.setLocationRelativeTo(null);
            downloadItemDetailsFrame.setVisible(true);
            //Newing our frames contents(downloads details)!
            JPanel downloadItemDetailsPanel = new JPanel(new GridLayout(8, 1));
            JLabel fileNameLabel = new JLabel("File Name : " + getFileName(), SwingConstants.CENTER);
            JLabel fileSizeLabel = new JLabel("File Size : " + String.valueOf(getFileSize()), SwingConstants.CENTER);
            JLabel progressSizeLabel = new JLabel("File's Progress Size : " + String.valueOf(getProgressSize()), SwingConstants.CENTER);
            JLabel downloadSpeedLabel = new JLabel("Download Speed : " + String.valueOf(getDownloadSpeed()), SwingConstants.CENTER);
            JLabel downloadItemURLLabel = new JLabel("URL : " + uRL, SwingConstants.CENTER);
            JLabel downloadDestinationLabel = new JLabel("Save to : " + downloadDestination, SwingConstants.CENTER);
            //JLabel downloadStartTimeLabel = new JLabel("Started at (from zero) : " + String.valueOf(downloadStartTime), SwingConstants.CENTER);
            JPanel timePanel = new JPanel(new GridLayout(2, 2));
            JPanel cancelAndOKPanel=new JPanel(new GridLayout(1,2));
            JRadioButton downloadStartsAtASpecifiedTimeRadioButton = new JRadioButton("Start at a specifiedTime");
            JTextField downloadStartsAtASpecifiedTimeTextField = new JTextField("");//When you want to start!
            JRadioButton downloadStartsAfterSpecifiedSecondsRadioButton = new JRadioButton("Start after n seconds");
            JTextField downloadStartsAfterSpecifiedSecondsTextField = new JTextField("");//After how many seconds you want to start?
            ButtonGroup timeGroupButton = new ButtonGroup();
            timeGroupButton.add(downloadStartsAfterSpecifiedSecondsRadioButton);
            timeGroupButton.add(downloadStartsAtASpecifiedTimeRadioButton);
            JButton cancelButton=new JButton("Cancel");
            JButton oKButton=new JButton("OK");
            downloadStartsAtASpecifiedTimeRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (downloadStartsAtASpecifiedTimeRadioButton.isSelected()) {
                        downloadStartsAtASpecifiedTimeTextField.setEditable(true);//When the user chose it,we let him change the text!
                        downloadStartsAfterSpecifiedSecondsTextField.setEditable(false);
                    }
                    else {
                        downloadStartsAtASpecifiedTimeTextField.setEditable(false);
                        downloadStartsAfterSpecifiedSecondsTextField.setEditable(true);
                        int unnecessary=1;//Just so it doesn't gives us duplication codes!
                    }
                }
            });
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    downloadItemDetailsFrame.dispose();
                }
            });
            oKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String time1=downloadStartsAtASpecifiedTimeTextField.getText();
                    String time2=downloadStartsAfterSpecifiedSecondsTextField.getText();
                    JOptionPane.showMessageDialog(downloadItemDetailsFrame,"You're input has been successfully saved!Further modifications are Coming soon!","Successful Scheduling",JOptionPane.INFORMATION_MESSAGE);
                    downloadItemDetailsFrame.dispose();
                }
            });
            cancelAndOKPanel.add(cancelButton);
            cancelAndOKPanel.add(oKButton);
            downloadStartsAfterSpecifiedSecondsRadioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (downloadStartsAfterSpecifiedSecondsRadioButton.isSelected()) {
                        downloadStartsAfterSpecifiedSecondsTextField.setEditable(false);//When the user chose it,we let him change the text!
                        downloadStartsAtASpecifiedTimeTextField.setEditable(true);
                    }
                    else {
                        downloadStartsAfterSpecifiedSecondsTextField.setEditable(true);
                        downloadStartsAtASpecifiedTimeTextField.setEditable(false);
                    }
                }
            });
            //Adding our time components!
            timePanel.add(downloadStartsAtASpecifiedTimeRadioButton);
            timePanel.add(downloadStartsAtASpecifiedTimeTextField);
            timePanel.add(downloadStartsAfterSpecifiedSecondsRadioButton);
            timePanel.add(downloadStartsAfterSpecifiedSecondsTextField);
            //Adding our components!
            downloadItemDetailsPanel.add(fileNameLabel);
            downloadItemDetailsPanel.add(fileSizeLabel);
            downloadItemDetailsPanel.add(progressSizeLabel);
            downloadItemDetailsPanel.add(downloadSpeedLabel);
            downloadItemDetailsPanel.add(downloadItemURLLabel);
            downloadItemDetailsPanel.add(downloadDestinationLabel);
            downloadItemDetailsPanel.add(timePanel);
            downloadItemDetailsPanel.add(cancelAndOKPanel);
            downloadItemDetailsFrame.add(downloadItemDetailsPanel);
            downloadItemDetailsFrame.add(downloadItemDetailsPanel);
        }
    }

    public void setDownloadSpeed(int downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }
}
