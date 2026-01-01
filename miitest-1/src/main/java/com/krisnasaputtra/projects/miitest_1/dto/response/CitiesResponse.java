package com.krisnasaputtra.projects.miitest_1.dto.response;

import java.util.List;

import com.krisnasaputtra.projects.miitest_1.model.City;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitiesResponse {
  private int status;
  private String message;
  private List<City> result;
}
