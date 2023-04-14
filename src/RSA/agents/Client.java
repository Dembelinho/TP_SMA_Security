package RSA.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class Client extends Agent {
    @Override
    protected void setup() {
        //PublicKey publicKey= (PublicKey) getArguments()[0];

        // Récupération de la clé publique encodée en base64 passée en argument
        String encodedPbk= (String) getArguments()[0];
        String msg= "Voici le msg qu'on veut crypter";
        try {
            // Décodage de la clé publique en base64
            byte[] decodedPbk = Base64.getDecoder().decode(encodedPbk);
            // Création d'une instance de la fabrique de clés RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // Génération de la clé publique à partir des données décodées
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(decodedPbk));

           // Création d'une instance de chiffrement RSA
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            byte[] cryptedMsg = cipher.doFinal(msg.getBytes());

            // Encodage du message chiffré en base64
            String encodedMsg = Base64.getEncoder().encodeToString(cryptedMsg);

            // Création d'un message ACL pour envoyer le message chiffré à l'agent "Server"
            ACLMessage aclMessage=new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(new AID("server",AID.ISLOCALNAME));
            aclMessage.setContent(encodedMsg);
            send(aclMessage);

            System.out.println(Arrays.toString(cryptedMsg));
            System.out.println("---------------");
            System.out.println(encodedMsg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
