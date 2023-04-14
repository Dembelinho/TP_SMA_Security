import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;

public class MainContainer {
    public static void main(String[] args) throws ControllerException {
        // Création d'une instance de Runtime pour gérer les agents JADE
        Runtime instance = Runtime.instance();

        // Création d'un profil d'agent
        ProfileImpl profile=new ProfileImpl();

        // Paramètre pour afficher l'interface graphique de JADE
        profile.setParameter(ProfileImpl.GUI,"true");

        // Création du conteneur d'agents principal
        AgentContainer agentContainer=instance.createMainContainer(profile);
        // Démarrage du conteneur d'agents
        agentContainer.start();
    }
}
