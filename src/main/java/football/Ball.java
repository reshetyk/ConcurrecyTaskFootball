package football;

import java.awt.*;

/**
 * @author Alexey
 */
public class Ball extends Point {
    private Player player;

    public Ball(int x, int y) {
        super(x, y);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
