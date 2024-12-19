package Controllers;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;
import DAO.Vehicle;

@WebService
public interface VehicleController {
    @WebMethod
    void addVehicle(String type, String brand, String model, int year, double price, String imageUrl);

    @WebMethod
    List<Vehicle> getAllVehicles();

    @WebMethod
    Vehicle getVehicleById(int vehicleId);

    @WebMethod
    void updateVehicle(int vehicleId, String type, String brand, String model, int year, double price, String imageUrl);

    @WebMethod
    void deleteVehicle(int vehicleId);
}
