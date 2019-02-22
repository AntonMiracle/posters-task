package com;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Posters {
    private int[] houses;
    private int count;

    public Posters() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("pla.in"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        houses = new int[Integer.valueOf(lines.get(0))];
        for (int i = 1; i < lines.size(); ++i) {
            int h = Integer.valueOf(lines.get(i).split(" ")[1]);
            houses[i - 1] = h;
        }
        calculation();
        writeAnswer();
    }

    public Posters(String fileName) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        houses = new int[Integer.valueOf(lines.get(0))];
        for (int i = 1; i < lines.size(); ++i) {
            int h = Integer.valueOf(lines.get(i).split(" ")[1]);
            houses[i - 1] = h;
        }
    }

    public void calculation() {
        Stack<Integer> stack = new DequeStack<>();
        stack.push(houses[0]);
        int h;
        for (int i = 1; i < houses.length; ++i) {
            h = houses[i];
            if (h > stack.peek()) stack.push(h);
            else while (!stack.isEmpty()) {
                if (stack.peek() > h) {
                    ++count;
                    stack.pop();
                } else break;
                if (stack.isEmpty() || stack.peek() < h) stack.push(h);
            }
        }
        count += stack.size();
    }

    private void writeAnswer() {
        List<String> lines = Arrays.asList(String.valueOf(count));
        Path file = Paths.get("pla.out");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        return count;
    }
}
