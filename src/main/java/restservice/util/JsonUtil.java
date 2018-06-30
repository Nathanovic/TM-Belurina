package restservice.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ResponseTransformer;

public class JsonUtil {
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();;

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}
