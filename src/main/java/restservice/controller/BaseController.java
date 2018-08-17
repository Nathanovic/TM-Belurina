package restservice.controller;

import black.door.hate.HalResource;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.UriTemplateBuilder;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.field.Field;
import com.speedment.runtime.field.IntField;
import com.speedment.runtime.field.trait.HasComparableOperators;
import restservice.util.EmptyHalResource;
import restservice.util.QueryParamStreamFilter;
import restservice.util.RequestUtil;
import restservice.util.ResponseUtil;
import spark.QueryParamsMap;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static spark.Spark.get;

public abstract class BaseController<T extends HalResource> {
    private Map<String, QueryParamStreamFilter<T>> map;
    private static String uriTemplate;

    public BaseController(String basePath, Manager<T> manager){
        map = new HashMap<>();
        fillMap(map);
        UriTemplateBuilder templateBuilder = UriTemplate.buildFromTemplate(basePath);
        for(String s: map.keySet()) {
            templateBuilder.query(s.toLowerCase());
        }
        uriTemplate = templateBuilder.build().getTemplate();

        get(getUriTemplate().expand(), (request, response) -> {
            Stream<T> stream = manager.stream();
            QueryParamsMap queryParams = request.queryMap();
            for(String queryKey: request.queryParams()){
                if(map.containsKey(queryKey)){
                    map.get(queryKey).filter(stream, queryParams.get(queryKey));
                }
            }
            return ResponseUtil.renderList("data", stream.collect(Collectors.toList()), request);
        });

        get(getUriTemplate().expand() + "/:id", (request, response) -> {
            int id = RequestUtil.getIntFromRequest(":id", request);
            return ResponseUtil.render(manager.stream().filter(getPrimaryKeyField().equal(id)).findFirst().orElseGet(EmptyHalResource::instantiate), request);
        });
    }


    protected abstract IntField<T, Integer> getPrimaryKeyField();

    protected abstract void fillMap(Map<String, QueryParamStreamFilter<T>> map);

    protected static UriTemplate getUriTemplate(){
        return UriTemplate.buildFromTemplate(uriTemplate).build();
    }

    public static String getUrlForResource(int id) {
        return getUriTemplate().expand() + "/" + id;
    }

    public static String getUrl(Field<?> teamField, Object value) {
        return getUriTemplate().set(teamField.identifier().getColumnId().toLowerCase(), value).expand();
    }

    protected void stringEqualFilter(Map<String, QueryParamStreamFilter<T>> map, HasComparableOperators<T, String> field) {
        map.put(field.identifier().getColumnId().toLowerCase() ,(stream, queryParamsMap) -> stream.filter(field.equal(queryParamsMap.value())));
    }

    protected void integerEqualFilter(Map<String, QueryParamStreamFilter<T>> map, HasComparableOperators<T, Integer> field) {
        map.put(field.identifier().getColumnId().toLowerCase(), (stream, queryParamsMap) -> stream.filter(field.equal(queryParamsMap.integerValue())));
    }
}
