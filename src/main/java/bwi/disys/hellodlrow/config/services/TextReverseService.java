package bwi.disys.hellodlrow.config.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class TextReverseService {

    private final RabbitTemplate rabbitTemplate;

    public TextReverseService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "text-reverse-queue")
    public void receive(String message) {
        String reversed = new StringBuilder(message).reverse().toString();
        System.out.println("Reversed: " + reversed);
        rabbitTemplate.convertAndSend("text-exchange", "second-char.routing", reversed);
    }
}