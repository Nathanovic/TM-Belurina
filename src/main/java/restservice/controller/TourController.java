package restservice.controller;

import persistence.db.test.tour.Tour;
import persistence.db.test.tour.TourManager;
import restservice.util.EmptyHalResource;
import restservice.util.RequestUtil;
import restservice.util.ResponseUtil;

import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.path;

public class TourController /*extends BaseController<Tour>*/ {

    public TourController(TourManager tourManager) {
        path(Tour.BASE_PATH, () -> {
            get("/:id", (request, response) -> {
                int id = RequestUtil.getIntFromRequest(":id", request);
                return ResponseUtil.render(tourManager.stream().filter(Tour.TOUR_ID.equal(id)).findFirst().orElseGet(EmptyHalResource::instantiate), request);
            });
            get("", (request, response) -> ResponseUtil.renderList("tours", tourManager.stream().collect(Collectors.toList()), request));
        });
    }
}