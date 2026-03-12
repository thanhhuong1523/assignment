package Constructors.Access.Modifiers.Lab01;

public class Library {
    public static void displayBookInfo(Book book) {
        System.out.printf("Title: %s\nAuthor: %s\nPublication year: %d%n",
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear());
        System.out.println("----------------------------");
    }

    public static void main(String[] args) {
        Book book1 = new Book("Book 1", "Author 1", 2015);
        Book book2 = new Book("Book 2", "Author 2", 2016);
        Book book3 = new Book("Book 3", "Author 3", 2017);

        displayBookInfo(book1);
        displayBookInfo(book2);
        displayBookInfo(book3);
    }
}
