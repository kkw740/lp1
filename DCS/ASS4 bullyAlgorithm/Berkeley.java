// ==================== BERKELEY ALGORITHM ====================
import java.util.*;

class Berkeley {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);                             // Scanner for user input
        System.out.print("Enter no. of clocks: ");                      // Ask total clocks (master + slaves)
        int n = s.nextInt();
        double[] t = new double[n], d = new double[n];                  // Arrays for clock times and adjustments
        double sum = 0;

        System.out.println("Enter clock times:");                       // Input each clock’s time
        for (int i = 0; i < n; i++) {
            t[i] = s.nextDouble();
            sum += t[i];                                                // Add to total for averaging
        }

        double avg = sum / n;                                           // Average = synchronized time
        System.out.println("\nMaster clock: " + t[0]);                  // Master time shown
        System.out.println("Average time: " + avg);                     // Show average time calculated

        System.out.println("\nAdjustments:");
        for (int i = 0; i < n; i++) {
            d[i] = avg - t[i];                                          // Difference between avg and current
            t[i] = avg;                                                 // Adjust each clock to average
            System.out.println("Clock " + i + ": " + 
                String.format("%+.2f", d[i]) + " -> " + t[i]);          // Show adjustment made
        }
        s.close();
    }
    
}
