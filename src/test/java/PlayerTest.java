import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    Player player;

    @Before
    public void test_player_functions() {
        Map map = new Map();
        map.size = 5;
        map.generate(true);
        player = new Player(5, map);
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

    @Test
    public void undoPreviousMove_test() {
        player.position.x = -1;
        player.position.y = 2;
        player.undoPreviousMove('l');
        Assert.assertEquals(0, player.position.x);

    }

    @Test
    public void move_test() {
        player.position.x = 0;
        player.position.y = 2;
        player.move('d');
        Assert.assertEquals(0,player.position.x);
        Assert.assertEquals(3, player.position.y);
    }

}