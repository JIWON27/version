package com.example.version.controller;

import com.example.version.domain.Version;
import com.example.version.service.VersionService;
import com.example.version.web.dto.AddVersionRequestDto;
import com.example.version.web.dto.UpdateVersionRequestDto;
import com.example.version.web.dto.VersionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin //디폴트값으로 다 됨
@RequiredArgsConstructor
@RestController
public class TestController {
  private final VersionService versionService;

  // Test가 약간 단건조회 느낌
  @GetMapping("api/latest/versions/{idx}")
  public ResponseEntity<?> findVersion(@PathVariable Long idx) {
    Version version = versionService.findById(idx);
    if (version.getFlag() == "N") {
      return ResponseEntity.ok()
          .body(new VersionResponseDto(versionService.findById(idx)));
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미 삭제된 버전입니다.");
  }

  // 기본 화면에 출력할때 사용하는거 같음.
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

  //프론트에서 삭제 버튼 클릭시 Y값을 받을 수 있나?
  @DeleteMapping("/api/latest/versions/{idx}")
  public ResponseEntity<Version> deleteVersion(@PathVariable Long idx){
    Version version = versionService.delete(idx);
    return ResponseEntity.ok().body(version);
  }

}