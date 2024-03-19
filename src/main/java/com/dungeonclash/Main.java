package main.java.com.dungeonclash;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Lógica principal do seu programa aqui
        Game game = new Game("game.txt");
        game.start();
    }
}
