package com.example.version.service;

import com.example.version.domain.Version;
import com.example.version.repository.VersionRepository;
import com.example.version.web.dto.AddVersionRequestDto;
import com.example.version.web.dto.UpdateVersionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        .orElseThrow( () -> new IllegalArgumentException("Version not Exist! " + idx));
  }
  // R - 전체 조회
  public List<Version> findAll(){

    return versionRepository.findAll();
  }
  // U
  @Transactional
  public Version update(Long idx, UpdateVersionRequestDto requestDto) {
    // step 1 . 기존 등록된 글 가져오기
    Version version = versionRepository.findById(idx)
        .orElseThrow(() -> new IllegalArgumentException("Version not exist! : " + idx));

    version.update(requestDto.getVersion(), requestDto.getMessage(), requestDto.getUpdate_type());
    return version;
  }
  // D
  @Transactional

  public Version delete(Long idx){
    Version version = versionRepository.findById(idx)
        .orElseThrow(() -> new IllegalArgumentException("Version not exist! : " + idx));

    version.setFlag("Y");
    return version;
  }
}
