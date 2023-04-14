package RSA.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Server extends Agent {
    @Override
    protected void setup() {
        //PrivateKey privateKey= (PrivateKey) getArguments()[0];

        // Récupération de la clé privée encodée en base64 passée en argument
        String encodedPvk=(String) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {

                ACLMessage receive=receive();

                if(receive != null){
                    String encodedMsg=receive.getContent();
                    byte[] cryptedMsg = Base64.getDecoder().decode(encodedMsg);

                    try {
                        // Décodage de la clé privée en base64
                        byte[] decodePk=Base64.getDecoder().decode(encodedPvk);
                        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

                        // Génération de la clé privée à partir des données décodées
                        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodePk));

                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE,privateKey);
                        // Déchiffrement du message chiffré avec la clé privée
                        byte[] bytes = cipher.doFinal(cryptedMsg);
                        System.out.println(new String(bytes));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }else block();
            }
        });
    }
}
