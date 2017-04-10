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
}
