package sprint_3.textEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextEditor extends JFrame implements ActionListener {

    private final Path folderPath = Paths.get("res/textFiles");
    private final List<String> pathList = new ArrayList<>();
    private final JTextArea tArea;
    private final JButton create, open, save, print, clear, close;
    private JComboBox<String> cBox;
    private String currentFile;

    protected TextEditor() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Lukas Text Editor");

        tArea = new JTextArea();
        JScrollPane sp = new JScrollPane(tArea);
        sp.setPreferredSize(new Dimension(700, 500));
        JPanel menuPanel = new JPanel(new GridLayout(1, 7));

        add(menuPanel, BorderLayout.NORTH);
        add(sp, BorderLayout.SOUTH);

        create = new JButton("New");
        open = new JButton("Open");
        save = new JButton("Save");
        print = new JButton("Print");
        clear = new JButton("Clear");
        close = new JButton("Close");
        create.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        print.addActionListener(this);
        clear.addActionListener(this);
        close.addActionListener(this);

        getFiles();

        menuPanel.add(create);
        menuPanel.add(cBox);
        menuPanel.add(open);
        menuPanel.add(save);
        menuPanel.add(print);
        menuPanel.add(clear);
        menuPanel.add(close);


        pack();
        setLocationRelativeTo(null);
    }

    private void getFiles() {
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(folderPath)) {
            paths.forEach(path -> pathList.add(path.toString()));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Folder was not found");
        }
        if (!pathList.isEmpty()) {
            String[] strings = pathList.toArray(String[]::new);
            cBox = new JComboBox<>(strings);
        }
    }

    private String setName() {
        while (true) {
            String fileName = JOptionPane.showInputDialog("Name of text file?");
            if (fileName == null) {
                return null;
            }
            fileName = fileName.trim();
            if ((!fileName.isBlank() && fileName.chars().allMatch(Character::isLetter))) {
                return fileName;
            }
            JOptionPane.showMessageDialog(null, "Name is not valid");
        }
    }

    private void createNewFile() {
        String fileName = setName();
        if (fileName == null) {
            return;
        }
        currentFile = folderPath + "\\" + fileName;
        saveFile(currentFile);

    }

    private void saveFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(tArea.getText());
            writer.flush();
            if (cBox.getItemCount() != 0) {
                for (int i = 0; i < cBox.getItemCount(); i++) {
                    if (cBox.getItemAt(i).equals(filePath)) {
                        return;
                    }
                }
            }
            cBox.addItem(filePath);
            cBox.setSelectedItem(filePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            createNewFile();
        } else if (e.getSource() == open && cBox.getItemCount() != 0) {
            try {
                FileReader reader = new FileReader(Objects.requireNonNull(cBox.getSelectedItem()).toString());
                currentFile = cBox.getSelectedItem().toString();
                tArea.read(reader, currentFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == save) {
            if (currentFile != null) {
                saveFile(currentFile);
                JOptionPane.showMessageDialog(null, currentFile + " was saved");
            } else {
                createNewFile();
            }
        } else if (e.getSource() == print) {
            try {
                tArea.print();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == clear) {
            tArea.setText("");
        } else if (e.getSource() == close) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        TextEditor tEditor = new TextEditor();
    }
}
