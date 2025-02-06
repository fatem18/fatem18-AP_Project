package com.bookrecommendation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookRecommendation {

    private static final String[][] books = {
            {"Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Fantasy"},
            {"Harry Potter and the Chamber of Secrets", "J.K. Rowling", "Fantasy"},
            {"1984", "George Orwell", "Dystopian"},
            {"Animal Farm", "George Orwell", "Dystopian"},
            {"War and Peace", "Leo Tolstoy", "Historical"},
            {"Anna Karenina", "Leo Tolstoy", "Romance"},
            {"One Hundred Years of Solitude", "Gabriel García Márquez", "Magical Realism"},
            {"Crime and Punishment", "Fyodor Dostoevsky", "Philosophy"},
            {"The Idiot", "Fyodor Dostoevsky", "Drama"},
            {"To Kill a Mockingbird", "Harper Lee", "Classic"},
            {"Pride and Prejudice", "Jane Austen", "Romance"},
            {"Sense and Sensibility", "Jane Austen", "Romance"},
            {"The Old Man and the Sea", "Ernest Hemingway", "Classic"},
            {"The Adventures of Huckleberry Finn", "Mark Twain", "Adventure"},
            {"Great Expectations", "Charles Dickens", "Classic"},
            {"A Tale of Two Cities", "Charles Dickens", "Historical"},
            {"The Great Gatsby", "F. Scott Fitzgerald", "Classic"},
            {"Murder on the Orient Express", "Agatha Christie", "Mystery"},
            {"The Shining", "Stephen King", "Horror"},
            {"Carrie", "Stephen King", "Horror"},
            {"Foundation", "Isaac Asimov", "Science Fiction"},
            {"The Hobbit", "J.R.R. Tolkien", "Fantasy"},
            {"The Lord of the Rings", "J.R.R. Tolkien", "Fantasy"},
            {"Sherlock Holmes: A Study in Scarlet", "Arthur Conan Doyle", "Mystery"},
            {"Wuthering Heights", "Emily Brontë", "Romance"},
            {"The Time Machine", "H.G. Wells", "Science Fiction"},
            {"Les Misérables", "Victor Hugo", "Historical"},
            {"The Raven", "Edgar Allan Poe", "Poetry"},
            {"The Picture of Dorian Gray", "Oscar Wilde", "Classic"},
            {"The Divine Comedy", "Dante Alighieri", "Mythology"},
            {"The Odyssey", "Homer", "Mythology"},
            {"Metamorphosis", "Franz Kafka", "Postmodern"},
            {"Mrs Dalloway", "Virginia Woolf", "Classic"},
            {"The Stranger", "Albert Camus", "Philosophy"},
            {"Moby-Dick", "Herman Melville", "Adventure"},
            {"Dracula", "Bram Stoker", "Horror"},
            {"Frankenstein", "Mary Shelley", "Horror"},
            {"Journey to the Center of the Earth", "Jules Verne", "Science Fiction"},
            {"Fahrenheit 451", "Ray Bradbury", "Dystopian"},
            {"Do Androids Dream of Electric Sheep?", "Philip K. Dick", "Science Fiction"},
            {"Brave New World", "Aldous Huxley", "Dystopian"},
            {"The Da Vinci Code", "Dan Brown", "Thriller"},
            {"Norwegian Wood", "Haruki Murakami", "Drama"},
            {"Slaughterhouse-Five", "Kurt Vonnegut", "Postmodern"},
            {"Ulysses", "James Joyce", "Classic"},
            {"A Streetcar Named Desire", "Tennessee Williams", "Drama"},
            {"Of Mice and Men", "John Steinbeck", "Classic"},
            {"A Game of Thrones", "George R.R. Martin", "Fantasy"},
            {"The Hunger Games", "Suzanne Collins", "Dystopian"},
            {"Percy Jackson & The Lightning Thief", "Rick Riordan", "Fantasy"}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("📚 Welcome to the Book Recommendation System!");
        System.out.print("🔹 Enter your favorite author: ");
        String author = scanner.nextLine().trim();

        System.out.print("🔹 Enter your favorite genre: ");
        String genre = scanner.nextLine().trim();

        List<String> matchedBooks = new ArrayList<>();

        for (String[] book : books) {
            if (book[1].equalsIgnoreCase(author) && book[2].equalsIgnoreCase(genre)) {
                matchedBooks.add(book[0]);
            }
        }

        if (matchedBooks.isEmpty()) {
            System.out.println("❌ No books found matching your criteria.");
            scanner.close();
            return;
        }

        System.out.println("\n✅ Recommended Books:");
        for (int i = 0; i < matchedBooks.size(); i++) {
            System.out.println((i + 1) + ". 📖 " + matchedBooks.get(i));
        }

        System.out.print("\n🔹 Select a number to view the book summary: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > matchedBooks.size()) {
            System.out.println("❌ Invalid selection!");
        } else {
            String selectedBook = matchedBooks.get(choice - 1);
            System.out.println("\n🔎 Fetching summary for: " + selectedBook);
            String summary = BookSummaryFetcher.getBookSummary(selectedBook);
            System.out.println("\n📜 Summary:\n" + summary);
        }

        scanner.close();
    }
}
