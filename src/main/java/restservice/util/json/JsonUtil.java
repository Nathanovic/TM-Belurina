package restservice.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import persistence.db.test.user.User;
import spark.ResponseTransformer;

import java.util.Map;

public class JsonUtil {
    private static Gson gson = new GsonBuilder().registerTypeAdapter(User.class, new UserJsonAdapter()).disableHtmlEscaping().setPrettyPrinting().create();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static Map parseString(String jsonString) {
        return gson.fromJson(jsonString, Map.class);
    }

    public static <T> T parse(String jsonString, Class<T> toClass) {
        return gson.fromJson(jsonString, toClass);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}
