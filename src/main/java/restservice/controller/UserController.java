package restservice.controller;

import black.door.hate.HalResource;
import org.eclipse.jetty.http.HttpStatus;
import persistence.db.test.runner.Runner;
import persistence.db.test.runner.RunnerManager;
import persistence.db.test.runnerinusertourteam.RunnerInUserTourTeam;
import persistence.db.test.runnerinusertourteam.RunnerInUserTourTeamImpl;
import persistence.db.test.runnerinusertourteam.RunnerInUserTourTeamManager;
import persistence.db.test.runnerinusertourteam.generated.GeneratedRunnerInUserTourTeam;
import persistence.db.test.user.User;
import persistence.db.test.usertourteam.UserTourTeam;
import persistence.db.test.usertourteam.UserTourTeamImpl;
import persistence.db.test.usertourteam.UserTourTeamManager;
import restservice.util.JWTUtil;
import restservice.util.RequestUtil;
import restservice.util.ResponseUtil;
import spark.Request;

import java.util.List;
import java.util.stream.Collectors;

import static spark.Spark.*;

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
                if(userTourTeam == null) {
                    userTourTeam = userTourTeamManager.persist(new UserTourTeamImpl().setTourId(3).setUserId(userid));
                    // TODO: 20/08/2018 tour moet worden meegegeven
                }
                return getReponse(runnerInUserTourTeamManager, runnerManager, userTourTeam.getUserTourTeamId(), request);
            });

            put("/team/:runnerid", (request, response) -> {
                int runnerid = RequestUtil.getIntFromRequest("runnerid", request);
                String userid = "";
                try {
                    userid = JWTUtil.getUserIdFromRequest(request);
                } catch (Exception e) {
                    return ResponseUtil.error(HttpStatus.UNAUTHORIZED_401, e.getMessage(), request, response);
                }
                UserTourTeam userTourTeam = userTourTeamManager.stream().filter(UserTourTeam.USER_ID.equal(userid)).findFirst().orElse(null);
                if(userTourTeam == null) {
                    userTourTeam = userTourTeamManager.persist(new UserTourTeamImpl().setTourId(3).setUserId(userid));
                    // TODO: 20/08/2018 tour moet worden meegegeven
                }
                if(runnerInUserTourTeamManager.stream().anyMatch(RunnerInUserTourTeam.USER_TOUR_TEAM_ID.equal(userTourTeam.getUserTourTeamId()).and(RunnerInUserTourTeam.RUNNER_ID.equal(runnerid)))){
                    return ResponseUtil.conflictError("Runner already in team", request, response);
                }
                runnerInUserTourTeamManager.persist(new RunnerInUserTourTeamImpl().setRunnerId(runnerid).setUserTourTeamId(userTourTeam.getUserTourTeamId()));
                response.status(HttpStatus.CREATED_201);
                return getReponse(runnerInUserTourTeamManager, runnerManager, userTourTeam.getUserTourTeamId(), request);
            });

            delete("/team/:runnerid", (request, response) -> {
                int runnerid = RequestUtil.getIntFromRequest("runnerid", request);
                String userid = "";
                try {
                    userid = JWTUtil.getUserIdFromRequest(request);
                } catch (Exception e) {
                    return ResponseUtil.error(HttpStatus.UNAUTHORIZED_401, e.getMessage(), request, response);
                }
                UserTourTeam userTourTeam = userTourTeamManager.stream().filter(UserTourTeam.USER_ID.equal(userid)).findFirst().orElse(null);
                if(userTourTeam == null) {
                    userTourTeam = userTourTeamManager.persist(new UserTourTeamImpl().setTourId(3).setUserId(userid));
                    // TODO: 20/08/2018 tour moet worden meegegeven
                }
                runnerInUserTourTeamManager.stream()
                        .filter(RunnerInUserTourTeam.USER_TOUR_TEAM_ID.equal(userTourTeam.getUserTourTeamId()).and(RunnerInUserTourTeam.RUNNER_ID.equal(runnerid)))
                        .forEach(runnerInUserTourTeamManager.remover());
                return getReponse(runnerInUserTourTeamManager, runnerManager, userTourTeam.getUserTourTeamId(), request);
            });
        });
    }

    private String getReponse(RunnerInUserTourTeamManager runnerInUserTourTeamManager, RunnerManager runnerManager, int userTourTeamid, Request request){
        List<Integer> runnerInUserTourTeamList = runnerInUserTourTeamManager.stream().filter(RunnerInUserTourTeam.USER_TOUR_TEAM_ID.equal(userTourTeamid)).map(GeneratedRunnerInUserTourTeam::getRunnerId).collect(Collectors.toList());
        List<HalResource> runners = runnerManager.stream().filter(Runner.RUNNER_ID.in(runnerInUserTourTeamList)).collect(Collectors.toList());
        return ResponseUtil.renderList("runners", runners, request);
    }
}
