package be.gestion.naturopathie.dimitri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class NaturopathieApplication {

    public static void main(String[] args) {
        // Récupère le port depuis la variable d'environnement, ou 8080 par défaut
        String port = System.getenv("PORT");
        if (port == null) {
            port = "8080";
        }

        SpringApplication app = new SpringApplication(NaturopathieApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", port));
        app.run(args);
    }
}
