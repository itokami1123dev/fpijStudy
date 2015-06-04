package com.company.chapter3;

// メソッドのスタティックインポート
import static com.company.Util.pTitle;

/**
 * 3.1 文字列のイテレーション
 * 2. mapToObjd
 */
public class Chap31_2_mapToObj {
    public static void main(String ...args){
        final String 文字列 = "abc";

        pTitle("文字コード１個右で表示");
        文字列.chars().forEach(OneRightChar::println);

        pTitle("小文字のみ文字コード１個右で表示");
        "aBcD".chars()
                .filter(c->Character.isLowerCase(c))
                .forEach(c -> OneRightChar.println(c));

        pTitle("小文字のみ文字コード１個右で表示");
        "aBcD".chars()
                .filter(Character::isLowerCase)
                .forEach(OneRightChar::println);
    }


    static class OneRightChar {
        private static void println(int iChar){
            System.out.println((char)(iChar+1));
        }
    }

}
