import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    Calculator calc;

    @Before
    public void test_calculator_functions() {
        calc = new Calculator();
    }

    @After
    public void test_calc_functions() {
        calc = null;
    }

    @Test
    public void additionTest(){
        Assert.assertEquals(5+6, calc.add(5,6));
    }

    @Test
    public void subtractionTest(){
        Assert.assertEquals(6-5, calc.subtract(6,5));
    }

    @Test
    public void multiplicationTest(){
        Assert.assertEquals(6*5, calc.multiply(6,5));
    }

    @Test
    public void divisionTest(){
        Assert.assertEquals(-999, calc.divide(6,0));
        Assert.assertEquals(8/2, calc.divide(8,2));
    }
}
