package dev.misiek.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public abstract class FileUtils {

    public static List<String> readFromFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}