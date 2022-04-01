package com.sjq.study.rabbitmq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;

/**
 * @author java coder
 * @date 2022/3/31 16:19
 * @desc: description
 */
@Component
public class MessageSender {

    private final static String ROUTING_KEY_LOGIN_FAILED = "login_failed";
    private final static String ROUTING_KEY_REGISTRATION_FAILED = "registration_failed";

    private final static String EXCHANGE_LOGIN = "sjq_login";
    private final static String EXCHANGE_REGISTRATION = "sjq_registration";

    private final static String ROUTING_KEY_CALCULATE = "calculate";
    private final static String EXCHANGE_CALCULATE = "oracle2pg";

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendLoginMqMessage(MqMessageDto message) {
        String routingKey = message.isSuccess() ? null : ROUTING_KEY_LOGIN_FAILED;
        rabbitTemplate.convertAndSend(EXCHANGE_LOGIN, routingKey, message);
    }

    public void sendRegistrationMqMessage(MqMessageDto message) {
        String routingKey = message.isSuccess() ? null : ROUTING_KEY_REGISTRATION_FAILED;
        rabbitTemplate.convertAndSend(EXCHANGE_REGISTRATION, routingKey, message);
    }

    public void sendCalculateMqMessage(MqMessageDto message) {
        rabbitTemplate.convertAndSend(EXCHANGE_CALCULATE, ROUTING_KEY_CALCULATE, message, new CorrelationData(UUID.randomUUID().toString()));
    }

    /**
     * 测试 在没有exchange和queue时发送消息，自动创建 exchange和queue
     * @param message
     */
    public void sendMqMessageNoExchangeAndQueue(MqMessageDto message) {
        rabbitTemplate.convertAndSend("auto_create_exchange", "auto_routing_key", message, new CorrelationData(UUID.randomUUID().toString()));
    }

}
