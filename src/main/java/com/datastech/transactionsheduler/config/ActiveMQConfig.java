package com.datastech.transactionsheduler.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.jms.ConnectionFactory;

/**
 * Created by ivan salivon on 11.12.17.
 */
@Configuration
public class ActiveMQConfig {

    @Value("${activemq.host}")
    private String host;
    @Value("${activemq.port}")
    private int port;
    @Value("${activemq.poll.max.connection}")
    private int maxConnections;
    @Value("${activemq.concurent.consumer.count}")
    private int concurentConsumerCount;

    @Autowired
    private ActiveMQConnectionFactory connectionFactory;
    @Autowired
    private PooledConnectionFactory polConnectionFactory;
    @Autowired
    private JmsConfiguration jmsConfiguration;

    @Bean("activemq")
    public JmsComponent getJmsComponent() {
        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConfiguration(jmsConfiguration);
        return activeMQComponent;
    }

    @Bean("jmsConnectionFactory")
    public ActiveMQConnectionFactory getActiveMQConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://"+host+":"+port);
        return connectionFactory;
    }

    @Bean("pooledConnectionFactory")
    public PooledConnectionFactory getPooledConnectionFactory() {
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setMaxConnections(maxConnections);
        pooledConnectionFactory.setConnectionFactory(connectionFactory);
        return pooledConnectionFactory;
    }

    @Bean("jmsConfig")
    public JmsConfiguration getJmsConfiguration() {
        JmsConfiguration configuration = new JmsConfiguration();
        configuration.setConnectionFactory(polConnectionFactory);
        configuration.setConcurrentConsumers(concurentConsumerCount);
        return configuration;
    }

}
