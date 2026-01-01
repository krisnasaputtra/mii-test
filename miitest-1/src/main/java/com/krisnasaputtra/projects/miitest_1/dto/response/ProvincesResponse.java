package com.krisnasaputtra.projects.miitest_1.dto.response;

import java.util.List;

import com.krisnasaputtra.projects.miitest_1.model.Province;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvincesResponse {
  private int status;
  private String message;
  private List<Province> result;
}
