package com.krisnasaputtra.projects.miitest_1.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krisnasaputtra.projects.miitest_1.dto.request.CheckAddressRequest;
import com.krisnasaputtra.projects.miitest_1.model.City;
import com.krisnasaputtra.projects.miitest_1.model.Province;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** Validates Indonesian addresses by verifying province and city existence. */
@Service
@RequiredArgsConstructor
@Slf4j
public class IndonesianAddressService {
  private final ProvinceService provinceService;
  private final CityService cityService;

  public boolean checkAddress(CheckAddressRequest request) {
    Province province = provinceService.getProvinceById(request.getProvinsi());

    Optional<City> city = cityService.getCityByNameCity(province.getId(), request.getKabkota());

    return city.isPresent();
  }
}
