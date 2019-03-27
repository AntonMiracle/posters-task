package com;

import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SymbolsTest {
    private static Map<String, List<String>> input;
    private static double maxTime = 0;

    @Test
    public void makeTests() throws IOException {
        makeTest("lit1.in", 4);
        makeTest("lit2.in", 16);
        makeTest("lit3a.in", 44599);
        makeTest("lit3b.in", 480766);
        makeTest("lit4a.in", 1499627);
        makeTest("lit4b.in", 48076920);
        makeTest("lit5a.in", 17536625);
        makeTest("lit5b.in", 1201923076);
        makeTest("lit6a.in", 47167845);
        makeTest("lit6b.in", 4807692306L);
        makeTest("lit7a.in", 130542635);
        makeTest("lit7b.in", 19230769228L);
        makeTest("lit8a.in", 580934901);
        makeTest("lit8b.in", 120192307690L);
        makeTest("lit9a.in", 1482550867);
        makeTest("lit9b.in", 480769230766L);
        makeTest("lit10a.in", 1819136406);
        makeTest("lit10b.in", 480769230766L);
        System.out.println("Symbols maxTime = " + maxTime + " seconds");
    }

    private void makeTest(String fileName, long result) throws IOException {
        long start = System.nanoTime();
        assertThat(new Symbols().calculation(fileName)).isEqualTo(result);
        double time = (System.nanoTime() - start) / 1_000_000_000.0;
        maxTime = maxTime > time ? maxTime : time;
        assertThat(time < 5).isTrue();
    }
}