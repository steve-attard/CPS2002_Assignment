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

    @Test
    public void getTileType_test() {
        Map map = new Map();
        map.size = 5;
        map.table[3][3] = 'g';
        map.table[1][1] = 'w';
        Assert.assertEquals('g', map.getTileType(3,3));
        Assert.assertEquals('w', map.setMapSize(1,1));
    }

}

