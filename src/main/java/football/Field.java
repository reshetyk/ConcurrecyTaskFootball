package football;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey
 */
public class Field {
    private final List<Player> players = new ArrayList<Player>();
    private Ball ball;
    private int width = 20;
    private int height = 20;

    public boolean isPositionWithinField(Positionable positionable) {
        return !(positionable.getX() > width || positionable.getY() > height);
    }

//    public void draw() {
//        for (int i = 1; i <= width; i++) {
//            System.out.print("\n");
//            for (int j = 1; j <= height; j++) {
//                if (points.contains(new Point(j, i))) {
//                    int ndx = points.indexOf(new Point(j, i));
//                    Point p = points.get(ndx);
//                    if (p instanceof Ball) {
//                        System.out.print(" o ");
//                    } else if (p instanceof Player) {
////                        System.out.print("(" + p.x + "," + p.y + ")");
//                        System.out.print(" x ");
//                    }
//                    continue;
//                }
//                System.out.print(" . ");
//            }
//        }
//        System.out.print("\n\n");
//    }

    public Player getPlayerById(int playerId) {
        for (Player player : players) {
            if (playerId == player.getId()) {
                return player;
            }
        }

        throw new RuntimeException("Player with id=" + playerId + " not found");
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public void setSize(int width, int high) {
        this.width = width;
        this.height = high;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
