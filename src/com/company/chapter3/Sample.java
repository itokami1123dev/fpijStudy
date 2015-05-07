package com.company.chapter3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class Sample {
    private final List<Hero> heros = Arrays.asList(
            new Hero("あくましょうぐん", 10000),
            new Hero("あしゅらまん", 200),
            new Hero("ろびんますく", 100),
            new Hero("うぉーずまん", 100),
            new Hero("うるふまん", 90),
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
        title("collectを使って命令型から宣言型に");

        List<Hero> power1000AndOver = heros.stream()
                .filter(hero -> hero.getPower() >= 1000)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        power1000AndOver.forEach(System.out::println);
    }

    public void proc42() {
        title("Collectorを使って短く書く");

        List<Hero> power1000AndOver = heros.stream()
                .filter(hero -> hero.getPower() >= 1000)
                .collect(Collectors.toList());

        power1000AndOver.forEach(System.out::println);
    }

    public void proc43() {
        title("groupingByでパワー毎の集計");

        Map<Integer, List<Hero>> powerGroup = heros.stream()
                .collect(Collectors.groupingBy(Hero::getPower));

        powerGroup.entrySet().forEach(System.out::println);
        powerGroup.entrySet().forEach(entry -> System.out.println(entry));
    }

    public void proc44() {
        title("groupingByでパワー毎の集計...名前だけ");

        Map<Integer, List<String>> powerGroupNameList = heros.stream()
                .collect(Collectors.groupingBy(Hero::getPower,
                        Collectors.mapping(Hero::getName, Collectors.toList())));

        powerGroupNameList.entrySet().forEach(System.out::println);
    }

    public void proc441() {
        title("mappingのみを試した");

        List<String> nameList = heros.stream()
                .collect(Collectors.mapping(Hero::getName, Collectors.toList()));

        nameList.forEach(System.out::println);
    }

    public void proc450() {
        title("頭文字で最強の前にBinaryOperator.maxByのみ確認");

        Optional<Hero> saikyo = heros.stream()
                .reduce(BinaryOperator.maxBy(Hero::powerDiff));

        saikyo.ifPresent(System.out::println);
    }

    public void proc451() {
        title("頭文字の中で最強");

        Map<String, Optional<Hero>> saikyoInGroup = heros.stream()
                .collect(Collectors.groupingBy(
                        hero -> hero.getName().substring(0, 1),
                        Collectors.reducing(
                                BinaryOperator.maxBy(Hero::powerDiff)
                        )
                ));

        saikyoInGroup.entrySet().forEach(System.out::println);
    }

    // 3.5 ディレクトリの全ファイルをリスト

    public void proc51(){
        title("ファイル一覧を外部イテレータで..");

        File dir = new File(".");
        String[] fileNames = dir.list();

        for (String fileName:fileNames){
            System.out.println("fileName="+fileName);
        }
    }

    public void proc52(){
        title("ファイル一覧をstreamで");

        try(Stream<Path> stream = Files.list(Paths.get("."))){
            stream.forEach(System.out::println);
        }catch (IOException e){
            System.out.println("error!");
        }
    }

    public void proc53(){
        title("ファイル一覧をstreamでFileのみを表示");

        try(Stream<Path> stream = Files.list(Paths.get("."))){
            stream
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        }catch (IOException e){
            System.out.println("error!");
        }
    }
}
