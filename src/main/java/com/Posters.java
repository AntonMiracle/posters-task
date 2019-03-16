package com;

import com.util.DequeStack;
import com.util.Helper;
import com.util.Stack;

import java.io.IOException;
import java.nio.file.Files;
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

            houses = new int[Integer.valueOf(lines.get(0))];
            for (int i = 1; i < lines.size(); ++i) {
                int h = Integer.valueOf(lines.get(i).split(" ")[1]);
                houses[i - 1] = h;
            }
            calculation();
            Helper.writeResult("pla", Arrays.asList(String.valueOf(count)));
        } catch (IOException e) {
            e.printStackTrace();
            Helper.writeError("pla", e.toString());
        }

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

    public Posters(int[] houses) {
        this.houses = houses;
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

    public int getCount() {
        return count;
    }
}
