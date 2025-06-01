package be.gestion.naturopathie.dimitri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class NaturopathieApplication implements CommandLineRunner {

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(NaturopathieApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String port = env.getProperty("local.server.port");
        System.out.println("Server is running on port: " + port);
    }
}
