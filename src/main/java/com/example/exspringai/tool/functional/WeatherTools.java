package com.example.exspringai.tool.functional;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

/*
* 프로그래밍 방식 도구를 런타임에 동적으로 사용하기 위한 방법
* */
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class WeatherTools {

    public static final String CURRENT_WEATHER_TOOL = "currentWeather";

    private final WeatherService weatherService;

    @Bean(CURRENT_WEATHER_TOOL)
    @Description("Get the weather in location")
    Function<WeatherRequest, WeatherResponse> currentWeather() {
        return weatherService;
    }
}
