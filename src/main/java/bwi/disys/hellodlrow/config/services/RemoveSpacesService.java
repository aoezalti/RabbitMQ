package bwi.disys.hellodlrow.config.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RemoveSpacesService {

    private final RabbitTemplate rabbitTemplate;

    public RemoveSpacesService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @RabbitListener(queues = "remove-spaces-queue")
    public void receive(String message) {
        String noSpaces = message.replace(" ", "");
        System.out.println("Spaces removed: " + noSpaces);
        rabbitTemplate.convertAndSend("text-exchange", "final-output.routing", noSpaces);
    }
}