package dev.misiek.day17;

public class Day17 {

//    target area: x=179..201, y=-109..-63

    public static final int X_MIN = 179;
    public static final int X_MAX = 201;

    public static final int Y_MIN = -109;
    public static final int Y_MAX = -63;

    public static void main(String[] args) {
        int maxHeight = 0;
        int hits = 0;

        // For each forward movements from 1
        for (int xVelocity = 1; xVelocity <= X_MAX; xVelocity++) {

            // For each vertical movement from Y_MIN
            for (int yVelocity = Y_MIN; yVelocity <= 1000; yVelocity++) {
                int currentY = shootProbe(xVelocity, yVelocity);

                // Check for highest point reached
                if (currentY > maxHeight) maxHeight = currentY;

                // Add up all hits
                if (currentY > Integer.MIN_VALUE) hits++;
            }
        }

        System.out.println("Maximum height reached: " + maxHeight);
        System.out.println("Number of hits: " + hits);
    }

    private static int shootProbe(int xVelocity, int yVelocity) {
        int maxY = Y_MAX;

        int x = 0;
        int y = 0;

        boolean pastTarget = false;
        while (!pastTarget) {
            x += xVelocity;
            y += yVelocity;
            if (y > maxY)
                maxY = y;

            // If probe is within the target area, otherwise stop checking
            if (X_MIN <= x && X_MAX >= x && Y_MIN <= y && Y_MAX >= y) return maxY;
            else if (Y_MIN > y) pastTarget = true;

            // If xVelocity is larger than 0 we decrement by 1, and vice versa
            xVelocity += Integer.compare(0, xVelocity);
            yVelocity -= 1;
        }
        return Integer.MIN_VALUE;
    }

}
