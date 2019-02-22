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

        System.out.printf("%f \n",getSeconds(startTime, endTime));
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

}