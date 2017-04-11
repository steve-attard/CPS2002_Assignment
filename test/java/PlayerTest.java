import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    Player player;

    @Before
    public void test_player_functions() {
        player = new Player(5);
    }

    @After
    public void test_plr_functions() {
        player = null;
    }

    @Test
    public void setPosition_test() {
        Position p1 = new Position();
        p1.x = -1;
        p1.y = -1;

        Assert.assertEquals(false, player.isPositionInBounds(p1));
    }

}