package com.example.exspringai.multimodal;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MultiModalEx {

    private final ChatModel chatModel;
    private final ChatClient chatClient;

    @GetMapping("/ai/multimodal/ex01")
    public String multiModalEx01() {
        var imageResource = new ClassPathResource("/multimodal/test.png");

        var userMessage = UserMessage.builder()
                .text("Explain what do you see in this picture?")
                .media(new Media(MimeTypeUtils.IMAGE_PNG, imageResource))
                .build();

        return chatModel.call(new Prompt(userMessage)).getResult().getOutput().getText();
    }

    @GetMapping("/ai/multimodal/ex02")
    public String multiModalEx02() {
        return chatClient.prompt()
                .user(u -> u.text("Explain what do you see in this picture?")
                        .media(MimeTypeUtils.IMAGE_PNG, new ClassPathResource("/multimodal/test.png")))
                .call()
                .content();
    }
}
