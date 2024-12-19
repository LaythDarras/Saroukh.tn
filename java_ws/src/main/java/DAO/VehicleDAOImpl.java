package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/projet_soa";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public void addVehicle(String type, String brand, String model, int year, double price, String imageUrl) {
        String query = "INSERT INTO vehicles (type, brand, model, year, price, image_url, created_at) VALUES (?, ?, ?, ?, ?, ?, NOW())";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, type);
            statement.setString(2, brand);
            statement.setString(3, model);
            statement.setInt(4, year);
            statement.setDouble(5, price);
            statement.setString(6, imageUrl);

            statement.executeUpdate();
            System.out.println("Vehicle added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding vehicle: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        String query = "SELECT * FROM vehicles";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle(
                        resultSet.getInt("vehicle_id"),
                        resultSet.getString("type"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image_url"),
                        resultSet.getTimestamp("created_at")
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicles: " + e.getMessage());
            e.printStackTrace();
        }

        return vehicles;
    }

    @Override
    public Vehicle getVehicleById(int vehicleId) {
        String query = "SELECT * FROM vehicles WHERE vehicle_id = ?";
        Vehicle vehicle = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, vehicleId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                vehicle = new Vehicle(
                        resultSet.getInt("vehicle_id"),
                        resultSet.getString("type"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("year"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image_url"),
                        resultSet.getTimestamp("created_at")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving vehicle: " + e.getMessage());
            e.printStackTrace();
        }

        return vehicle;
    }

    @Override
    public void updateVehicle(int vehicleId, String type, String brand, String model, int year, double price, String imageUrl) {
        String query = "UPDATE vehicles SET type = ?, brand = ?, model = ?, year = ?, price = ?, image_url = ? WHERE vehicle_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, type);
            statement.setString(2, brand);
            statement.setString(3, model);
            statement.setInt(4, year);
            statement.setDouble(5, price);
            statement.setString(6, imageUrl);
            statement.setInt(7, vehicleId);

            statement.executeUpdate();
            System.out.println("Vehicle updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating vehicle: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVehicle(int vehicleId) {
        String query = "DELETE FROM vehicles WHERE vehicle_id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, vehicleId);
            statement.executeUpdate();
            System.out.println("Vehicle deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Error deleting vehicle: "+query +"|"+ e.getMessage());
            e.printStackTrace();
        }
    }
}
