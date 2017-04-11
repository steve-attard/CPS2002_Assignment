import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapTest {
    Map map;

    @Before
    public void test_player_functions() {
        map = new Map();
    }

    @After
    public void test_plr_functions() {
        map = null;
    }

    @Test
    public void setMapSize_test() {
        Assert.assertEquals(false, map.setMapSize(-1,-1));
        Assert.assertEquals(true, map.setMapSize(5,5));
    }

}

