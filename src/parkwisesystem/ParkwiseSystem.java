package parkwisesystem;
import java.util.*;

// Seperate class to organize the data and order it
class ParkingData implements Comparable<ParkingData> {
    private String ID;
    private double latitude;
    private double longitude;
    private double occupancyRate;
    private double trafficFlow;
    private double distanceToDestination;
    
    // Paramterized constructor
    public ParkingData(String ID, double latitude, double longitude, double occupancyRate, double trafficFlow, double distanceToDestination) {
        this.ID = ID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.occupancyRate = occupancyRate;
        this.trafficFlow = trafficFlow;
        this.distanceToDestination = distanceToDestination;
    }
    
    public double getOccupancyRate() {
        return this.occupancyRate;
    }
    
    public String getID() {
        return this.ID;
    }
    
    @Override
    public int compareTo(ParkingData other) {
        return Double.compare(this.occupancyRate, other.occupancyRate);
    }
    
    @Override
    public String toString() {
        String formattedOccupancyRate = String.format("%.2f", this.getOccupancyRate());
        return formattedOccupancyRate;
    }
} // end of class

// Define the PARKWISE system class
public class ParkwiseSystem {

    // Attributes for the PARKWISE system
    // Define any required attributes here, such as a list or map to store parking data
    private BST<ParkingData> parkingData;

    // Constructor for ParkwiseSystem
    public ParkwiseSystem() {
        // Initialize your attributes here
        parkingData = new BST<>();
    }

    // Method to collect and preprocess data
    public void processData() {
        // TODO: Implement data collection and preprocessing
        // Collect data related to parking occupancy, latitude, and longitude
        // Preprocess the data to fit the required format for analysis
        Random randomData = new Random();
        
        for (int i=1; i<=10; i++) {
            String ID = "P" + i;
            double latitude = 24.4539 + randomData.nextDouble() * 0.01;
            double longitude = 54.3773 + randomData.nextDouble() * 0.01;
            double occupancyRate = randomData.nextDouble();
            double trafficFlow = randomData.nextDouble();
            double distanceToDestination = randomData.nextDouble() * 5.0;
            
            ParkingData dataSample = new ParkingData(ID, latitude, longitude, occupancyRate, trafficFlow, distanceToDestination);
            parkingData.insert(dataSample);
        }
        
        parkingData.print();
    }

    // Method to analyze parking data
    public void analyzeParkingData() {
        // TODO: Implement parking data analysis
        // Analyze the data to determine the current state of parking space usage
        // This might involve sorting, filtering, or other data manipulation techniques
        System.out.println("");
        System.out.println("Parking Data: ");
        parkingData.inorder();
        
        // calculating the average occupancy rate
        double averageOccupancyRate = calculateAverageOccupancyRate();
        System.out.println("\n");
        System.out.println("Average Occupancy Rate: " + averageOccupancyRate);
    }
    
    private double calculateAverageOccupancyRate() {
        if (parkingData.isEmpty()) return 0.0;
        
        int totalSpaces = parkingData.getSize();
        double occupancyRate = 0.0;
        
        for (ParkingData data : parkingData) {
            occupancyRate += data.getOccupancyRate();
        }
        
        return occupancyRate/totalSpaces;
    }

    // Method to optimize parking allocation
    public void optimizeParking() {
        // TODO: Implement parking optimization logic
        // Apply algorithms or heuristics to optimize the allocation of parking spaces
        // This might involve determining where to add or remove parking spaces
        System.out.println("");
        BST<ParkingData> prioritizedData = new BST<>();
        inorderPrioritization(parkingData.getRoot(), prioritizedData);
        parkingData = prioritizedData;
        System.out.println("Optimized Parking Allocation (Prioritize Low Occupancy): ");
        parkingData.print();
    }
    
    private void inorderPrioritization(TreeNode<ParkingData> node, BST<ParkingData> prioritizedData) {
        if (node != null) {
            inorderPrioritization(node.left, prioritizedData);
            prioritizedData.insert(node.element);
            inorderPrioritization(node.right, prioritizedData);
        }
    }
    
    // Method to visualize the parking allocation
    public void displayOptimizedParking() {
        // TODO: Implement parking allocation visualization
        // Display the optimized allocation of parking spaces
        // This could be a textual representation, or more advanced visualizations if appropriate
        System.out.println("\n");
        System.out.println("Optimized Parking Allocation: ");
        inorderOptimizedParking(parkingData.getRoot());
    }
    
    private void inorderOptimizedParking(TreeNode<ParkingData> root) {
        if (root == null) return; 
        
        inorderOptimizedParking(root.left);
        System.out.println("ID: " + root.element.getID() + ", Occupancy Rate: " + root.element.getOccupancyRate());
        inorderOptimizedParking(root.right);
    }

    // Main method
    public static void main(String[] args) {
        ParkwiseSystem parkwise = new ParkwiseSystem();
        parkwise.processData();
        parkwise.analyzeParkingData();
        parkwise.optimizeParking();
        parkwise.displayOptimizedParking();
    } // end of main
} // end of class
