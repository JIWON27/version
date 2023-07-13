package com.example.version.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateVersionRequestDto {

  private String service_name;
  private String os;
  private Float version;
  private String message;

}