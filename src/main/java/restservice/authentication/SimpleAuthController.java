package restservice.authentication;

import black.door.hate.HalRepresentation;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.eclipse.jetty.http.HttpStatus;
import persistence.db.test.user.User;
import persistence.db.test.user.UserImpl;
import persistence.db.test.user.UserManager;
import restservice.util.EmptyHalResource;
import restservice.util.JWTUtil;
import restservice.util.ResponseUtil;
import restservice.util.json.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static restservice.util.JWTUtil.generateUUID;
import static spark.Spark.*;

public class SimpleAuthController {

    private String get_SHA_512_SecurePassword(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public SimpleAuthController(UserManager userManager){

        path("/auth", () -> {
            post("/login", (request, response) -> {
                Map map = JsonUtil.parse(request.body(), Map.class);
                User user = userManager.stream().filter(User.NAME.equalIgnoreCase(map.get("username").toString())).findFirst().orElse(null);
                if(user != null) {
                    if(get_SHA_512_SecurePassword(map.get("password").toString(), user.getUserId()).equals(user.getPassword())) {
                        try {
                            return ResponseUtil.render(HalRepresentation.builder().addProperty("token", JWTUtil.createJWT(request, user.getUserId())).build(), request);
                        } catch (JWTCreationException exception){
                            exception.printStackTrace();
                        }
                    }
                }
                return ResponseUtil.error(HttpStatus.UNAUTHORIZED_401,"Invalid Username or Password", request, response);
            });
            post("/register", (request, response) -> {
                Map map = JsonUtil.parse(request.body(), Map.class);
                String username = map.get("username").toString();
                String email = map.get("email").toString();
                String password = map.get("password").toString();
                if(userManager.stream().anyMatch(User.NAME.equalIgnoreCase(username))){
                    return ResponseUtil.conflictError("Username already in use", request, response);
                } else if(userManager.stream().anyMatch(User.EMAIL.equalIgnoreCase(email))) {
                    return ResponseUtil.conflictError("Email already in use", request, response);
                }
                User user = new UserImpl();
                user.setName(username);
                user.setEmail(email);
                String uuid = "";
                do {
                    uuid = generateUUID();
                } while (userManager.stream().anyMatch(User.USER_ID.equal(uuid)));
                user.setUserId(uuid);
                user.setPassword(get_SHA_512_SecurePassword(password, uuid));
                user = userManager.persist(user);
                response.status(HttpStatus.CREATED_201);
                return ResponseUtil.render(user, request);
            });
            get("/user", (request, response) -> {
                try {
                    String userId = JWTUtil.getUserIdFromRequest(request);
                    return ResponseUtil.render(userManager.stream().filter(User.USER_ID.equal(userId)).findFirst().orElseGet(EmptyHalResource::instantiate), request);
                } catch (Exception exception){
                    return ResponseUtil.error(HttpStatus.UNAUTHORIZED_401, exception.getMessage(), request, response);
                }
            });
        });
    }
}
