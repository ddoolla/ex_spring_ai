package com.example.exspringai.chatmemory;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 기본적으로 InMemoryChatMemoryRepository 를 사용한다.
 * 다른 리포지토리가 설정되어있으면 해당 저장소를 사용한다. (ex: Cassandra, JDBC, Neo4j)
 *
 * * JDBC 사용
 *      1. 의존성 추가
 *          - implementation("org.springframework.ai:spring-ai-starter-model-chat-memory-repository-jdbc")
 *      2. 스키마 sql 파일 추가
 *          - classpath:/schema/schema-mysql.sql (경로랑 파일 이름은 상관 없음)
 *      3. 외부 파일 설정 추가.
 *          - spring.ai.chat.memory.repository.jdbc.schema=classpath:/schema/schema-mysql.sql
 *          - spring.ai.chat.memory.repository.jdbc.initialize-schema=always
 */
@Configuration
@RequiredArgsConstructor
public class ChatMemoryConfig {

    @Bean
    public ChatMemory getChatMemory(JdbcChatMemoryRepository chatMemoryRepository) {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(10) // 대화 아이디별 10개 대화까지 기억
                .build();
    }
}
