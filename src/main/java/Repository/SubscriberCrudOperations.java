package Repository;

import Entity.Subscriber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriberCrudOperations implements CrudOperations<Subscriber> {
    private final Connection connection;

    public SubscriberCrudOperations(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Subscriber> findAll() {
        List<Subscriber> subscribers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("""
            SELECT * FROM "Subscriberer"; 
            """)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String sex = resultSet.getString("sex");
                Subscriber subscriber = new Subscriber(id,name,sex);
                subscribers.add(subscriber);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscribers;
    }

    @Override
    public List<Subscriber> saveAll(List<Subscriber> toSave) {
        List<Subscriber> Subscribers = new ArrayList<>();
        for (Subscriber subscriber : toSave) {
            Subscriber savedSubscriber = save(subscriber);
            if (savedSubscriber != null) {
                Subscribers.add(savedSubscriber);
            }
        }
        return null;

    }

    @Override
    public Subscriber save(Subscriber toSave) {

        try (PreparedStatement statement = connection.prepareStatement("""
                INSERT INTO "Subscriber" (id,name,sex)VALUES ?,?,?;
                """)) {
            statement.setString(1, toSave.getId());
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
    public Subscriber delete(Subscriber toDelete) {
        try (PreparedStatement statement = connection.prepareStatement("""
            DELETE from "Subscriber" where name ?; 
            """)) {
            statement.setString(1, toDelete.getName());
            int rowAffected = statement.executeUpdate();
            if (rowAffected == 1) {
                return toDelete;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
