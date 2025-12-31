package com.example.exspringai.tool.quickstart;

import com.example.exspringai.tool.DateTimeTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DateTimeToolEx {

    private final ChatClient chatClient;

    @GetMapping("/ai/tool/qs/ex01")
    public String dateTimeToolEx01() {
        return chatClient
                .prompt("What day is tomorrow?")
                .tools(new DateTimeTools())
                .call()
                .content();
    }

    @GetMapping("/ai/tool/qs/ex02")
    public String dateTimeToolEx02() {
        return chatClient
                .prompt("Can you set an alarm 10 minutes from now?")
                .tools(new DateTimeTools())
                .call()
                .content();
    }
}
