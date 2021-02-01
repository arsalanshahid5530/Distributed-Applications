package com.distributed.applications.BMI_App.jms;

import javax.ejb.Singleton;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;


@Singleton
public class JMSQueueImpl {


    public final static String JNDI_FACTORY = "org.jboss.ejb.client.naming";
    private final static String CF_name = "java:/ConnectionFactory";
    private final static String Q_name = "java:/jms/queue/BMIQueue";

    public void sendMessage(String messageData) {
        try {
            Properties properties = new Properties();
            properties.put(Context.URL_PKG_PREFIXES, JNDI_FACTORY);

            InitialContext ctx = new InitialContext(properties);
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) ctx.lookup(CF_name);

            QueueConnection connection = connectionFactory.createQueueConnection();
            connection.start();

            QueueSession queueSession = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);


            Queue queue = (Queue) ctx.lookup(Q_name);

            System.out.println("The queue name is " + queue.getQueueName());

            QueueSender sender = queueSession.createSender(queue);
            TextMessage msg = queueSession.createTextMessage(messageData);
            sender.send(msg);

            System.out.println("Message successfully sent to a BMIQueue.");
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
