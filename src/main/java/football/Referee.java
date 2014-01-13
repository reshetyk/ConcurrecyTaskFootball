package football;

import java.util.*;

/**
 * @author Alexey
 */
public class Referee implements Observer {

    private final Field field;

    public Referee(Field field) {
        this.field = field;
        for (Player player : field.getPlayers()) {
            player.registerObserver(this);
        }
    }

    public boolean isBallWithinField() {
        if (!field.isPositionWithinField(field.getBall())) {
            broadcast(field.getBall() + " outside!");
            return false;
        }
        return true;
    }

    public void defineAndSetBallOwner() {
        if (field.getBall() == null) {
            throw new NullPointerException("Ball is not set");
        }
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
        field.getBall().setPlayerOwnerId(ballOwnerPlayer.getId());
        broadcast(ballOwnerPlayer + " captured the ball!");
    }

    private void broadcast(String message) {
        System.out.println(message.toUpperCase());
    }

    @Override
    public void onBallHit(Observable observable) {
        broadcast(observable + " hit the " + field.getBall());
    }

    public void startGame() {
        broadcast("Game started!");
        moveBallRandom();
        while (!isOverGame()) {
            defineAndSetBallOwner();
            final Player playerBallOwner = field.getPlayerById(field.getBall().getPlayerOwnerId());
            playerBallOwner.hitBallRandom(field);

        }
    }

    public boolean isOverGame() {
        if (isBallWithinField()) {
            return false;
        }
        broadcast("game over!");
        return true;
    }

    private void moveBallRandom() {
        final int outRangeValue = 1;
        Random random = new Random();
        int randomX = random.nextInt(field.getWidth() + outRangeValue) + 1;
        int randomY = random.nextInt(field.getHeight() + outRangeValue) + 1;

        Ball ball = field.getBall();
        if (ball == null) {
            ball = new Ball();
            field.setBall(ball);
        }
        ball.setLocation(randomX, randomY);
        broadcast("Ball moved to [X=" + ball.getX() + ", Y=" + ball.getY() + "]");
    }
}
