package server;
import  jakarta.xml.ws.Endpoint;
import ws.*;
public class serverJWS {
    public static void main(String[] args) {
        final String URL="http://localhost:8080/";
        Endpoint.publish(URL,new VehicleWS());
        System.out.println("web service deploye sur "+URL);
    }
}
