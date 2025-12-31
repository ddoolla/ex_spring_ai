package com.example.exspringai.tool.context;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

/*
* ToolContext
* - 도구에 추가적인 컨텍스트 정보를 전달
* - AI 모델이 전달하는 인수와 함께 같이 전달된다. (ToolContext는 AI 모델에 전달되지 않는다.)
* */
@Component
@RequiredArgsConstructor
public class PersonTools {

    private final PersonRepository personRepository;

    @Tool(description = "사람 정보를 검색합니다.")
    public Person getPerson(ToolContext toolContext) {
        String name = (String) toolContext.getContext().get("name");
        return personRepository.getPerson(name);
    }
}
