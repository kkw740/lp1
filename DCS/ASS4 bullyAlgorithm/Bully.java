// ==================== BULLY ALGORITHM ====================
import java.util.*;

class Bully {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter no. of processes: ");                   // Ask number of processes
        int n = s.nextInt();
        int[] p = new int[n];
        
        System.out.println("Enter process IDs:");                       // Input process IDs
        for (int i = 0; i < n; i++) p[i] = s.nextInt();

        System.out.print("Enter initiator ID: ");                       // Process starting the election
        int id = s.nextInt();

        System.out.println("\nProcess " + id + " starts election");
        System.out.println("Sending ELECTION to higher processes:");

        int max = id;                                                   // Store possible leader
        boolean found = false;                                          // Check if any higher process exists

        for (int i = 0; i < n; i++) {
            if (p[i] > id) {                                            // Send election message to higher ID
                System.out.println("  -> Process " + p[i]);
                found = true;
                if (p[i] > max) max = p[i];                             // Keep track of highest ID
            }
        }

        if (found) {                                                    // If higher IDs exist
            System.out.println("\nHigher processes respond with OK");
            System.out.println("Process " + max + " becomes coordinator");
        } else {                                                        // If initiator is the highest
            System.out.println("\nNo higher process found");
            System.out.println("Process " + id + " becomes coordinator");
            max = id;
        }

        System.out.println("\n*** COORDINATOR: " + max + " ***");       // Final leader printed
        s.close();
    }
}
