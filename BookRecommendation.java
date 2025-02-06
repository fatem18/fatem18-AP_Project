package com.bookrecommendation;

import java.util.List;
import java.util.Scanner;

public class BookRecommendation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookRepository bookRepository = new BookRepository();

        System.out.println("📚 Welcome to the Book Recommendation System!");
        System.out.print("🔹 Enter your favorite author: ");
        String author = scanner.nextLine().trim();

        System.out.print("🔹 Enter your favorite genre: ");
        String genre = scanner.nextLine().trim();

        // دریافت کتاب‌های مرتبط از پایگاه داده
        List<String> matchedBooks = bookRepository.getBooksByAuthorAndGenre(author, genre);

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
