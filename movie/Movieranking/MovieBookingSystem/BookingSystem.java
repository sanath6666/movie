import java.util.*;
import java.io.*;

public class BookingSystem {
    private List<Movie> movies = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public BookingSystem() {
        loadMovies();
    }

    private void loadMovies() {
        movies.add(new Movie("Avengers: Endgame", Arrays.asList("10:00 AM", "1:00 PM", "6:00 PM")));
        movies.add(new Movie("Inception", Arrays.asList("11:00 AM", "3:00 PM", "9:00 PM")));
        movies.add(new Movie("Interstellar", Arrays.asList("9:00 AM", "12:00 PM", "5:00 PM")));
    }

    public void showMainMenu() {
        System.out.println("\n🎬 Welcome to the Movie Booking System 🎬");

        while (true) {
            System.out.println("\nAvailable Movies:");
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i + 1) + ". " + movies.get(i).name);
            }

            System.out.print("\nEnter movie number to book or 0 to exit: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            if (choice < 1 || choice > movies.size()) {
                System.out.println("❌ Invalid choice.");
                continue;
            }

            bookTicket(movies.get(choice - 1));
        }

        System.out.println("Thank you for using the system!");
    }

    private void bookTicket(Movie movie) {
        System.out.println("\nSelected Movie: " + movie.name);
        for (int i = 0; i < movie.showtimes.size(); i++) {
            System.out.println((i + 1) + ". " + movie.showtimes.get(i));
        }

        System.out.print("Select showtime number: ");
        int showChoice = scanner.nextInt();
        scanner.nextLine();

        if (showChoice < 1 || showChoice > movie.showtimes.size()) {
            System.out.println("❌ Invalid showtime.");
            return;
        }

        String selectedShow = movie.showtimes.get(showChoice - 1);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter number of seats: ");
        int seats = scanner.nextInt();
        scanner.nextLine();

        saveBooking(movie.name, selectedShow, name, seats);

        System.out.println("✅ Booking Confirmed!");
        System.out.println("🎟️  Movie: " + movie.name);
        System.out.println("🕒 Showtime: " + selectedShow);
        System.out.println("👤 Name: " + name);
        System.out.println("💺 Seats: " + seats);
    }

    private void saveBooking(String movie, String time, String name, int seats) {
        try (FileWriter writer = new FileWriter("bookings.txt", true)) {
            writer.write("Name: " + name + ", Movie: " + movie + ", Time: " + time + ", Seats: " + seats + "\n");
        } catch (IOException e) {
            System.out.println("⚠️ Error saving booking: " + e.getMessage());
        }
    }
}
