package sprint_3.rock_paper_scissors;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Window extends JFrame implements Runnable {

    private JPanel mainPanel;
    private JButton rockButton, paperButton, scissorsButton, restartButton;
    private JLabel rockLabel, paperLabel, scissorsLabel, resetLabel, pScoreLabel, cScoreLabel;
    private choice compChoice;
    private int pScore, cScore;
    boolean run;
    Thread thread;

    private enum choice {ROCK, PAPER, SCISSORS}

    private final Random random = new Random();

    public Window() {
        super("Rock Paper Scissors");

        setVisible(true);
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setScore();
        rockLabel.setOpaque(true);
        paperLabel.setOpaque(true);
        scissorsLabel.setOpaque(true);

        rockButton.addActionListener(e -> {
            setUp();
            if (compChoice == choice.ROCK) {
                rockButton.setBackground(Color.yellow);
                rockLabel.setBackground(Color.yellow);
            } else if (compChoice == choice.PAPER) {
                rockButton.setBackground(Color.red);
                paperLabel.setBackground(Color.green);
                cScore++;
            } else if (compChoice == choice.SCISSORS) {
                rockButton.setBackground(Color.green);
                scissorsLabel.setBackground(Color.red);
                pScore++;
            }
            endRound();
        });

        paperButton.addActionListener(e -> {
            setUp();
            if (compChoice == choice.ROCK) {
                paperButton.setBackground(Color.green);
                rockLabel.setBackground(Color.red);
                pScore++;
            } else if (compChoice == choice.PAPER) {
                paperButton.setBackground(Color.yellow);
                paperLabel.setBackground(Color.yellow);
            } else if (compChoice == choice.SCISSORS) {
                paperButton.setBackground(Color.red);
                scissorsLabel.setBackground(Color.green);
                cScore++;
            }
            endRound();
        });

        scissorsButton.addActionListener(e -> {
            setUp();
            if (compChoice == choice.ROCK) {
                scissorsButton.setBackground(Color.red);
                rockLabel.setBackground(Color.green);
                cScore++;
            } else if (compChoice == choice.PAPER) {
                scissorsButton.setBackground(Color.green);
                paperLabel.setBackground(Color.red);
                pScore++;
            } else if (compChoice == choice.SCISSORS) {
                scissorsButton.setBackground(Color.yellow);
                scissorsLabel.setBackground(Color.yellow);
            }
            endRound();
        });

        restartButton.addActionListener(e -> {
            pScore = 0;
            cScore = 0;
            run = false;
            setScore();
            resetColors();
            resetLabel.setText("Reset in:");
        });
    }

    private void setUp(){
        resetColors();
        run = false;
        getRandomChoice();
    }

    private void getRandomChoice() {
        int number = random.nextInt(3);
        switch (number) {
            case 0 -> compChoice = choice.ROCK;
            case 1 -> compChoice = choice.PAPER;
            case 2 -> compChoice = choice.SCISSORS;
        }
    }

    private void setScore() {
        pScoreLabel.setText("Player score: " + pScore);
        cScoreLabel.setText("Computer score: " + cScore);
    }

    private void resetColors() {
        rockButton.setBackground(null);
        paperButton.setBackground(null);
        scissorsButton.setBackground(null);
        rockLabel.setBackground(null);
        paperLabel.setBackground(null);
        scissorsLabel.setBackground(null);
    }

    private void endRound() {
        setScore();
        thread = new Thread(this);
        thread.start();
        run = true;
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        resetLabel.setText("Reset in: 3");
        for (int i = 3; i > 0; ) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                i--;
                resetLabel.setText("Reset in: " + i);
                delta--;
            }
            if (!run) {
                return;
            }
        }
        resetLabel.setText("Reset in:");
        resetColors();
    }

    public static void main(String[] args) {
        Window window = new Window();
    }
}
