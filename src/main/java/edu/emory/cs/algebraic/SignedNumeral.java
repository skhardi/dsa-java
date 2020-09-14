package edu.emory.cs.algebraic;

public abstract class SignedNumeral<K extends SignedNumeral<K>> implements Numeral<K> {

    protected Sign sign;

    public SignedNumeral() {
        this(Sign.POSITIVE);
    }

    public SignedNumeral(Sign sign) {
        this.sign = sign;
    }

    /* @return true if numeral positive */
    public boolean isPositive() {
        return sign == Sign.POSITIVE;
    }

    /* @return true if numeral negative */
    public boolean isNegative() {
        return sign == Sign.NEGATIVE;
    }

    public void flipSign() {
        sign = isPositive() ? Sign.NEGATIVE : Sign.POSITIVE;
    }

    public void subtract(K n) {
        n.flipSign();
        add(n);
        n.flipSign();
    }

    public abstract void multiply(K n);
}
