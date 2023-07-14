package com.example.version.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
@DynamicInsert
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) // 시간 자동으로 수정해주는거 같음... 찾아보기
@Table(name = "versions")
@Entity
public class Version {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idx", updatable = false)
  private Long idx;

  @Column(name="service_name", nullable = false)
  private String service_name;

  @Column(name = "version", nullable = false)
  private String version;

  @Column(name = "os", nullable = false)
  private String os;

  @Column(name = "update_type", nullable = false)
  private Boolean update_type;

  @Column(name = "message")
  private String message;

  @ColumnDefault("'com.test.package'")
  private String package_name;

  //@Column(name = "flag" )
  @ColumnDefault("'N'")
  private String flag;

  // regedate
  @CreatedDate
  @Column(name = "regdate_at")
  private LocalDateTime regdate;//생성 시간

  @Builder
  public Version(String service_name, String version, String os, Boolean update_type,
                 String message) {
    // Id는 AutoIncrease이고 시각도 어노테이션 자동 생성 줫으니 안받음
    this.service_name = service_name;
    this.version = version;
    this.os = os;
    this.update_type = update_type;
    this.message = message;
  }

  public void update(String service_name, String os, String version, String message){
    this.service_name = service_name;
    this.os = os;
    this.version = version;
    this.message = message;
  }

  public void setFlag(String flag){
    this.flag = flag;
  }
  public String getFlag(){

    return flag;
  }
}
