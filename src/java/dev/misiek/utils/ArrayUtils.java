package dev.misiek.utils;

import java.util.Arrays;
import java.util.List;

public abstract class ArrayUtils {

    public static int[][] get2DIntArray(List<String> input) {
        return input.stream()
                .map(line -> line.chars()
                        .map(character -> Integer.parseInt(Character.toString(character)))
                        .toArray())
                .toArray(int[][]::new);
    }

    public static boolean[][] get2DBooleanArray(List<String> input) {
        int rowSize = input.size();
        int columnSize = input.get(0).length();
        boolean[][] boolArray = new boolean[rowSize][columnSize];

        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < columnSize; column++) {
                boolArray[row][column] = false;
            }
        }

        return boolArray;
    }

    public static int[][] increment2DIntArray(int[][] intArray, int incrementBy) {
        return Arrays.stream(intArray)
                .map(arr -> Arrays.stream(arr)
                        .map(cell -> cell + incrementBy)
                        .toArray())
                .toArray(int[][]::new);
    }

    public static void printGrid(int[][] array) {
        System.out.println();
        Arrays.stream(array).forEach(row ->
                        System.out.printf("%s%n", Arrays.toString(row)
                                .replace(", ", "")
                                .replace("[", "")
                                .replace("]", "")));

    }

    public static void printGrid(boolean[][] array) {
        System.out.println();
        Arrays.stream(array).forEach(row ->
                        System.out.printf("%s%n", Arrays.toString(row)
                                .replace(", ", " ")
                                .replace("[", "")
                                .replace("]", "")));

    }

    public static int[][] fillArray(int[][] arr, int filler) {
        int[][] filledArr = new int[arr.length][];

        for (int i = 0; i < filledArr.length; i++) {
            filledArr[i] = new int[arr[i].length];
            Arrays.fill(filledArr[i], filler);
        }

        return filledArr;
    }

}