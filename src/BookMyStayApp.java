import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}

class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

class RoomAllocationService {

    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        String roomType = reservation.getRoomType();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get(roomType) > 0) {
            String roomId = generateRoomId(roomType);

            allocatedRoomIds.add(roomId);

            assignedRoomsByType.putIfAbsent(roomType, new HashSet<>());
            assignedRoomsByType.get(roomType).add(roomId);

            int current = availability.get(roomType);
            inventory.updateAvailability(roomType, current - 1);

            System.out.println("Booking confirmed for Guest: " + reservation.getGuestName() + ", Room ID: " + roomId);
        } else {
            System.out.println("No available rooms for Guest: " + reservation.getGuestName());
        }
    }

    private String generateRoomId(String roomType) {
        assignedRoomsByType.putIfAbsent(roomType, new HashSet<>());
        int id = assignedRoomsByType.get(roomType).size() + 1;
        return roomType + "-" + id;
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService();

        bookingQueue.addRequest(new Reservation("Sanjiv", "Single"));
        bookingQueue.addRequest(new Reservation("Habi", "Single"));
        bookingQueue.addRequest(new Reservation("Nithila", "Suite"));

        while (bookingQueue.hasPendingRequests()) {
            Reservation r = bookingQueue.getNextRequest();
            allocationService.allocateRoom(r, inventory);
        }
    }
}