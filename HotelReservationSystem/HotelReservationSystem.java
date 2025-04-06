import java.util.*;

class Room {
    int roomNumber;
    String category;
    double price;
    boolean isBooked;

    Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isBooked = false;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + category + ", ₹" + price + ", " + (isBooked ? "Booked" : "Available") + ")";
    }
}

class Booking {
    String guestName;
    Room room;
    Date checkIn;
    Date checkOut;

    Booking(String guestName, Room room, Date checkIn, Date checkOut) {
        this.guestName = guestName;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Booking: " + guestName + " | Room: " + room.roomNumber + " | From: " + checkIn + " To: " + checkOut;
    }
}

public class HotelReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        seedRooms();
        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> showAvailableRooms();
                case 2 -> makeReservation();
                case 3 -> showBookings();
                case 4 -> {
                    System.out.println("Thank you for using the system.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void seedRooms() {
        rooms.add(new Room(101, "Single", 1500));
        rooms.add(new Room(102, "Double", 2500));
        rooms.add(new Room(103, "Suite", 5000));
        rooms.add(new Room(104, "Single", 1500));
        rooms.add(new Room(105, "Double", 2500));
    }

    static void showAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println(room);
            }
        }
    }

    static void makeReservation() {
        showAvailableRooms();
        System.out.print("Enter room number to book: ");
        int roomNo = sc.nextInt();
        Room selectedRoom = null;

        for (Room room : rooms) {
            if (room.roomNumber == roomNo && !room.isBooked) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not available!");
            return;
        }

        sc.nextLine(); // clear buffer
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        Date checkIn = java.sql.Date.valueOf(sc.nextLine());
        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        Date checkOut = java.sql.Date.valueOf(sc.nextLine());

        // Simulate payment
        if (processPayment(selectedRoom.price)) {
            selectedRoom.isBooked = true;
            bookings.add(new Booking(name, selectedRoom, checkIn, checkOut));
            System.out.println("Booking successful!");
        } else {
            System.out.println("Payment failed.");
        }
    }

    static void showBookings() {
        System.out.println("\n--- Booking Details ---");
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    static boolean processPayment(double amount) {
        System.out.println("Processing payment of ₹" + amount + "...");
        try {
            Thread.sleep(1000); // simulate delay
        } catch (InterruptedException ignored) {}
        return true; // always succeeds in mock
    }
}
