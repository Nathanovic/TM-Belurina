package restservice.util;

import black.door.hate.HalRepresentation;
import black.door.hate.HalResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.net.URI;
import java.util.List;

public class ResponseUtil {
    public static String renderList(String listName, List<HalResource> data, Request request) {
        return HalRepresentation.builder().addLink("self", URI.create(request.pathInfo())).addEmbedded(listName, data).build().toString();
    }

    public static String render(HalRepresentation data, Request request) {
        return data.toString();
    }

    public static String render(HalResource data, Request request) {
        try {
            return data.representationBuilder()
                    .build()
                    .serialize();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return HalRepresentation.builder().addProperty("data", data).build().toString();
    }

    public static String error(int statusCode, String message, Request request, Response response) {
        HttpStatus.Code code = HttpStatus.getCode(statusCode);
        response.status(code.getCode());
        return HalRepresentation.builder()
                .addProperty("title", code.getCode() + " " + code.getMessage())
                .addProperty("status", code.getCode())
                .addProperty("detail", message)
                .addLink("self", URI.create(request.pathInfo()))
                .build()
                .toString();
    }

    public static String conflictError(String message, Request request, Response response) {
        return error(HttpStatus.CONFLICT_409, message, request, response);
    }

}
