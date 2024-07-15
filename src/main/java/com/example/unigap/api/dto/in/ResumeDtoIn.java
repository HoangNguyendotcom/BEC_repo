package com.example.unigap.api.dto.in;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ResumeDtoIn {
  @NotNull
  private Integer seekerId;
  private String careerObj;
  private String title;
  private Integer salary;
  @NotEmpty
  private List<Integer> fieldIds;
  @NotEmpty
  private List<Integer> provinceIds;
}
