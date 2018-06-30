package restservice.controller;

import persistence.db.test.runner.Runner;
import persistence.db.test.runner.RunnerManager;
import persistence.db.test.team.Team;
import persistence.db.test.user.User;
import persistence.db.test.user.UserManager;
import persistence.db.test.userteam.UserTeam;
import persistence.db.test.userteam.UserTeamManager;
import restservice.util.RequestUtil;
import spark.Request;
import spark.Response;

import java.util.stream.Collectors;

import static restservice.util.JsonUtil.json;
import static spark.Spark.get;
import static spark.Spark.path;

public class UserController {

    public UserController(UserManager userManager, UserTeamManager userTeamManager, RunnerManager runnerManager, String basePath) {
        path(basePath, () -> {
            get("/:id", (Request request, Response response) -> {
                int id = RequestUtil.getIntFromRequest(":id", request);
                return userManager.stream().filter(User.USER_ID.equal(id)).findFirst().orElse(null);
            }, json());
            get("/:id/team", (request, response) -> {
                int id = RequestUtil.getIntFromRequest(":id", request);
                return userTeamManager.stream().filter(UserTeam.USER_ID.equal(id)).map(userteam -> userteam.findRunnerId(runnerManager)).collect(Collectors.toList());
            }, json());
            get("", (request, response) -> userManager.stream().collect(Collectors.toList()), json());
        });
    }
}
