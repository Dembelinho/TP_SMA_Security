package Containers;

import RSA.CryptoKeysGenerator;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class ClientContainer {
    public static void main(String[] args) throws StaleProxyException, NoSuchAlgorithmException {
        Runtime instance = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=instance.createAgentContainer(profile);
/*
        KeyPair rsaKeys = CryptoKeysGenerator.generateRSAKeys();
        PrivateKey privateKey = rsaKeys.getPrivate();
        PublicKey publicKey = rsaKeys.getPublic();

 */
        String encodePbk="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMG0SGjBNGN5JNpOO/81KCfRtrbxZGQgD0FSjWm3CTk5eXQenhlEuqDdmhZTZsU/7mrJod8AyYGkPv4Ulsv3ns8CAwEAAQ==";

        //AgentController agentClient=agentContainer.createNewAgent("client","agents.Client",new Object[]{publicKey});
        AgentController agentClient=agentContainer.createNewAgent("client","agents.Client",new Object[]{encodePbk});
        agentClient.start();

    }
}
