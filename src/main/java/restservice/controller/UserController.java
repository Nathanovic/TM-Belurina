package restservice.controller;

import black.door.hate.HalResource;
import org.eclipse.jetty.http.HttpStatus;
import persistence.db.test.runner.Runner;
import persistence.db.test.runner.RunnerManager;
import persistence.db.test.runnerinusertourteam.RunnerInUserTourTeam;
import persistence.db.test.runnerinusertourteam.RunnerInUserTourTeamManager;
import persistence.db.test.runnerinusertourteam.generated.GeneratedRunnerInUserTourTeam;
import persistence.db.test.user.User;
import persistence.db.test.usertourteam.UserTourTeam;
import persistence.db.test.usertourteam.UserTourTeamManager;
import restservice.util.JWTUtil;
import restservice.util.ResponseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.path;

public class UserController {

    public UserController(UserTourTeamManager userTourTeamManager, RunnerInUserTourTeamManager runnerInUserTourTeamManager, RunnerManager runnerManager) {
        path(User.BASE_PATH, () -> {
            get("/team", (request, response) -> {
                String userid = "";
                try {
                    userid = JWTUtil.getUserIdFromRequest(request);
                } catch (Exception e) {
                    return ResponseUtil.error(HttpStatus.UNAUTHORIZED_401, e.getMessage(), request, response);
                }
                UserTourTeam userTourTeam = userTourTeamManager.stream().filter(UserTourTeam.USER_ID.equal(userid)).findFirst().orElse(null);
                if(userTourTeam == null)
                    return ResponseUtil.renderList("runners", new ArrayList<>(), request);
                List<Integer> runnerInUserTourTeamList = runnerInUserTourTeamManager.stream().filter(RunnerInUserTourTeam.USER_TOUR_TEAM_ID.equal(userTourTeam.getUserTourTeamId())).map(GeneratedRunnerInUserTourTeam::getRunnerId).collect(Collectors.toList());
                List<HalResource> runners = runnerManager.stream().filter(Runner.RUNNER_ID.in(runnerInUserTourTeamList)).collect(Collectors.toList());
                return ResponseUtil.renderList("runners", runners, request);
            });
        });
    }

    /*public UserController(UserManager userManager, UserTeamManager userTeamManager, RunnerManager runnerManager, String basePath) {
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
    }*/
}
