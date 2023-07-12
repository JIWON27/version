package com.example.version.web.dto;

import com.example.version.domain.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor // 매개변수 있는 생성자
@Getter
public class AddVersionRequestDto {
   private String service_name;
   private Float version;
   private String os;
   private boolean update_type;
   private String message;
   private String package_name;

  public Version toEntity() {
    return Version.builder()
        .service_name(service_name)
        .version(version)
        .os(os)
        .update_type(update_type)
        .message(message)
        .package_name(package_name)
        .build();
  }
}
