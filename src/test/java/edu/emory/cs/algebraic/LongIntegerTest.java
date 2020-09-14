package edu.emory.cs.algebraic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class LongIntegerTest {

    @Test
    public void testConstructors() {
        // default constructor
        assertEquals("0", new LongInteger().toString());

        // copy constructor
        assertEquals("12", new LongInteger(new LongInteger("12")).toString());
        assertEquals("-34", new LongInteger(new LongInteger("-34")).toString());

        // constructor (string param)
        assertEquals("12", new LongInteger("12").toString());
        assertEquals("34", new LongInteger("+34").toString());
        assertEquals("-56", new LongInteger("-56").toString());
        assertEquals("-0", new LongInteger("-0").toString());

    }

    @Test
    public void testMultiply() {
        LongInteger a = new LongInteger("123456789");

        a.multiply(new LongInteger("1"));
        assertEquals("123456789", a.toString());

        a.multiply(new LongInteger("-1"));
        assertEquals("-123456789", a.toString());

        a.multiply(new LongInteger("0"));
        assertEquals("0", a.toString());

        a.multiply(new LongInteger("-0"));
        assertEquals("-0", a.toString());
    }

    @Test
    public void testCompareTo() {
        assertTrue(0 < new LongInteger("0").compareTo(new LongInteger("-0")));
        assertTrue(0 > new LongInteger("-0").compareTo(new LongInteger("0")));

        assertTrue(0 < new LongInteger("12").compareTo(new LongInteger("-34")));
        assertTrue(0 > new LongInteger("-12").compareTo(new LongInteger("34")));

        assertTrue(0 < new LongInteger("34").compareTo(new LongInteger("-12")));
        assertTrue(0 > new LongInteger("-34").compareTo(new LongInteger("12")));
    }
}
