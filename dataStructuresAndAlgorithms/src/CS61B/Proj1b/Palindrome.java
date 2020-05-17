package CS61B.Proj1b;

import CS61B.Proj1a.LinkedListDeque;

public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    private Boolean isPalindromeHelper(Deque deque) {
        if (deque.size() <= 1) {
            return true;
        } else {
            if (deque.removeFirst() == deque.removeLast()) {
                return isPalindromeHelper(deque);
            } else {
                return false;
            }
        }
    }

    private Boolean isPalindromeHelperWithCharacterComparator(Deque deque,
                                                              CharacterComparator cc) {
        if (deque.size() <= 1) {
            return true;
        } else {
            if (cc.equalChars((char) deque.removeFirst(),
                    (char) deque.removeLast())) {
                return isPalindromeHelperWithCharacterComparator(deque, cc);
            } else {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word) {
        /*
         * create a helper method, that takes in a deque,
         * and does the following recursively:
         * base case:
         * if the size of the deque is 1 or zero, return true
         * else:
         *   remove first and last elements,
         *   if they're the same character, call the method itself,
         *   else, return false
         * */
        Deque deque = wordToDeque(word);
//        if (deque.size() <= 1){
//            return true;
//        }
        return isPalindromeHelper(deque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque deque = wordToDeque(word);
        return isPalindromeHelperWithCharacterComparator(deque, cc);
    }
}
