package main.java.com.mageslowwages;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {

    // Screen settings
    final int originalTileSize = 32; // 32x32 tile size.
    final int scale = 2; // Scale factor.

    final int tileSize = originalTileSize * scale; // 64x64 tile size.
    final int maxScreenCols = 20; // Maximum number of columns on the screen.
    final int maxScreenRows = 15; // Maximum number of rows on the screen.
    final int screenWidth = tileSize * maxScreenCols; // Screen width. (32*2)*20 = 640
    final int screenHeight = tileSize * maxScreenRows; // Screen height. (32*2)*15 = 480

    // Constructor

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the size of this class (JPanel)
        setBackground(Color.BLACK); // Draw a black background
        setDoubleBuffered(true); // *very simplified* can improve render performance
        setFocusable(true);
        requestFocus();
    }

}
