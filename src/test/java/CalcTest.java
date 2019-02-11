import org.junit.Test;

import static org.junit.Assert.assertEquals;


class CalcTest {

    @Test
    void add() {
        Calc calc = new Calc();
        int result = calc.add(5, 6);
        assertEquals(11, result);
    }
}