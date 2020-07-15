import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A frame that shows us the downloads that are searched!
 */
public class SearchResult extends JFrame {
    public SearchResult(ArrayList<DownloadItem> searchResultDownloads,String title) {
        JFrame searchFrame = new JFrame(title);
        searchFrame.setSize(400, 400);
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        searchFrame.setVisible(true);
        JPanel backGroundPanel = new JPanel(new BorderLayout());
        JPanel downloadResultsPanel = new JPanel(new GridLayout(searchResultDownloads.size(), 1, 5, 5));
        for (DownloadItem downloadItem : searchResultDownloads)
            downloadResultsPanel.add(downloadItem.getDownloadItemPanel());
        JScrollPane resultScrollPane = new JScrollPane(downloadResultsPanel);
        backGroundPanel.add(resultScrollPane, BorderLayout.NORTH);
        searchFrame.add(backGroundPanel);
    }
}
