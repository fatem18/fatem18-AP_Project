package com.bookrecommendation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    static final String DATABASE_URL = "jdbc:sqlite:books.db";

    public static void main(String[] args) {
        createTables();

        DataInserter dataInserter = new DataInserter();
        dataInserter.insertData();

        BookRepository bookRepository = new BookRepository();
        bookRepository.removeDuplicateBooks();
    }

    public static void createTables() {
        String createAuthorsTable = "CREATE TABLE IF NOT EXISTS authors (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT UNIQUE NOT NULL COLLATE NOCASE)";

        String createGenresTable = "CREATE TABLE IF NOT EXISTS genres (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "genre_name TEXT UNIQUE NOT NULL COLLATE NOCASE)";

        String createBooksTable = "CREATE TABLE IF NOT EXISTS books (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT NOT NULL, " +
                "author_id INTEGER NOT NULL, " +
                "genre_id INTEGER NOT NULL, " +
                "FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE, " +
                "FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createAuthorsTable);
            stmt.execute(createGenresTable);
            stmt.execute(createBooksTable);
            System.out.println("✅ Tables created.");
        } catch (SQLException e) {
            System.err.println("❌ Error creating tables : " + e.getMessage());
        }
    }
}
