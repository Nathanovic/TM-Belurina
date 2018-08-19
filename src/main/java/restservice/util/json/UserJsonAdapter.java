package restservice.util.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import persistence.db.test.user.User;
import persistence.db.test.user.UserImpl;

import java.io.IOException;

public class UserJsonAdapter extends TypeAdapter<User> {

    private User createEntity() {
        return new UserImpl();
    }

    @Override
    public void write(JsonWriter jsonWriter, User user) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("userId").value(user.getUserId());
        jsonWriter.name("name").value(user.getName());
        jsonWriter.name("password").value(user.getPassword());
        jsonWriter.endObject();
    }

    @Override
    public User read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        User user = createEntity();
        while (jsonReader.hasNext()){
            switch (jsonReader.nextName()){
                case "username":
                case "name":
                    user.setName(jsonReader.nextString());
                    break;
                case "password":
                    user.setPassword(jsonReader.nextString());
                    break;
            }
        }
        jsonReader.endObject();
        return user;
    }
}
