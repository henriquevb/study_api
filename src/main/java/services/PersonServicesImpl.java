package services;

import model.Person;
import services.interfaces.PersonServices;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonServicesImpl implements PersonServices {
    final static String URL = "jdbc:mysql://localhost:3306/person";
    final static String USER = "root";
    final static String PASSWORD = "mysql_root";

    @Override
    public void postPerson(Person person) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "INSERT INTO person (name, id) values(?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, person.getName());
            statement.setString(2, person.getId());
            statement.execute();
            System.out.println("salvei");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAllPeople() {
        List<String> people = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "select * from person";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet metaData = statement.executeQuery(sql);

            while (metaData.next()) {
                people.add(metaData.getString("name"));
                people.add(metaData.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    @Override
    public Person getPersonById(String id) {
        Person person = new Person();

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "select * from person where id=" + id;
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet metaData = statement.executeQuery(sql);

            while (metaData.next()) {
                person.setName(metaData.getString("name"));
                person.setId(metaData.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
}
