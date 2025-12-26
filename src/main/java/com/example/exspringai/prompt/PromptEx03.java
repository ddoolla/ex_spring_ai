package com.example.exspringai.prompt;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * org.springframework.core.is.Resource 지원
 */
@RestController
@RequiredArgsConstructor
public class PromptEx03 {

    private final ChatClient chatClient;

    @Value("classpath:/prompt/system-message.st")
    private Resource systemResource;

    @GetMapping("/ai/prompt/ex03")
    public String prompt_ex03() {

        UserMessage userMessage = new UserMessage("hello");

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemResource);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("language", "korean", "name", "Tom", "voice", "neutral"));

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        return chatClient.prompt(prompt).call().content();
    }
}
