package edu.emory.cs.algebraic;

public interface Numeral<K extends Numeral<K>> {

    void add(K n);
}
