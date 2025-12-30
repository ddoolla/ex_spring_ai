package com.example.exspringai.chatmemory;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatMemoryEx {

    private final ChatClient chatClient;
    private final ChatMemory chatMemory;

    @GetMapping("/ai/chatMemory/ex01")
    public String chatMemoryEx01() {

        return chatClient.prompt()
                .user("My name is Lee")
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .call()
                .content();
    }

    @GetMapping("/ai/chatMemory/ex02")
    public String chatMemoryEx02() {

        return chatClient.prompt()
                .user("What is my name?")
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .call()
                .content();
    }

    @GetMapping("/ai/chatMemory/ex03")
    public String chatMemoryEx03() {

        return chatClient.prompt()
                .user("My name is Kim")
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).conversationId("001").build())
                .call()
                .content();
    }

    @GetMapping("/ai/chatMemory/ex04")
    public String chatMemoryEx04() {

        return chatClient.prompt()
                .user("What is my name?")
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).conversationId("001").build())
                .call()
                .content();
    }
}
