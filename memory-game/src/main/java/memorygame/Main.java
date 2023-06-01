package memorygame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        JFrame.setDefaultLookAndFeelDecorated(true);
        window.setTitle("Memory Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(500, 500));
        new PlayState().update(jPanel);
        window.setContentPane(jPanel);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.pack();
        window.setVisible(true);
    }
}
