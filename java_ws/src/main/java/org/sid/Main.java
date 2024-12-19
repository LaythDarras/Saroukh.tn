package org.sid;

import DAO.Vehicle;
import DAO.VehicleDAO;
import DAO.VehicleDAOImpl;
import Services.VehicleService;
import Services.VehicleServiceImpl;

import java.util.List;

public class Main {
    private final VehicleService vehicleService;


    public Main() {
        VehicleDAO vehicleDAO = new VehicleDAOImpl();
        this.vehicleService =new VehicleServiceImpl(vehicleDAO);
        List<Vehicle> vh =vehicleService.getAllVehicles();
        for (Vehicle v:vh) {
            System.out.println(v);

        }
    }




}