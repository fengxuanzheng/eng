package com.EnergyProject.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@TableName("hik_code")
public class HikCodeString {
  private Long index;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "GTM+8")
  private LocalDateTime codeTime;
  private String code1;
  private String code2;
  private String code3;
  private String code4;
  private String code5;
  private String code6;
  private String code7;
  private String code8;
  private String code9;
  private String code10;
  private String code11;
  private String code12;
  private String code13;
  private String code14;
  private String code15;
  private String code16;
  private String code17;
  private String code18;
  private String status;
}
