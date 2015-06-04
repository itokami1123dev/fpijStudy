package com.company.chapter3;

// メソッドのスタティックインポート

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.Util.pTitle;

/**
 * 3.2 Comparatorインターフェースを実装
 * 1. コンパレータを使ったソート
 */
public class Chap32_2_reversed_stream_sorted {
    private static final List<Hero> heros = Arrays.asList(
            new Hero("あくましょうぐん", 10000),
            new Hero("あしゅらまん", 200),
            new Hero("ろびんますく", 100),
            new Hero("うぉーずまん", 100),
            new Hero("うるふまん", 90),
            new Hero("ばっふぁろーまん", 1000)
    );


    public static void main(String... args) {

        pTitle("昇順と降順二つ書いた時になるべくコードの重複をなくす");

        // 昇順
        Comparator<Hero> ascendingPower = (hero1, hero2) -> hero1.powerDiff(hero2);
        {
            pTitle("昇順");

            List<Hero> sortedHeros = heros
                    .stream()
                    .sorted(ascendingPower)
                    .collect(Collectors.toList());

            sortedHeros.forEach(System.out::println);
        }

        // 降順
        Comparator<Hero> descendingPower = ascendingPower.reversed();
        {
            pTitle("降順");

            List<Hero> sortedHeros = heros
                    .stream()
                    .sorted(descendingPower)
                    .collect(Collectors.toList());

            sortedHeros.forEach(System.out::println);
        }

        pTitle("一番つよいのは？");

        heros.stream()
                .max(Hero::powerDiff)
                .ifPresent(System.out::println);

   }
}