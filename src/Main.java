import java.util.Scanner;

public class Main {
    // Constants for building properties
    private static final int THEATRE_TIME = 5;
    private static final int PUB_TIME = 4;
    private static final int COMMERCIAL_PARK_TIME = 10;

    private static final int THEATRE_EARNING = 1500;
    private static final int PUB_EARNING = 1000;
    private static final int COMMERCIAL_PARK_EARNING = 3000;

    static class Solution {
        int theatres;
        int pubs;
        int commercialParks;
        int earnings;

        Solution(int t, int p, int c, int e) {
            this.theatres = t;
            this.pubs = p;
            this.commercialParks = c;
            this.earnings = e;
        }
    }

    /**
     * Finds the single optimal development plan for the given time units.
     *
     * @param timeUnits The available time units.
     * @return The optimal {@link Solution}.
     */
    public static Solution findOptimalDevelopment(int timeUnits) {
        Solution optimalSolution = new Solution(0, 0, 0, 0);
        int maxEarnings = 0;

        // Try all possible combinations
        for (int t = 0; t <= timeUnits / THEATRE_TIME; t++) {
            for (int p = 0; p <= timeUnits / PUB_TIME; p++) {
                for (int c = 0; c <= timeUnits / COMMERCIAL_PARK_TIME; c++) {
                    int time = 0;
                    int earnings = 0;

                    // Build theatres sequentially
                    for (int i = 0; i < t; i++) {
                        time += THEATRE_TIME;
                        if (time < timeUnits) {
                            earnings += (timeUnits - time) * THEATRE_EARNING;
                        }
                    }

                    // Build pubs sequentially
                    for (int i = 0; i < p; i++) {
                        time += PUB_TIME;
                        if (time < timeUnits) {
                            earnings += (timeUnits - time) * PUB_EARNING;
                        }
                    }

                    // Build commercial parks sequentially
                    for (int i = 0; i < c; i++) {
                        time += COMMERCIAL_PARK_TIME;
                        if (time < timeUnits) {
                            earnings += (timeUnits - time) * COMMERCIAL_PARK_EARNING;
                        }
                    }

                    if (time <= timeUnits && earnings > maxEarnings) {
                        maxEarnings = earnings;
                        optimalSolution = new Solution(t, p, c, earnings);
                    }
                }
            }
        }

        return optimalSolution;
    }

    /**
     * Displays the optimal solution for a given time unit.
     *
     * @param timeUnit The available time units.
     * @param solution The optimal {@link Solution}.
     */
    public static void displayResult(int timeUnit, Solution solution) {
        System.out.println("\nTime Unit: " + timeUnit);
        System.out.println("Earnings: $" + solution.earnings);
        System.out.println("T: " + solution.theatres + " P: " + solution.pubs + " C: " + solution.commercialParks);
        System.out.println("-----------------------------");
    }

    /**
     * Main method to dynamically accept input from the user until termination.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter time units (or type 'exit' to stop): ");

            if (scanner.hasNext("exit")) {
                System.out.println("Exiting program...");
                break;
            }

            if (scanner.hasNextInt()) {
                int timeUnit = scanner.nextInt();
                if (timeUnit <= 0) {
                    System.out.println("Please enter a positive integer.");
                    continue;
                }

                Solution solution = findOptimalDevelopment(timeUnit);
                displayResult(timeUnit, solution);
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
        }

        scanner.close();
    }
}