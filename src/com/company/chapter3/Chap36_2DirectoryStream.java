package com.company.chapter3;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.company.Util.pTitle;

public class Chap36_2DirectoryStream {
    public static void main(String ...args) throws IOException{

        pTitle("36_2.Directory stream とラムダ");
        Files.newDirectoryStream(
                Paths.get("src/com/company/chapter3"),
                path->path.getFileName().toString().startsWith("Chap36")
        ).forEach(System.out::println);
    }
}
