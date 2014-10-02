package football;

import java.awt.*;

/**
 * @author Alexey
 */
public class Launcher {

    public static void main(String[] args) {
        Field field = new Field();

        Referee referee = new Referee(field);

        field.addPlayer(new Player("player 1", 1, new Point(10, 1), field, referee));
        field.addPlayer(new Player("player 2", 2, new Point(5, 10), field, referee));
        field.addPlayer(new Player("player 3", 3, new Point(15, 10), field, referee));
        field.addPlayer(new Player("player 4", 4, new Point(10, 19), field, referee));

        referee.startGame();
    }
}
