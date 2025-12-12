package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.parser.SleepingSessionParser;
import ru.yandex.practicum.sleeptracker.sleepingsession.SleepingSession;
import ru.yandex.practicum.sleeptracker.analysis.*;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Function;

public class SleepTrackerApp {

    public static void main(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException("Не указан путь к файлу с логом сна");
        }

        String filePath = args[0];
        SleepingSessionParser parser = new SleepingSessionParser();

        List<SleepingSession> sessions;
        try {
            sessions = Files.lines(Path.of(filePath))
                    .map(parser::parse)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + filePath, e);
        }

        List<Function<List<SleepingSession>, ? extends SleepAnalysisResult<? extends Serializable>>> analyzers = List.of(
                new TotalSessionsAnalyzer(),
                new MinDurationAnalyzer(),
                new MaxDurationAnalyzer(),
                new AverageDurationAnalyzer(),
                new BadQualityCountAnalyzer(),
                new SleeplessNightAnalyzer(),
                new ChronotypeAnalyzer()
        );

        analyzers.stream()
                .map(analyzer -> analyzer.apply(sessions))
                .forEach(result ->
                        System.out.println(
                                result.getDescription() + ": " + result.getValue()
                        )
                );
    }
}