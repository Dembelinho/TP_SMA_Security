package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;

import java.security.PrivateKey;
import java.util.Base64;

public class Server extends Agent {
    @Override
    protected void setup() {
        PrivateKey privateKey= (PrivateKey) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {

                ACLMessage receive=receive();

                if(receive != null){
                    String encodedMsg=receive.getContent();
                    byte[] cryptedMsg = Base64.getDecoder().decode(encodedMsg);

                    try {
                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE,privateKey);
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
