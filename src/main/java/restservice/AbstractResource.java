package restservice;

import com.speedment.runtime.core.manager.Manager;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.util.stream.Collectors;

import static restservice.util.json.JsonUtil.json;
import static spark.Spark.path;

public abstract class AbstractResource<M extends Manager> {
    protected M manager;

    public AbstractResource(M manager, String basePath){
        this.manager = manager;
        setUpRoutes(basePath);
    }

    protected void setUpRoutes(String basePath) {
        path(basePath, () -> {
            Spark.get("/:id", (Request request, Response response) -> {
                try {
                    return getById(Integer.parseInt(request.params(":id")));
                } catch(NumberFormatException e) {
                    response.status(HttpStatus.BAD_REQUEST_400);
                }
                return null;
            }, json());
            Spark.get("/", getAll, json());
        });
    }

    public Route getAll = (Request request, Response respone) -> manager.stream().collect(Collectors.toList());

    protected abstract Object getById(int id);

}
