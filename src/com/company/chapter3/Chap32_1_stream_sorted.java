package com.company.chapter3;

// メソッドのスタティックインポート
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.Util.pTitle;

/**
 * 3.2 Comparatorインターフェースを実装
 * 1. コンパレータを使ったソート
 */
public class Chap32_1_stream_sorted {
    private static final List<Hero> heros = Arrays.asList(
            new Hero("あくましょうぐん", 10000),
            new Hero("あしゅらまん", 200),
            new Hero("ろびんますく", 100),
            new Hero("うぉーずまん", 100),
            new Hero("うるふまん", 90),
            new Hero("ばっふぁろーまん", 1000)
    );


    public static void main(String ...args){

        pTitle("元のリストを壊さずにラムダでソート");
        {
            List<Hero> sortedHeros = heros
                    .stream()
                    .sorted(
                            (hero1,hero2)->hero1.powerDiff(hero2)
                    )
                    .collect(Collectors.toList());
            sortedHeros.forEach(System.out::println);
        }


        pTitle("メソッド参照");
        {
            List<Hero> sortedHeros = heros
                    .stream()
                    .sorted(Hero::powerDiff)
                    .collect(Collectors.toList());
            sortedHeros.forEach(System.out::println);
        }
    }
}
