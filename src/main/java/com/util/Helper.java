package com.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Helper {
    public static void writeError(String prefixFileName, String text) {
        List<String> lines = Arrays.asList(text);
        Path file = Paths.get(prefixFileName + ".ERROR");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeResult(String prefixFileName, List<String> lines) {
        Path file = Paths.get(prefixFileName + ".out");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
