package com.example.version.service;

import com.example.version.domain.Version;
import com.example.version.repository.VersionRepository;
import com.example.version.web.dto.AddVersionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VersionService {
  private final VersionRepository versionRepository;
  // C
  public Version save(AddVersionRequestDto requestDto){
    return versionRepository.save(requestDto.toEntity());
  }
  // R - 단건 조회
  public Version findById(Long idx){
    return versionRepository.findById(idx)
        .orElseThrow( () -> new IllegalArgumentException("Service not Exist! " + idx));
  }
  // R - 전체 조회
  public List<Version> findAll(){
    return versionRepository.findAll();
  }
  // U

  // D
}
