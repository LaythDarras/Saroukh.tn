package Services;

import DAO.Vehicle;
import DAO.VehicleDAO;

import java.util.List;

public class VehicleServiceImpl implements VehicleService {
    private final VehicleDAO vehicleDAO;

    public VehicleServiceImpl(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    @Override
    public void addVehicle(String type, String brand, String model, int year, double price, String imageUrl) {
        if (price < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Le type est obligatoire");
        }
        if (imageUrl == null || imageUrl.isEmpty()) {
            throw new IllegalArgumentException("L'URL de l'image est obligatoire");
        }
        vehicleDAO.addVehicle(type, brand, model, year, price, imageUrl);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.getAllVehicles();
    }

    @Override
    public Vehicle getVehicleById(int vehicleId) {
        Vehicle vehicle = vehicleDAO.getVehicleById(vehicleId);
        if (vehicle == null) {
            throw new IllegalArgumentException("Véhicule introuvable avec l'ID : " + vehicleId);
        }
        return vehicle;
    }

    @Override
    public void updateVehicle(int vehicleId, String type, String brand, String model, int year, double price, String imageUrl) {
        Vehicle existingVehicle = vehicleDAO.getVehicleById(vehicleId);
        if (existingVehicle == null) {
            throw new IllegalArgumentException("Impossible de mettre à jour, le véhicule n'existe pas.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Le type est obligatoire");
        }
        if (imageUrl == null || imageUrl.isEmpty()) {
            throw new IllegalArgumentException("L'URL de l'image est obligatoire");
        }
        vehicleDAO.updateVehicle(vehicleId, type, brand, model, year, price, imageUrl);
    }

    @Override
    public void deleteVehicle(int vehicleId) {
        Vehicle existingVehicle = vehicleDAO.getVehicleById(vehicleId);
        if (existingVehicle == null) {
            throw new IllegalArgumentException("Impossible de supprimer, le véhicule n'existe pas.");
        }
        vehicleDAO.deleteVehicle(vehicleId);
    }
}
