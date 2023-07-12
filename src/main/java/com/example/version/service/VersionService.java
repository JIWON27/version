package com.example.version.service;

import com.example.version.domain.Version;
import com.example.version.web.dto.AddVersionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VersionService {
  private final VersionService versionService;

  // C
  public Version save(AddVersionRequestDto requestDto){
    return versionService.save(requestDto);
  }
  // R - 단건 조회
  public Version findById(Long id){
    return versionService.findById(id);
       // .orElseThrow( () -> new IllegalArgumentException("Service not Exist! " + id));
  }
  // R - 전체 조회

  // U

  // D
}
