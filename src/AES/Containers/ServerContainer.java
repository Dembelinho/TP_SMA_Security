package AES.Containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.security.NoSuchAlgorithmException;

public class ServerContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime instance = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer=instance.createAgentContainer(profile);

        String secret="password12345678"; // Clé secrète qui sera passer en argument

        AgentController agentServer=agentContainer.createNewAgent("server","AES.agents.Server",new Object[]{secret});
        agentServer.start();

    }
}
