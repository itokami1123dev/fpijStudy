package com.company.chapter3;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static com.company.Util.pTitle;
import static java.util.stream.Collectors.toList;

public class Chap38_1FileWatch {
    public static void main(String... args) throws IOException, InterruptedException {

        final String TEST_PATH = "src/com/company";

        pTitle("38_1. WatchServiceでファイル変更を監視");

        final Path path = Paths.get(TEST_PATH);

        final WatchService watchService =
                path.getFileSystem()
                        .newWatchService();

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_MODIFY);

        System.out.println("設定完了...監視中....");

        final WatchKey watchKey = watchService.poll(
                1,
                TimeUnit.MINUTES
        );

        if (watchKey != null) {
            watchKey.pollEvents()
                    .stream()
                    .forEach(
                            event -> System.out.println(
                                    event.context()
                            )
                    );
        }

    }
}
