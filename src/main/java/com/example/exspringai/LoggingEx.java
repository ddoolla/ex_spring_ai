package com.example.exspringai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * * application.properties
 *   - logging.level.org.springframework.ai.chat.client.advisor=DEBUG 추가.
 */
@RestController
@RequiredArgsConstructor
public class LoggingEx {

    private final ChatClient chatClient;

    @GetMapping("/ai/logging/ex01")
    public String logging_ex01() {
        return chatClient
                .prompt()
                .advisors(new SimpleLoggerAdvisor())
                .user("Tell me a joke?")
                .call()
                .content();
    }

    @GetMapping("/ai/logging/ex02")
    public String logging_ex02() {
        SimpleLoggerAdvisor customLogger = new SimpleLoggerAdvisor(
                request -> "Custom request: " + request.prompt().getUserMessage(),
                response -> "Custom response: " + response.getResult(),
                0 // 순번이 낮은 어드바이저가 먼저 실행됨.
        );

        return chatClient
                .prompt()
                .advisors(customLogger)
                .user("Tell me a joke?")
                .call()
                .content();
    }
}
