import java.util.ArrayList;
import java.util.List;
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

        @Override
        public String toString() {
            return String.format("T: %d P: %d C: %d", theatres, pubs, commercialParks);
        }
    }

    /**
     * Finds the optimal development plan for the given time units.
     *
     * @param timeUnits The available time units.
     * @return {@link List} of {@link Solution} containing the optimal solutions.
     */
    public static List<Solution> findOptimalDevelopment(int timeUnits) {
        List<Solution> optimalSolutions = new ArrayList<>();
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

                    if (time <= timeUnits) {
                        if (earnings > maxEarnings) {
                            maxEarnings = earnings;
                            optimalSolutions.clear();
                            optimalSolutions.add(new Solution(t, p, c, earnings));
                        } else if (earnings == maxEarnings && earnings > 0) {
                            if (optimalSolutions.size() == 1) {
                                optimalSolutions.add(new Solution(t, p, c, earnings));
                            }
                        }
                    }
                }
            }
        }

        return optimalSolutions;
    }

    /**
     * Displays the optimal solutions for a given time unit.
     *
     * @param timeUnit The available time units.
     * @param solutions {@link List} of {@link Solution} containing the results.
     */
    public static void displayResults(int timeUnit, List<Solution> solutions) {
        System.out.println("\nFor Time Unit: " + timeUnit);
        System.out.println("Earnings: $" + (solutions.isEmpty() ? 0 : solutions.get(0).earnings));
        System.out.println("Solutions:");

        if (!solutions.isEmpty()) {
            System.out.println("1. " + solutions.get(0));
            if (solutions.size() > 1) {
                System.out.println("2. " + solutions.get(1));
            }
        }

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

                List<Solution> solutions = findOptimalDevelopment(timeUnit);
                displayResults(timeUnit, solutions);
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
        }

        scanner.close();
    }
}