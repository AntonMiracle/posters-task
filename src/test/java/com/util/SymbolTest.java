package com.util;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

public class SymbolTest {

    @Test
    public void test1() {

        Set<Symbol> symbols = new TreeSet<>();
        for (int i = 0; i < 3; ++i) {
            symbols.add(new Symbol(i));
        }

        int index = -1;
        for (Symbol n : symbols) {
            if (++index == 0) assert n.getIndex() == index;
        }
    }

    @Test
    public void test2() {
        assert new Symbol(0).equals(new Symbol(0));
    }
}