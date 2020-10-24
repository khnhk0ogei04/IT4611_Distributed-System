package com.company;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.Map;

public class JMSConnection {
    
    public static Map<String, Object> startJmsConnection(String source, String destination)
            throws NamingException, JMSException {
        InitialContext ctx = new InitialContext();
        
        TopicConnectionFactory factory =
                (TopicConnectionFactory) ctx.lookup(source);
        TopicConnection connection = factory.createTopicConnection();
        connection.start();
    
        TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = (Topic) ctx.lookup(destination);
        
        Map<String, Object> map = new HashMap<>();
        map.put("topicConnection", connection);
        map.put("topicSession", session);
        map.put("topic", topic);
        
        return map;
    }
}
