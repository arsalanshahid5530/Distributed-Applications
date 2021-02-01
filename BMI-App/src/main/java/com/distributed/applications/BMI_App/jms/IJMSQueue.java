package com.distributed.applications.BMI_App.jms;

import javax.ejb.Local;

@Local
public interface IJMSQueue {
    void sendMessage(String messageData);
}
