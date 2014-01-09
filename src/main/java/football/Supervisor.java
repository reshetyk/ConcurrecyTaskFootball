package football;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author Alexey
 */
public class Supervisor {

    private final Field field;

    public Supervisor(Field field) {
        this.field = field;
    }

    public void moveBallRandom() {
        Ball ball = findBallOnField();
        if (ball != null) {
            field.getPoints().remove(ball);
        }

        final int outRangeValue = 1;
        Random random = new Random();
        int randomX = random.nextInt(field.getWidth() + outRangeValue) + 1;
        int randomY = random.nextInt(field.getHeight() + outRangeValue) + 1;

        Ball movedBall = new Ball(randomX, randomY);
        field.addPoint(movedBall);
        broadcast("Ball moved to [X=" + movedBall.x + ", Y=" + movedBall.y + "]");
    }

    public boolean isBallWithinField() {
        if (!Field.isPointWithinField(findBallOnField(), field)) {
            broadcast(findBallOnField() + " outside!");
            return false;
        }
        return true;
    }

    public void defineAndSetBallOwner() {
        final Ball ball = findBallOnField();
        List<Point> sortedPoints = new ArrayList<Point>(field.getPoints().size());
        sortedPoints.addAll(field.getPoints());
        sortedPoints.remove(ball);
//        System.out.println(Arrays.toString(sortedPoints.toArray()));
        Collections.sort(sortedPoints, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int d1 = (int) Math.round(ball.distance(p1));
                int d2 = (int) Math.round(ball.distance(p2));

                return d1 < d2 ? -1 : d1 == d2 ? 0 : 1;
            }
        });
//        System.out.println(Arrays.toString(sortedPoints.toArray()));
        Player ballOwnerPlayer = (Player) sortedPoints.get(0);
        ball.setPlayer(ballOwnerPlayer);
        broadcast(ballOwnerPlayer + " captured the ball!");
    }

    private Ball findBallOnField() {
        for (Point p : field.getPoints()) {
            if (p instanceof Ball) {
                return (Ball) p;
            }
        }
        return null;
    }

    private void broadcast(String message) {
        System.out.println(message.toUpperCase());
    }
}
