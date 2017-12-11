package com.datastech.transactionsheduler.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.jms.ConnectionFactory;

/**
 * Created by ivan salivon on 11.12.17.
 */
@Configuration
public class ActiveMQConfig {

    @Qualifier("jmsConnectionFactory")
    @Autowired
    private ActiveMQConnectionFactory connectionFactory;

    @Qualifier("pooledConnectionFactory")
    @Autowired
    private PooledConnectionFactory polConnectionFactory;
    @Autowired
    private JmsConfiguration jmsConfiguration;

    @Bean(name = "activemq")
    public JmsComponent getJmsComponent() {
        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConfiguration(jmsConfiguration);
        return activeMQComponent;
    }

    @Bean(name = "jmsConnectionFactory")
    public ActiveMQConnectionFactory getActiveMQConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        return connectionFactory;
    }

    @Bean("pooledConnectionFactory")
    public PooledConnectionFactory getPooledConnectionFactory() {
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setMaxConnections(8);
        pooledConnectionFactory.setConnectionFactory(connectionFactory);
        return pooledConnectionFactory;
    }

    @Bean("jmsConfig")
    public JmsConfiguration getJmsConfiguration() {
        JmsConfiguration configuration = new JmsConfiguration();
        configuration.setConnectionFactory(polConnectionFactory);
        configuration.setConcurrentConsumers(10);
        return configuration;
    }

}
