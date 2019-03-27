package com;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Symbols {
    private int length;
    private int[] compare;
    private int[] prime;
    private int[][] compressedCompare;
    private boolean isCompressedEfficiencies;
    private long result;


    private void readAndSetUpData(String file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file));
        length = Integer.valueOf(lines.get(0));
        prime = new int[length];
        compare = new int[length];
        char[] c1 = lines.get(1).toCharArray();
        char[] c2 = lines.get(2).toCharArray();
        for (int i = 0; i < length; ++i) {
            compare[i] = c1[i];
            prime[i] = c2[i];
        }
        compressedCompare();
    }

    private void compressedCompare() {
        compressedCompare = new int[2][length];
        int index = 0;
        compressedCompare[0][index] = compare[0];
        compressedCompare[1][index] = 1;
        for (int i = 1; i < length; ++i) {
            if (compare[i] == compressedCompare[0][index]) {
                compressedCompare[1][index] = compressedCompare[1][index] + 1;
            } else {
                ++index;
                compressedCompare[0][index] = compare[i];
                compressedCompare[1][index] = 1;
            }
        }
        isCompressedEfficiencies = index < length / 2;
    }

    private long getResultWithCompressed() {
        result = 0;
        int startIndex = 0;
        int index;
        for (int i = 0; i < length; ++i) {
            int symbol = prime[i];
            index = startIndex;
            while (symbol != compressedCompare[0][index]) {
                result += compressedCompare[1][index];
                if (index == length - 1) break;
                else ++index;
            }
            if (compressedCompare[1][index] == 1) {
                while (index > startIndex) {
                    compressedCompare[0][index] = compressedCompare[0][index - 1];
                    compressedCompare[1][index] = compressedCompare[1][index - 1];
                    --index;
                }
                ++startIndex;
            } else {
                compressedCompare[1][index] = compressedCompare[1][index] - 1;
            }
        }
        return result;
    }

    private long getResultWithoutCompressed() {
        result = 0;
        int index;
        for (int i = 0; i < length; ++i) {
            index = i;
            int symbol = prime[i];
            while (symbol != compare[index]) {
                ++result;
                ++index;
            }
            while (index > i) {
                compare[index] = compare[index - 1];
                --index;
            }
        }
        return result;
    }

    private long getResult() {
        return isCompressedEfficiencies ? getResultWithCompressed() : getResultWithoutCompressed();
    }

    private void writeResult() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(String.valueOf(result));
        Files.write(Paths.get("lit.out"), lines, Charset.forName("UTF-8"));
    }

    public long calculation(String fileName) throws IOException {
        readAndSetUpData(fileName);
        getResult();
        writeResult();
        return result;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Symbols().calculation(args[0]));
    }
}
