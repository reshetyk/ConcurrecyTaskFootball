package football;

import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Alexey
 */
public class Ball implements Drawable {
    private final static Lock LOCK = new ReentrantLock();
    private int playerOwnerId = -1;
    private final Point point = new Point();

    public Ball() {
    }

    public void lock() {
//        System.out.println("Locked by " + Thread.currentThread().getName());
        LOCK.lock();
    }

    public void unlock() {
        LOCK.unlock();
//        System.out.println("Unlocked by " + Thread.currentThread().getName());
    }

    @Override
    public void draw() {
        System.out.println(" o ");
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

    public int getPlayerOwnerId() {
        return playerOwnerId;
    }

    public void setPlayerOwnerId(int playerOwnerId) {
        this.playerOwnerId = playerOwnerId;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "playerOwnerId=" + playerOwnerId +
                ", in position=[x=" + point.x + ", y=" + point.y + "]" +
                '}';
    }
}
