package com.example.exspringai.tool.functional;

import org.springframework.stereotype.Service;

import java.util.function.Function;

/*
* 함수의 입력 출력 유형으로 기본 유형, Optional, 컬렉션, 비동기 타입, 반응형 타입은 지원하지 않는다.
* -> 기본 유형과 컬렉션은 프로그래밍 방식 도구 참조.
* */
@Service
public class WeatherService implements Function<WeatherRequest, WeatherResponse> {

    @Override
    public WeatherResponse apply(WeatherRequest request) { // request에 정의한 매개변수들을 전달하지 않으면 AI가 요청 값으로 요구함
        return new WeatherResponse(30.0, Unit.C);
    }
}
