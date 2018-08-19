package restservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import spark.Request;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

public class JWTUtil {
    private static final Algorithm algorithm =  Algorithm.HMAC256(generateUUID());
    private static final String userIdClaimName = "user";
    private static final long jwtDuration = 3600000L;

    public static DecodedJWT decodeJWT(Request request) throws JWTVerificationException {
        String auth = request.headers("Authorization");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(request.ip())
                .build(); //Reusable verifier instance
        return verifier.verify(auth.substring(7));
    }

    public static String getUserIdFromRequest(Request request) throws JWTVerificationException {
        return decodeJWT(request).getClaim(userIdClaimName).asString();
    }

    public static String generateUUID(){
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public static String createJWT(Request request, String userid){
        return JWT.create()
                .withIssuer(request.ip())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtDuration))
                .withClaim(userIdClaimName, userid)
                .sign(algorithm);
    }

}
