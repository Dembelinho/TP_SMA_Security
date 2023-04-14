package AES.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class Client extends Agent {
    @Override
    protected void setup() {
        // Récupération de l'argument passé lors du lancement de l'agent
        String secret= (String) getArguments()[0];

        String msg= "Voici le msg qu'on veut crypter";
        try {
            // Création d'une clé secrète pour le chiffrement AES
            SecretKey secretKey= new SecretKeySpec(secret.getBytes(),"AES");

            // Initialisation du chiffrement AES en mode chiffrement avec la clé secrète
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);

            // Chiffrement du message
            byte[] cryptedMsg = cipher.doFinal(msg.getBytes());
            String encodedMsg = Base64.getEncoder().encodeToString(cryptedMsg);// Encodage du message chiffré en base64

            // Création d'un ACLmessage  pour informer l'agent "Server"
            ACLMessage aclMessage=new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(new AID("server",AID.ISLOCALNAME));
            aclMessage.setContent(encodedMsg);
            send(aclMessage);

            System.out.println(Arrays.toString(cryptedMsg));
            System.out.println("-------------------------");
            System.out.println(encodedMsg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
