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
        String secret= (String) getArguments()[0];
        String msg= "Voici le msg qu'on veut crypter";
        try {
            SecretKey secretKey= new SecretKeySpec(secret.getBytes(),"AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            byte[] cryptedMsg = cipher.doFinal(msg.getBytes());
            String encodedMsg = Base64.getEncoder().encodeToString(cryptedMsg);
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
