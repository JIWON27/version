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

@RequiredArgsConstructor
@RestController
public class TestController {
  private final VersionService versionService;

  @RequestMapping("/api/test")
  public String test() {

    return "Hello World";
  }

  @GetMapping("api/latest/versions")
  public ResponseEntity<VersionResponseDto> findVersion(@PathVariable Long idx) {
    return ResponseEntity.ok()
        .body(new VersionResponseDto(versionService.findById(idx)));
  }

  @PostMapping("/api/latest/versions")
  public ResponseEntity<Version> addAVersion(@RequestBody AddVersionRequestDto requestDto) {
    // RequestBody는 서블릿으로부터 담겨오는 매핑객체임을 알게해주는 어노테이션
    // create는 status붙임
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(versionService.save(requestDto));
  }
}