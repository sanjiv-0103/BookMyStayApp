import java.util.*;
import java.io.*;

class RoomInventory {
    private Map<String, Integer> rooms;

    public RoomInventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 5);
        rooms.put("Double", 3);
        rooms.put("Suite", 2);
    }

    public Map<String, Integer> getRooms() {
        return rooms;
    }

    public void setRoom(String type, int count) {
        rooms.put(type, count);
    }

    public int getAvailable(String type) {
        return rooms.getOrDefault(type, 0);
    }
}

class FilePersistenceService {

    public void saveInventory(RoomInventory inventory, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : inventory.getRooms().entrySet()) {
                writer.println(entry.getKey() + " " + entry.getValue());
            }
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    public void loadInventory(RoomInventory inventory, String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String type = scanner.next();
                int count = scanner.nextInt();
                inventory.setRoom(type, count);
            }
        } catch (Exception e) {
            System.out.println("No valid inventory data found. Starting fresh.");
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("System Recovery");

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistence = new FilePersistenceService();

        String filePath = "inventory.txt";

        persistence.loadInventory(inventory, filePath);

        System.out.println("Current Inventory:");
        System.out.println("Single: " + inventory.getAvailable("Single"));
        System.out.println("Double: " + inventory.getAvailable("Double"));
        System.out.println("Suite: " + inventory.getAvailable("Suite"));

        persistence.saveInventory(inventory, filePath);
    }
}