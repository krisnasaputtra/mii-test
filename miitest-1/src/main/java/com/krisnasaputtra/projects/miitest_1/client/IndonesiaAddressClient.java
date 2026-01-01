package com.krisnasaputtra.projects.miitest_1.client;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.krisnasaputtra.projects.miitest_1.dto.response.CitiesResponse;
import com.krisnasaputtra.projects.miitest_1.dto.response.ProvincesResponse;
import com.krisnasaputtra.projects.miitest_1.exception.ExternalApiException;
import com.krisnasaputtra.projects.miitest_1.model.City;
import com.krisnasaputtra.projects.miitest_1.model.Province;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class IndonesiaAddressClient {

  private final RestClient restClient;

  /* Fetch all Provinces */
  public List<Province> getAllProvinces() {
    try {
      ProvincesResponse response = restClient.get()
          .uri("/provinsi/get/")
          .retrieve()
          .body(ProvincesResponse.class);

      if (response.getStatus() != 200) {
        log.error("Error fetching provinces: {}", response.getMessage());
        throw new ExternalApiException("Error fetching provinces");
      }

      if (response.getResult() == null || response.getResult().isEmpty()) {
        throw new ExternalApiException("No provinces found");
      }

      return response.getResult();
    } catch (ExternalApiException e) {
      log.error("Error fetching provinces", e);
      throw new ExternalApiException("Error fetching provinces", e);
    }
  }

  /* Fetch all Cities by Province ID */
  public List<City> getCities(String provinceId) {
    log.info("Fetching cities for province ID: {}", provinceId);
    try {
      CitiesResponse response = restClient.get()
          .uri(uriBuilder -> uriBuilder.path("/kabkota/get/")
              .queryParam("d_provinsi_id", provinceId)
              .build())
          .retrieve()
          .body(CitiesResponse.class);

      if (response.getStatus() != 200) {
        log.error("Error fetching cities: {}", response.getMessage());
        throw new ExternalApiException("Error fetching cities");
      }

      if (response.getResult() == null || response.getResult().isEmpty()) {
        throw new ExternalApiException("No cities found");
      }

      return response.getResult();
    } catch (ExternalApiException ex) {
      log.error("Error fetching cities", ex);
      throw new ExternalApiException("Error fetching cities");
    }
  }

}
