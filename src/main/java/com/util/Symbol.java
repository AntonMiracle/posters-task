package com.util;

public class Symbol implements Comparable<Symbol> {
    private int index;
    private int shift;
    private char symbol;

    public Symbol(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(Symbol o) {
        return this.index - o.index;
    }

    @Override
    public boolean equals(Object o) {
        return this.index == ((Symbol) o).index;
    }

    @Override
    public int hashCode() {
        return index;
    }

    @Override
    public String toString() {
        return symbol + " [" + index + "] " + shift;
    }
}
