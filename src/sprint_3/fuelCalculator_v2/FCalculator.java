package sprint_3.fuelCalculator_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FCalculator extends JFrame implements ActionListener {
    private final JPanel jtfPanel = new JPanel(new GridLayout(3, 1));
    private final JPanel textPanel = new JPanel(new GridLayout(3, 1));
    private final JPanel resultPanel = new JPanel(new GridLayout(1, 3));

    private final JTextField thenJTF = new JTextField();
    private final JTextField nowJTF = new JTextField();
    private final JTextField fuelJTF = new JTextField();

    private final JLabel thenText = new JLabel(" Last year mileage? ");
    private final JLabel nowText = new JLabel(" Current mileage? ");
    private final JLabel fuelText = new JLabel(" Fuel consumed between mileages? ");

    private final JLabel milesResult = new JLabel();
    private final JLabel litersResult = new JLabel();
    private final JLabel consumptionResult = new JLabel();


    private double oldMileage;
    private double newMileage;
    private double liters;

    private void dialogWindow() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(jtfPanel, BorderLayout.CENTER);
        add(textPanel, BorderLayout.WEST);
        add(resultPanel, BorderLayout.SOUTH);

        jtfPanel.add(thenJTF);
        jtfPanel.add(nowJTF);
        jtfPanel.add(fuelJTF);
        thenJTF.addActionListener(this);
        nowJTF.addActionListener(this);
        fuelJTF.addActionListener(this);

        textPanel.add(thenText);
        textPanel.add(nowText);
        textPanel.add(fuelText);

        resultPanel.add(milesResult);
        resultPanel.add(litersResult);
        resultPanel.add(consumptionResult);

        updateResult();
        pack();
        setLocationRelativeTo(null);
    }

    private void updateResult(){
        milesResult.setText(" Miles done: " + (newMileage - oldMileage) + " ");
        litersResult.setText("Fuel consumed: " + liters + " ");
        consumptionResult.setText("Average consumption: " + (liters / (newMileage - oldMileage)) + " ");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == thenJTF) {
                oldMileage = Integer.parseInt(thenJTF.getText());
            } else if (e.getSource() == nowJTF) {
                newMileage = Integer.parseInt(nowJTF.getText());
            } else if (e.getSource() == fuelJTF) {
                liters = Integer.parseInt(fuelJTF.getText());
            }
        } catch (NumberFormatException n) {
            System.out.println("Error: not a number");
        }
        updateResult();
    }

    public static void main(String[] args) {
        FCalculator fCalc= new FCalculator();
        fCalc.dialogWindow();
    }
}
