package dev.misiek.day15;

import dev.misiek.utils.ArrayUtils;
import dev.misiek.utils.FileUtils;
import dev.misiek.utils.Position;

import java.nio.file.Path;
import java.util.*;

// https://www.baeldung.com/java-dijkstra
public class Day15 {

    private static final List<String> INPUT_FILE = FileUtils.readFromFile(
            Path.of("/Users/misk/IdeaProjects/advent-of-code-2021/src/java/dev/misiek/day15/input.txt"));


    public static void main(String[] args) {
        int[][] valueGrid = ArrayUtils.get2DIntArray(INPUT_FILE);

        int partOneResult = calculateDistance(valueGrid);
        int partTwoResult = calculateDistance(calculateExtendedDistance(valueGrid));

        System.out.printf("Part one result = %s%n", partOneResult);
        System.out.printf("Part one result = %s%n", partTwoResult);
    }

    //TODO: Add comments
    private static int[][] calculateExtendedDistance(int[][] valueGrid) {
        int maxMultiplier = 5;
        int maxCost = 9;
        int[][] extendedDistance = new int[valueGrid.length * maxMultiplier][];
        int baseLineLength = valueGrid[0].length;

        for (int y = 0; y < valueGrid.length; y++) {
            extendedDistance[y] = new int[valueGrid[0].length * maxMultiplier];

            for (int xMultiplier = 0; xMultiplier < maxMultiplier; xMultiplier++) {
                for (int x = 0; x < valueGrid[0].length; x++) {
                    int cost = (valueGrid[y % valueGrid.length][x] + xMultiplier);
                    if (cost > maxCost) {
                        cost -= maxCost;
                    }

                    extendedDistance[y][x + baseLineLength * xMultiplier] = cost;
                }
            }
        }

        for (int y = valueGrid.length; y < extendedDistance.length; y++) {
            extendedDistance[y] = new int[valueGrid[0].length * maxMultiplier];

            for (int x = 0; x < extendedDistance[0].length; x++) {
                int cost = extendedDistance[y - valueGrid.length][x] + 1;
                if (cost >= 10) {
                    cost -= 9;
                }

                extendedDistance[y][x] = cost;
            }
        }

        return extendedDistance;
    }

    public static int calculateDistance(int[][] grid) {
        // Fill array with max available distance
        int[][] distances = ArrayUtils.fillArray(grid, Integer.MAX_VALUE);

        // Set start position
        distances[0][0] = 0;

        // Create a Priority Query compared by value
        final PriorityQueue<Position> queue = new PriorityQueue<>(
                Comparator.comparingInt(Position::value));

        // Add starting position to filled with "infinite" distance array
        queue.add(new Position(0, 0, distances[0][0]));

        // Create end position
        final Position endPosition = new Position(grid.length - 1, grid[0].length - 1);

        while (!queue.isEmpty()) {
            final Position current = queue.poll();
            final List<Position> visitableNeighbors = getNeighbors(current, grid);

            for (Position neighbor : visitableNeighbors) {

                // Get distance to current neighbor
                int currentDistanceToNeighbor = distances[current.y()][current.x()]
                        + grid[neighbor.y()][neighbor.x()];

                int lowestDistance = distances[neighbor.y()][neighbor.x()];

                // Check if lowest distance starting from 0 is larger than distance to current neighbour
                if (lowestDistance > currentDistanceToNeighbor) {
                    // If neighbour distance is not "infinity", remove it.
                    if (distances[neighbor.y()][neighbor.x()] != Integer.MAX_VALUE) {
                        queue.remove(neighbor);
                    }

                    distances[neighbor.y()][neighbor.x()] = currentDistanceToNeighbor;
                    queue.add(new Position(neighbor.x(), neighbor.y(), currentDistanceToNeighbor));
                }
            }
        }

        return distances[endPosition.x()][endPosition.y()];
    }

    private static List<Position> getNeighbors(Position current, int[][] locations) {
        int x = current.x();
        int y = current.y();
        int minX = Math.max(0, x - 1);
        int maxX = Math.min(locations[y].length - 1, x + 1);
        int minY = Math.max(0, y - 1);
        int maxY = Math.min(locations.length - 1, y + 1);

        List<Position> neighbors = new ArrayList<>();

        if (y != minY) {
            neighbors.add(new Position(x, minY, locations[minY][x]));
        }

        if (y != maxY) {
            neighbors.add(new Position(x, maxY, locations[maxY][x]));
        }

        if (x != minX) {
            neighbors.add(new Position(minX, y, locations[y][minX]));
        }

        if (x != maxX) {
            neighbors.add(new Position(maxX, y, locations[y][maxX]));
        }

        return neighbors;
    }
}
