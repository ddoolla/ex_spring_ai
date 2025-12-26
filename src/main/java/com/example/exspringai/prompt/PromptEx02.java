package com.example.exspringai.prompt;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PromptEx02 {

    private final ChatClient chatClient;

    /**
     * Message
     *      ◁- UserMessage - 사용자의 입력 (질문, 명령 등)
     *      ◁- SystemMessage - AI의 동작 및 응답 방식 안내
     *      ◁- AssistantMessage - 사용자의 입력에 대한 AI 응답
     *      ◁- ToolResponseMessage - Tool 호출 지원 메시지에 대한 응답
     *
     * PromptTemplate, SystemPromptTemplate -> create() -> Prompt
     * PromptTemplate, SystemPromptTemplate -> createMassage() -> Message
     *
     * Prompt(List<Message> messages) -> 메시지 조합하여 프롬프트 생성.
     */
    @GetMapping("/ai/prompt/ex02")
    public String prompt_ex02() {

        String userText = """
                Tell me about three famous pirates from the Golden Age of Piracy and why they did.
                Write at least a sentence for each pirate.
                """;

        Message userMessage = new UserMessage(userText);

        String systemText = """
                You are a helpful AI assistant that helps people find information.
                Your name is {name}
                You should reply to the user's request with your name and also in the style of a {voice}.
                """;

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemText);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("name", "Tom", "voice", "neutral"));

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));

        return chatClient.prompt(prompt).call().content();
    }
}
