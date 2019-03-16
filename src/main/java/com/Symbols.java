package com;

import com.util.Helper;
import com.util.SymbolGroup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Symbols {
    private int length;
    private String name1;
    private String name2;
    private long result;


    public Symbols() {
        this("lit0.in");
    }

    public Symbols(String file) {
        List<String> input = null;
        try {
            input = Files.readAllLines(Paths.get(file));
            length = Integer.valueOf(input.get(0));
            name1 = input.get(1);
            name2 = input.get(2);
            result = calculate();
            if (file.equals("lit0.in")) Helper.writeResult("lit", Arrays.asList(String.valueOf(result)));
        } catch (IOException e) {
            e.printStackTrace();
            Helper.writeError("lit", e.toString());
            System.out.println("SYMBOLS");
        }
    }

    private long calculate() {
        return calculate(name1, name2, length);
    }

    private long calculate(String name1, String name2, int n) {
        long result = 0;
        char[] compare = name1.toCharArray();
        char[] prime = name2.toCharArray();
        Set<SymbolGroup> tree = compression(compare);
        boolean isCompressionEffective = tree.size() < n / 2;

        if (!isCompressionEffective) return makeShift(compare, prime, n);

        for (int i = 0; i < n; ++i) {
            for (SymbolGroup sg : tree) {
                if (prime[i] != sg.getSymbol()) {
                    sg.shift();
                } else {
                    result += sg.getShift();
                    sg.getLeft();
                    if (sg.getLength() == 0) tree.remove(sg);
                    break;
                }
            }
        }
        return result;
    }

    private Set<SymbolGroup> compression(char[] array) {
        Set<SymbolGroup> result = new TreeSet<>();
        int startIndex = 0;
        for (int i = 1; i < array.length; ++i) {
            if (array[i] != array[i - 1]) {
                result.add(new SymbolGroup(startIndex, i - 1, array[i - 1]));
                startIndex = i;
            }
            if (i == array.length - 1)
                result.add(new SymbolGroup(startIndex, i, array[i]));
        }
        return result;
    }

    private long makeShift(char[] compare, char[] prime, int n) {
        int i = -1;
        long shift = 0;
        char[] c1 = new char[n];
        while (++i < n) {
            int j = i - 1;
            while (++j < compare.length && prime[i] != compare[j]) {
                c1[j] = compare[j];
            }
            int tmp = j;
            while (i < tmp) {
                compare[tmp] = c1[tmp - 1];
                --tmp;
            }
            shift += Math.abs(i - j);
        }
        return shift;
    }

    public long getResult() {
        return result;
    }
}
