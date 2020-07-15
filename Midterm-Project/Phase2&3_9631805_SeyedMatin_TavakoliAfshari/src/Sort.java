import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Sort extends JFrame {
    private File settingsDotJdm = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\settings.jdm");//A file that is going to give us the downloads details!
    private BufferedReader settingsReader = new BufferedReader(new FileReader(settingsDotJdm));//Defining a buffer reader that gives us the key information!
    private String[] englishWords = showEnglishWords();
    private String[] persianWords = showPersianWords();
    private String lookandfeel=settingsReader.readLine();
    private String languageIdentifierLine = settingsReader.readLine();//The second line of settings.jdm shows us our language!
    private String languageIdentifier = languageIdentifierLine.substring(0, languageIdentifierLine.indexOf(','));//Fro example for Persian,2 it gives Persian!
    private JFrame sortFrame;
    private JRadioButton timeRadioButton;
    private JRadioButton fileSizeRadioButton;
    private JRadioButton fileNameRadioButton;
    private JButton oKButton;
    public Sort(String title) throws IOException {
        sortFrame = new JFrame(title);
        sortFrame.setSize(460, 200);
        sortFrame.setLocationRelativeTo(null);
        sortFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        sortFrame.setVisible(true);
        JPanel sortPanel = new JPanel(new BorderLayout());
        JPanel radioButtonsPanel=new JPanel(new GridLayout(1,3,5,5));
        JPanel cancelAndOKPanel=new JPanel(new GridLayout(1,2,5,5));
        timeRadioButton = new JRadioButton(wordLanguageSelector(englishWords[27],persianWords[27],languageIdentifier));
        fileSizeRadioButton = new JRadioButton(wordLanguageSelector(englishWords[28],persianWords[28],languageIdentifier));
        fileNameRadioButton = new JRadioButton(wordLanguageSelector(englishWords[29],persianWords[29],languageIdentifier));
        JButton cancelButton = new JButton(wordLanguageSelector(englishWords[5],persianWords[5],languageIdentifier));
        oKButton = new JButton(wordLanguageSelector(englishWords[22],persianWords[22],languageIdentifier));


        //Setting action listeners for our components!!
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortFrame.dispose();
            }
        });


        //Adding our components!
        radioButtonsPanel.add(timeRadioButton);
        radioButtonsPanel.add(fileSizeRadioButton);
        radioButtonsPanel.add(fileNameRadioButton);
        cancelAndOKPanel.add(cancelButton);
        cancelAndOKPanel.add(oKButton);
        sortPanel.add(radioButtonsPanel,BorderLayout.CENTER);
        sortPanel.add(cancelAndOKPanel,BorderLayout.SOUTH);
        sortFrame.add(sortPanel);
    }

    public JFrame getSortFrame() {
        return sortFrame;
    }

    public JButton getoKButton() {
        return oKButton;
    }

    public JRadioButton getTimeRadioButton() {
        return timeRadioButton;
    }

    public JRadioButton getFileSizeRadioButton() {
        return fileSizeRadioButton;
    }

    public JRadioButton getFileNameRadioButton() {
        return fileNameRadioButton;
    }
    public String[] showEnglishWords() throws IOException {
        File englishWordsDotJdm = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\EnglishWords.jdm");
        BufferedReader englishWordsReader = new BufferedReader(new FileReader(englishWordsDotJdm));
        String englishWordsLine = englishWordsReader.readLine();
        String[] englishWords = englishWordsLine.split(",");
        int a=5;
        return englishWords;
    }

    public String[] showPersianWords() throws IOException {
        File persianWordsDotJdm = new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\PersianWords.jdm");
        BufferedReader persianWordsReader = new BufferedReader(new FileReader(persianWordsDotJdm));
        String persianWordsLine = persianWordsReader.readLine();
        String[] persianWords = persianWordsLine.split("،");
        int a=5;
        return persianWords;
    }

    public String wordLanguageSelector(String englishWord, String persianWord, String languageIdentifier) {
        int a=5;
        if (languageIdentifier.equals("English")) {
            return englishWord;
        }
        else if (languageIdentifier.equals("فارسی")) {
            return persianWord;
        }
        else return null;//Just so it does'nt give us errors
    }
}
