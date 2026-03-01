import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {

    // ================= BOOK CLASS =================
    static class Book {

        private String isbn;
        private String title;
        private String author;
        private String genre;
        private boolean availability;

        public Book(String isbn, String title, String author, String genre) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.availability = true; // default available
        }

        public String getIsbn() { return isbn; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getGenre() { return genre; }
        public boolean isAvailable() { return availability; }

        public void setAvailability(boolean availability) {
            this.availability = availability;
        }
    }

    // ================= MEMBER CLASS =================
    static class Member {

        private String memberId;
        private String name;
        private long contact;
        private List<Book> borrowedBooks;

        public Member(String memberId, String name, long contact) {
            this.memberId = memberId;
            this.name = name;
            this.contact = contact;
            this.borrowedBooks = new ArrayList<>();
        }

        public String getMemberId() { return memberId; }
        public String getName() { return name; }
        public long getContact() { return contact; }
        public List<Book> getBorrowedBooks() { return borrowedBooks; }

        public void borrowBook(Book book) {
            borrowedBooks.add(book);
        }

        public void returnBook(Book book) {
            borrowedBooks.remove(book);
        }
    }

    // ================= LIBRARY CLASS =================
    static class Library {

        private List<Book> books;
        private List<Member> members;

        public Library() {
            books = new ArrayList<>();
            members = new ArrayList<>();
        }

        public void addBook(Book book) {
            books.add(book);
        }

        public void addMember(Member member) {
            members.add(member);
        }

        public Book findBook(String isbn) {
            for (Book book : books) {
                if (book.getIsbn().equals(isbn)) {
                    return book;
                }
            }
            return null;
        }

        public Member findMember(String memberId) {
            for (Member member : members) {
                if (member.getMemberId().equals(memberId)) {
                    return member;
                }
            }
            return null;
        }

        public void borrowBook(String memberId, String isbn) {

            Member member = findMember(memberId);
            Book book = findBook(isbn);

            if (member != null && book != null && book.isAvailable()) {

                member.borrowBook(book);
                book.setAvailability(false);
                System.out.println("Book borrowed successfully.");

            } else {
                System.out.println("Borrow failed.");
            }
        }

        public void returnBook(String memberId, String isbn) {

            Member member = findMember(memberId);
            Book book = findBook(isbn);

            if (member != null && book != null &&
                    member.getBorrowedBooks().contains(book)) {

                member.returnBook(book);
                book.setAvailability(true);
                System.out.println("Book returned successfully.");

            } else {
                System.out.println("Return failed.");
            }
        }

        public void searchByTitle(String title) {

            boolean found = false;

            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    System.out.println(book.getIsbn() + " - " +
                            book.getTitle() + " by " + book.getAuthor());
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No book found with title: " + title);
            }
        }
        

        public void searchByAuthor(String author) {

            boolean found = false;

            for (Book book : books) {
                if (book.getAuthor().equalsIgnoreCase(author)) {
                    System.out.println(book.getIsbn() + " - " +
                            book.getTitle() + " by " + book.getAuthor());
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No books found by author: " + author);
            }
        }
        
        public void displayAllBooks() {

            if (books.isEmpty()) {
                System.out.println("No books in library.");
                return;
            }

            for (Book book : books) {
                System.out.println(book.getIsbn() + " | " +
                        book.getTitle() + " | " +
                        book.getAuthor() + " | " +
                        book.getGenre() + " | " +
                        (book.isAvailable() ? "Available" : "Not Available"));
            }
        }
        
        public void displayAvailableBooks() {

            boolean found = false;

            for (Book book : books) {

                if (book.isAvailable()) {

                    System.out.println(book.getIsbn() + " | " +
                            book.getTitle() + " | " +
                            book.getAuthor() + " | " +
                            book.getGenre());

                    found = true;
                }
            }

            if (!found) {
                System.out.println("No available books.");
            }
        }
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {

        	System.out.println("1. Add Book");
        	System.out.println("2. Add Member");
        	System.out.println("3. Borrow Book");
        	System.out.println("4. Return Book");
        	System.out.println("5. Search by Title");
        	System.out.println("6. Search by Author");
        	System.out.println("7. Display All Books");
        	System.out.println("8. Display Available Books");
        	System.out.println("9. Exit");

        	System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter Genre: ");
                    String genre = sc.nextLine();

                    library.addBook(new Book(isbn, title, author, genre));
                    System.out.println("Book added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Member ID: ");
                    String memberId = sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Contact: ");
                    long contact = sc.nextLong();
                    sc.nextLine();

                    library.addMember(new Member(memberId, name, contact));
                    System.out.println("Member added successfully!");
                    break;

                case 3:
                    System.out.print("Enter Member ID: ");
                    String borrowId = sc.nextLine();

                    System.out.print("Enter Book ISBN: ");
                    String borrowIsbn = sc.nextLine();

                    library.borrowBook(borrowId, borrowIsbn);
                    break;

                case 4:
                    System.out.print("Enter Member ID: ");
                    String returnId = sc.nextLine();

                    System.out.print("Enter Book ISBN: ");
                    String returnIsbn = sc.nextLine();

                    library.returnBook(returnId, returnIsbn);
                    break;

                case 5:
                    System.out.print("Enter Title: ");
                    String searchTitle = sc.nextLine();
                    library.searchByTitle(searchTitle);
                    break;

                case 6:
                    System.out.print("Enter Author: ");
                    String searchAuthor = sc.nextLine();
                    library.searchByAuthor(searchAuthor);
                    break;
                case 7:
                    library.displayAllBooks();
                    break;

                case 8:
                    library.displayAvailableBooks();
                    break;

               
                case 9:
                    System.out.println("Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}