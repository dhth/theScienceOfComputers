package CS61BProj1b;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindromeTrueCases() {
        assertEquals(palindrome.isPalindrome("racecar"), true);
        assertEquals(palindrome.isPalindrome("noon"), true);
        assertEquals(palindrome.isPalindrome("a"), true);
        assertEquals(palindrome.isPalindrome(""), true);
    }

    @Test
    public void testIsPalindromeFalseCases() {
        assertEquals(palindrome.isPalindrome("cat"), false);
        assertEquals(palindrome.isPalindrome("dummy word"), false);
        assertEquals(palindrome.isPalindrome("algorithm"), false);
    }

    @Test
    public void testIsPalindromeWithCharacterComparatorFalseCases() {
        OffByOne obo = new OffByOne();
        assertEquals(palindrome.isPalindrome("cat", obo), false);
        assertEquals(palindrome.isPalindrome("dummy word", obo), false);
        assertEquals(palindrome.isPalindrome("algorithm", obo), false);
    }

    @Test
    public void testIsPalindromeWithCharacterComparatorTrueCases() {
        OffByOne obo = new OffByOne();
        assertEquals(palindrome.isPalindrome("flake", obo), true);
        assertEquals(palindrome.isPalindrome("acefdb", obo), true);
        assertEquals(palindrome.isPalindrome("acexfdb", obo), true);
    }

    @Test
    public void testIsPalindromeWithCharacterComparator2FalseCases() {
        OffByN obo = new OffByN(3);
        assertEquals(palindrome.isPalindrome("cat", obo), false);
        assertEquals(palindrome.isPalindrome("dummy word", obo), false);
        assertEquals(palindrome.isPalindrome("algorithm", obo), false);
    }

    @Test
    public void testIsPalindromeWithCharacterComparator2TrueCases() {
        OffByN obo = new OffByN(3);
        assertEquals(palindrome.isPalindrome("aehd", obo), true);
        assertEquals(palindrome.isPalindrome("dg", obo), true);
        assertEquals(palindrome.isPalindrome("gkornj", obo), true);
    }
}