package football;

import junit.framework.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * @author Alexey
 */
public class RefereeTest {
    @Test
    public void testDefineAndSetBallOwner() throws Exception {
        Field field = new Field();
        field.addPlayer(new Player("player 1", 1, new Point (10, 1)));
        field.addPlayer(new Player("player 2", 2, new Point (5, 10)));
        field.addPlayer(new Player("player 3", 3, new Point (15, 10)));
        field.addPlayer(new Player("player 4", 4, new Point (10, 19)));
        field.setBall(new Ball(8, 1));

        Referee referee = new Referee(field);
        referee.defineAndSetBallOwner();

        Assert.assertEquals(1, field.getBall().getPlayerOwnerId());

        Player player = field.getPlayerById(field.getBall().getPlayerOwnerId());
        player.hitBall(field.getBall(), 9, 16);
        referee.defineAndSetBallOwner();

        Assert.assertEquals(4, field.getBall().getPlayerOwnerId());
    }
}
