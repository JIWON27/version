package com.example.version.web.dto;

import com.example.version.domain.Version;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;
@Getter
public class VersionResponseDto {

  private Long idx;
  private String service_name;
  private String version;
  private String os;
  private boolean update_type;
  private String message;
  private String package_name;
  private LocalDateTime regdate;

  public VersionResponseDto(Version version) {
    this.idx = version.getIdx();
    this.service_name = version.getService_name();
    this.version = version.getVersion();
    this.os = version.getOs();
    this.update_type = version.getUpdate_type();
    this.message = version.getMessage();
    this.package_name = version.getPackage_name();
    this.regdate = version.getRegdate();
  }
}
