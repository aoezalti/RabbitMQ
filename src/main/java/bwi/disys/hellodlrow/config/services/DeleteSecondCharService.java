package bwi.disys.hellodlrow.config.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeleteSecondCharService {

    private final RabbitTemplate rabbitTemplate;

    public DeleteSecondCharService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "delete-second-char-queue")
    public void receive(String message) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            if(message.charAt(i) != ' '){
                if (i % 2 == 0) result.append(message.charAt(i));
            }
            else {
                result.append(message.charAt(i));
            }

        }
        String processed = result.toString();
        System.out.println("Every second char removed: " + processed);
        rabbitTemplate.convertAndSend("text-exchange", "remove-spaces.routing", processed);
    }
}