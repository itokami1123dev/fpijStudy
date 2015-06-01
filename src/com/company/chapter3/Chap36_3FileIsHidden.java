package com.company.chapter3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.company.Util.pTitle;

public class Chap36_3FileIsHidden {
    public static void main(String... args) throws IOException {

        pTitle("36_3.特定ファイルを探す 全部");
        final File[] files1 = new File("src/com/company").
                listFiles();
        for (File f : files1) {
            System.out.println(f.getName());
        }

        pTitle("36_3.ファイルのみをラムダで抽出");
        final File[] files2 = new File("src/com/company").
                listFiles(file->file.isFile());
        for (File f : files2) {
            System.out.println(f.getName());
        }

        pTitle("36_3.ファイルのみをメソッド参照");
        final File[] files3 = new File("src/com/company").
                listFiles(File::isFile);
        for (File f : files3) {
            System.out.println(f.getName());
        }
    }
}
