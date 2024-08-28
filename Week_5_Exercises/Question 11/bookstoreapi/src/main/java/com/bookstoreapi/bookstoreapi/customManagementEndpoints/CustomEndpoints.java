package com.bookstoreapi.bookstoreapi.customManagementEndpoints;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "customfeature")
public class CustomEndpoints {
    private final Map<String, String> features = new HashMap<>();

    @ReadOperation
    public Map<String,String> customfeature(){
        features.put("customFeatures", "example-custom endpoint works!");
        return features;
    }
}
