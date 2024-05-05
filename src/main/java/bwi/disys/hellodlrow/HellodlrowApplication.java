package bwi.disys.hellodlrow;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Scanner;

@SpringBootApplication
public class HellodlrowApplication implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;

    public HellodlrowApplication(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(HellodlrowApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter text (type exit to quit):");
                String input = scanner.nextLine().trim();

                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }

                // Sending the input to RabbitMQ
                rabbitTemplate.convertAndSend("text-exchange", "text.input", input);
            }
        }
    }
    @RabbitListener(queues = "final-output-queue")
    public void receive(String message) {
        System.out.println("Final output: " + message);
    }
}
