package com.example.unigap.api.dto.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SeekerDtoIn {
  @NotBlank
  private String name;

  @NotEmpty
  private String birthday;

  private String address;

  private Integer province;
}
