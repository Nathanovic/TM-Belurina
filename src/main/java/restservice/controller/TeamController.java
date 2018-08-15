package restservice.controller;

import persistence.db.test.team.Team;
import persistence.db.test.team.TeamManager;
import persistence.db.test.tour.Tour;
import restservice.util.EmptyHalResource;
import restservice.util.RequestUtil;
import restservice.util.ResponseUtil;

import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.path;

public class TeamController /*extends BaseController<Team>*/ {
    public TeamController(TeamManager teamManager) {
        /* super(Team.BASE_PATH, teamManager);*/
        path(Team.BASE_PATH, () -> {
            get("/:id", (request, response) -> {
                int id = RequestUtil.getIntFromRequest(":id", request);
                return ResponseUtil.render(teamManager.stream().filter(Team.TEAM_ID.equal(id)).findFirst().orElseGet(EmptyHalResource::instantiate), request);
            });
            get("", (request, response) -> ResponseUtil.renderList("teams", teamManager.stream().collect(Collectors.toList()), request));
        });
        get(Tour.BASE_PATH + "/:tourid" + Team.BASE_PATH, (request, response) ->
                ResponseUtil.renderList("teams", teamManager.stream().filter(Team.TOUR_ID.equal(RequestUtil.getIntFromRequest("tourid", request))).collect(Collectors.toList()), request));
    }

    /*@Override
    protected IntField<Team, Integer> getPrimaryKeyField() {
        return Team.TEAM_ID;
    }

    @Override
    protected void fillMap(Map<String, QueryParamStreamFilter<Team>> map) {
        map.put(Team.Identifier.NAME.getColumnId(), (stream, queryParamsMap) -> stream.filter(Team.NAME.equal(queryParamsMap.value())));
        map.put(Team.Identifier.TEAM_ID.getColumnId(), (stream, queryParamsMap) -> stream.filter(Team.TEAM_ID.equal(queryParamsMap.integerValue())));
        map.put(Team.Identifier.TOUR_ID.getColumnId(), (stream, queryParamsMap) -> stream.filter(Team.TOUR_ID.equal(queryParamsMap.integerValue())));
    }*/
}
