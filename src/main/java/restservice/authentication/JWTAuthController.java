package restservice.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTAuthController {

    public JWTAuthController(){
        /*KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.ES256);
        Jws<Claims> jws;
        try {
            jws = Jwts.parser()         // (1)
                    .setSigningKey(keyPair.getPublic()) // (2)
                    .parseClaimsJws(""); // (3)
            // we can safely trust the JWT
        } catch (JwtException ex) {       // (4)

                // we *cannot* use the JWT as intended by its creator
        }*/
    }

    public static void main(String[] args) {
        // met dit keypair kan de client zijn wachtwoord veilig versturen en kan alleen de server het bekijken.
        // Het wachtwoord zal dus wel moeten worden opgeslagen in een database.
        // Bij elk request naar de server zal de gebruiker een jwt moeten meesturen.
        /*KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.ES256);
        String jws = Jwts.builder() // (1)
                .setSubject("Dit is een test")
                .setExpiration(new Date(System.currentTimeMillis() + 10000))
                .signWith(keyPair.getPrivate())          // (3)
                .compact();
        // (4)
        System.out.println(jws);
        Jws<Claims> jws2;
        try {
            jws2 = Jwts.parser()         // (1)
                    .setSigningKey(keyPair.getPublic()) // (2)
                    .parseClaimsJws(jws); // (3)
            System.out.println(jws2.getBody());
            // we can safely trust the JWT
        } catch (JwtException ex) {       // (4)
            ex.printStackTrace();
            // we *cannot* use the JWT as intended by its creator
        }*/
        Algorithm algorithm = Algorithm.HMAC256("oegqurqas3vaqbo5kmg21da7h6");
        String token = "";
        try {
            token = JWT.create()
                    .withClaim("ip","127.0.0.2")
                    //.withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(1534428324963L))
                    .withClaim("user", "testuser")
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        System.out.println(token);
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("ip", "127.0.0.1")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(jwt.getClaim("user").asString());
        } catch (JWTVerificationException exception){
            exception.printStackTrace();
        }
    }
}
