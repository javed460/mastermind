package com.mastermind;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class RandomGeneratorAPI {

    public static String[] getRandomNumberCombination() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.random.org/integers/?num=4&min=0&max=7&col=1&base=10&format=plain&rnd=new"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String randomNumbers = response.body();
                return randomNumbers.split("\n");
            } else {
                throw new RuntimeException("Failed to get random numbers - status code" + response.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get random numbers" + e.getMessage());
        }
    }
}
