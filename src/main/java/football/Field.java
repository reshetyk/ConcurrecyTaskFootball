package football;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey
 */
public class Field {
    private final List<Point> points = new ArrayList<Point>();
    private int width = 20;
    private int height = 20;

    public static boolean isPointWithinField(Point point, Field field) {
        return !(point.getX() > field.width || point.getY() > field.height);
    }

    public void draw() {
        for (int i = 1; i <= width; i++) {
            System.out.print("\n");
            for (int j = 1; j <= height; j++) {
                if (points.contains(new Point(j, i))) {
                    int ndx = points.indexOf(new Point(j, i));
                    Point p = points.get(ndx);
                    if (p instanceof Ball) {
                        System.out.print(" o ");
                    } else if (p instanceof Player) {
//                        System.out.print("(" + p.x + "," + p.y + ")");
                        System.out.print(" x ");
                    }
                    continue;
                }
                System.out.print(" . ");
            }
        }
        System.out.print("\n\n");
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public List<Point> getPoints() {
        return points;
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
