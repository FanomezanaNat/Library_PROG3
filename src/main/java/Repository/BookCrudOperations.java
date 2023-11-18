package Repository;

import DatabaseConfiguration.DatabaseConnectionManager;
import Entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class BookCrudOperations implements CrudOperations<Book> {
    private Connection connection;
    private DatabaseConnectionManager connectionManager;

    public BookCrudOperations(Connection connection, DatabaseConnectionManager connectionManager) {
        this.connection = connection;
        this.connectionManager = connectionManager;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("Select * from \"Books\"")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int pageNumber = resultSet.getInt("pageNumber");
                Date releaseDate = resultSet.getDate("releaseDate");
                int idAuthor = resultSet.getInt("idAuthor");
                Book.Topic topicValue = (Book.Topic) resultSet.getObject("topic");
                String status = resultSet.getString("status");
                Book book = new Book(id, name, pageNumber, releaseDate, idAuthor, topicValue, status);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public List<Book> saveAll(List<Book> toSave) {
        List<Book> savedBooks = new ArrayList<>();
        for (Book book : toSave) {
            Book savedBook = save(book);
            if (savedBook != null) {
                savedBooks.add(savedBook);
            }
        }
        return savedBooks;
    }

    @Override
    public Book save(Book toSave) {
        try (PreparedStatement statement =
                     connection.prepareStatement
                             ("INSERT INTO \"Books\" " +
                                     "(id, name, pageNumber, releaseDate, idAuthor, topic, status) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, toSave.getId());
            statement.setString(2, toSave.getName());
            statement.setInt(3, toSave.getPageNumber());
            statement.setDate(4, new java.sql.Date(toSave.getReleaseDate().getTime()));
            statement.setInt(5, toSave.getIdAuthor());
            statement.setString(6, toSave.getTopic().toString());
            statement.setString(7, toSave.getStatus());

            int rowAffected = statement.executeUpdate();
            if (rowAffected >0) {
                return toSave;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book delete(Book toDelete) {
        try(PreparedStatement statement= connection.prepareStatement("DELETE from\"Books\" where name ? ")) {
            statement.setString(1, String.valueOf(toDelete));
        } catch (SQLException e) {
            e.printStackTrace();
        } ;
        return null;
    }
}
