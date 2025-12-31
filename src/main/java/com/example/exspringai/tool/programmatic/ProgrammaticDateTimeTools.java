package com.example.exspringai.tool.programmatic;

import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;

/*
* 프로그래밍 방식으로 도구 정의
* - 매개변수 및 반환 유형으로 Optional, 비동기 유형, 반응형 타입, 함수형 유형 등은 지원되지 않는다. (공식 문서 참조)
*   -> 함수 유형은 functional tool 참조
* */
public class ProgrammaticDateTimeTools {

    String getCurrentDateTime() {
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }
}
