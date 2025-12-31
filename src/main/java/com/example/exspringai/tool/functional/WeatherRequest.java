package com.example.exspringai.tool.functional;

import org.springframework.ai.tool.annotation.ToolParam;

/*
* @ToolParam 어노테이션을 사용하여 매개변수에 대한 추가 정보를 제공할 수 있다.
*
*
* */
public record WeatherRequest(@ToolParam(description = "The name of a city or a country") String location, Unit unit) {
}
