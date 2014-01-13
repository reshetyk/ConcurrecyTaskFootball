package football;

import java.awt.*;
import java.util.Random;

/**
 * @author Alexey
 */
public class Launcher {

    public static void main(String[] args) {
        Field field = new Field();
        field.addPlayer(new Player("player 1", 1, new Point(10, 1)));
        field.addPlayer(new Player("player 2", 2, new Point (5, 10)));
        field.addPlayer(new Player("player 3", 3, new Point (15, 10)));
        field.addPlayer(new Player("player 4", 4, new Point (10, 19)));

        Referee referee = new Referee(field);
        referee.startGame();
    }
}
