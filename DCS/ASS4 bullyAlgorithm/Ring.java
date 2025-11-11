// ==================== RING ALGORITHM ====================
import java.util.*;

class Ring {
    public static void main(String[] a) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter no. of processes: ");                   // Total processes
        int n = s.nextInt();
        int[] p = new int[n];
        
        System.out.println("Enter process IDs in ring order:");         // Input IDs in logical ring sequence
        for (int i = 0; i < n; i++) p[i] = s.nextInt();
        
        System.out.print("Enter initiator ID: ");                       // Process starting the election
        int id = s.nextInt();
        
        int pos = 0;                                                    // Find position of initiator in ring
        for (int i = 0; i < n; i++) {
            if (p[i] == id) { pos = i; break; }
        }
        
        System.out.println("\nProcess " + id + " starts election");
        System.out.println("Election message travels through ring:");
        
        List<Integer> msg = new ArrayList<>();                          // Store IDs that pass in the ring
        int max = id;
        
        for (int i = 0; i < n; i++) {
            int idx = (pos + i) % n;                                    // Traverse circularly
            msg.add(p[idx]);
            System.out.println("  -> Process " + p[idx] + 
                " (IDs collected: " + msg + ")");                       // Show each hop
            if (p[idx] > max) max = p[idx];                             // Keep track of highest ID
        }
        
        System.out.println("\nElection message completed ring");
        System.out.println("Highest ID: " + max);
        System.out.println("\n*** COORDINATOR: " + max + " ***");   
        s.close();    // Display final elected leader
    }
}
