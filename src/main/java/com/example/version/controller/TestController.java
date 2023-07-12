package com.example.version.controller;

import com.example.version.reopsitory.VersionResitory;
import com.example.version.web.dto.VersionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {
  private final VersionResitory versionResitory;
  @RequestMapping("/api/test")
  public String test(){

    return "Hello World";
  }

  @GetMapping("api/latest/versions")
  public ResponseEntity<VersionResponseDto> findArticle(@PathVariable Long id){
    return  ResponseEntity.ok()
        .body(new VersionResponseDto(versionResitory.findById(id)));
  }
}
