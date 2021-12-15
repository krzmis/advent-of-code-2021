package dev.misiek.day13;

import dev.misiek.utils.ArrayUtils;
import dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.List;

public class Day13 {

    private static final List<String> INPUT_FILE = FileUtils.readFromFile(
            Path.of("/Users/misk/IdeaProjects/advent-of-code-2021/src/java/dev/misiek/day12/input.txt"));


    public static void main(String[] args) {
        int[][] grid = ArrayUtils.get2DIntArray(INPUT_FILE);

        System.out.println(grid.length);
        System.out.println(grid[0].length);
    }
}
