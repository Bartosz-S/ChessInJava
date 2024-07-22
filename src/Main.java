import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello chess!");
        JFrame window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        GamePanel gamePan = new GamePanel();
        window.add(gamePan);
        window.pack();
        gamePan.launch();
    }
}