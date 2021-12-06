package dev.misiek.day6;

import dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.*;

public class Day6 {

    public static final Path FILE_PATH = Path.of("/home/misiek/IdeaProjects/advent-of-code-2021/src/java/dev/misiek/day6/input.txt");
    public static final String COMA = ",";
    public static final int END_DAY = 80;
    public static final int END_DAY_PART2 = 256;
    public static final int RESET = 6;
    public static final int NEW_FISH = 8;

    public static void main(String[] args) {
        List<String> inputFile = FileUtils.readFromFile(FILE_PATH);
        List<Integer> initialState = Arrays.stream(inputFile.stream()
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Input file is empty"))
                        .split(COMA))
                .map(Integer::parseInt)
                .toList();

        List<Integer> partOneResult = new ArrayList<>(initialState);
        computeNumberOfFishOfList(partOneResult);

        System.out.printf("Number of fish in Part 1 = %s%n", partOneResult.size());

        System.out.printf("Number of fish in Part 2 = %s%n", computeNumberOfFishOfMap(
                populateMap(initialState)).values().stream()
                .reduce(0L, Long::sum));
    }

    private static void computeNumberOfFishOfList(List<Integer> partOneResult) {
        for (int day = 0; day < END_DAY; day++) {
            List<Integer> currentGeneration = new ArrayList<>(partOneResult);

            for (int index = 0; index < currentGeneration.size(); index++) {
                Integer fish = currentGeneration.get(index);

                if (fish == 0) {
                    fish = RESET;
                    partOneResult.add(NEW_FISH);
                } else {
                    fish -= 1;
                }

                partOneResult.set(index, fish);
            }
        }
    }

    private static Map<Integer, Long> populateMap(List<Integer> initialList) {
        Map<Integer, Long> initialGeneration = new HashMap<>();

        // Map of Time to live and number of fishes with that ttl
        initialList.forEach(fish ->
                initialGeneration.put(fish, initialGeneration.getOrDefault(fish, 0L) + 1));

        return initialGeneration;
    }

    private static Map<Integer, Long> computeNumberOfFishOfMap(Map<Integer, Long> initialGeneration) {
        for (int day = 0; day < END_DAY_PART2; day++) {
            for (int ttl = 0; ttl <= 8; ttl++) {
                initialGeneration.put(ttl - 1, initialGeneration.getOrDefault(ttl, 0L));
            }

            initialGeneration.put(6, initialGeneration.getOrDefault(6, 0L) + initialGeneration.get(-1));
            initialGeneration.put(8, initialGeneration.remove(-1));
        }

        return initialGeneration;
    }

}