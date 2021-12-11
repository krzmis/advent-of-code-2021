package dev.misiek.day11;

import dev.misiek.utils.ArrayUtils;
import dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.List;

public class Day11 {

    private static final Path FILE_PATH = Path.of("/Users/misk/IdeaProjects/advent-of-code-2021/src/java/dev/misiek/day11/input.txt");
    private static final int STEPS = 100;

    private static final List<String> INPUT_FILE = FileUtils.readFromFile(FILE_PATH);

    private static final int[][] OCTOPUSES_MATRIX = ArrayUtils.get2DIntArray(INPUT_FILE);
    private static final boolean[][] FLASHED_OCTOPUSES = ArrayUtils.get2DBooleanArray(INPUT_FILE);

    private static final int ROW_SIZE = OCTOPUSES_MATRIX.length;
    private static final int COLUMN_SIZE = OCTOPUSES_MATRIX[0].length;

    private static int flashes = 0;

    public static void main(String[] args) {
        int stepsForPartTwo = 0;

//        for (int step = 1; step <= STEPS; step++) {
        while (flashes != ROW_SIZE * COLUMN_SIZE) {
//            System.out.printf("%nIteration: %s", step);
            flashes = 0;

            System.out.printf("%nIteration: %s", stepsForPartTwo);
            ArrayUtils.printGrid(OCTOPUSES_MATRIX);

            for (int row = 0; row < ROW_SIZE; row++) {
                for (int column = 0; column < COLUMN_SIZE; column++) {
                    flash(row, column);
                }
            }

            ArrayUtils.printGrid(FLASHED_OCTOPUSES);
            for (int row = 0; row < ROW_SIZE; row++) {
                for (int column = 0; column < COLUMN_SIZE; column++) {

                    if (OCTOPUSES_MATRIX[row][column] > 9) {
                        OCTOPUSES_MATRIX[row][column] = 0;
                        FLASHED_OCTOPUSES[row][column] = false;
                    }
                }
            }

            stepsForPartTwo++;
        }

        System.out.println("\n############################ PART ONE ############################");
        System.out.printf("Number of flashes after %s steps = %s%n", STEPS, flashes);

        System.out.println("\n############################ PART TWO ############################");
        System.out.printf("Number of steps needed = %s%n", stepsForPartTwo);
    }

    private static void flash(int row, int column) {
        if (row < 0 || row >= ROW_SIZE
                || column < 0 || column >= COLUMN_SIZE) {
            return;
        }

        OCTOPUSES_MATRIX[row][column]++;

        if (OCTOPUSES_MATRIX[row][column] == 10 && !FLASHED_OCTOPUSES[row][column]) {
            FLASHED_OCTOPUSES[row][column] = true;
            flashes++;

            flash(row - 1, column);
            flash(row + 1, column);
            flash(row, column - 1);
            flash(row, column + 1);
            flash(row - 1, column - 1);
            flash(row - 1, column + 1);
            flash(row + 1, column - 1);
            flash(row + 1, column + 1);
        }
    }
}