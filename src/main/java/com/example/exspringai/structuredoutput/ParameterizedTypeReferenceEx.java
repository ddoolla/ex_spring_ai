package com.example.exspringai.structuredoutput;

import com.example.exspringai.ActorFilms;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ParameterizedTypeReferenceEx {

    private final ChatModel chatModel;
    private final ChatClient chatClient;

    @GetMapping("/ai/structured-output/parameterizedTypeReferenceEx01")
    public List<ActorFilms> parameterizedTypeReferenceEx01() {
        return chatClient
                .prompt()
                .user("Generate the filmography of 5 movies for Tom Hanks and Bill Murray.")
                .call()
                .entity(new ParameterizedTypeReference<List<ActorFilms>>() {
                });
    }

    /**
     * ChatModel API 직접 사용 방법 (하위 레벨 모델에서)
     */
    @GetMapping("/ai/structured-output/parameterizedTypeReferenceEx02")
    public List<ActorFilms> parameterizedTypeReferenceEx02() {
        BeanOutputConverter<List<ActorFilms>> outputConverter = new BeanOutputConverter<>(
                new ParameterizedTypeReference<List<ActorFilms>>() {
                });

        String format = outputConverter.getFormat();
        String template = """
                Generate the filmography of 5 movies for Tom Hanks and Bill Murray.
                {format}
                """;

        Prompt prompt = PromptTemplate.builder()
                .template(template)
                .variables(Map.of("format", format))
                .build()
                .create();

        Generation generation = chatModel.call(prompt).getResult();

        return outputConverter.convert(generation.getOutput().getText());
    }
}
