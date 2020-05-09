package CS61BProj1b;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestOffByN {
    @Test
    public void testOffByN() {
        OffByN obn = new OffByN(3);
        assertEquals(true, obn.equalChars('a', 'd'));
        assertEquals(true, obn.equalChars('s', 'p'));
        assertEquals(false, obn.equalChars('a', 'e'));
        assertEquals(false, obn.equalChars('a', 'x'));
    }
}
