package football;

import java.awt.*;
import java.util.Random;

/**
 * @author Alexey
 */
public class Player implements Drawable, Runnable {
    private final String name;
    private final int id;
    private Point point = new Point();
    private final Referee referee;
    private final Field field;


    public Player(String name, int id, Point point, Field field, Referee referee) {
        this.name = name;
        this.id = id;
        this.point = point;
        this.field = field;
        this.referee = referee;
    }

    @Override
    public void run() {
        synchronized (field.getBall()) {
            while (!referee.isOverGame()) {
                if (referee.canPlayerHitBall() &&  field.getBall().getPlayerOwnerId() == this.id) {
                    hitBallRandom();
                    referee.setCanPlayerHitBall(false);
                    field.getBall().notifyAll();
                } else {
                    try {
                        field.getBall().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void hitBallRandom() {
        final Ball ball = field.getBall();
        final int outRangeValue = 1;
        Random random = new Random();
        int randomX = random.nextInt(field.getWidth() + outRangeValue) + 1;
        int randomY = random.nextInt(field.getHeight() + outRangeValue) + 1;
        ball.setLocation(randomX, randomY);
        referee.broadcast(this.toString() + " hit the ball to [X=" + ball.getX() + ", Y=" + ball.getY() + "]");
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", in position=[x=" + point.x + ", y=" + point.y + "]" +
                '}';
    }

    @Override
    public void draw() {
        System.out.println(" x ");
    }

    @Override
    public void setLocation(int x, int y) {
        point.setLocation(x, y);
    }

    @Override
    public int getX() {
        return (int) point.getX();
    }

    @Override
    public int getY() {
        return (int) point.getY();
    }

    @Override
    public double distance(int x, int y) {
        return point.distance(x, y);
    }

    public int getId() {
        return id;
    }
}
