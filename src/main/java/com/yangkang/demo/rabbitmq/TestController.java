package com.yangkang.demo.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by hzlbo on 2016/7/2.
 */
@RestController
public class TestController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String testSend() {
        String context = "hello world lmc " + new Date();
        System.out.println("提供者发送一条信息 : " + context);
        rabbitTemplate.convertAndSend("hello_test", context);
        return "ok";
    }
}
