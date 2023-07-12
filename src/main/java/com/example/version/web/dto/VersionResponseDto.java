package com.example.version.web.dto;

import com.example.version.domain.Version;

import java.util.Optional;

public class VersionResponseDto {

  private String service_name;
  private Float version;
  private String os;
  private boolean update_type;
  private String message;
  private String package_name;

  public VersionResponseDto(Version version) {
    this.service_name = version.getService_name();
    this.version = version.getVersion();
    this.os = version.getOs();
    this.update_type = version.getUpdate_type();
    this.message = version.getMessage();
    this.package_name = version.getPackage_name();
  }
}
