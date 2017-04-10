import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    Player player;

    @Before
    public void test_player_functions() {
        player = new Player();
    }

    @After
    public void test_plr_functions() {
        player = null;
    }

    @Test
    public void setPosition_test() {
        Position p1 = new Position();
        Position p2 = new Position();
        p1.x = -1;
        p1.y = -1;
        p2.x = 3;
        p2.y = 3;


        Assert.assertEquals(false, player.setPosition(p1));
        Assert.assertEquals(true, player.setPosition(p2));
    }

}