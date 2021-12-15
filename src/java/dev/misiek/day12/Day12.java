package dev.misiek.day12;

import dev.misiek.utils.FileUtils;

import java.nio.file.Path;
import java.util.*;

public class Day12 {

    private static final Path FILE_PATH = Path.of("/Users/misk/IdeaProjects/advent-of-code-2021/src/java/dev/misiek/day12/input.txt");
    private static final List<String> INPUT_FILE = FileUtils.readFromFile(FILE_PATH);
    private static final Map<String, Collection<String>> ROUTES = new HashMap<>();
    public static final String START = "start";
    public static final String END = "end";

    public static void main(String[] args) {

        // Get all routes from every point
        INPUT_FILE.stream()
                .map(line -> line.split("-"))
                .forEach(pair -> {
                    ROUTES.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
                    ROUTES.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
                });


        System.out.println(getAllRoutes(START, END, Collections.emptyList(), true).size());
    }

    private static List<List<String>> getAllRoutes(String from, String to, List<String> visited, boolean canVisitTwice) {

        // TODO: Grow a Tree
        if (from.equals(to)) return Collections.singletonList(Collections.singletonList(to));

        List<String> currentPath = new ArrayList<>(visited);
        currentPath.add(from);

        boolean isSecondSmallCaveVisit = from.toLowerCase().equals(from) && visited.contains(from);

        List<String> visitable = ROUTES.get(from).stream()
                .filter(cave -> !START.equals(cave))
                .filter(cave -> cave.toUpperCase().equals(cave)
                        || !visited.contains(cave)
                        || (!isSecondSmallCaveVisit && canVisitTwice))
                .toList();

        List<List<String>> paths = new ArrayList<>();

        visitable.forEach(cave -> paths.addAll(
                getAllRoutes(cave, to, currentPath, !isSecondSmallCaveVisit && canVisitTwice)));

        return paths;
    }

}
