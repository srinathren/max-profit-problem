import java.util.*;

public class Main {

    /**
     * Computes the maximum profit solutions based on the given time units.
     *
     * @param n Total available time units
     * @return {@link List} of {@link String} containing optimal solutions
     */
    public static List<String> maxProfit(int n) {
        int maxProfit = 0;
        List<String> solutions = new ArrayList<>();

        // Try all possible numbers of Theatres (T)
        for (int T = 0; T <= n / 5; T++) {
            for (int P = 0; P <= n / 4; P++) {
                for (int C = 0; C <= n / 10; C++) {
                    int totalTime = (T * 5) + (P * 4) + (C * 10);

                    // Check if total build time is within available time
                    if (totalTime <= n) {
                        // Calculate operational time
                        int operationalTimeT = Math.max(0, n - (T * 5));
                        int operationalTimeP = Math.max(0, n - (P * 4));
                        int operationalTimeC = Math.max(0, n - (C * 10));

                        // Calculate earnings
                        int profit = (T * 1500 * operationalTimeT) +
                                (P * 1000 * operationalTimeP) +
                                (C * 3000 * operationalTimeC);

                        // If we find a better profit, reset solutions list
                        if (profit > maxProfit) {
                            maxProfit = profit;
                            solutions.clear(); // Clear previous results
                            solutions.add("T: " + T + ", P: " + P + ", C: " + C + ", Earnings: $" + profit);
                        }
                        // If we find another combination with the same max profit, add it to solutions
                        else if (profit == maxProfit) {
                            solutions.add("T: " + T + ", P: " + P + ", C: " + C + ", Earnings: $" + profit);
                        }
                    }
                }
            }
        }

        return solutions;
    }

    /**
     * Displays the results of the maximum profit calculations.
     *
     * @param n        Total available time units
     * @param results  {@link List} of {@link String} containing optimal solutions
     */
    public static void displayResults(int n, List<String> results) {
        System.out.println("\nFor Time Unit: " + n);
        System.out.println("Optimal solutions:");
        for (String result : results) {
            System.out.println(result);
        }
        System.out.println("-----------------------------");
    }

    /**
     * Main method to interact with the user and execute the max profit calculation.
     * The user can continuously input time units and receive results until they
     * decide to terminate by entering "exit".
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Ask user for input
            System.out.print("Enter time units (or type 'exit' to stop): ");

            // Check if user wants to exit
            if (scanner.hasNext("exit")) {
                System.out.println("Exiting program...");
                break;
            }

            // Read integer input
            int n = scanner.nextInt();

            // Process the test case
            List<String> results = maxProfit(n);

            // Display results
            displayResults(n, results);
        }

        scanner.close();
    }
}
