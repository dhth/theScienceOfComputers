package CS61B.Lab8;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Tests by Brendan Hu, Spring 2015, revised for 2016 by Josh Hug
 */
public class TestMyHashMap {

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestMyHashMap.class);
    }

    @Test
    public void testGetSimple() {
        MyHashMap<String, String> map = new MyHashMap<>(5);
        map.put("hello", "world");
        map.put("wat", "up");
        map.put("apple", "pie");
        String v1 = map.get("hello");
        String v2 = map.get("apple");
        assertEquals("world", v1);
        assertEquals("pie", v2);
    }

    @Test
    public void testGetOverride() {
        MyHashMap<String, String> map = new MyHashMap<>(5);
        map.put("hello", "world");
        map.put("wat", "up");
        map.put("hello", "world2");
        String v1 = map.get("hello");
        assertEquals("world2", v1);
    }

    @Test
    public void testRemoveWithKey() {
        MyHashMap<String, String> map = new MyHashMap<>(5);
        map.put("hello", "world");
        map.put("hello1", "world1");
        map.put("hello2", "world2");
        map.put("wat", "up");
        String removedValue1 = map.remove("hello1");
        String removedValue2 = map.remove("hello2");
        assertEquals("world1", removedValue1);
        assertEquals("world2", removedValue2);
        assertNull(map.get("hello1"));
        assertNull(map.get("hello2"));
    }

    @Test
    public void testRemoveWithKeyAndValue() {
        MyHashMap<String, String> map = new MyHashMap<>(5);
        map.put("hello", "world");
        map.put("hello1", "world1");
        map.put("hello2", "world2");
        map.put("wat", "up");
        String removedValue1 = map.remove("hello1", "world1");
        String removedValue2 = map.remove("hello2", "world3");
        assertEquals("world1", removedValue1);
        assertNull(removedValue2);
        assertNull(map.get("hello1"));
        assertEquals("world2", map.get("hello2"));
    }

    private String randomString(int n) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder(n);
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    @Test
    public void testResize() {
        MyHashMap<String, String> map = new MyHashMap<>(5);
        for (int i = 0; i < 3; i++) {
            map.put(randomString(5), randomString(5));
        }
        assertEquals(3 / (double) 5, map.getCurrentLoad(), 0.1);
        map.put(randomString(5), randomString(5));
        assertEquals(4 / (double) 10, map.getCurrentLoad(), 0.1);
        for (int i = 0; i < 3; i++) {
            map.put(randomString(5), randomString(5));
        }
        assertEquals(7 / (double) 10, map.getCurrentLoad(), 0.1);
        map.put(randomString(5), randomString(5));
        assertEquals(8 / (double) 20, map.getCurrentLoad(), 0.1);
    }

    @Test
    public void sanityGenericsTest() {
        try {
            MyHashMap<String, String> a = new MyHashMap<String, String>();
            MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
            MyHashMap<Integer, String> c = new MyHashMap<Integer, String>();
            MyHashMap<Boolean, Integer> e = new MyHashMap<Boolean, Integer>();
        } catch (Exception e) {
            fail();
        }
    }

    //assumes put/size/containsKey/get work
    @Test
    public void sanityClearTest() {
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
            //make sure put is working via containsKey and get
            assertTrue(null != b.get("hi" + i)
                    && b.containsKey("hi" + i));
        }
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
        assertEquals(null, b.get("starChild"));
        b.put("starChild", 5);
        assertNotEquals(null, b.get("starChild"));
        b.put("KISS", 5);
        assertNotEquals(null, b.get("KISS"));
        assertNotEquals(null, b.get("starChild"));
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    public void sanityPutTest() {
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi") && b.get("hi") != null);
    }

    /*
     * Sanity test for keySet
     */
    @Test
    public void sanityKeySetTest() {
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
        HashSet<String> values = new HashSet<String>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
            values.add("hi" + i);
        }
        assertEquals(455, b.size()); //keys are there
        Set<String> keySet = b.keySet();
        assertTrue(values.containsAll(keySet));
        assertTrue(keySet.containsAll(values));
    }

    /*
     * Test for general functionality and that the properties of Maps hold.
     */
    @Test
    public void functionalityTest() {
        MyHashMap<String, String> dictionary = new MyHashMap<>();
        assertEquals(0, dictionary.size());

        // can put objects in dictionary and get them
        dictionary.put("hello", "world");
        assertTrue(dictionary.containsKey("hello"));
        assertEquals("world", dictionary.get("hello"));
        assertEquals(1, dictionary.size());

        // putting with existing key updates the value
        dictionary.put("hello", "kevin");
        assertEquals(1, dictionary.size());
        assertEquals("kevin", dictionary.get("hello"));

        // putting key in multiple times does not affect behavior
        MyHashMap<String, Integer> studentIDs = new MyHashMap<String, Integer>();
        studentIDs.put("sarah", 12345);
        assertEquals(1, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        studentIDs.put("alan", 345);
        assertEquals(2, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        assertEquals(345, studentIDs.get("alan").intValue());
        studentIDs.put("alan", 345);
        assertEquals(2, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        assertEquals(345, studentIDs.get("alan").intValue());
        studentIDs.put("alan", 345);
        assertEquals(2, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        assertEquals(345, studentIDs.get("alan").intValue());
        assertTrue(studentIDs.containsKey("sarah"));
        assertTrue(studentIDs.containsKey("alan"));

        // handle values being the same
        assertEquals(345, studentIDs.get("alan").intValue());
        studentIDs.put("evil alan", 345);
        assertEquals(345, studentIDs.get("evil alan").intValue());
        assertEquals(studentIDs.get("evil alan"), studentIDs.get("alan"));
    }
}
