package com.company.chapter3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.company.Util.pTitle;
import static java.util.stream.Collectors.toList;

public class Chap37_2flatMap {
    public static void main(String... args) throws IOException {

        final String TEST_PATH = "src/com/company";

        pTitle("37_2.一階層下まで一覧取得 flatMap");
        List<File> files =
                Stream.of(new File(TEST_PATH).listFiles())
                        .flatMap(file ->
                                        file.listFiles() == null ?
                                                Stream.of(file) :
                                                Stream.of(file.listFiles())
                        )
                        .collect(toList());

        files.stream().forEach(System.out::println);
    }
}
