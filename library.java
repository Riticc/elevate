import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class library {
    private Map<String, Book> books;
    private Map<String, User> users;

    public library() {
        books = new HashMap<>();
        users = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public void issueBook(String isbn, String userId) {
        Book book = books.get(isbn);
        User user = users.get(userId);

        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " does not exist.");
            return;
        }
        if (user == null) {
            System.out.println("User with ID " + userId + " does not exist.");
            return;
        }
        if (book.isIssued()) {
            System.out.println("Book '" + book.getTitle() + "' is already issued.");
            return;
        }

        book.setIssued(true);
        user.borrowBook(book);
        System.out.println("Book '" + book.getTitle() + "' issued to " + user.getName() + ".");
    }

    public void returnBook(String isbn, String userId) {
        Book book = books.get(isbn);
        User user = users.get(userId);

        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " does not exist.");
            return;
        }
        if (user == null) {
            System.out.println("User with ID " + userId + " does not exist.");
            return;
        }
        if (!book.isIssued()) {
            System.out.println("Book '" + book.getTitle() + "' is not currently issued.");
            return;
        }
        if (!user.hasBook(book)) {
            System.out.println(user.getName() + " does not have the book '" + book.getTitle() + "'.");
            return;
        }

        book.setIssued(false);
        user.returnBook(book);
        System.out.println("Book '" + book.getTitle() + "' returned by " + user.getName() + ".");
    }

    public void listAllBooks() {
        System.out.println("Books in Library:");
        for (Book book : books.values()) {
            book.displayBookInfo();
        }
    }

    public void listAllUsers() {
        System.out.println("Users in Library:");
        for (User user : users.values()) {
            user.displayUserInfo();
        }
    }

    public static void main(String[] args) {
        library myLibrary = new library();
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565");
        Book book2 = new Book("1984", "George Orwell", "978-0451524935");
        Book book3 = new Book("To Kill a Mockingbird", "Harper Lee", "978-0061120084");
        Book book4 = new Book("Pride and Prejudice", "Jane Austen", "978-0141439518");
        myLibrary.addBook(book1);
        myLibrary.addBook(book2);
        myLibrary.addBook(book3);
        myLibrary.addBook(book4);
        User user1 = new User("U001", "Alice Smith");
        User user2 = new User("U002", "Bob Johnson");
        myLibrary.addUser(user1);
        myLibrary.addUser(user2);
        System.out.println("\n--- Initial State ---");
        myLibrary.listAllBooks();
        myLibrary.listAllUsers();

        System.out.println("\n--- Issuing Books ---");
        myLibrary.issueBook(book1.getIsbn(), user1.getUserId()); // Alice borrows Gatsby
        myLibrary.issueBook(book2.getIsbn(), user1.getUserId()); // Alice borrows 1984
        myLibrary.issueBook(book3.getIsbn(), user2.getUserId()); // Bob borrows Mockingbird
        myLibrary.issueBook(book1.getIsbn(), user2.getUserId()); // Bob tries to borrow Gatsby (already issued)
        myLibrary.issueBook("999-0000000000", user1.getUserId()); // Try to issue a non-existent book
        myLibrary.issueBook(book4.getIsbn(), "U999"); // Try to issue to a non-existent user

        System.out.println("\n--- State After Issuing ---");
        myLibrary.listAllBooks();
        myLibrary.listAllUsers();
        System.out.println("\n--- Returning Books ---");
        myLibrary.returnBook(book1.getIsbn(), user1.getUserId()); // Alice returns Gatsby
        myLibrary.returnBook(book2.getIsbn(), user2.getUserId()); // Bob tries to return 1984 (Alice has it)
        myLibrary.returnBook(book4.getIsbn(), user1.getUserId()); // Alice tries to return Pride and Prejudice (not issued to her)

        System.out.println("\n--- Final State ---");
        myLibrary.listAllBooks();
        myLibrary.listAllUsers();
        book1.displayBookInfo();
        user1.displayUserInfo();
    }
}

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean issued;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.issued = false;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public void displayBookInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Issued: " + issued);
    }
}

class User {
    private String userId;
    private String name;
    private List<Book> borrowedBooks;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public boolean hasBook(Book book) {
        return borrowedBooks.contains(book);
    }

    public void displayUserInfo() {
        System.out.print("User ID: " + userId + ", Name: " + name + ", Borrowed Books: ");
        if (borrowedBooks.isEmpty()) {
            System.out.println("None");
        } else {
            for (Book book : borrowedBooks) {
                System.out.print(book.getTitle() + "; ");
            }
            System.out.println();
        }
    }
}
