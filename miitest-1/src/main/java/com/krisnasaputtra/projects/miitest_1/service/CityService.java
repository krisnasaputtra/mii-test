package com.krisnasaputtra.projects.miitest_1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krisnasaputtra.projects.miitest_1.client.IndonesiaAddressClient;
import com.krisnasaputtra.projects.miitest_1.exception.ResourceNotFoundException;
import com.krisnasaputtra.projects.miitest_1.model.City;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** Retrieves and searches Indonesian cities by province from external API. */
@Service
@RequiredArgsConstructor
@Slf4j
public class CityService {
  private final IndonesiaAddressClient client;

  public List<City> getCities(@NonNull String provinceId) {
    return client.getCities(provinceId);
  }

  public City getCityByName(@NonNull String provinceId, @NonNull String cityName) {
    List<City> cities = client.getCities(provinceId);

    return cities.stream()
        .filter(c -> c.getText().equalsIgnoreCase(cityName))
        .findFirst()
        .orElseThrow(() -> new ResourceNotFoundException("City not found"));
  }

  public boolean isCityExist(@NonNull String provinceId, @NonNull String cityName) {
    List<City> cities = client.getCities(provinceId);

    return cities.stream()
        .anyMatch(c -> c.getText().equalsIgnoreCase(cityName));
  }

}
