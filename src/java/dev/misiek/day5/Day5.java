package dev.misiek.day5;

import dev.misiek.utils.FileUtils;
import dev.misiek.utils.Position;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Day5 {

    public static final Path FILE_PATH = Path.of("/home/misiek/IdeaProjects/advent-of-code-2021/src/java/dev/misiek/day5/input.txt");
    public static final String PAIR_SPLITTER = " -> ";
    public static final String COMA = ",";

    public static void main(String[] args) {
        // 955,125,151,929
        Stream<String> input = FileUtils.readFromFile(FILE_PATH).stream().map(line -> line.replace(PAIR_SPLITTER, COMA));

        // Position[x=955, y=125]=Position[x=151, y=929]
        MultiValuedMap<Position, Position> positions = getPositions(input);
        Map<Position, Integer> visitedPositions = getVisitedPositions(positions);

        System.out.printf("Solution = %s%n", getPartOneSolution(visitedPositions));
    }

    private static MultiValuedMap<Position, Position> getPositions(Stream<String> input) {
        MultiValuedMap<Position, Position> positions = new HashSetValuedHashMap<>();

        AtomicInteger counter = new AtomicInteger();
        input.forEach(line -> {
            int[] positionArray = Arrays.stream(line.split(COMA)).mapToInt(Integer::parseInt).toArray();

            final Position start = new Position(positionArray[0], positionArray[1]);
            final Position end = new Position(positionArray[2], positionArray[3]);

            if (positions.containsKey(start)) {
                System.out.printf("Duplicated key = %s -> %s%n", start, end);
            }

            counter.getAndIncrement();
            positions.put(start, end);
        });

        System.out.println("counter" + counter.get());

        return positions;
    }

    private static Map<Position, Integer> getVisitedPositions(MultiValuedMap<Position, Position> positions) {
        Map<Position, Integer> visitedPositions = new HashMap<>();

        positions.asMap().forEach((position1, multiPositions) -> multiPositions.forEach(position2 -> {
            if (position1.x() == position2.x()) {
                addVerticalLines(position1, position2, visitedPositions);
            } else if (position1.y() == position2.y()) {
                addHorizontalLines(position1, position2, visitedPositions);
            } else {
                addDiagonalLines(position1, position2, visitedPositions);
            }
        }));

        return visitedPositions;
    }

    private static void addVerticalLines(Position position1, Position position2, Map<Position, Integer> visitedPositions) {
        // Get lower value to get starting position
        int min = Math.min(position1.y(), position2.y());
        int max = Math.max(position1.y(), position2.y());

        // Iterate from start to end
        for (int i = min; i <= max; i++) {
            final Position currentPosition = new Position(position1.x(), i);

            // Get Current positions and increase if needed
            visitedPositions.put(currentPosition, visitedPositions.getOrDefault(currentPosition, 0) + 1);
        }
    }

    private static void addHorizontalLines(Position position1, Position position2, Map<Position, Integer> visitedPositions) {
        int min = Math.min(position1.x(), position2.x());
        int max = Math.max(position1.x(), position2.x());

        for (int i = min; i <= max; i++) {
            final Position currentPosition = new Position(i, position1.y());
            visitedPositions.put(currentPosition, visitedPositions.getOrDefault(currentPosition, 0) + 1);
        }
    }

    private static void addDiagonalLines(Position position1, Position position2, Map<Position, Integer> visitedPositions) {
        int movements = Math.abs(position2.x() - position1.x());

        // Get travel directions
        int xDirection = getDirection(position2.x(), position1.x());
        int yDirection = getDirection(position2.y(), position1.y());

        for (int i = 0; i <= movements; i++) {
            final Position position = new Position(position1.x() + (xDirection * i), position1.y() + (yDirection * i));
            visitedPositions.put(position, visitedPositions.getOrDefault(position, 0) + 1);
        }
    }

    private static int getDirection(int position2, int position1) {
        return position2 - position1 > 0 ? 1 : -1;
    }

    private static int getPartOneSolution(Map<Position, Integer> visitedPositions) {
        AtomicInteger positionsVisited = new AtomicInteger();
        visitedPositions.forEach((position, score) -> {
            if (score > 1) {
                positionsVisited.getAndIncrement();
            }
        });

        return positionsVisited.get();
    }
}
