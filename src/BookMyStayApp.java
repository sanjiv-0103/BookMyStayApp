/**
 * ============================================================
 * ABSTRACT CLASS - Room
 * ============================================================
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * This abstract class represents a generic hotel room.
 * It models attributes intrinsic to a room type.
 *
 * @version 2.1
 */
abstract class Room {

    /** Number of beds available in the room */
    protected int numberOfBeds;

    /** Total size of the room in square feet */
    protected int squareFeet;

    /** Price charged per night for this room type */
    protected double pricePerNight;

    /**
     * Constructor used by child classes
     * to initialize common attributes.
     */
    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    /** Displays room details */
    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

/**
 * CLASS SingleRoom
 * Represents a single room in the hotel.
 */
class SingleRoom extends Room {

    /** Initializes a SingleRoom with predefined attributes */
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

/**
 * CLASS DoubleRoom
 * Represents a double room in the hotel.
 */
class DoubleRoom extends Room {

    /** Initializes a DoubleRoom with predefined attributes */
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

/**
 * CLASS SuiteRoom
 * Represents a suite room in the hotel.
 */
class SuiteRoom extends Room {

    /** Initializes a SuiteRoom with predefined attributes */
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

/**
 * ============================================================
 * MAIN CLASS - UseCase2RoomInitialization
 * ============================================================
 *
 * Demonstrates room initialization with static availability.
 *
 * @version 2.1
 */
public class BookMyStayApp {

    /**
     * Application entry point
     */
    public static void main(String[] args) {

        System.out.println("Hotel Room Initialization");

        // Create room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability
        int singleRoomAvailable = 5;
        int doubleRoomAvailable = 3;
        int suiteRoomAvailable = 2;

        // Single Room
        System.out.println("\nSingle Room:");
        singleRoom.displayRoomDetails();
        System.out.println("Available: " + singleRoomAvailable);

        // Double Room
        System.out.println("\nDouble Room:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleRoomAvailable);

        // Suite Room
        System.out.println("\nSuite Room:");
        suiteRoom.displayRoomDetails();
        System.out.println("Available: " + suiteRoomAvailable);
    }
}