package com.sjq.study.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author java coder
 * @date 2022/3/31 17:11
 * @desc: description
 */
@SpringBootTest(classes = RabbitMqApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class RabbitMqApplicationTest {

    @Resource
    private MessageSender sender;

    @Test
    public void testSendMsg() {
        MqMessageDto msg1 = new MqMessageDto();
        msg1.setMsg("test1");
        msg1.setSuccess(true);
//        sender.sendLoginMqMessage(msg1);
//        log.info("发送消息 msg1---");
//
//        MqMessageDto msg2 = new MqMessageDto();
//        msg2.setMsg("test2");
//        msg2.setSuccess(false);
//        sender.sendRegistrationMqMessage(msg2);
//        log.info("发送消息 msg2---");

        sender.sendMqMessageNoExchangeAndQueue(msg1);
        log.info("发送消息成功 ---");
    }


    @Test
    public void testSendCalculateMsg() {

        List<MqMessageDto> list = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            MqMessageDto msg = new MqMessageDto();
            msg.setMsg("calculate msg:" + i);
            msg.setSuccess(true);
            list.add(msg);
        }

        for (int i = 0; i < list.size(); i++) {
            sender.sendCalculateMqMessage(list.get(i));
            log.info("发送消息 calculate msg:" + i);
        }

    }


}
