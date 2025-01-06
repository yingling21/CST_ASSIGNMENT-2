import java.util.*;

public class DiskScheduling {

    // Method for FCFS Disk Scheduling
    public static int fcfs(int head, int[] requests) {
        int totalMovement = 0;
        int j = 1;
        
        for (int request : requests) {
            System.out.println("Path " + j + ": Track " + head + " to " + request);
            totalMovement += Math.abs(head - request);
            head = request;
            j++;
        }

        return totalMovement;
    }

    // Method for SSTF Disk Scheduling
    public static int sstf(int head, int[] requests) {
        int totalMovement = 0;
        int i = 1;
        boolean[] visited = new boolean[requests.length];
        Arrays.fill(visited, false);

        for (int j = 0; j < requests.length; j++) {
            int closestIndex = -1;
            int closestDistance = Integer.MAX_VALUE;

            for (int k = 0; k < requests.length; k++) {
                if (!visited[k]) {
                    int distance = Math.abs(head - requests[k]);
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestIndex = k;
                    }
                }
            }
            System.out.println("Path " + i + ": Track " + head + " to " + requests[closestIndex]);
            totalMovement += closestDistance;
            head = requests[closestIndex];
            visited[closestIndex] = true;
            i++;
            
        }

        return totalMovement;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Input: Initial position of the disk head
            System.out.print("Enter disk's initial head position: ");
            int head = scanner.nextInt();

            // Input: Number of requests
            System.out.print("Total number of cylinders on the disk: ");
            int n = scanner.nextInt();

            // Input: Sequence of requests
            int[] requests = new int[n];
            System.out.println("Enter disk requests:");
            for (int i = 0; i < n; i++) {
                System.out.print(i+1 + ": ");
                requests[i] = scanner.nextInt();
            }  
            
            // FCFS Algorithm
            int fcfsMovement = fcfs(head, requests);
            System.out.println("Total head movement (FCFS): " + fcfsMovement);
            // SSTF Algorithm
            int sstfMovement = sstf(head, requests);
            System.out.println("Total head movement (SSTF): " + sstfMovement);
        }
    }
}
