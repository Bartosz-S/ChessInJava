import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello chess!");
        JFrame window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        GamePanel gamePan = new GamePanel();
        Button resetButton = new Button();
        resetButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("reset");
            }
        });
        gamePan.add(resetButton);
        window.add(gamePan);
        window.pack();
        gamePan.launch();
    }
}