package com.yangkang.demo.rabbitmq;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@EnableRabbit
@Configuration
public class AmqpConfig {

    /**
     * RabbitMQ的配置信息
     */
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;


    /**
     * 建立一个连接容器，类型数据库的连接池
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        // 确认机制
        connectionFactory.setPublisherConfirms(true);
        //发布确认，template要求CachingConnectionFactory的publisherConfirms属性设置为true
        return connectionFactory;
    }

    /**
     * RabbitMQ的使用入口
     * 必须是prototype类型
     * @return
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(this.connectionFactory());
        return template;
    }

    /**
     * 配置Queue
     * @return
     */
    @Bean
    public Queue helloQueue(){
        return new Queue("hello_test");
    }
}
