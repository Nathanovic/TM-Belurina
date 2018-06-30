package restservice.controller;

import persistence.db.test.runner.Runner;
import persistence.db.test.runner.RunnerManager;
import persistence.db.test.team.Team;
import persistence.db.test.team.TeamManager;
import persistence.db.test.tour.Tour;
import restservice.util.RequestUtil;
import spark.Request;
import spark.Response;

import java.util.stream.Collectors;

import static restservice.util.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.path;

public class TeamController {

    public TeamController(TeamManager teamManager, RunnerManager runnerManager, String basePath) {
        path(basePath, () -> {
            get("/:id", (Request request, Response response) -> {
                int id = RequestUtil.getIntFromRequest(":id", request);
                return teamManager.stream().filter(Team.TEAM_ID.equal(id)).collect(Collectors.toList());
            }, json());
            get("/:id/runners", (request, response) -> {
                int id = RequestUtil.getIntFromRequest(":id", request);
                return runnerManager.stream().filter(Runner.TEAM_ID.equal(id)).collect(Collectors.toList());
            }, json());
            get("", (request, response) -> teamManager.stream().collect(Collectors.toList()), json());
        });
    }

}
