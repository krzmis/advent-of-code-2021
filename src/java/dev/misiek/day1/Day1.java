package dev.misiek.day1;

import dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

    public static final String INPUT_FILE_PATH = "/home/misiek/IdeaProjects/advent-of-code-2021/src/java/dev/misiek/utils/day1/input";

    public static void main(String[] args) {
        final Path path = Path.of(INPUT_FILE_PATH);

        List<Integer> depthMeasurements = FileUtils.readFromFile(path).stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        var singularIncreases = 0;
        for (int i = 0; i < depthMeasurements.size() - 1; i++) {
            if (depthMeasurements.get(i + 1) > depthMeasurements.get(i)) singularIncreases++;
        }

        System.out.printf("Singular increases = %s%n", singularIncreases);

        var tripleIncreases = 0;
        for (int i = 0; i < depthMeasurements.size() - 3; i += 1) {
            var firstSum = depthMeasurements.get(i) + depthMeasurements.get(i + 1) + depthMeasurements.get(i + 2);
            var secondSum = depthMeasurements.get(i + 1) + depthMeasurements.get(i + 2) + depthMeasurements.get(i + 3);

            if (secondSum > firstSum)
                tripleIncreases++;
        }

        System.out.printf("Triple increases = %s%n", tripleIncreases);
    }
}