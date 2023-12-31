package com.challenge.courierserv;

import lombok.extern.log4j.Log4j2;
import org.aopalliance.aop.Advice;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;


@Configuration
@Log4j2
public class CourierservConsumerConfig {

    @Bean
    public MessageRecoverer recovererToDeadLetterQueue(RabbitTemplate template){
        return new RepublishMessageRecoverer(template,"dlx");
    }

    @Bean
    public RetryOperationsInterceptor retryInterceptor(MessageRecoverer recoverer) {
        return RetryInterceptorBuilder.stateless()
                .backOffOptions(1000, 3.0, 10000)
                .maxAttempts(5)
                //.recoverer((message, throwable) -> {log.error("Retries exhausted: " + new String(message.getBody()));})
                .recoverer(recoverer)
                .build();


    }


    @Bean
    public SimpleRabbitListenerContainerFactory retryContainerFactory(
            ConnectionFactory connectionFactory, RetryOperationsInterceptor retryInterceptor) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter()); //Allow conversion from JSON messages to Java Objects
    factory.setAcknowledgeMode(AcknowledgeMode.AUTO);

        Advice[] adviceChain = { retryInterceptor };
        factory.setAdviceChain(adviceChain);

        return factory;
    }
}
