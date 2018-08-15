package restservice.controller;

import persistence.db.test.runner.Runner;
import persistence.db.test.runner.RunnerManager;
import persistence.db.test.team.Team;
import restservice.util.EmptyHalResource;
import restservice.util.RequestUtil;
import restservice.util.ResponseUtil;

import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.path;

public class RunnerController /*extends BaseController<Runner>*/ {

    public RunnerController(RunnerManager runnerManager) {
        /*super(Runner.BASE_PATH, runnerManager);*/
        path(Runner.BASE_PATH, () -> {
            get("/:id", (request, response) -> {
                int id = RequestUtil.getIntFromRequest(":id", request);
                return ResponseUtil.render(runnerManager.stream().filter(Runner.RUNNER_ID.equal(id)).findFirst().orElseGet(EmptyHalResource::instantiate), request);
            });
            get("", (request, response) -> ResponseUtil.renderList("runners", runnerManager.stream().collect(Collectors.toList()), request));
        });
        get(Team.BASE_PATH + "/:teamid" + Runner.BASE_PATH, (request, response) -> ResponseUtil.renderList("runners", runnerManager.stream().filter(Runner.TEAM_ID.equal(RequestUtil.getIntFromRequest("teamid", request))).collect(Collectors.toList()), request));
        /*path(basePath, () -> {
            get("/:id", (Request request, Response response) -> {
                int id = RequestUtil.getIntFromRequest(":id", request);
                return runnerManager.stream().filter(Runner.RUNNER_ID.equal(id)).collect(Collectors.toList());
            }, json());
            get("", (request, response) -> runnerManager.stream().collect(Collectors.toList()), json());
        });*/
    }

    /*@Override
    protected IntField<Runner, Integer> getPrimaryKeyField() {
        return Runner.RUNNER_ID;
    }

    @Override
    protected void fillMap(Map<String, QueryParamStreamFilter<Runner>> map) {
        this.stringEqualFilter(map, Runner.FIRST_NAME);
        this.stringEqualFilter(map, Runner.LAST_NAME);
        this.integerEqualFilter(map, Runner.RUNNER_ID);
        this.integerEqualFilter(map, Runner.TEAM_ID);
    }*/
}
