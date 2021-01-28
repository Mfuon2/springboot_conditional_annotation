package com.example.conditional_app;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CheckConfiguration implements Condition {

    String host = "3.250.203.227";
    int port = 9092;

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        if (checkAddressReachable(host, port)) {
            try {
                return checkIfTopicExists(host, port);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return false;
        }
        return false;
    }

    private boolean checkAddressReachable(String address, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(address, port), 2000);
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private boolean checkIfTopicExists(String host, int port) throws InterruptedException, ExecutionException {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, host + ":" + port);
        try (AdminClient client = AdminClient.create(props)) {
            ListTopicsOptions options = new ListTopicsOptions();
            ListTopicsResult topics = client.listTopics(options);
            Set<String> currentTopicList = topics.names().get();
            System.out.println("=========== > HOST TOPICS < ============  " + currentTopicList.toString());
            Set<String> filteredResults = currentTopicList.stream()
                    .filter(c -> c.matches("claims"))
                    .collect(Collectors.toSet());
            return !filteredResults.isEmpty();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


///head of digital