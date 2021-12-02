package dev.misiek.day2;

import dev.misiek.utils.FileUtils;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 {

    public static final String INPUT_FILE_PATH = "/Users/misk/IdeaProjects/advent-of-code-2021/src/java/dev/misiek/day2/input.txt";
    public static final String FORWARD = "forward";
    public static final String UP = "up";
    public static final String DOWN = "down";

    public static void main(String[] args) {
        List<String> navigationDirections = FileUtils.readFromFile(Path.of(INPUT_FILE_PATH));

        getPosition(navigationDirections);
        getAim(navigationDirections);
    }

    private static void getPosition(List<String> navigationDirections) {
        AtomicInteger horizontalPosition = new AtomicInteger();
        AtomicInteger verticalPosition = new AtomicInteger();

        navigationDirections.forEach(direction -> {
            String movement = direction.split(" ")[0];
            int distance = Integer.parseInt(direction.split(" ")[1]);

            switch (movement) {
                case UP:
                    verticalPosition.addAndGet(-distance);
                    break;
                case DOWN:
                    verticalPosition.addAndGet(distance);
                    break;
                case FORWARD:
                    horizontalPosition.addAndGet(distance);
                    break;
                default:
                    throw new IllegalStateException("Unexpected movement: " + movement);
            }
        });

        System.out.println("############################ PART ONE ############################");
        System.out.printf("Horizontal position = %s%n", horizontalPosition);
        System.out.printf("Vertical position = %s%n", verticalPosition);
        System.out.printf("Together = %s%n", horizontalPosition.get() * verticalPosition.get());
    }

    private static void getAim(List<String> navigationDirections) {
        AtomicInteger horizontalPosition = new AtomicInteger();
        AtomicInteger verticalPosition = new AtomicInteger();
        AtomicInteger aim = new AtomicInteger();

        navigationDirections.forEach(direction -> {
            String movement = direction.split(" ")[0];
            int distance = Integer.parseInt(direction.split(" ")[1]);

            switch (movement) {
                case UP:
                    aim.addAndGet(-distance);
                    break;
                case DOWN:
                    aim.addAndGet(distance);
                    break;
                case FORWARD:
                    horizontalPosition.addAndGet(distance);
                    verticalPosition.addAndGet(distance * aim.get());
                default:
                    throw new IllegalStateException("Unexpected movement: " + movement);
            }
        });

        System.out.println("\n############################ PART TWO ############################");
        System.out.printf("Horizontal position = %s%n", horizontalPosition);
        System.out.printf("Vertical position = %s%n", verticalPosition);
        System.out.printf("Together = %s%n", horizontalPosition.get() * verticalPosition.get());
    }
}