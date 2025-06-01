package be.gestion.naturopathie.dimitri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class NaturopathieApplication {

    public static void main(String[] args) {
        // Démarrage de l'application et récupération du contexte
        ConfigurableApplicationContext context = SpringApplication.run(NaturopathieApplication.class, args);

        // Récupération de l'environnement pour afficher le port utilisé
        Environment env = context.getEnvironment();
        System.out.println("Application started on port: " + env.getProperty("local.server.port"));
    }
}
