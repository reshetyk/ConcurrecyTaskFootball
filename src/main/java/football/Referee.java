package football;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Alexey
 */
public class Referee implements Runnable {

    private final Field field;
    private volatile boolean canPlayerHitBall;

    public Referee(Field field) {
        this.field = field;
    }

    public boolean isBallWithinField() {
        return field.isPositionWithinField(field.getBall());
    }

    @Override
    public void run() {
        synchronized (field.getBall()) {
            while (isBallWithinField()) {
                if (!canPlayerHitBall) {
                    defineAndSetBallOwner();
                    field.getBall().notifyAll();
                } else {
                    try {
                        field.getBall().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            broadcast(field.getBall() + " outside!");
            broadcast("game over!");
        }
    }

    public void defineAndSetBallOwner() {
        broadcast("referee defines player owner");
        final Ball ball = field.getBall();
        if (ball == null) {
            throw new NullPointerException("Ball is not set");
        }
        List<Player> sortedPlayers = new ArrayList<Player>(field.getPlayers());

        Collections.sort(sortedPlayers, new Comparator<Positionable>() {
            @Override
            public int compare(Positionable p1, Positionable p2) {
                double d1 = Math.round(ball.distance(p1.getX(), p1.getY()));
                double d2 = Math.round(ball.distance(p2.getX(), p2.getY()));
                return (int) (d1 - d2);
            }
        });

        Player ballOwnerPlayer = sortedPlayers.get(0);
        ball.setPlayerOwnerId(ballOwnerPlayer.getId());
        broadcast(ballOwnerPlayer + " captured the ball!");
        canPlayerHitBall = true;
    }

    private void moveBallRandom() {
        final int outRangeValue = 1;
        Random random = new Random();
        int randomX = random.nextInt(field.getWidth() + outRangeValue) + 1;
        int randomY = random.nextInt(field.getHeight() + outRangeValue) + 1;

        Ball ball = field.getBall();
        if (ball == null) {
            ball = new Ball();
            synchronized (ball) {
                field.setBall(ball);
                ball.setLocation(randomX, randomY);
                broadcast("Ball moved to [X=" + ball.getX() + ", Y=" + ball.getY() + "]");
            }
        }
    }

    public void broadcast(String message) {
        System.out.println(message.toUpperCase());
    }

    public void startGame() {
        broadcast("Game started!");
        moveBallRandom();
        ExecutorService executorService = Executors.newFixedThreadPool(field.getPlayers().size() + 1);
        for (Player player : field.getPlayers()) {
            executorService.submit(player);
        }
        executorService.submit(this);
        executorService.shutdown();
    }

    public boolean isOverGame() {
        return !isBallWithinField();
    }


    public boolean canPlayerHitBall() {
        return canPlayerHitBall;
    }

    public void setCanPlayerHitBall(boolean canPlayerHitBall) {
        this.canPlayerHitBall = canPlayerHitBall;
    }
}
