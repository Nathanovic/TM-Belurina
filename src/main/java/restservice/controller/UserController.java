package restservice.controller;

import org.eclipse.jetty.http.HttpStatus;
import persistence.db.test.runner.RunnerManager;
import persistence.db.test.user.User;
import persistence.db.test.user.UserManager;
import persistence.db.test.userteam.UserTeam;
import persistence.db.test.userteam.UserTeamImpl;
import persistence.db.test.userteam.UserTeamManager;
import restservice.exception.BadRequestException;
import restservice.exception.ConflictException;
import restservice.util.json.JsonUtil;
import restservice.util.RequestUtil;
import spark.Request;
import spark.Response;

import java.util.stream.Collectors;

import static restservice.util.json.JsonUtil.json;
import static spark.Spark.*;

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
            put("/:id/team/:runnerid", (request, response) -> {
                int id = RequestUtil.getIntFromRequest(":id", request);
                int runnerId = RequestUtil.getIntFromRequest(":runnerid", request);
                UserTeam userTeam = new UserTeamImpl();
                userTeam.setRunnerId(runnerId);
                userTeam.setUserId(id);
                if(userTeamManager.stream()
                        .noneMatch(UserTeam.USER_ID.equal(userTeam.getUserId()).and(UserTeam.RUNNER_ID.equal(userTeam.getRunnerId())))) {
                    return userTeamManager.persist(userTeam);
                }
                return userTeam;
            }, json());
            post("/register", (request, response) -> {
                User newUser = JsonUtil.parse(request.body(), User.class);
                if(newUser.getName() == null || newUser.getPassword() == null)
                    throw new BadRequestException();
                if(userManager.stream().noneMatch(User.NAME.equal(newUser.getName()))){
                    response.status(HttpStatus.CREATED_201);
                    return userManager.persist(newUser);
                } else {
                    throw new ConflictException("User with name " + newUser.getName() + " already exists.");
                }
            });
            delete("/:id/team/:runnerid", (request, response) -> {
                int id = RequestUtil.getIntFromRequest(":id", request);
                int runnerId = RequestUtil.getIntFromRequest(":runnerid", request);
                UserTeam userTeam = new UserTeamImpl();
                userTeam.setRunnerId(runnerId);
                userTeam.setUserId(id);
                userTeamManager.remove(userTeam);
                return "";
            }, json());
            get("", (request, response) -> userManager.stream().collect(Collectors.toList()), json());
        });
    }
}
