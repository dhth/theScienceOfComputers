package CS61BProj1b;

public class OffByN implements CharacterComparator {
    private final int offset;

    public OffByN(int n) {
        offset = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return (x - y) == offset || (x - y) == -1 * offset;
    }
}
