package edu.emory.cs.algebraic;

public enum Sign {
    POSITIVE('+'),
    NEGATIVE('-');

    private final char val;

    Sign(char val) {
        this.val = val;
    }

    public char value() {
        return val;
    }
}
