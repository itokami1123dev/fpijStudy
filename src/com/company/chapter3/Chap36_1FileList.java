package com.company.chapter3;

import java.io.File;
import java.io.FilenameFilter;

import static com.company.Util.pTitle;
public class Chap36_1FileList {
    public static void main(String ...args){

        pTitle("36_1.FileNameFilterを匿名クラスで");
        File file = new File("src/com/company/chapter3");

        final String[] filesNm = file.list(
                new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.startsWith("Chap36");
                    }
                }
        );

        for (String fileNm:filesNm){
            System.out.println(fileNm);
        }

        pTitle("36_1.FileNameFilterをラムダ式で");
        final String[] filesNm2 = file.list(
                (dir,name)->name.startsWith("Chap36")
        );
        for (String fileNm:filesNm2){
            System.out.println(fileNm);
        }
    }
}
