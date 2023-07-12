package com.example.version.reopsitory;

import com.example.version.domain.Version;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionResitory extends JpaRepository<Version, Long> {

}
