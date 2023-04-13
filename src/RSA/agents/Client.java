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
        String encodedPbk= (String) getArguments()[0];
        String msg= "Voici le msg qu'on veut crypter";
        try {
            byte[] decodedPbk = Base64.getDecoder().decode(encodedPbk);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(decodedPbk));

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            byte[] cryptedMsg = cipher.doFinal(msg.getBytes());
            String encodedMsg = Base64.getEncoder().encodeToString(cryptedMsg);
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
