import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
    Game game;

    @Before
    public void test_player_functions() {
        game = new Game();
    }

    @After
    public void test_plr_functions() {
        game = null;
    }

    @Test
    public void setNumPlayers_test() {
        Assert.assertEquals(false ,game.setNumPlayers(1));
        Assert.assertEquals(true, game.setNumPlayers(2));
    }

    @Test
    public void setMinMapSize_test() {
        Assert.assertEquals(5, game.setMinMapSize(2));
        Assert.assertEquals(8, game.setMinMapSize(8));
    }

    @Test
    public void printTurnAndPosition_test(){
        Map map = new Map();
        map.size = 5;
        map.generate(1);
        Player p = new Player(5, map, 1);
        Assert.assertEquals("Player 1's turn\nPlayer 1's current position: ("+p.position.x+","+p.position.y+")", game.printTurnAndPosition(1, p));
    }
}

