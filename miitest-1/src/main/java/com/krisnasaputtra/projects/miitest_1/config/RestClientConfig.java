package com.krisnasaputtra.projects.miitest_1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import com.krisnasaputtra.projects.miitest_1.exception.ExternalApiException;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RestClientConfig {

  @Value("${indonesian-address.api.base-url}")
  private String baseUrl;

  @Bean
  RestClient restClient() {
    return RestClient.builder()
        .baseUrl(baseUrl)
        .defaultStatusHandler(
            HttpStatusCode::is4xxClientError,
            (request, response) -> {
              log.error("Client error: " + response.getStatusText());
              throw new ExternalApiException("Client error: " + response.getStatusText());
            })
        .defaultStatusHandler(
            HttpStatusCode::is5xxServerError,
            (request, response) -> {
              log.error("Server error: " + response.getStatusText());
              throw new ExternalApiException("Server error: " + response.getStatusText());
            })
        .build();
  }
}