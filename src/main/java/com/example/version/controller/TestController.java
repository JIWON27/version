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
}