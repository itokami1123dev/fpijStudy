package com.company.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Sample {
    private final List<Hero> heros = Arrays.asList(
            new Hero("あくましょうぐん", 10000),
            new Hero("ろびんますく", 100),
            new Hero("うぉーずまん", 100),
            new Hero("ばっふぁろーまん", 1000)
    );

    private void title(String title) {
        System.out.println(" ");
        System.out.println("---------------------------------------------------------");
        System.out.println(" " + title);
        System.out.println(" ");
    }

    public void proc01() {
        title("テストデータ");

        heros.forEach(System.out::println);
    }

    public void proc02() {
        title("元のリストは壊さずに戦闘力順リストを出力！");

        List<Hero> sortedHeros = heros
                .stream()
                .sorted(
                        (hero1, hero2) -> hero1.powerDiff(hero2)
                )
                .collect(Collectors.toList());

        sortedHeros.forEach(System.out::println);
    }

    public void proc03() {
        title("メソッド参照だとみじかいよ");

        List<Hero> sortedHeros = heros
                .stream()
                .sorted(Hero::powerDiff)
                .collect(Collectors.toList());

        sortedHeros.forEach(System.out::println);
    }

    public void proc04() {
        title("ラムダだと逆にも書けるよ（メソッド参照はできないけど）");

        List<Hero> sortedHeros = heros
                .stream()
                .sorted(
                        (hero1, hero2) -> hero2.powerDiff(hero1)
                )
                .collect(Collectors.toList());

        sortedHeros.forEach(System.out::println);
    }

    public void proc05() {
        title("昇順と降順二つ書いた時になるべくコードの重複をなくす");

        // 昇順
        Comparator<Hero> ascendingPower = (hero1, hero2) -> hero1.powerDiff(hero2);
        {
            title("昇順");

            List<Hero> sortedHeros = heros
                    .stream()
                    .sorted(ascendingPower)
                    .collect(Collectors.toList());

            sortedHeros.forEach(System.out::println);
        }

        // 降順
        Comparator<Hero> descendingPower = ascendingPower.reversed();
        {
            title("降順");

            List<Hero> sortedHeros = heros
                    .stream()
                    .sorted(descendingPower)
                    .collect(Collectors.toList());

            sortedHeros.forEach(System.out::println);
        }
    }

    public void proc06() {
        title("一番つよいのは？");

        heros.stream()
                .max(Hero::powerDiff)
                .ifPresent(System.out::println);
    }

    public void proc30() {
        title("コンビニエンスメソッドで昇順");

        {
            title("ラムダ");
            Function<Hero, Integer> byPower = hero -> hero.getPower();

            List<Hero> sortedHeros = heros
                    .stream()
                    .sorted(comparing(byPower))
                    .collect(Collectors.toList());

            sortedHeros.forEach(System.out::println);
        }

        {
            title("メソッド参照");
            List<Hero> sortedHeros = heros
                    .stream()
                    .sorted(comparing(Hero::getPower))
                    .collect(Collectors.toList());

            sortedHeros.forEach(System.out::println);
        }
    }

    public void proc31() {
        title("複数条件");

        List<Hero> sortedHeros = heros
                .stream()
                .sorted(
                        comparing(Hero::getPower)
                                .thenComparing(Hero::getName)
                )
                .collect(Collectors.toList());

        sortedHeros.forEach(System.out::println);
    }

    // 3.4 CollectメソッドとCollectorsクラス

    public void proc40() {
        title("1000以上のパワーで抽出した結果をforEachで集約バージョン");

        List<Hero> power1000AndOver = new ArrayList<Hero>();

        heros.stream()
                .filter(hero -> hero.getPower() >= 1000)
                .forEach(hero -> power1000AndOver.add(hero));

        power1000AndOver.forEach(System.out::println);
    }

    public void proc41() {
        title("Collectors");
    }
}
