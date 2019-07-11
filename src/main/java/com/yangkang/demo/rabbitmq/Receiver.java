package com.yangkang.demo.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 监听的业务类，实现接口MessageListener
 * Created by hzlbo on 2016/7/1.
 */
@Component
@RabbitListener(queues="hello_test")
public class Receiver {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String str){
        logger.info("消费者收到一条信息:  " + str);
    }
}
