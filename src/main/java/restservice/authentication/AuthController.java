package restservice.authentication;

/*import com.nimbusds.srp6.BigIntegerUtils;
import com.nimbusds.srp6.SRP6CryptoParams;
import com.nimbusds.srp6.SRP6Exception;
import com.nimbusds.srp6.SRP6ServerSession;
import persistence.db.test.tour.TourImpl;

import java.math.BigInteger;

public class AuthController {

    public AuthController(){
        //client sends username
        SRP6CryptoParams config = SRP6CryptoParams.getInstance();
        System.out.println(config.N);
        System.out.println(config.g);
        SRP6ServerSession server = new SRP6ServerSession(config);

        BigInteger salt = BigIntegerUtils.fromHex("1f1659a77b66bfbf5fef68ef73d82f37");
        BigInteger verifier = BigIntegerUtils.fromHex("x");

        BigInteger publicServerValue = server.step1("test", BigInteger.valueOf(1), BigInteger.valueOf(1));
        //respond with salt and publicServerValue to client
        //client sends publicClientValue and evidence message
        BigInteger publicClientValue = BigIntegerUtils.fromHex("91b446bc56cdf02c74d2f494b3131b7a0e724de8527b36fc67a96c5e9d23836dfe9aaf06db64deea6009bb1f4bc0b423cbddbdb4906bd6569ff87b55811b2e1b");
        BigInteger clientEvidence = BigIntegerUtils.fromHex("7d3d92fd1cb39380b3dfc80319ff283cf3e93ca6");
        BigInteger serverEvidence = null;
        try {
            serverEvidence = server.step2(publicClientValue, clientEvidence);
        } catch (SRP6Exception e) {
            e.printStackTrace();
            // User authentication failed
        }
        // Respond with server evidence message
    }

    public static void main(String[] args) throws JsonProcessingException {
        //System.out.println(new TourImpl().setName("Test").setYear(2018).asEmbedded().serialize());
    }

}*/
