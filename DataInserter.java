package com.bookrecommendation;

import java.sql.*;

public class DataInserter {
    private static final String DATABASE_URL = "jdbc:sqlite:books.db";

    public void insertData() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL)) {
            insertAuthors(conn);
            insertGenres(conn);
            insertBooks(conn);
            System.out.println("✅ Data inserted successfully.");
        } catch (SQLException e) {
            System.err.println("❌ Error inserting data: " + e.getMessage());
        }
    }

    private void insertAuthors(Connection conn) throws SQLException {
        String[] authors = {
                "J.K. Rowling", "George Orwell", "Leo Tolstoy", "Gabriel García Márquez",
                "Fyodor Dostoevsky", "Harper Lee", "Jane Austen", "Ernest Hemingway",
                "Mark Twain", "Charles Dickens", "F. Scott Fitzgerald", "Agatha Christie",
                "Stephen King", "Isaac Asimov", "J.R.R. Tolkien", "Arthur Conan Doyle",
                "Emily Brontë", "H.G. Wells", "Victor Hugo", "Edgar Allan Poe",
                "Oscar Wilde", "Dante Alighieri", "Homer", "Franz Kafka", "Virginia Woolf",
                "Albert Camus", "Herman Melville", "Bram Stoker", "Mary Shelley",
                "Jules Verne", "Ray Bradbury", "Philip K. Dick", "Aldous Huxley",
                "Dan Brown", "Haruki Murakami", "Kurt Vonnegut", "James Joyce",
                "Tennessee Williams", "John Steinbeck", "Dostoyevsky", "Anton Chekhov",
                "Robert Louis Stevenson", "C.S. Lewis", "Douglas Adams", "Leo Tolstoy",
                "Margaret Atwood", "George R.R. Martin", "Suzanne Collins", "Rick Riordan"
        };

        String insertSQL = "INSERT OR IGNORE INTO authors (name) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
            for (String author : authors) {
                stmt.setString(1, author);
                stmt.executeUpdate();
            }
        }
    }

    private void insertGenres(Connection conn) throws SQLException {
        String[] genres = {
                "Fantasy", "Dystopian", "Historical", "Magical Realism", "Science Fiction",
                "Horror", "Mystery", "Classic", "Romance", "Adventure", "Crime", "Thriller",
                "Drama", "Philosophy", "Psychology", "Poetry", "Mythology", "Biography",
                "Comedy", "Postmodern"
        };

        String insertSQL = "INSERT OR IGNORE INTO genres (genre_name) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
            for (String genre : genres) {
                stmt.setString(1, genre);
                stmt.executeUpdate();
            }
        }
    }

    private void insertBooks(Connection conn) throws SQLException {
        String[][] books = {
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

        String insertSQL = "INSERT OR IGNORE INTO books (title, author_id, genre_id) " +
                "VALUES (?, (SELECT id FROM authors WHERE name = ?), (SELECT id FROM genres WHERE genre_name = ?))";

        try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
            for (String[] book : books) {
                stmt.setString(1, book[0]);
                stmt.setString(2, book[1]);
                stmt.setString(3, book[2]);
                stmt.executeUpdate();
            }
        }
        System.out.println("✅ Books inserted successfully.");
    }
}
