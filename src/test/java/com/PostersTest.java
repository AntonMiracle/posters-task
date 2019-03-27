package com;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PostersTest {
    private static Map<String, String> inOut;
    private static double maxTime = 0;

    @Test
    public void calculation() throws IOException {
        makeTest("pla1a.in", 41);
        makeTest("pla1b.in", 105);
        makeTest("pla1c.in", 1);
        makeTest("pla2a.in", 718);
        makeTest("pla2b.in", 662);
        makeTest("pla2c.in", 4);
        makeTest("pla3a.in", 1190);
        makeTest("pla3b.in", 2105);
        makeTest("pla3c.in", 1705);
        makeTest("pla4a.in", 2201);
        makeTest("pla4b.in", 3449);
        makeTest("pla5a.in", 105834);
        makeTest("pla5b.in", 107570);
        makeTest("pla6a.in", 122285);
        makeTest("pla6b.in", 130604);
        makeTest("pla7a.in", 133315);
        makeTest("pla7b.in", 149362);
        makeTest("pla8a.in", 112754);
        makeTest("pla8b.in", 150254);
        makeTest("pla9a.in", 174118);
        makeTest("pla9b.in", 148540);
        makeTest("pla10a.in", 159396);
        makeTest("pla10b.in", 155393);
        System.out.println("Posters maxTime = " + maxTime + " seconds");
    }

    private void makeTest(String fileName, long result) throws IOException {
        double start = System.nanoTime();
        assertThat(new Posters().calculation(fileName)).isEqualTo(result);
        double time = (System.nanoTime() - start) / 1_000_000_000.0;
        maxTime = maxTime > time ? maxTime : time;
        assertThat(time < 5).isTrue();
    }

}