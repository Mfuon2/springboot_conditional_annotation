package com.example.conditional_app;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CheckConfiguration implements Condition {

    String host   = "3.250.203.227";
    int port   = 22;

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return checkAddressReachable(host, port);
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
}
