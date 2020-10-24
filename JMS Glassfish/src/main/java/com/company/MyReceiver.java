package com.company;

import javax.jms.*;
import java.util.Map;

public class MyReceiver {
    
    public static void main(String[] args) {
        try {
            Map<String, Object> jmsMap = JMSConnection
                    .startJmsConnection("myTopicConnectionFactory", "myTopic");
            
            TopicSession session = (TopicSession) jmsMap.get("topicSession");
            Topic topic = (Topic) jmsMap.get("topic");
    
            TopicSubscriber subscriber = session.createSubscriber(topic);
            MyListener listener = new MyListener();
            
            subscriber.setMessageListener(listener);
    
            System.out.println("Subscriber1 is ready, waiting for messages...");
            System.out.println("Press Ctrl+c to shut down...");
            
            while (true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
