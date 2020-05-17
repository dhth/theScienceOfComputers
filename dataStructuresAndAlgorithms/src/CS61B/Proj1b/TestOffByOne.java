package CS61B.Proj1b;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertEquals(true, offByOne.equalChars('a', 'b'));
        assertEquals(true, offByOne.equalChars('b', 'a'));
        assertEquals(false, offByOne.equalChars('b', 'b'));
        assertEquals(false, offByOne.equalChars('x', 'a'));
        assertEquals(true, offByOne.equalChars('%', '&'));
    }
}