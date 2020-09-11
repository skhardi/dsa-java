package edu.emory.cs.algebraic;

import java.util.Arrays;
import java.security.InvalidParameterException;

public class LongInteger extends SignedNumeral<LongInteger> implements Comparable<LongInteger> {

    protected byte[] digits;

    // default constructor
    public LongInteger() {
        this("0");
    }

    // copy constructor
    public LongInteger(LongInteger n) {
        super(n.sign); // call to associated constructor in SignedNumeral
        digits = Arrays.copyOf(n.digits, n.digits.length);
    }

    // constructor w/ string parameter
    public LongInteger(String n) {
        set(n);
    }

    /**
     *
     * @throws NullPointerException if `n` is null
     * @throws InvalidParameterException if `n` contains non-digit char (except [+-\d])
     */
    public void set(String n) {
            if(n == null)
                throw new NullPointerException();

            sign = switch(n.charAt(0)) {
                case '-' -> {
                    n = n.substring(1);
                    yield Sign.NEGATIVE;
                }
                case '+' -> {
                    n = n.substring(1);
                    yield Sign.POSITIVE;
                }
                default -> Sign.POSITIVE;
            };

            digits = new byte[n.length()];

            for (int i = 0, j = n.length() - 1; i < n.length(); i++, j--) {
                byte b = (byte)(n.charAt(i) - 48);
                digits[j] = b;
                if(b < 0 || b > 9) {
                    String s = String.format("%d is not a valid value.", b);
                    throw new InvalidParameterException(s);
                }
            }
    }

    @Override
    public void add(LongInteger n) {
        if(sign == n.sign)
            addSameSign(n);
        else
            addDifferentSign(n);
    }

    protected void addSameSign(LongInteger n) {
        int m = Math.max(digits.length, n.digits.length);
        byte[] sum = new byte[m+1];
        System.arraycopy(digits, 0, sum, 0, digits.length);

        for (int i = 0; i < n.digits.length; i++) {
            sum[i] += n.digits[i];
            if (sum[i] >= 10) {
                sum[i] -= 10;
                sum[i+1] += 1;
            }
        }

        digits = sum[m] == 0 ? Arrays.copyOf(sum, m) : sum;
    }

    protected void addDifferentSign(LongInteger n) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void multiply(LongInteger n) {
        if(n.toString().compareTo("0") == 0|| n.toString().compareTo("-0") == 0)
            sign = Sign.POSITIVE;
        sign = (sign == n.sign) ? Sign.POSITIVE : Sign.NEGATIVE;

        byte[] result = new byte[digits.length + n.digits.length];
        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < n.digits.length; j++) {
                int k = i+j, prod = digits[i] * n.digits[j];
                result[k] += prod;
                result[k+1] += result[k] / 10;
                result[k] %= 10;
            }
        }

        int m;
        for(m = result.length - 1; m > 0; m--) {
            if(result[m] != 0)
                break;
        }
        digits = ++m < result.length ? Arrays.copyOf(result, m) : result;
    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();

        if(sign == Sign.NEGATIVE)
            build.append("-");
        for (int i = digits.length - 1; i >= 0; i--) {
            build.append(digits[i]); }

        return build.toString();
    }

    @Override
    public int compareTo(LongInteger n) {
        if(isPositive())
            return n.isNegative() ? 1 : compareAbs(n);
        else
            return n.isPositive() ? -1 : -compareAbs(n);
    }

    public int compareAbs(LongInteger n) {
        int diff = digits.length - n.digits.length;

        if(diff == 0) {
            for (int i = digits.length - 1; i >= 0; i--) {
                diff = digits[i] - n.digits[i];
                if(diff != 0)
                    break;
            }
        }

        return diff;
    }
}
