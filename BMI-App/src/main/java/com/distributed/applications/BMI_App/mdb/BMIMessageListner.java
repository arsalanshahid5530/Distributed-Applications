package com.distributed.applications.BMI_App.mdb;

import com.distributed.applications.BMI_App.BMICalculator;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/BMIQueue"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class BMIMessageListner implements MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String msg = textMessage.getText();
            addToCsv(msg);
        } catch (JMSException ex) {
            Logger.getLogger(BMICalculator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BMICalculator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addToCsv(String msg) throws IOException {

        HashMap<String, String> map = (HashMap<String, String>) Arrays.asList(msg.split(","))
                .stream()
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(e -> e[0], e -> e[1]));

        File file = new File("D:\\dev\\Projects\\Distributed Application\\csv\\records_mdb.csv");
        FileWriter fileWriter;
        StringBuilder sb = new StringBuilder();

        if (!file.exists()) {
            sb.append("Name");
            sb.append(',');
            sb.append("Height");
            sb.append(',');
            sb.append("Weight");
            sb.append(',');
            sb.append("BMI");
            sb.append('\n');
        }

        sb.append(map.get("name"));
        sb.append(',');
        sb.append(map.get("height"));
        sb.append(',');
        sb.append(map.get("weight"));
        sb.append(',');
        sb.append(map.get("bmi"));
        sb.append('\n');

        fileWriter = new FileWriter(file, true);
        fileWriter.write(sb.toString());
        fileWriter.close();
    }
}
