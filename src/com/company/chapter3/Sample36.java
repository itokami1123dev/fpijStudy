package com.company.chapter3;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Sample36 {

    private void title(String title) {
        System.out.println(" ");
        System.out.println("---------------------------------------------------------");
        System.out.println(" " + title);
        System.out.println(" ");
    }

    public void proc60() {
        title("匿名クラス");

        final String[] files =
                new File(".").list(
                        new FilenameFilter() {
                            @Override
                            public boolean accept(final File dir, final String name) {
                                return name.endsWith(".iml");
                            }
                        }
                );

        for (String file: files){
            System.out.println(file);
        }
    }

    public void proc61() {
        title("ラムダ式");

        final String[] files =
                new File(".").list(
                        (dir, name) -> name.endsWith(".iml")
                );

        for (String file: files){
            System.out.println(file);
        }
    }

    public void proc62() throws IOException{
        title("newDirectoryStream!!");

        Files.newDirectoryStream(
            Paths.get("."),
                path->path.toString().endsWith(".iml")
        ).forEach(System.out::println);
    }
}
