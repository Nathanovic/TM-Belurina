package restservice.controller;

import org.eclipse.jetty.http.HttpStatus;
import persistence.db.test.team.Team;
import persistence.db.test.team.TeamManager;
import persistence.db.test.tour.Tour;
import persistence.db.test.tour.TourManager;
import restservice.exception.badrequest.BadRequestException;
import restservice.util.RequestUtil;
import spark.Request;
import spark.Response;

import java.util.stream.Collectors;

import static restservice.util.JsonUtil.json;
import static spark.Spark.path;
import static spark.Spark.get;

public class TourController {

    public TourController(TourManager tours, TeamManager teamManager, String basePath) {
        path(basePath, () -> {
           get("/:id/teams", (Request request, Response response) -> {
                int id = RequestUtil.getIntFromRequest(":id", request);
                return teamManager.stream().filter(Team.TOUR_ID.equal(id)).collect(Collectors.toList());
           }, json());
           get("/:id", (request, response) -> {
               int id = RequestUtil.getIntFromRequest(":id", request);
               return tours.stream().filter(Tour.TOUR_ID.equal(id)).findFirst().orElse(null);
           }, json());
           get("", (request, response) -> tours.stream().collect(Collectors.toList()), json());
        });
    }
}
