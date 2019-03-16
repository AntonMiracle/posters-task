package com.util;

public class SymbolGroup implements Comparable<SymbolGroup> {
    private int startIndex;
    private int endIndex;
    private int length;
    private char symbol;
    private int shift;

    public int getShift() {
        return shift;
    }

    public SymbolGroup(int startIndex, int endIndex, char symbol) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.symbol = symbol;
        this.length = endIndex - startIndex + 1;
    }

    public void getLeft() {
        --length;
    }

    public void shift() {
        ++shift;
    }

    @Override
    public String toString() {
        return symbol + " [" + startIndex + " : " + endIndex + "] " + length;
    }

    @Override
    public int compareTo(SymbolGroup o) {
        return this.startIndex - o.startIndex;
    }

    @Override
    public boolean equals(Object o) {
        return startIndex == ((SymbolGroup) o).startIndex;
    }

    @Override
    public int hashCode() {
        return startIndex;
    }

    public int getLength() {
        return length;
    }

    public char getSymbol() {
        return symbol;
    }
}
