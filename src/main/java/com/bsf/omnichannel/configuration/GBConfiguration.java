package com.bsf.omnichannel.configuration;


import growthbook.sdk.java.GBContext;
import growthbook.sdk.java.GrowthBook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Configuration
public class GBConfiguration {
    @Value("${growthbook.endpoint}")
    private String gbEndpoint;

    private String getFeatures() throws URISyntaxException, IOException, InterruptedException {
        final URI featuresEndpoint = new URI(gbEndpoint);
        final HttpRequest request = HttpRequest.newBuilder().uri(featuresEndpoint).GET().build();
        final HttpResponse<String> response = HttpClient.newBuilder().build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Cannot get features from GrowthBook server");
        }

        final var featureFlag = new JSONObject(response.body()).get("features").toString();
        System.out.println("feature Flags in GB is " + featureFlag);
        return featureFlag;
    }


    @Bean
    public GrowthBook growthBook() throws URISyntaxException, IOException, InterruptedException {
        final var gbContext = GBContext.builder()
                .featuresJson(getFeatures())
                .build();

        return new GrowthBook(gbContext);
    }
}
