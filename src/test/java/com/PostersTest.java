package com;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class PostersTest {
    private static Map<String, String> inOut;

    @BeforeClass
    public static void beforeClass() {
        inOut = getInOutFileName();
    }

    @Test
    public void inOutMapIsCorrect() {
        for (String in : inOut.keySet()) {
            String out = inOut.get(in);

            assertThat(in.split("[.]")[0]).isEqualTo(out.split("[.]")[0]);
            assertThat(in.split("[.]")[1]).isEqualTo("in");
            assertThat(out.split("[.]")[1]).isEqualTo("out");
        }
        assertThat(inOut.keySet().size() == 23).isTrue();
    }

    @Test
    public void calculation() throws IOException {
        makeTest("1a");
        makeTest("1b");
        makeTest("1c");
        makeTest("2a");
        makeTest("2b");
        makeTest("2c");
        makeTest("3a");
        makeTest("3b");
        makeTest("3c");
        makeTest("4a");
        makeTest("4b");
        makeTest("5a");
        makeTest("5b");
        makeTest("6a");
        makeTest("6b");
        makeTest("7a");
        makeTest("7b");
        makeTest("8a");
        makeTest("8b");
        makeTest("9a");
        makeTest("9b");
        makeTest("10a");
        makeTest("10b");
    }

    @Test
    public void test() {
        new Posters();
    }

    private void makeTest(String numOfFile) throws IOException {
        String key = "pla" + numOfFile + ".in";
        long startTime = System.nanoTime();
        Posters posters = new Posters(key);
        posters.calculation();
        long endTime = System.nanoTime();

        System.out.printf("%f \n", getSeconds(startTime, endTime));
        assertThat(getSeconds(startTime, endTime) < 5).isTrue();
        assertThat(posters.getCount() == readAnswer(inOut.get(key))).isTrue();
    }

    private static Map<String, String> getInOutFileName() {
        Map<String, String> inOut = new HashMap<>();
        String prefix = "pla[0-9].*[.]";
        File[] filesList = new File(".").listFiles();

        for (File in : filesList) {
            if (Pattern.compile(prefix + "in").matcher(in.getName()).matches()) {
                for (File out : filesList) {
                    if (Pattern.compile(prefix + "out").matcher(out.getName()).matches()
                            && out.getName().startsWith(in.getName().split("[.]")[0])) {
                        inOut.put(in.getName(), out.getName());
                        break;
                    }
                }
            }
        }
        return inOut;
    }

    private long readAnswer(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        return Long.valueOf(lines.get(0));
    }

    private double getSeconds(long startTime, long endTime) {
        long duration = (endTime - startTime);
        return duration / 1_000_000_000.;
    }

    //================================================
    //GROW RATE TESTS
    //================================================
    private int[] createBuildings(int numberOfBuildings, GrowthRateType type) {
        int[] result = new int[numberOfBuildings];
        if (type == GrowthRateType.ALL_GROWTH) {
            for (int i = 0; i < result.length; ) result[i] = ++i;
        }
        if (type == GrowthRateType.LAST_LOWER_THEN_ALL) {
            for (int i = 0; i < result.length - 1; ) result[i] = ++i + 1;
            result[result.length - 1] = 1;
        }
        if (type == GrowthRateType.ALL_REDUCTION) {
            int max = 1_000_000;
            for (int i = 0; i < result.length; ) result[i] = max - ++i;
        }
        if (type == GrowthRateType.ALL_REDUCTION_FIRST_LOWER_THEN_ALL) {
            int max = 1_000_000;
            for (int i = 1; i < result.length; ) result[i] = max - ++i;
            result[0] = 1;
        }
        if (type == GrowthRateType.GROWTH_AFTER_MIDDLE_REDUCTION) {
            int middle = result.length / 2;
            int h = 100;
            for (int i = 0; i < result.length; ++i) {
                if (i < middle) result[i] = ++h;
                else result[i] = --h;
            }
            result[0] = 1;
        }
        return result;
    }

    private long makeGrowthRateTest(int numbersOfBuildings, GrowthRateType type) {
        int[] buildings = createBuildings(numbersOfBuildings, type);
        long startTime = System.nanoTime();
        Posters posters = new Posters(buildings);
        posters.calculation();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    @Test
    public void growthRateAllGrowth() {
        Map<Integer, Long> results = new TreeMap<>();
        for (int j = 200, i = 0; i < 250_000; ) {
            i += j;
            results.put(i, makeGrowthRateTest(i, GrowthRateType.ALL_GROWTH));
        }
        System.out.println("==All growth : is O(n) ? : " + isOn(results));
    }

    @Test
    public void growthRateLastLowerThenAll() {
        Map<Integer, Long> results = new TreeMap<>();
        for (int j = 200, i = 0; i < 250_000; ) {
            i += j;
            results.put(i, makeGrowthRateTest(i, GrowthRateType.LAST_LOWER_THEN_ALL));
        }
        System.out.println("==Last lower then all : is O(n) ? " + isOn(results));
    }

    @Test
    public void growthRateAllReduction() {
        Map<Integer, Long> results = new TreeMap<>();
        for (int j = 200, i = 0; i < 250_000; ) {
            i += j;
            results.put(i, makeGrowthRateTest(i, GrowthRateType.ALL_REDUCTION));
        }
        System.out.println("==All reduction is O(n) ? : " + isOn(results));
    }

    @Test
    public void growthRateAllReductionAndFirstLowerThenAll() {
        Map<Integer, Long> results = new TreeMap<>();
        for (int j = 200, i = 0; i < 250_000; ) {
            i += j;
            results.put(i, makeGrowthRateTest(i, GrowthRateType.ALL_REDUCTION_FIRST_LOWER_THEN_ALL));
        }
        System.out.println("==All reduction but first lower then all is O(n) ? : " + isOn(results));
    }

    @Test
    public void growthRateGrowthAfterMiddleReduction() {
        Map<Integer, Long> results = new TreeMap<>();
        for (int j = 200, i = 0; i < 250_000; ) {
            i += j;
            results.put(i, makeGrowthRateTest(i, GrowthRateType.GROWTH_AFTER_MIDDLE_REDUCTION));
        }
        System.out.println("==Growth after middle reduction is O(n) ? : " + isOn(results));
    }

    private boolean isOn(Map<Integer, Long> resultBuildingTime) {
        List<Long> deltaList = new ArrayList<>();
        long lastNumBuild = 0;
        long lastTime = 0;
        for (int numBuild : resultBuildingTime.keySet()) {
            long time = resultBuildingTime.get(numBuild);
            if (lastNumBuild == 0 && lastTime == 0) {
                lastNumBuild = numBuild;
                lastTime = time;
                continue;
            }
            deltaList.add(Math.abs(time - lastTime) / 100_000);
        }

        long sum = 0;
        for (Long num : deltaList) {
            sum += num;
        }
        long avDelta = sum / deltaList.size();
        double infelicity = avDelta / 100.;

        Stack<Long> acceptable = new DequeStack<>();
        for (int i = 0; i < deltaList.size(); ++i) {
            long delta = deltaList.get(i);
            if (delta > (avDelta - infelicity) && delta < (avDelta + infelicity)) {
                acceptable.push(delta);
            }
        }
        double result = acceptable.size() / (double) deltaList.size();
        result = Math.floor((1 - result) * 100);
        return result > 80;
    }

    enum GrowthRateType {
        ALL_GROWTH, LAST_LOWER_THEN_ALL, ALL_REDUCTION, ALL_REDUCTION_FIRST_LOWER_THEN_ALL, GROWTH_AFTER_MIDDLE_REDUCTION
    }
}