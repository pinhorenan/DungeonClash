package main.java.com.mageslowwages;

import javax.swing.JFrame;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets window's close button action.
        window.setResizable(false); // Causes the windows not to be resizable
        window.setTitle("Age of Mages with Low Wages"); // Set window title

        GamePanel gamePanel = new GamePanel(); // Creates the instance for the game window.
        window.add(gamePanel); // Adds the panel subcomponent to the game window.

        window.pack(); //Causes this window to be sized to fit the preferred size and layouts of its subcomponents.

        window.setLocationRelativeTo(null); // Centers the window.
        window.setVisible(true); // Sets the window as visible

        Game game = new Game("old_game.txt");
        game.start();
    }
}
