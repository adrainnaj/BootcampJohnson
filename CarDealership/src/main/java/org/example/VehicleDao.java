package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private final String connectionString;
    private final String userName;
    private final String password;

    public VehicleDao(String connectionString, String userName, String password) {
        this.connectionString = connectionString;
        this.userName = userName;
        this.password = password;
    }
    public List<Vehicle> getAll(){
        List<Vehicle> list = new ArrayList<>();
        String query = "Select * FROM Vehicle";

        try (Connection conn = DriverManager.getConnection(connectionString, userName, password);
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Vehicle newCategories = new Vehicle();
                newCategories.setVin(resultSet.getInt("VehicleVin"));
                newCategories.setCategoryName(resultSet.getString("CategoryName"));
                newCategories.setDescription(resultSet.getString("Description"));

                list.add(newCategories);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
