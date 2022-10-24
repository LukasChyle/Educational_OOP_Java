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
    Path folderPath = Paths.get("res/photos");
    private final JPanel jp = new JPanel();
    private final JButton jb = new JButton("Change picture");
    private final JLabel jl = new JLabel();
    private int counter = 1;
    private final List<String> pathList = new ArrayList<>();

    private void pictureFrame() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(jp);

        jp.setLayout(new BorderLayout());
        jp.add(jb, BorderLayout.SOUTH);
        jp.add(jl, BorderLayout.CENTER);
        jb.addActionListener(this);

        try (DirectoryStream<Path> paths = Files.newDirectoryStream(folderPath)){
            paths.forEach(path -> pathList.add(path.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        jl.setIcon(new ImageIcon(pathList.get(0)));

        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (counter >= pathList.size()) {
            counter = 0;
        }
        jl.setIcon(new ImageIcon(pathList.get(counter)));
        counter++;
    }

    public static void main(String[] args) {
        Browser browser = new Browser();
        browser.pictureFrame();
    }
}
