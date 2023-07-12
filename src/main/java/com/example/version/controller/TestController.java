package com.example.version.controller;

import com.example.version.domain.Version;
import com.example.version.repository.VersionRepository;
import com.example.version.service.VersionService;
import com.example.version.web.dto.AddVersionRequestDto;
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

  @RequestMapping("/api/test")
  public String test() {

    return "Hello World";
  }

  @GetMapping("api/latest/versions/{idx}")
  public ResponseEntity<VersionResponseDto> findVersion(@PathVariable Long idx) {
    return ResponseEntity.ok()
        .body(new VersionResponseDto(versionService.findById(idx)));
  }
  @GetMapping("/api/latest/versions")
  public ResponseEntity<List<VersionResponseDto>> findAllVersions() {
    List<VersionResponseDto> versions = versionService.findAll()
        .stream()
        .map(VersionResponseDto::new)
        .collect(Collectors.toList());

    return ResponseEntity.ok().body(versions);
  }
  @PostMapping("/api/latest/versions")
  public ResponseEntity<Version> addAVersion(@RequestBody AddVersionRequestDto requestDto) {

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(versionService.save(requestDto));
  }

  @DeleteMapping("/api/latest/versions/{idx}")
  public ResponseEntity<Void> deleteVersion(@PathVariable Long idx){
    // 원래는 <id>를 적는걸 추천하는데 void형식도 있다는 것을 알려주기 위해서 사용
    versionService.delete(idx);
    return ResponseEntity.ok().build();
  }

}