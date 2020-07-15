import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class FilteredLinks extends JFrame {
    private JTextArea filteredLinksArea;
    private File filterDotJdm=new File("C:\\Users\\atiye\\Desktop\\APMidtermDMProject\\filter.jdm");
    public FilteredLinks() throws IOException {
        JFrame filteredLinksFrame=new JFrame("Filtered Links");
        filteredLinksFrame.setSize(400,400);
        filteredLinksFrame.setLocationRelativeTo(null);
        filteredLinksFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        filteredLinksArea=new JTextArea(savedFilterLinks());

        //Adding our only sub-component!
        filteredLinksFrame.add(filteredLinksArea);
        filteredLinksFrame.setVisible(true);

        /**
         * Adding a windows listener that writes the links when in a file!
         */
        filteredLinksFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    FileWriter filterWriter=new FileWriter(filterDotJdm);
                    filterWriter.write(filteredLinksArea.getText());
                    filterWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public JTextArea getFilteredLinksArea() {
        return filteredLinksArea;
    }
    public String savedFilterLinks() throws IOException {
        BufferedReader filterReader=new BufferedReader(new FileReader(filterDotJdm));
        String filterLinks="";//The string that contains all the filter links!
        String filterLink=filterReader.readLine();
        while(filterLink!=null) {
            filterLinks+=filterLink+"\n";
            filterLink=filterReader.readLine();
        }
        return filterLinks;
    }
}
