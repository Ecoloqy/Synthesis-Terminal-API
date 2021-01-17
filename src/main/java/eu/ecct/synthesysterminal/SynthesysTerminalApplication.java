package eu.ecct.synthesysterminal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SynthesysTerminalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SynthesysTerminalApplication.class, args);
    }

}
