package com;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Posters {
    private int[] houses;
    private int length;
    private long result;

    private void readAndSetUpData(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        length = Integer.valueOf(lines.get(0));
        houses = new int[length];
        for (int i = 1; i < lines.size(); ++i) {
            houses[i - 1] = Integer.valueOf(lines.get(i).split(" ")[1]);
        }
    }

    private long getResult() {
        result = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(houses[0]);
        for (int i = 1; i < length; ++i) {
            int h = houses[i];
            if (h > stack.getFirst()) stack.addFirst(h);
            else {
                while (!stack.isEmpty()) {
                    if (h < stack.getFirst()) {
                        ++result;
                        stack.removeFirst();
                    } else break;
                    if (stack.isEmpty() || h > stack.getFirst()) stack.addFirst(h);
                }
            }
        }
        result += stack.size();
        return result;
    }

    private void writeResult() throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(String.valueOf(result));
        Files.write(Paths.get("pla.out"), lines, Charset.forName("UTF-8"));
    }

    public long calculation(String fileName) throws IOException {
        readAndSetUpData(fileName);
        getResult();
        writeResult();
        return result;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Posters().calculation(args[0]));
    }
}