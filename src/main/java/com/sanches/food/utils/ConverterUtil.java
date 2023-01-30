/*
* Copyright 2019 Hapvida
*************************************************************
*Name    : ConverterUtil.java
*Date    : 19/05/2019
*System  : Next Digital Platform
*************************************************************
*/
package com.sanches.food.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
@Slf4j
public class ConverterUtil {
  public static final String FORMATO_DATA = "dd/MM/yyyy HH:mm";
  public static Timestamp nowTime() {
    return new Timestamp(System.currentTimeMillis());
  }
}