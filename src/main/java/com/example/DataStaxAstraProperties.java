package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = "datastax.astra")
public class DataStaxAstraProperties {

    private Resource secureConnectBundle;

    public Resource getSecureConnectBundle() {
        return secureConnectBundle;
    }

    public void setSecureConnectBundle(Resource secureConnectBundle) {
        this.secureConnectBundle = secureConnectBundle;
    }
}