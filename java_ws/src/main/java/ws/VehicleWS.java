package ws;

import Controllers.VehicleController;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;
import Services.VehicleService;
import Services.VehicleServiceImpl;
import DAO.Vehicle;
import DAO.VehicleDAO;
import DAO.VehicleDAOImpl;

@WebService(serviceName = "VehicleWebService")
public class VehicleWS implements VehicleController {
    private final VehicleService vehicleService;

    public VehicleWS() {
        VehicleDAO vehicleDAO = new VehicleDAOImpl();
        this.vehicleService = new VehicleServiceImpl(vehicleDAO);
    }
    @WebMethod(operationName = "addVehicle")
    @Override
    public void addVehicle(
            @WebParam(name = "type") String type,
            @WebParam(name = "brand") String brand,
            @WebParam(name = "model") String model,
            @WebParam(name = "year") int year,
            @WebParam(name = "price") double price,
            @WebParam(name = "imageUrl") String imageUrl) {
        vehicleService.addVehicle(type, brand, model, year, price, imageUrl);
    }

    @WebMethod(operationName = "getAllVehicles")
    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @WebMethod(operationName = "getVehicleById")
    @Override
    public Vehicle getVehicleById(@WebParam(name = "vehicleId") int vehicleId) {
        return vehicleService.getVehicleById(vehicleId);
    }

    @WebMethod(operationName = "updateVehicle")
    @Override
    public void updateVehicle(
            @WebParam(name = "vehicleId") int vehicleId,
            @WebParam(name = "type") String type,
            @WebParam(name = "brand") String brand,
            @WebParam(name = "model") String model,
            @WebParam(name = "year") int year,
            @WebParam(name = "price") double price,
            @WebParam(name = "imageUrl") String imageUrl) {
        vehicleService.updateVehicle(vehicleId, type, brand, model, year, price, imageUrl);
    }

    @WebMethod(operationName = "deleteVehicle")
    @Override
    public void deleteVehicle(@WebParam(name = "vehicleId") int vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
    }
}
