package dev.misiek.day7;

import dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day7 {

    public static final Path FILE_PATH = Path.of("/home/misiek/IdeaProjects/advent-of-code-2021/src/java/dev/misiek/day7/input.txt");
    public static final String COMA = ",";

    public static void main(String[] args) {
        List<String> inputFile = FileUtils.readFromFile(FILE_PATH);
        List<Long> initialPosition = Arrays.stream(inputFile.stream()
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Input file is empty"))
                        .split(COMA))
                .map(Long::valueOf)
                .toList();

        long sumOfAllPositions = initialPosition.stream()
                .reduce(0L, Long::sum);

        long min = initialPosition.stream()
                .min(Long::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("Cannot compute min"));
        long max = initialPosition.stream()
                .max(Long::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("Cannot compute max"));

        long lowestCost = sumOfAllPositions;
        for (long position = min; position <= max; position++) {
            long requiredFuel = getSumOfFuelToPosition(initialPosition, position);
            if (requiredFuel < lowestCost) lowestCost = requiredFuel;
        }

        long lowestNoneConstantCost = Integer.MAX_VALUE;
        for (long position = min; position <= max; position++) {
            long requiredFuel = getFuelToPositionNonConstant(initialPosition, position);
            if (requiredFuel < lowestNoneConstantCost) lowestNoneConstantCost = requiredFuel;
        }

        // 344535
        System.out.println("\n############################### PART ONE ###############################\n");
        System.out.printf("Sum of all positions = %s%n", sumOfAllPositions);
        System.out.printf("Average position = %s%n", sumOfAllPositions / initialPosition.size());
        System.out.printf("Least required fuel = %s%n", lowestCost);

        // 95581659
        System.out.println("\n############################### PART TWO ###############################\n");
        System.out.printf("Least required fuel = %s%n", lowestNoneConstantCost);
    }

    private static long getSumOfFuelToPosition(List<Long> initialPosition, long destination) {
        return initialPosition.stream()
                .map(position -> Math.abs(destination - position))
                .reduce(0L, Long::sum);
    }

    private static long getFuelToPositionNonConstant(List<Long> initialPosition, long destination) {
        return initialPosition.stream()
                .map(position -> Math.abs(destination - position))
                .map(position -> (position * (position + 1) / 2L))
                .reduce(0L, Long::sum);
    }
}