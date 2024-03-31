import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

interface Search {
      String search(String query);
}
interface addNewBook {
    void addBook(Book book);
}
class LibraryCatalog implements Search, addNewBook {
    private final List<Book> books;

    public LibraryCatalog() {
        this.books = new ArrayList<>();
    }
    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String search(String query) {
        List<Book> searchResults = new ArrayList<>();
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(query)) {
                searchResults.add(book);
            }
            for (String author : book.getAuthors()) {
                if (author.equalsIgnoreCase(query)) {
                    searchResults.add(book);
                    break;
                }
            }
            if (String.valueOf(book.getPublicationYear()).equalsIgnoreCase(query)) {
                searchResults.add(book);
            }
        }
        if (searchResults.isEmpty()) {
            return "No books found matching the search query.";
        } else {
            return searchResults.stream().map(Book::toString).collect(Collectors.joining("\n"));
        }
    }

    public String displayAllBooks() {
        if (books.isEmpty()) {
            return "No books in the catalog.";
        } else {
            return books.stream().map(Book::toString).collect(Collectors.joining("\n"));
        }
    }
}

class Book {
    private final String name;
    private final List<String> authors;
    private final int year;

    public Book(String name, List<String> authors, int year) {
        this.name = name;
        this.authors = authors;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public int getPublicationYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book Name: " + name + "\nAuthors: " + authors + "\nPublication Year: " + year + "\n";
    }
}

    class Main {
    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();

        catalog.addBook(new Book("Java Programming", List.of("John Doe"), 2020));
        catalog.addBook(new Book("Python for Beginners", List.of("Jane Smith"), 2019));
        catalog.addBook(new Book("Algorithms", List.of("Alice Johnson", "Bob Brown"), 2018));

        System.out.println("Search Results for 'Java Programming':\n" + catalog.search("Java Programming"));
        System.out.println("Search Results for 'John Doe':\n" + catalog.search("John Doe"));
        System.out.println("Search Results for '2019':\n" + catalog.search("2019"));

        System.out.println("All Books in the Catalog:\n" + catalog.displayAllBooks());
    }
}
/*
Single Responsibility:
The Single Responsibility Principle (SRP) in a system assigns each class a single responsibility.
LibraryCatalog manages the library's book collection, provides search functionality, and displays all books.
Book class represents book objects with properties, while the search interface defines contract for search functionality.

Open/Closed:
The system is open for extension but closed for modification.
The LibraryCatalog class can be extended by adding new search criteria or additional functionalities without modifying existing code.

Liskov Substitution:
Subtypes can be substituted for their base types without affecting the correctness of the program.
Any class that implements the Search interface can be used interchangeably with LibraryCatalog for search functionality.

Interface Segregation:
The Search interface provides a single method search, catering to the specific needs of clients who use it (same for AddNewBook)

Dependency Inversion:
High-level modules, like LibraryCatalog, rely on abstractions for functionality, allowing flexibility in search strategies or algorithms.
 */