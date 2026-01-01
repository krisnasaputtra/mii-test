package com.krisnasaputtra.projects.miitest_1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CheckAddressRequest {

  @NotBlank(message = "Provinsi is required")
  private String provinsi;

  @NotBlank(message = "Kabupaten/Kota is required")
  private String kabkota;
}
