package com.example.exspringai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1. 기본 값을 설정하여 빈 등록할 때 사용
 * 2. 여러 모델을 사용할 때 사용
 */
@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient geminiAiChatClient(ChatModel chatModel) {
        return ChatClient.create(chatModel);
    }
}
