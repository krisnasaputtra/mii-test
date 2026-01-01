package com.krisnasaputtra.projects.miitest_1.controller;

import org.springframework.web.bind.annotation.RestController;

import com.krisnasaputtra.projects.miitest_1.dto.request.CheckAddressRequest;
import com.krisnasaputtra.projects.miitest_1.dto.response.ApiResponse;
import com.krisnasaputtra.projects.miitest_1.service.IndonesianAddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/** REST endpoint for validating Indonesian addresses against provinces and cities. */
@RestController
@RequiredArgsConstructor
@Tag(name = "Indonesian Address API")
@Slf4j
public class IndonesianAddressController {
  private final IndonesianAddressService service;

  @PostMapping("/cekAlamat")
  @Operation(summary = "Check Indonesian Address", description = "Check Indonesian Address")
  public ResponseEntity<ApiResponse> checkAddress(@RequestBody CheckAddressRequest request) {
    boolean result = service.checkAddress(request);

    if (!result) {
      return ResponseEntity.ok(ApiResponse.builder().code("0").message("Tidak Sesuai").build());
    }

    return ResponseEntity.ok(ApiResponse.builder().code("1").message("Sesuai").build());
  }
}
