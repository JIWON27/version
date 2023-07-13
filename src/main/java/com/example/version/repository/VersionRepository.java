package com.example.version.repository;

import com.example.version.domain.Version;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VersionRepository extends JpaRepository<Version, Long> {

  Page<Version> findAll(Pageable pageable);
}
