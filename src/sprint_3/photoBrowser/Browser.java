package sprint_3.photoBrowser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Browser extends JFrame implements ActionListener {
    private final Path folderPath = Paths.get("res/photos");
    private final JPanel panel = new JPanel();
    private final JButton button = new JButton("Change picture");
    private final JLabel label = new JLabel();
    private int counter = 0;
    private final List<String> pathList = new ArrayList<>();

    private void pictureFrame() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(panel);

        panel.setLayout(new BorderLayout());
        panel.add(button, BorderLayout.SOUTH);
        panel.add(label, BorderLayout.CENTER);
        button.addActionListener(this);

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(folderPath)){
            paths.forEach(path -> pathList.add(path.toString()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Folder was not found");
            System.exit(0);
        }

        if (pathList.isEmpty()) {
            System.out.println("No images found in folder");
            System.exit(0);
        }

        label.setIcon(new ImageIcon(pathList.get(counter)));
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        counter++;
        if (counter >= pathList.size()) {
            counter = 0;
        }
        label.setIcon(new ImageIcon(pathList.get(counter)));
    }

    public static void main(String[] args) {
        Browser browser = new Browser();
        browser.pictureFrame();
    }
}
