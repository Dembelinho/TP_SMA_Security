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

public class ServerContainer {
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
        String encodedPvk="MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAwbRIaME0Y3kk2k47/zUoJ9G2tvFkZCAPQVKNabcJOTl5dB6eGUS6oN2aFlNmxT/uasmh3wDJgaQ+/hSWy/eezwIDAQABAkEApNGV2yyqOwnVhbuT6W99n4QeCEQVu+xbjqWDljQcMQduSZIycq1sZkki1AhkiZVPvsi6Dg+DCbHO6LglHlmNIQIhAPGt+6LYlTeLaptlwxLq7TlzX/rvFXO/xC8GpZELpYc1AiEAzS6QvNUdeXsxLSlOBAmnl+pkHvbHpoNA0JV4i2LumnMCIAogWhzWqlnrc4oTV3shcNMpbGodPm7dKCE4TBOpw0PJAiA1b6cqRqaCeN750AQNcSZAnj6tAcBFUXGT4Rmf04gomQIgNArGoCkqHhdAI1cF/E2IlJtPTuOvvYGN7xpYU81AzgE=";

        //AgentController agentServer=agentContainer.createNewAgent("server","agents.Server",new Object[]{privateKey});
        AgentController agentServer=agentContainer.createNewAgent("server","agents.Server",new Object[]{encodedPvk});
        agentServer.start();


    }
}
