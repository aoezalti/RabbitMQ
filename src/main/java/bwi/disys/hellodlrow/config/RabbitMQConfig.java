package bwi.disys.hellodlrow.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue textReverseQueue() {
        return new Queue("text-reverse-queue", true);
    }

    @Bean
    public Queue deleteSecondCharQueue() {
        return new Queue("delete-second-char-queue", true);
    }

    @Bean
    public Queue removeSpacesQueue() {
        return new Queue("remove-spaces-queue", true);
    }
    @Bean
    public Queue finalOutputQueue() {
        return new Queue("final-output-queue", true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("text-exchange");
    }

    @Bean
    public Binding bindingTextReverse(Queue textReverseQueue, TopicExchange exchange) {
        return BindingBuilder.bind(textReverseQueue).to(exchange).with("text.reverse.routing");
    }

    @Bean
    public Binding bindingDeleteSecondChar(Queue deleteSecondCharQueue, TopicExchange exchange) {
        return BindingBuilder.bind(deleteSecondCharQueue).to(exchange).with("second-char.routing");
    }

    @Bean
    public Binding bindingRemoveSpaces(Queue removeSpacesQueue, TopicExchange exchange) {
        return BindingBuilder.bind(removeSpacesQueue).to(exchange).with("remove-spaces.routing");
    }

    @Bean
    public Binding bindingFinalOutput(Queue finalOutputQueue, TopicExchange exchange) {
        return BindingBuilder.bind(finalOutputQueue).to(exchange).with("final-output.routing");
    }

}
