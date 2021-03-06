package com.company.chapter3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.company.Util.pTitle;

public class Chap37_1getSubDirectoryList {
    public static void main(String... args) throws IOException {
        pTitle("37_1.一階層下まで一覧取得 forで頑張る");
        final String TEST_PATH = "src/com/company";
        List<File> files = new ArrayList<>();
        File[] startDir = new File(TEST_PATH).listFiles();
        for (File file : startDir) {
            File[] subDir = file.listFiles();
            if (subDir != null) {
                files.addAll(Arrays.asList(subDir));
            } else {
                files.add(file);
            }
        }
        files.stream().forEach(System.out::println);
    }
}
