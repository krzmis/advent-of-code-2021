package dev.misiek.utils;

public record Position(int x, int y, int value) {

    public Position(int x, int y) {
        this(x, y, 0);
    }
}
