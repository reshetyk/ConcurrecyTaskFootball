package football;

/**
 * @author Alexey
 */
public class Launcher {

    public static void main(String[] args) {
        Field field = new Field();
        field.addPoint(new Player(10, 1));
        field.addPoint(new Player(5, 10));
        field.addPoint(new Player(15, 10));
        field.addPoint(new Player(10, 19));
        Supervisor supervisor = new Supervisor(field);
        supervisor.moveBallRandom();
        while (supervisor.isBallWithinField()) {
            field.draw();
            supervisor.defineAndSetBallOwner();
            supervisor.moveBallRandom();
        }
    }
}
