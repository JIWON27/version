package com.example.version.controller;

import com.example.version.domain.Version;
import com.example.version.service.VersionService;
import com.example.version.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin //디폴트값으로 다 됨
@RequiredArgsConstructor
@RestController
public class TestController {
  private final VersionService versionService;

  // Test가 약간 단건조회 느낌
  @GetMapping("/api/latest/versions/{idx}")
  public ResponseEntity<?> findVersion(@PathVariable Long idx) {
    Version version = versionService.findById(idx);
    if (version.getFlag().equals("N")) {
      return ResponseEntity.ok()
          .body(new VersionResponseDto(versionService.findById(idx)));
    }
    return ResponseEntity.ok().body("해당 버전은 이미 삭제된 버전입니다.");
  }

  // 디폴트 화면.
  @GetMapping("/api/latest/versions")
  public ResponseEntity<?> findAllVersions() {
    List<VersionResponseDto> versions = versionService.findAll()
        .stream()
        .filter(version -> version.getFlag().equals("N"))
        .map(VersionResponseDto::new)
        .collect(Collectors.toList());
    if (versions.isEmpty()){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회할 버전이 없습니다.");
    }
    return ResponseEntity.ok().body(versions);
  }

  // 특정 서비스명에 대한 버전 화면
  @GetMapping("/api/latest/versions/services/{service}")
  public ResponseEntity<?> findByServiceVersions(@PathVariable String service) {
    List<VersionResponseDto> versions = versionService.findAll()
        .stream()
        .filter(version -> version.getFlag().equals("N"))
        .filter(version -> version.getService_name().equals(service))
        .map(VersionResponseDto::new)
        .collect(Collectors.toList());
    if (versions.isEmpty()){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회할 버전이 없습니다.");
    }
    return ResponseEntity.ok().body(versions);
  }

  @PostMapping("/api/latest/versions")
  public ResponseEntity<Version> addAVersion(@RequestBody AddVersionRequestDto requestDto) {

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(versionService.save(requestDto));
  }

  @PutMapping("/api/latest/versions/{idx}")
  public ResponseEntity<Version> updateVersion(@PathVariable Long idx,
                                               @RequestBody UpdateVersionRequestDto requestDto){
    Version updatedVersion = versionService.update(idx, requestDto);

    return ResponseEntity.ok().body(updatedVersion);
  }

  @DeleteMapping("/api/latest/versions/{idx}")
  public ResponseEntity<Version> deleteVersion(@PathVariable Long idx){
    Version version = versionService.delete(idx);
    return ResponseEntity.ok().body(version);
  }

  //paging
  @GetMapping("/api/latest/versions/list")
  public ResponseEntity<?> index(Model model, @PageableDefault(sort = "idx", direction = Sort.Direction.ASC)
                                 Pageable pageable){
    List<VersionResponseDto> versions = versionService.pageList(pageable)
        .stream()
        .filter(version -> version.getFlag().equals("N"))
        .map(VersionResponseDto::new)
        .collect(Collectors.toList());
    if (versions.isEmpty()){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회할 버전이 없습니다.");
    }
    return ResponseEntity.ok().body(new PageImpl<>(versions));
  }
}
