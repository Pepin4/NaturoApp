package be.gestion.naturopathie.dimitri;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NaturopathieApplication  {

	public static void main(String[] args) {
		SpringApplication.run(NaturopathieApplication .class, args);
		SpringApplication app = new SpringApplication(NaturopathieApplication.class);
        Map<String, Object> props = new HashMap<>();
        String port = System.getenv("PORT");
        if (port != null) {
            props.put("server.port", port);
        }
        app.setDefaultProperties(props);
        app.run(args);
	}

	
}
