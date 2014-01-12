package football;

import java.util.*;
import java.util.List;

/**
 * @author Alexey
 */
public class Referee implements Observer {

    private final Field field;

    public Referee(Field field) {
        this.field = field;
    }

    public void moveBallRandom() {
        final int outRangeValue = 1;
        Random random = new Random();
        int randomX = random.nextInt(field.getWidth() + outRangeValue) + 1;
        int randomY = random.nextInt(field.getHeight() + outRangeValue) + 1;

        field.getBall().setLocation(randomX, randomY);
        broadcast("Ball moved to [X=" + field.getBall().getX() + ", Y=" + field.getBall().getY() + "]");
    }

    public boolean isBallWithinField() {
        if (!field.isPositionWithinField(field.getBall())) {
            broadcast(field.getBall() + " outside!");
            return false;
        }
        return true;
    }

    public void defineAndSetBallOwner() {
        List<Player> sortedPlayers = new ArrayList<Player>(field.getPlayers());
        Collections.sort(sortedPlayers, new Comparator<Positionable>() {
            @Override
            public int compare(Positionable p1, Positionable p2) {
                double d1 = Math.round(field.getBall().distance(p1.getX(), p1.getY()));
                double d2 = Math.round(field.getBall().distance(p2.getX(), p2.getY()));
                return (int) (d1 - d2);
            }
        });
        Player ballOwnerPlayer = sortedPlayers.get(0);
        ballOwnerPlayer.registerObserver(this);
        field.getBall().setPlayerOwnerId(ballOwnerPlayer.getId());
        broadcast(ballOwnerPlayer + " captured the ball!");
    }

    private void broadcast(String message) {
        System.out.println(message.toUpperCase());
    }

    @Override
    public void onBallHit() {
        broadcast("Player with id=" + field.getBall().getPlayerOwnerId() + " hit the " + field.getBall());
    }
}
