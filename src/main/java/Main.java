import model.Tour;
import persistence.tour.TourDAO;
import spark.Route;
import util.JsonUtil;
import util.MapBuilderUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        TourDAO tourDAO = new TourDAO();

        List<Tour> tours = tourDAO.getAllTours();
        if(tours.size() < 2) {
            Tour giro = new Tour("Giro d'Italia", 2018);
            Tour vuelta = new Tour("La Vuelta ciclista a España", 2018);
            tourDAO.insertTour(giro);
            tourDAO.insertTour(vuelta);
            tours = tourDAO.getAllTours();
        }
        List<Tour> finalTours = tours;
        port(8082);
        Map<String, Route> routeMap = new MapBuilderUtil<String, Route>()
                .put("/hello", (request, response) -> "Hello world!")
                .put("/tours", (request, response) -> JsonUtil.toJson(finalTours))
                .build();

        for (Map.Entry<String, Route> routeEntry: routeMap.entrySet()){
            get(routeEntry.getKey(), routeEntry.getValue());
        }
    }

}
