package Repository;

import Entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CrudOperations<Author> {
    private Connection connection;

    public AuthorCrudOperations(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("""
                SELECT * FROM "Author"; 
                """)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String sex = resultSet.getString("sex");
                Author author = new Author(id, name, sex);
                authors.add(author);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public List<Author> saveAll(List<Author> toSave) {
        List<Author> Authors = new ArrayList<>();
        for (Author author : toSave) {
            Author savedAuthor = save(author);
            if (savedAuthor != null) {
                Authors.add(savedAuthor);
            }

        }
        return Authors;
    }

    @Override
    public Author save(Author toSave) {
        try (PreparedStatement statement = connection.prepareStatement(""" 
                INSERT INTO "Author" (id,name,sex)VALUES (?,?,?);
                """)) {
            statement.setInt(1, toSave.getId());
            statement.setString(2, toSave.getName());
            statement.setString(3, toSave.getSex());

            int rowAffected = statement.executeUpdate();
            if (rowAffected > 0) {
                return toSave;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Author delete(Author toDelete) {
        try (PreparedStatement statement = connection.prepareStatement("""
                DELETE from"Author" where name ILIKE '%'||?||'%'; 
                """)) {
            statement.setString(1, toDelete.getName());
            int rowAffected = statement.executeUpdate();
            if (rowAffected == 1) {
                return toDelete;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;
        return null;
    }
}
