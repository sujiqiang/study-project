package com.sjq.study.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author java coder
 * @date 2022/3/31 19:44
 * @desc: 配置类
 */
@EnableRabbit
@Configuration
@Slf4j
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.host}")
    private String addresses;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String vhost;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setAddresses(addresses);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(vhost);
        factory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.SIMPLE);
        return factory;
    }

    /**
     * 如果rabbitTemplate设置为单例bean，则所有的rabbitTemplate实际的ConfirmCallback为最后一次申明的ConfirmCallback。
     * 如果不同的生产者需要对应不同的ConfirmCallback，则 创建不同的 rabbitTemplate
     *
     * @return
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(this.connectionFactory());
        rabbitTemplate.setMessageConverter(this.createMessageConverter());

        // 配置文件中 publisher-returns
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.info("消息发送后返回字段：{},{},{},{},{}", correlationId, replyCode, replyText, exchange, routingKey);
        });

        // 表示需要在生产者发送后的回调. 配置文件中 publisher-confirm-type
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息发送成功：{}", correlationData == null ? "" : correlationData.getId());
            } else {
                log.error("消息发送失败：{}", cause);
            }
        });
        return rabbitTemplate;
    }


    /**
     * 将Java对象转换为RabbitMQ的消息.
     * 默认情况下，Spring Boot使用SimpleMessageConverter，只能发送String和byte[]类型的消息，不太方便.
     *
     * @return
     */
    @Bean
    MessageConverter createMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
