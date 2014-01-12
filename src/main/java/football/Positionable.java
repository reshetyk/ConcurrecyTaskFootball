package football;

/**
 * @author Alexey
 */
public interface Positionable {
    void setLocation(int x, int y);
    int getX();
    int getY();
    double distance(int x, int y);
}
