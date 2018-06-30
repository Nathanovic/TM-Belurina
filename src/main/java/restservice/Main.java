package restservice;

import org.eclipse.jetty.http.HttpStatus;
import persistence.TestApplication;
import persistence.TestApplicationBuilder;
import persistence.db.test.runner.RunnerManager;
import persistence.db.test.team.TeamManager;
import persistence.db.test.tour.TourManager;
import persistence.db.test.user.UserManager;
import persistence.db.test.userteam.UserTeamManager;
import restservice.controller.RunnerController;
import restservice.controller.TeamController;
import restservice.controller.TourController;
import restservice.controller.UserController;
import restservice.exception.badrequest.BadRequestException;

import static spark.Spark.exception;

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

        TourController tourController = new TourController(tourManager,teamManager, "/tours");
        TeamController teamController = new TeamController(teamManager, runnerManager, "/teams");
        RunnerController runnerController = new RunnerController(runnerManager, "/runners");
        UserController userController = new UserController(userManager, userTeamManager, runnerManager, "/users");

        exception(BadRequestException.class, (exception, request, response) -> {
            response.status(HttpStatus.BAD_REQUEST_400);
        });
    }

}
