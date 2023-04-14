package AES.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Server extends Agent {
    @Override
    protected void setup() {
        // Récupération de l'argument passé lors du lancement de l'agent
        String secret=(String) getArguments()[0];

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {

                ACLMessage receive=receive(); // Réception d'un message ACL

                if(receive != null){
                    // Récupération du contenu du message (message chiffré en base64)
                    String encodedMsg=receive.getContent();
                    // Décodage du message chiffré en base64
                    byte[] cryptedMsg = Base64.getDecoder().decode(encodedMsg);

                    try {
                        // Création d'une clé secrète pour le déchiffrement AES
                        SecretKey secretKey=new SecretKeySpec(secret.getBytes(),"AES");

                        Cipher cipher = Cipher.getInstance("AES");
                        cipher.init(Cipher.DECRYPT_MODE,secretKey);
                        // Déchiffrement du message
                        byte[] bytes = cipher.doFinal(cryptedMsg);
                        System.out.println(new String(bytes));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }else block(); // Si aucun message reçu, mise en attente de nouveaux messages
            }
        });
    }
}
