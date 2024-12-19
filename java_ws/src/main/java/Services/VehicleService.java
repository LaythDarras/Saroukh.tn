package Services;

import java.util.List;
import DAO.Vehicle;

public interface VehicleService {
    void addVehicle(String type, String brand, String model, int year, double price, String imageUrl);
    List<Vehicle> getAllVehicles();
    Vehicle getVehicleById(int vehicleId);
    void updateVehicle(int vehicleId, String type, String brand, String model, int year, double price, String imageUrl);
    void deleteVehicle(int vehicleId);
}