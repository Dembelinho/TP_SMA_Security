package RSA;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class CryptoKeysGenerator {
    public static KeyPair generateRSAKeys() throws NoSuchAlgorithmException {
        // Création d'une instance de générateur de paires de clés RSA
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        // Initialisation du générateur avec une taille de clé de 512 bits
        keyPairGenerator.initialize(512);
        // Génération d'une paire de clés RSA
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }
}
