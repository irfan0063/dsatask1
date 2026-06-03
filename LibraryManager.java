import java.util.TreeSet;

public class LibraryManager {

    private TreeSet<String> books;

    public LibraryManager() {
        books = new TreeSet<>();
    }

    // Add a new book title
    public void addBook(String title) {
        if (books.add(title)) {
            System.out.println("Added: " + title);
        } else {
            System.out.println("Book already exists: " + title);
        }
    }

    // Remove a book title
    public void removeBook(String title) {
        if (books.remove(title)) {
            System.out.println("Removed: " + title);
        } else {
            System.out.println("Book not found: " + title);
        }
    }

    // Check if a book is available
    public boolean isBookAvailable(String title) {
        return books.contains(title);
    }

    // Display all books in sorted order
    public void displayAllBooks() {
        System.out.println("\n--- All Books (Sorted) ---");
        for (String book : books) {
            System.out.println(book);
        }
        System.out.println("Total: " + books.size() + " books\n");
    }

    // Find all books starting with a specific letter
    public void findBooksByLetter(char letter) {
        String from = String.valueOf(letter);
        String to   = String.valueOf((char)(letter + 1));
        TreeSet<String> result =
                new TreeSet<>(books.subSet(from, true, to, false));
        System.out.println("\nBooks starting with '" + letter + "':");
        if (result.isEmpty()) {
            System.out.println("  None found.");
        } else {
            for (String book : result) {
                System.out.println("  " + book);
            }
        }
    }

    // --- Main: demo ---
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();

        library.addBook("The Great Gatsby");
        library.addBook("To Kill a Mockingbird");
        library.addBook("1984");
        library.addBook("Brave New World");
        library.addBook("The Catcher in the Rye");
        library.addBook("The Great Gatsby");   // duplicate

        library.displayAllBooks();

        System.out.println("Is '1984' available? "
                + library.isBookAvailable("1984"));
        System.out.println("Is 'Dune' available? "
                + library.isBookAvailable("Dune"));

        library.findBooksByLetter('T');

        library.removeBook("1984");
        library.displayAllBooks();
    }
}