package football;

import sun.net.www.content.audio.basic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Alexey
 */
public class Player implements Observable, Drawable {
    private final List<Observer> observers = new ArrayList<Observer>();
    private String name;
    private int id;
    private Point point = new Point();

    public Player(String name, int id, Point point) {
        this.name = name;
        this.id = id;
        this.point = point;
    }

    public void hitBall(Ball ball, int x, int y) {
        ball.setLocation(x, y);
        notifyObservers();
    }
    public void hitBallRandom(Field field) {
        final int outRangeValue = 1;
        Random random = new Random();
        int randomX = random.nextInt(field.getWidth() + outRangeValue) + 1;
        int randomY = random.nextInt(field.getHeight() + outRangeValue) + 1;
        field.getBall().setLocation(randomX, randomY);
        notifyObservers();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.onBallHit(this);
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", in position=[x=" + point.x + ", y=" + point.y + "]" +
                '}';
    }
}
