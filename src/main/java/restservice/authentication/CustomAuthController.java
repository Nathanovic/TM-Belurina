package restservice.authentication;

import black.door.hate.HalRepresentation;
import restservice.util.ResponseUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.net.URI;
import java.security.*;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

import static spark.Spark.get;
import static spark.Spark.post;

public class CustomAuthController {
    private KeyPair keyPair;
    private Cipher cipher;
    private Base64.Encoder encoder;

    public CustomAuthController(){
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            keyPair = kpg.generateKeyPair();
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        encoder = Base64.getEncoder();

        get("/auth/key", (request, response) -> {
            HalRepresentation halRepresentation = HalRepresentation.builder()
                    .addProperty("key", encoder.encodeToString(keyPair.getPublic().getEncoded()))
                    .addLink("self", URI.create("/auth/key"))
                    .addLink("login", URI.create("/auth/login"))
                    .build();
            return ResponseUtil.render(halRepresentation, request);
        });

        post("/auth/login", (request, response) -> {
            System.out.println(request.body());
            String decoded = new String(cipher.doFinal(Base64.getDecoder().decode(request.bodyAsBytes())));
            System.out.println(decoded);
            return "";
        });
    }

    private String decrypt(String message, PrivateKey privateKey) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(message.getBytes()));
    }

    public static void main(String[] args) throws Exception {

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair keyPair = kpg.generateKeyPair();
        RSAPublicKeySpec pks = KeyFactory.getInstance("RSA").getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);
        System.out.println(pks.getModulus());
        System.out.println(pks.getPublicExponent());

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        KeyFactory.getInstance("RSA").generatePublic(
                new RSAPublicKeySpec(
                        new BigInteger("27327721233477548128894506679684317271470263405390393966112567900119861778857922459310465916098333583066619485179351045514014267331753044099505803504651537587786086602762757553862101328366098645840244981753205749080475100752683602160703632241493017493691208006977792431237843886062022064137341610693553767979876470663830248056463175480832691116048133906680669233584827639231691740673441504305567768728389198529626624237863237921155057344416403620965840257556641943966691170841550360138365391002236318021936862725798655839402361494767944749865389914168910341722309730755587142592521371579962237790990581327264046954327"), BigInteger.valueOf(65537)));

        /*BigInteger exp = new BigInteger("10001", 16);
        RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, exp);
        PublicKey pub = f.generatePublic(spec);
        byte[] data = pub.getEncoded();
        String base64encoded = new String(Base64.getEncoder().encode(data));
        System.out.println(base64encoded);
        */
        //System.out.println(KeyPairGenerator.getInstance("RSA/ECB/PKCS1Padding").generateKeyPair().getPublic().getFormat());
        /*List<String> list = new ArrayList<>();
        for(int i = 0; i < 1; i++) {
            long start = System.currentTimeMillis();
            try {
                KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
                kpg.initialize(1024);
                KeyPair kp = kpg.generateKeyPair();
                String password = "test123";
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, kp.getPublic());
                String encrypted = Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes()));

                PublicKey publicKey = kp.getPublic();
                X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
               // RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(publicKey.getEncoded(), 16))
                Base64.Encoder encoder = Base64.getEncoder();
                System.out.println(encoder.encodeToString(publicKey.getEncoded()));
                System.out.println(Arrays.toString(publicKey.getEncoded()));
                if(list.contains(encrypted)) {
                    throw new Exception(encrypted + " komt 2 keer voor!");
                }
                list.add(encrypted);
                cipher.init(Cipher.DECRYPT_MODE, kp.getPrivate());
                System.out.println(new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)), "UTF-8"));
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
        }*/
    }

}
