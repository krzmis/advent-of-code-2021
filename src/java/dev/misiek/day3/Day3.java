package dev.misiek.day3;

import dev.misiek.utils.BaseUtils;
import dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static final Path INPUT_FILE_PATH = Path.of("/home/misiek/IdeaProjects/advent-of-code-2021/src/java/dev/misiek/day3/input.txt");
    public static final String ZERO = "0";
    public static final String ONE = "1";

    public static void main(String[] args) {
        List<String> diagnosticData = FileUtils.readFromFile(INPUT_FILE_PATH);
        List<String> oxygenRating = new ArrayList<>(diagnosticData);
        List<String> co2Rating = new ArrayList<>(diagnosticData);

        final StringBuilder gamaRate = new StringBuilder();
        final StringBuilder epsilonRate = new StringBuilder();

        int columns = diagnosticData.get(0).length();

        for (int row = 0; row < columns; row++) {
            Boolean isZeroPrimaryInDiagnostics = moreZerosThanOnes(row, diagnosticData);
            Boolean isZeroPrimaryInOxygen = moreZerosThanOnes(row, oxygenRating);
            Boolean isZeroPrimaryInCo2 = moreZerosThanOnes(row, co2Rating);

            if (isZeroPrimaryInDiagnostics != null) {
                if (isZeroPrimaryInDiagnostics) {
                    gamaRate.append(ZERO);
                    epsilonRate.append(ONE);
                } else {
                    gamaRate.append(ONE);
                    epsilonRate.append(ZERO);
                }
            }

            if (isZeroPrimaryInOxygen != null) {
                if (isZeroPrimaryInOxygen) {
                    removeRecords(row, ONE, oxygenRating);
                } else {
                    removeRecords(row, ZERO, oxygenRating);
                }
            } else {
                removeRecords(row, ZERO, oxygenRating);
            }

            if (isZeroPrimaryInCo2 != null) {
                if (isZeroPrimaryInCo2) {
                    removeRecords(row, ZERO, co2Rating);
                } else {
                    removeRecords(row, ONE, co2Rating);
                }
            } else {
                removeRecords(row, ONE, co2Rating);
            }
        }

        int decimalGamaRate = BaseUtils.baseToDecimal(2, gamaRate.toString());
        int decimalEpsilonRate = BaseUtils.baseToDecimal(2, epsilonRate.toString());

        System.out.println("\n############################ PART ONE ############################");
        System.out.printf("Gama Rate = (%s)2, (%s)10%n", gamaRate, decimalGamaRate);
        System.out.printf("Epsilon Rate = (%s)2, (%s)10%n", epsilonRate, decimalEpsilonRate);
        System.out.printf("Result = %s%n", decimalGamaRate * decimalEpsilonRate);

        String oxygenRatingResult = oxygenRating.stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Cannot find oxygen rating result"));

        String co2RatingResult = co2Rating.stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Cannot find co2 rating result"));

        int decimalOxygenRating = BaseUtils.baseToDecimal(2, oxygenRatingResult);
        int decimalCo2Rating = BaseUtils.baseToDecimal(2, co2RatingResult);

        System.out.println("\n############################ PART TWO ############################");
        System.out.printf("Oxygen Rating = (%s)2, (%s)10%n", oxygenRatingResult, decimalOxygenRating);
        System.out.printf("Co2 Rating = (%s)2, (%s)10%n", co2RatingResult, decimalCo2Rating);
        System.out.printf("Result = %s%n", decimalOxygenRating * decimalCo2Rating);
    }

    private static Boolean moreZerosThanOnes(int row, List<String> diagnostics) {
        int zeros = 0;
        int ones = 0;

        for (String line : diagnostics) {
            var character = Character.toString(line.charAt(row));
            if (ZERO.equalsIgnoreCase(character)) {
                zeros++;
            } else {
                ones++;
            }
        }
        return zeros != ones ? zeros > ones : null;
    }

    private static void removeRecords(int row, String forRemoval, List<String> diagnostics) {
        List<String> tempDiagnostics = new ArrayList<>(diagnostics);

        for (String line : tempDiagnostics) {
            if (diagnostics.size() == 1) {
                break;
            }

            var character = Character.toString(line.charAt(row));
            if (forRemoval.equalsIgnoreCase(character)) {
                diagnostics.remove(line);
            }
        }
    }
}
