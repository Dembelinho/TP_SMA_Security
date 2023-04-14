package RSA;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class GenerateRSAKeys {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Génération d'une paire de clés RSA
        KeyPair keyPair = CryptoKeysGenerator.generateRSAKeys();
        // Récupération de la clé privée de la paire de clés générée
        PrivateKey privateKey = keyPair.getPrivate();
        // Récupération de la clé publique de la paire de clés générée
        PublicKey publicKey = keyPair.getPublic();

        // Encodage de la clé privée et de la clé publique en base64 pour les afficher
        String encodePk = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String encodePbk =Base64.getEncoder().encodeToString(publicKey.getEncoded());

        System.out.println("----------------Private key-------------");
        System.out.println(encodePk);
        System.out.println("----------------Public key-------------");
        System.out.println(encodePbk);
    }
}
