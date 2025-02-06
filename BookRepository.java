package com.bookrecommendation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private static final String DATABASE_URL = "jdbc:sqlite:books.db";

    public List<String> getBooksByAuthorAndGenre(String authorName, String genreName) {
        List<String> books = new ArrayList<>();
        String query = "SELECT DISTINCT b.title FROM books b " +
                "JOIN authors a ON b.author_id = a.id " +
                "JOIN genres g ON b.genre_id = g.id " +
                "WHERE a.name COLLATE NOCASE = ? AND g.genre_name COLLATE NOCASE = ?";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, authorName);
            stmt.setString(2, genreName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(rs.getString("title"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error receiving information: " + e.getMessage());
        }
        return books;
    }

    public void removeDuplicateBooks() {
        String query = "DELETE FROM books WHERE id NOT IN (SELECT MIN(id) FROM books GROUP BY title)";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            int affectedRows = stmt.executeUpdate(query);
            System.out.println("✅ Removed " + affectedRows + " duplicate book(s).");
        } catch (SQLException e) {
            System.err.println("❌ Error removing duplicate books: " + e.getMessage());
        }
    }
}
