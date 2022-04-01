package com.sjq.study.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author java coder
 * @date 2022/3/31 17:02
 * @desc: description
 */
@Slf4j
@Component
public class MessageListener {

    private static final String QUEUE_MAIL = "q_mail";
    private static final String QUEUE_APP = "q_app";
    private static final String QUEUE_SMS = "q_sms";
    private static final String QUEUE_CALCULATE = "calculate_success";


    @RabbitListener(queues = QUEUE_MAIL)
    public void listenQueueMail(Message message, Channel channel) throws IOException {
        log.info("[{}]收到消息：{}", QUEUE_MAIL, message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


    @RabbitListener(queues = QUEUE_APP)
    public void listenQueueApp(Message message, Channel channel) throws IOException {
        log.info("[{}]收到消息：{}", QUEUE_APP, message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = QUEUE_SMS)
    public void listenQueueSms(Message message, Channel channel) throws IOException {
        log.info("[{}]收到消息：{}", QUEUE_SMS, message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


    @RabbitListener(queues = QUEUE_CALCULATE)
    public void listenCalculateMsg(Message message, Channel channel) throws IOException {
        log.info("[{}]收到消息：{}", QUEUE_CALCULATE, new String(message.getBody()));
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 消费应答。
        // 参数：deliveryTag: 该消息的index
        // 参数：multiple：是否批量.true: 将一次性 ack 所有小于 deliveryTag的消息。
        // 由于配置了 acknowledge-mode: manual，消费消息后需要手动确认，因此如果没有下面的代码手动确认，则消息一直在队列里
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 测试 未创建 exchange和queue时自动创建. ---> 项目启动后 新建了 exchange和queue，并且绑定了 routingKey
     * QueueBinding: 本次要监听的消息绑定
     * Queue： 队列，没有则创建
     * Exchange： 交换机，没有则创建
     */
    @RabbitListener(
            bindings =
            @QueueBinding(value = @Queue(value = "auto_create_queue", durable = "true"),
                    exchange = @Exchange(value = "auto_create_exchange", type = ExchangeTypes.DIRECT),
                    key = "auto_routing_key"
            )
    )
    public void listenMsgNoExchangeAndQueue(Message message, Channel channel) throws IOException {
        log.info("收到消息：{}", new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
