import DatabaseConfiguration.DatabaseConnectionManager;
import Entity.Author;
import Entity.Book;
import Entity.Subscriber;
import Repository.AuthorCrudOperations;
import Repository.BookCrudOperations;
import Repository.SubscriberCrudOperations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager();

        try (Connection connection = connectionManager.getConnection()) {
            SubscriberCrudOperations subscriberCrudOperations = new SubscriberCrudOperations(connection);
            if (connection != null) {
                // Find all subscribers
                List<Subscriber> allSubscribers = subscriberCrudOperations.findAll();
                System.out.println("All Subscribers:");
                for (Subscriber subscriber : allSubscribers) {
                    System.out.println(subscriber.toString());
                }
                // Creating a new subscriber
                Subscriber newSubscriber = new Subscriber(4, "Nathanael", "M");
                Subscriber createdSubscriber = subscriberCrudOperations.save(newSubscriber);
                if (createdSubscriber != null) {
                    System.out.println("New subscriber created: " + createdSubscriber);
                } else {
                    System.out.println("Failed to create a new subscriber.");
                }

                // Deleting an existing subscriber
                Subscriber subscriberToDelete = new Subscriber();
                subscriberToDelete.setName("Nathanael"); // Set the name of the subscriber to delete
                Subscriber deletedSubscriber = subscriberCrudOperations.delete(subscriberToDelete);
                if (deletedSubscriber != null) {
                    System.out.println("Subscriber deleted: " + deletedSubscriber);
                } else {
                    System.out.println("Failed to delete the subscriber.");
                }
            } else {
                System.out.println("Failed to establish a connection.");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching subscribers: " + e.getMessage());
            System.out.println("Failed to retrieve subscribers. Please try again later.");
        }
        try (Connection connection = connectionManager.getConnection()) {
            AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations(connection);
            if (connection != null) {
                // Find all authors
                List<Author> allAuthors = authorCrudOperations.findAll();
                System.out.println("All authors:");
                for (Author author : allAuthors) {
                    System.out.println(author.toString());
                }
                // Creating a new author
                Author author = new Author(5, "RAKOTO", "M");
                Author createdAuthor = authorCrudOperations.save(author);
                if (createdAuthor != null) {
                    System.out.println("New author created: " + createdAuthor);
                } else {
                    System.out.println("Failed to create a new author.");
                }

                // Deleting an existing author
                Author authorToDelete = new Author();
                authorToDelete.setName(null); // Set the name of the subscriber to delete
                Author deletedAuthor = authorCrudOperations.delete(authorToDelete);
                if (deletedAuthor != null) {
                    System.out.println("author deleted: " + deletedAuthor);
                } else {
                    System.out.println("Failed to delete the author.");
                }
            } else {
                System.out.println("Failed to establish a connection.");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while fetching author " + e.getMessage());
            System.out.println("Failed to retrieve author. Please try again later.");
        }
        try (Connection connection = connectionManager.getConnection()) {
            BookCrudOperations bookCrudOperations = new BookCrudOperations(connection);
            if (connection != null) {
                // Find all books
                List<Book> allBooks = bookCrudOperations.findAll();
                System.out.println("All books:");
                for (Book book : allBooks) {
                    System.out.println(book.toString());
                }
                // Creating a new book
                Book Book = new Book("001", "Sample Book", 200, new Date(System.currentTimeMillis()), 5, Entity.Book.Topic.Other, "Available");
                Book createdBook = bookCrudOperations.save(Book);
                if (createdBook != null) {
                    System.out.println("New book created: " + createdBook);
                } else {
                    System.out.println("Failed to create a new book.");
                }
                Book bookToDelete = new Book();
                bookToDelete.setName("Sample Book"); // Set the name of the book to delete
                Book deletedBook = bookCrudOperations.delete(bookToDelete);
                if (deletedBook != null) {
                    System.out.println("book deleted: " + deletedBook);
                } else {
                    System.out.println("Failed to delete the book.");
                }
            } else {
                System.out.println("Failed to establish a connection.");
            }

        } catch (SQLException e) {
            System.err.println("An error occurred while fetching author " + e.getMessage());
            System.out.println("Failed to retrieve author. Please try again later.");
        }


    }
}

