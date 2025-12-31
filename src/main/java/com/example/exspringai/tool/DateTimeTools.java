package com.example.exspringai.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
* 채팅 요청 ~ 응답 과정
* 1. 채팅 요청 + 도구 정의 -> ai model 에게 스키마 제공 (도구 이름, 설명 등)
* 2. ai 는 해당 스키마에 도구의 이름, 매개변수 등 포함하는 응답 제공.
* 3. 애플리케이션은 응답 받은 내용을 바탕으로 도구 식별 및 실행
* 4. 도구 호출 결과는 애플리케이션에서 실행 됨
* 5. 애플리케이션은 도구 호츌 결과를 다시 모델로 보냄.
* 6. 모델은 도구 호출 결과를 활용하여 최종 응답을 생성함.
* --> 도구를 사용하면 요청에 대해서 2번의 응답을 사용함.
*
*
* @Tool 어노테이션이 붙은 메서드가 포함된 클래스가 Spring 빈인 경우 AOT 컴파일을 기본적으로 지원 (@Component)
* 그렇지 않은 경우 GraalVM 컴파일러에 필요한 구성을 제공해야함. (ex, @RegisterReflection(memberCategories = MemberCategory.INVOKE_DECLARED_METHODS 추가)
*
* 예를 들어, static 메서드들만 갖고 있는 유틸 클래스라서 빈으로 등록 안한 경우?
* */
@Component
public class DateTimeTools {
    /*
    * 정보 검색 툴
    * - 사용자 시간대의 현재 날짜 및 시간 검색
    *
    * @Tool (선언적 방식)
    *   - 도구에 대한 주요 정보 제공
    *   name - 도구 이름, 안쓰면 메서드 이름 사용 (같은 클래스 내 중복 X)
    *   description - 도구에 대한 설명, 안쓰면 메서드 이름 사용 (모델이 도구의 목적과 사용법을 이해하려면 자세히 적어주는게 좋음)
    *   returnDirect - 클라이언트로 직접 반환 or 모델로 다시 전달 여부
    *   resultConverter - 툴 호출 결과를 AI 모델로 다시 전송할 문자열 객체로 변환하는데 사용할 ToolCallResultConverter 구현체 지정
    *       기본적으로 Jackson을 사용하여 JSON 으로 직렬화된다. 사용자 지정이 필요할 경우 재정의하여 사용.
    *       프로그래밍 방법을 사용할 경우 MethodToolCallback.Builder.resultConverter()를 사용하면 된다.
    *
    *   접근 제어자 자유롭게 사용 가능 (public, protected, package-private (default), private)
    * */
    @Tool(description = "Get the current date and time in the user's timezone")
    public String getCurrentDateTime() {
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }

    /*
    * 행동 툴
    * ISO-8601 포멧의 시간을 받아서 세팅
    *
    * @ToolParam
    *   - Tool 매개변수에 대한 정보 제공
    *   description - 매개변수에 대한 설명 (모델이 해당 매개변수를 더 잘 이해하고 사용하는데 도움을 줌)
    *   required - 해당 매개변수 필수 여부 (기본 필수이며 전달하지 않으면 모델은 임의로 값을 만들어낼 가능성이 높음)
    * */
    @Tool(description = "Set a user alarm for the given time")
    public void setAlarm(@ToolParam(description = "Time in ISO-8601 format") String time) {
        LocalDateTime alarmTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("Alarm set for " + alarmTime);
    }
}
