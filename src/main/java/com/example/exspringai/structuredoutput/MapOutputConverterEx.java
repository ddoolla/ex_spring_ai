package com.example.exspringai.structuredoutput;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MapOutputConverterEx {

    private final ChatModel chatModel;
    private final ChatClient chatClient;

    @GetMapping("/ai/structured-output/mapOutputConverterEx01")
    public Map<String, Object> mapOutputConverterEx01() {
        return chatClient
                .prompt()
                .user(u -> u.text("Provide me a List of {subject}")
                        .param("subject", "an array of numbers from 1 to 9 under they key name 'numbers'"))
                .call()
                .entity(new ParameterizedTypeReference<Map<String, Object>>() {});
    }

    /**
     * ChatModel API 직접 사용 방법 (하위 레벨 모델에서)
     */
    @GetMapping("/ai/structured-output/mapOutputConverterEx02")
    public Map<String, Object> mapOutputConverterEx02() {
        MapOutputConverter mapOutputConverter = new MapOutputConverter();

        String format = mapOutputConverter.getFormat();
        String template = """
                Provide me List of {subject}
                {format}
                """;

        Prompt prompt = PromptTemplate.builder()
                .template(template)
                .variables(Map.of(
                        "subject", "an array of numbers from 1 to 9 under they key name 'numbers'",
                        "format", format
                ))
                .build()
                .create();

        Generation generation = chatModel.call(prompt).getResult();

        return mapOutputConverter.convert(generation.getOutput().getText());
    }
}
