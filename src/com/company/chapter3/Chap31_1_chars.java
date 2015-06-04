package com.company.chapter3;

// メソッドのスタティックインポート
import static com.company.Util.pTitle;

/**
 * 3.1 文字列のイテレーション
 * 1. chars
 */
public class Chap31_1_chars {
    public static void main(String ...args){
        final String 文字列 = "abc";

        pTitle("ラムダ省略無");
        文字列.chars().forEach(
                (int 文字コード) -> {
                    System.out.println(文字コード);
                }
        );

        pTitle("ラムダ省略");
        文字列.chars().forEach(
                文字コード->System.out.println(文字コード)
        );

        pTitle("メソッド参照");
        文字列.chars().forEach(System.out::println);
    }
}
