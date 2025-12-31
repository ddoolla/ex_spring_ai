package com.example.exspringai.tool.resultconverter;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final ChatClient chatClient;
    private final CustomerTools customerTools;

    @GetMapping("/ai/tool/customResultConverter/ex01")
    public String customResultConverterEx01() {
        return chatClient
                .prompt()
                .user("고객 ID 1번의 정보를 알려줘")
                .tools(customerTools)
                .call()
                .content();
    }
}
