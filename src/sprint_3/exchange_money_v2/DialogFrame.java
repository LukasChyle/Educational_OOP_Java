package sprint_3.exchange_money_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DialogFrame extends JFrame implements ActionListener {

    ExchangeCalculator_v2 eCalc = new ExchangeCalculator_v2();
    JPanel leftPanel, rightPanel;
    List<JCheckBox> boxList = new ArrayList<>();
    JButton unbox, refresh, pay;
    JTextField payment;
    JLabel message;
    JTextArea textArea;
    int cost, paid;

    private void window() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(leftPanel = new JPanel(new GridLayout(11, 1)), BorderLayout.WEST);
        add(rightPanel = new JPanel(new GridLayout(4, 1)), BorderLayout.EAST);
        add(textArea = new JTextArea("Choose what currencies you want in exchange"), BorderLayout.CENTER);

        boxList.add(new JCheckBox("1000"));
        boxList.add(new JCheckBox("500"));
        boxList.add(new JCheckBox("200"));
        boxList.add(new JCheckBox("100"));
        boxList.add(new JCheckBox("50"));
        boxList.add(new JCheckBox("20"));
        boxList.add(new JCheckBox("10"));
        boxList.add(new JCheckBox("5"));
        boxList.add(new JCheckBox("2"));
        boxList.add(new JCheckBox("1"));
        leftPanel.add(boxList.get(0));
        leftPanel.add(boxList.get(1));
        leftPanel.add(boxList.get(2));
        leftPanel.add(boxList.get(3));
        leftPanel.add(boxList.get(4));
        leftPanel.add(boxList.get(5));
        leftPanel.add(boxList.get(6));
        leftPanel.add(boxList.get(7));
        leftPanel.add(boxList.get(8));
        leftPanel.add(boxList.get(9));
        leftPanel.add(unbox = new JButton("Unbox"));
        rightPanel.add(refresh = new JButton("Refresh"));
        rightPanel.add(message = new JLabel());
        rightPanel.add(payment = new JTextField());
        rightPanel.add(pay = new JButton("Pay"));

        refresh.addActionListener(this);
        unbox.addActionListener(this);
        pay.addActionListener(this);

        whatToPay();
        pack();
        setLocationRelativeTo(null);
    }

    private void whatToPay() {
        Random random = new Random();
        int minReturn = 50;
        int maxReturn = 10000;
        cost = random.nextInt(maxReturn - minReturn + 1) + minReturn;
        message.setText("You have to pay: " + cost + "          ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refresh) {
            whatToPay();
        } else if (e.getSource() == unbox) {
            for (JCheckBox box : boxList) {
                box.setSelected(false);
            }
        } else if (e.getSource() == pay) {
            if (payment.getText().chars().allMatch(Character::isDigit) && !payment.getText().isBlank()) {
                paid = Integer.parseInt(payment.getText());
                if (paid < cost) {
                    textArea.setText("Shame, you didn't pay enough        ");
                } else {
                    textArea.setText(eCalc.getMoneyBack(paid - cost, boxList) + "                  ");
                }
            } else {
                textArea.setText("Input is not a valid digit        ");
            }
        }
        pack();
    }

    public static void main(String[] args) {
        DialogFrame df = new DialogFrame();
        df.window();
    }
}
