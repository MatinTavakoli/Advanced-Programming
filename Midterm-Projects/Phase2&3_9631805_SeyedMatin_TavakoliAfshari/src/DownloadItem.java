import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DownloadItem {
    private JPanel downloadItemPanel;
    //Download Item Methods
    private String fileName;
    private JProgressBar jDMProgressBar;
    private int fileSize;
    private int progressSize;
    private int downloadSpeed;
    private String downloadURL;
    private String downloadDestination;
    private int downloadStartTime;
    private JButton fileModeButton;

    //Download Labels!
    private JLabel statusLabel;//Is it Running or Paused!
    private JLabel fileProgressAndTotalSizeLabel;
    private JLabel fileProgressionPercentageLabel;
    private JLabel fileProgressSpeed;


    private JButton downloadItemDetailsButton;//A button that opens a details frame when pressed!
    private SwingWorker<Integer, Integer> downloadWorker;
    private File exampleFile;

    public DownloadItem(String fileName, int progressSize, int fileSize, int downloadSpeed, String downloadURL, String downloadDestination, int downloadStartTime) throws IOException {
        this.fileName = fileName;
        this.progressSize = progressSize;
        this.fileSize = fileSize;
        this.downloadSpeed = downloadSpeed;
        this.downloadURL = downloadURL;
        this.downloadDestination = downloadDestination;
        this.downloadStartTime = downloadStartTime;
        exampleFile = new File(downloadDestination + "\\" + fileName);
        statusLabel = new JLabel("Status : Paused", SwingConstants.CENTER);//We consider it as a running download!


        //Defining our Download Item(A panel!)
        downloadItemPanel = new JPanel(new BorderLayout());
        JPanel downloadItemsCentralPanel = new JPanel(new GridLayout(3, 1));
        JPanel downloadItemsCentralBottomPanel = new JPanel(new BorderLayout());//A more inside panel for the downloads statistics!
        JPanel downloadItemsEasternPanel = new JPanel(new GridLayout(1, 2));
        JPanel fileTypePanel = new JPanel(new BorderLayout());//Just so we can click the rest of the space!
        JLabel fileTypeLabel = new JLabel(fileName);
        jDMProgressBar = new JProgressBar(0,100);
        jDMProgressBar.setValue((int) ((((float) progressSize) / fileSize) * jDMProgressBar.getMaximum()));
        fileProgressAndTotalSizeLabel = new JLabel(progressSize + "MB" + "/" + fileSize + "MB", SwingConstants.RIGHT);//The size of the file and the amount that has been downloaded!
        fileProgressionPercentageLabel = new JLabel(String.valueOf(jDMProgressBar.getPercentComplete() * 100) + "%");
        downloadItemDetailsButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/Icons/ps/details.png")).getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING)));
        fileProgressSpeed = new JLabel(downloadSpeed + "MB/s", SwingConstants.CENTER);
        fileModeButton = new JButton(fileName.substring(fileName.lastIndexOf('.') + 1));
        if(jDMProgressBar.getValue()>=99) {
            fileModeButton.setBackground(Color.GREEN);//If it has been downloaded before,it needs to pass from here to be considered as completed!
            statusLabel.setText("Status : Downloaded");
            setDownloadSpeed(0);//Because the download is over!
        }
        boolean isURLValid = checkDownload();
        if (!isURLValid)
            fileModeButton.setBackground(Color.RED);//If the file is not valid then paint its button red and consider it as a filtered link!


        //Setting tool tips for these components!
        fileTypeLabel.setToolTipText("The type of file that is being downloaded.");
        jDMProgressBar.setToolTipText("Displaying the percentage of the download's Progress.");
        fileProgressAndTotalSizeLabel.setToolTipText("The size of the download's Progression & download shown respectively.");
        fileProgressionPercentageLabel.setToolTipText("The percentage of the download's Progress.");
        downloadItemDetailsButton.setToolTipText("The downloads details.");
        fileProgressSpeed.setToolTipText("The speed of the download(measured by Megabytes per Second)");
        fileModeButton.setToolTipText("The icon of the file that is being downloaded.");


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
                    JPanel downloadItemDetailsPanel = new JPanel(new GridLayout(10, 1));
                    JLabel fileNameLabel = new JLabel("File Name : " + getFileName(), SwingConstants.CENTER);
                    JLabel fileModeLabel = new JLabel("File Mode : " + getFileModeButton().getText(), SwingConstants.CENTER);
                    JLabel progressSizeLabel = new JLabel("Progress Size : " + String.valueOf(getProgressSize()), SwingConstants.CENTER);
                    JLabel fileSizeLabel = new JLabel("File Size : " + String.valueOf(getFileSize()), SwingConstants.CENTER);
                    JLabel downloadSpeedLabel = new JLabel("Download Speed : " + String.valueOf(getDownloadSpeed()), SwingConstants.CENTER);
                    JLabel downloadItemURLLabel = new JLabel("URL : " + downloadURL, SwingConstants.CENTER);
                    JLabel downloadDestinationLabel = new JLabel("Save to : " + downloadDestination, SwingConstants.CENTER);
                    JLabel downloadStartTimeLabel = new JLabel("Start Time : " + downloadStartTime + " seconds", SwingConstants.CENTER);


                    //JLabel downloadStartTimeLabel = new JLabel("Started at (from zero) : " + String.valueOf(downloadStartTime), SwingConstants.CENTER);
                    JButton oKButton = new JButton("OK");
                    oKButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            downloadItemDetailsFrame.dispose();
                        }
                    });


                    //Adding our components!
                    downloadItemDetailsPanel.add(fileNameLabel);
                    downloadItemDetailsPanel.add(fileModeLabel);
                    downloadItemDetailsPanel.add(progressSizeLabel);
                    downloadItemDetailsPanel.add(fileSizeLabel);
                    downloadItemDetailsPanel.add(statusLabel);
                    downloadItemDetailsPanel.add(downloadSpeedLabel);
                    downloadItemDetailsPanel.add(downloadItemURLLabel);
                    downloadItemDetailsPanel.add(downloadDestinationLabel);
                    downloadItemDetailsPanel.add(downloadStartTimeLabel);
                    downloadItemDetailsPanel.add(oKButton);
                    downloadItemDetailsFrame.add(downloadItemDetailsPanel);
                    downloadItemDetailsFrame.add(downloadItemDetailsPanel);
                } else if (e.getClickCount() == 2) {
                    if(jDMProgressBar.getValue()==100) {
                        try {
                            Desktop.getDesktop().open(exampleFile);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
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
        downloadItemPanel.add(fileModeButton, BorderLayout.WEST);
        downloadItemPanel.add(downloadItemsCentralPanel, BorderLayout.CENTER);
        downloadItemPanel.add(downloadItemsEasternPanel, BorderLayout.EAST);


        //Defining our SwingWorker
        downloadWorker = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                URL url = new URL(downloadURL);
                HttpURLConnection urlConnection = null;
                if (url.getProtocol().equals("http")) {
                    urlConnection = (HttpURLConnection) url.openConnection();
                } else if (url.getProtocol().equals("https"))
                    urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.connect();
                if (urlConnection.getResponseCode() / 100 != 2)
                    throw new IOException("Invalid url");
                else {
                    InputStream inputStream = urlConnection.getInputStream();
                    FileOutputStream outputStream = new FileOutputStream(exampleFile);
                    int contentLength = urlConnection.getContentLength();
                    System.out.println(contentLength/1000000);
                    setFileSize(contentLength/1000000);
                    int total = 0;
                    long time1=System.currentTimeMillis();//time before buffering!
                    byte[] buffer = new byte[800000];
                    int bytesRead = inputStream.read(buffer);
                    while (bytesRead != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        long time2=System.currentTimeMillis();
                        setDownloadSpeed((int)((float)time2-time1/1000000000));
                        System.out.println(bytesRead + " bytes read");
                        total += bytesRead;
                        setProgressSize((int)(float)total/1000000);
                        updateFileProgressAndTotalSizeLabel(getProgressSize() + "MB" + "/" + getFileSize() + "MB");
                        jDMProgressBar.setValue((int)(((float)(getProgressSize())/getFileSize())*100));
                        updateFileProgressionPercentageLabel(jDMProgressBar.getPercentComplete()*100+"%");
                        updateFileProgressSpeed((float)getDownloadSpeed()/1000000000+"MB/s");
                        Thread.sleep(10);
                        bytesRead = inputStream.read(buffer);//Increment!!
                    }
                    System.out.println(total + " total bytes read");
                    inputStream.close();
                    outputStream.close();
                    System.out.println(exampleFile.getAbsolutePath());
                    Desktop.getDesktop().open(exampleFile);
                }
                return getProgressSize();
            }
            @Override
            protected void done(){
                if(jDMProgressBar.getValue()>=99) {
                    fileModeButton.setBackground(Color.GREEN);
                    statusLabel.setText("Status : Downloaded");
                    setDownloadSpeed(0);//Because the download is over!
                    JOptionPane.showMessageDialog(downloadItemPanel,"Your download is completed.Please click the completed button to see your completed downloads!","Download Completed!",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };

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

    public String getDownloadURL() {
        return downloadURL;
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

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public JButton getDownloadItemDetailsButton() {
        return downloadItemDetailsButton;
    }

    public SwingWorker<Integer, Integer> getDownloadWorker() {
        return downloadWorker;
    }

    /**
     * @return the format of hte file(for example mp4)
     */
    public String getFileFormat() {
        return downloadURL.substring(downloadURL.lastIndexOf('.') + 1);
    }

    /**
     * An action listener that shows the details of a download!
     *
     * @Override
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
            JPanel downloadItemDetailsPanel = new JPanel(new GridLayout(10, 1));
            JLabel fileNameLabel = new JLabel("File Name : " + getFileName(), SwingConstants.CENTER);
            JLabel fileModeLabel = new JLabel("File Mode : " + getFileModeButton().getText(), SwingConstants.CENTER);
            JLabel progressSizeLabel = new JLabel("Progress Size : " + String.valueOf(getProgressSize()), SwingConstants.CENTER);
            JLabel fileSizeLabel = new JLabel("File Size : " + String.valueOf(getFileSize()), SwingConstants.CENTER);
            JLabel downloadSpeedLabel = new JLabel("Download Speed : " + String.valueOf(getDownloadSpeed()), SwingConstants.CENTER);
            JLabel downloadItemURLLabel = new JLabel("URL : " + downloadURL, SwingConstants.CENTER);
            JLabel downloadDestinationLabel = new JLabel("Save to : " + downloadDestination, SwingConstants.CENTER);
            JLabel downloadStartTimeLabel = new JLabel("Start Time : " + downloadStartTime + " seconds", SwingConstants.CENTER);

            JButton oKButton = new JButton("OK");
            oKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    downloadItemDetailsFrame.dispose();
                }
            });


            //Adding our components!
            downloadItemDetailsPanel.add(fileNameLabel);
            downloadItemDetailsPanel.add(fileModeLabel);
            downloadItemDetailsPanel.add(progressSizeLabel);
            downloadItemDetailsPanel.add(fileSizeLabel);
            downloadItemDetailsPanel.add(statusLabel);
            downloadItemDetailsPanel.add(downloadSpeedLabel);
            downloadItemDetailsPanel.add(downloadItemURLLabel);
            downloadItemDetailsPanel.add(downloadDestinationLabel);
            downloadItemDetailsPanel.add(downloadStartTimeLabel);
            downloadItemDetailsPanel.add(oKButton);
            downloadItemDetailsFrame.add(downloadItemDetailsPanel);
            downloadItemDetailsFrame.add(downloadItemDetailsPanel);
        }
    }

    public void setDownloadSpeed(int downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }

    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

    public void setProgressSize(int progressSize) {
        this.progressSize = progressSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }


    public void updateFileProgressAndTotalSizeLabel(String fileProgressAndTotalSizeLabelString) {
        fileProgressAndTotalSizeLabel.setText(fileProgressAndTotalSizeLabelString);
    }

    public void updateFileProgressionPercentageLabel(String fileProgressionPercentageLabelString) {
        this.fileProgressionPercentageLabel.setText(fileProgressionPercentageLabelString);
    }

    public void updateFileProgressSpeed(String fileProgressSpeedString) {
        this.fileProgressSpeed.setText(fileProgressSpeedString);
    }

    public boolean checkDownload() throws IOException {
        File filterLinks = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\filter.jdm");
        BufferedReader filterReader = new BufferedReader(new FileReader(filterLinks));
        String filterLink = filterReader.readLine();
        while (filterLink != null) {
            if (downloadURL.contains(filterLink)) //If it was a sub-link of the forbidden link!
                return false;
            filterLink = filterReader.readLine();//Increment!
        }
        int a = 3;
        return true;//In case nothing wrong happens!
    }
}
