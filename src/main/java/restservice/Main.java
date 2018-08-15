package restservice;

import black.door.hate.HalRepresentation;
import persistence.TestApplication;
import persistence.TestApplicationBuilder;
import persistence.db.test.runner.Runner;
import persistence.db.test.runner.RunnerManager;
import persistence.db.test.team.Team;
import persistence.db.test.team.TeamManager;
import persistence.db.test.tour.Tour;
import persistence.db.test.tour.TourManager;
import persistence.db.test.user.UserManager;
import persistence.db.test.userteam.UserTeamManager;
import restservice.controller.RunnerController;
import restservice.controller.TeamController;
import restservice.controller.TourController;
import restservice.controller.UserController;
import restservice.exception.ExceptionController;
import restservice.util.ResponseUtil;

import java.net.URI;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        TestApplication app = new TestApplicationBuilder()
                .withPassword("Testing@12")
                .build();

        TourManager tourManager = app.getOrThrow(TourManager.class);
        RunnerManager runnerManager = app.getOrThrow(RunnerManager.class);
        TeamManager teamManager = app.getOrThrow(TeamManager.class);
        UserManager userManager = app.getOrThrow(UserManager.class);
        UserTeamManager userTeamManager = app.getOrThrow(UserTeamManager.class);

        options("/*",(request, response) -> {
            String accessControlRequestHeaders = request
                    .headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers",
                        accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request
                    .headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods",
                        accessControlRequestMethod);
            }

            return "OK";
        });
        before(((request, response) -> response.header("Access-Control-Allow-Origin", "*")));

        get("/", (request, response) -> ResponseUtil.render(HalRepresentation.builder()
                .addLink("self", URI.create(""))
                .addLink("tours", URI.create(Tour.BASE_PATH))
                .addLink("teams", URI.create(Team.BASE_PATH))
                .addLink("runners", URI.create(Runner.BASE_PATH)).build(), request));

        ExceptionController exceptionController = new ExceptionController();
        TourController tourController = new TourController(tourManager);
        TeamController teamController = new TeamController(teamManager);
        RunnerController runnerController = new RunnerController(runnerManager);
        UserController userController = new UserController(userManager, userTeamManager, runnerManager, "/users");
    }

}
