package com.company;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class MySender {
    
    public static void main(String[] args) {
        try {
            Map<String, Object> jmsMap = JMSConnection
                    .startJmsConnection("myTopicConnectionFactory", "myTopic");
            
            TopicConnection connection = (TopicConnection) jmsMap.get("topicConnection");
            TopicSession session = (TopicSession) jmsMap.get("topicSession");
            Topic topic = (Topic) jmsMap.get("topic");
            
            TopicPublisher publisher = session.createPublisher(topic);
            TextMessage message = session.createTextMessage();
    
            BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
            
            while (true) {
                System.out.println("Enter message, 'end' to terminate: ");
                String s = b.readLine();
                
                if (s.equals("end")) {
                    break;
                }
                
                message.setText(s);
                publisher.publish(message);
    
                System.out.println("Message successfully sent");
            }
            
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
