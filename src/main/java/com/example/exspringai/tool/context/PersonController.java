package com.example.exspringai.tool.context;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final ChatClient chatClient;
    private final PersonTools personTools;

    /*
    * 사용자 요청 값에서 이름을 전달하지 않았지만
    * ToolContext로 도구에 정보를 전달하여 Lee 에 대한 응답을 받을 수 있다.
    * */
    @GetMapping("/ai/tool/context/ex")
    public String getPersonInfo() {
        return chatClient
                .prompt("사람 정보를 알려주세요.")
                .tools(personTools)
                .toolContext(Map.of("name", "Lee"))
                .call()
                .content();
    }
}
