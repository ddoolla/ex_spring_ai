package com.example.exspringai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1. 기본 값을 설정하여 빈 등록할 때 사용
 * 2. 여러 모델을 사용할 때 사용
 *
 * ChatModel, ChatClient.Builder 는 자동 주입해주는데 ChatClient 바로 쓰고 싶으면 설정 해야함.
 */
@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient geminiAiChatClient(ChatModel chatModel) {
        // 기본 설정 방법
//        ChatClient.builder(chatModel)
//                .defaultUser()
//                .defaultSystem()
//                .defaultAdvisors()
//                .build();

        return ChatClient.create(chatModel);
    }
}
