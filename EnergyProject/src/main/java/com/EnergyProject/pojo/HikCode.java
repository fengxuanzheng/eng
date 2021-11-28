package com.EnergyProject.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@TableName("hik_code")
public class HikCode implements Serializable {
  private Long index;
  private LocalDateTime codeTime;
  private Byte code1;
  private Byte code2;
  private Byte code3;
  private Byte code4;
  private Byte code5;
  private Byte code6;
  private Byte code7;
  private Byte code8;
  private Byte code9;
  private Byte code10;
  private Byte code11;
  private Byte code12;
  private Byte code13;
  private Byte code14;
  private Byte code15;
  private Byte code16;
  private Byte code17;
  private Byte code18;
  private String status;
}
