package be.gestion.naturopathie.dimitri;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NaturopathieApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(NaturopathieApplication.class);
        Map<String, Object> props = new HashMap<>();

        String port = System.getenv("PORT");
        System.out.println("✅ PORT ENV: " + port); // debug log

        if (port != null) {
            props.put("server.port", port);
        } else {
            props.put("server.port", 8080); // fallback
        }

        app.setDefaultProperties(props);
        app.run(args);
    }
}
