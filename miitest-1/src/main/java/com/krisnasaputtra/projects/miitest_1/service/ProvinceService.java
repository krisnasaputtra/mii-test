package com.krisnasaputtra.projects.miitest_1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krisnasaputtra.projects.miitest_1.client.IndonesiaAddressClient;
import com.krisnasaputtra.projects.miitest_1.model.Province;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

/** Fetches and searches Indonesian provinces from external API. */
@Service
@RequiredArgsConstructor
public class ProvinceService {
  private final IndonesiaAddressClient client;

  public List<Province> getAllProvinces() {
    return client.getAllProvinces();
  }

  public Province getProvinceById(@Nonnull String provinceName) {
    List<Province> provinces = client.getAllProvinces();

    return provinces.stream()
        .filter(p -> p.getText().equalsIgnoreCase(provinceName))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Province not found"));
  }
}
