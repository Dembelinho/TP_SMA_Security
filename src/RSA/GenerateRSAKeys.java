package RSA;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class GenerateRSAKeys {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPair keyPair =CryptoKeysGenerator.generateRSAKeys();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        String encodePk = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String encodePbk =Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("----------------Private key-------------");
        System.out.println(encodePk);
        System.out.println("----------------Public key-------------");
        System.out.println(encodePbk);
    }
}
