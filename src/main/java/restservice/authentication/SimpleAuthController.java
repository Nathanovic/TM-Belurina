package restservice.authentication;

import persistence.db.test.user.User;
import persistence.db.test.user.UserManager;
import restservice.util.json.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

import static spark.Spark.path;
import static spark.Spark.post;

public class SimpleAuthController {
    private String secret;

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

    public static String generateUUID(){
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public SimpleAuthController(UserManager userManager){
        secret = generateUUID();

        path("/auth", () -> {
            post("/login", (request, response) -> {
                Map map = JsonUtil.parse(request.body(), Map.class);
                User user = userManager.stream().filter(User.NAME.equal(map.get("username").toString())).findFirst().orElse(null);
                if(user != null) {
                    /*if(get_SHA_512_SecurePassword(map.get("password").toString(), user.getUserId()).equals(user.getPassword()) {

                    }*/
                    System.out.println(map.get("username"));
                    System.out.println(map.get("password"));
                }
                return "";
            });
            post("/register", (request, response) -> {

                return "";
            });
        });
    }

    public static void main(String[] args) {
        String password = "mysecretpass";
        String username = "user1";
        String uuid = "oegqurqas3vaqbo5kmg21da7h6";
        System.out.println(uuid.length());
        int max = 0;
        for (int i = 0; i < 1000000; i++) {
            int length = SimpleAuthController.generateUUID().length();
            if(length > max)
                max = length;
        }
        System.out.println("max: " + max);
        //System.out.println(get_SHA_512_SecurePassword(username, uuid));
    }
}
