package com.example.exspringai.structuredoutput;

import com.example.exspringai.ActorFilms;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BeanOutputConverterEx {

    private final ChatModel chatModel;
    private final ChatClient chatClient;

    @GetMapping("/ai/structured-output/beanOutputConverterEx01")
    public ActorFilms beanOutputConverterEx01() {
        return chatClient
                .prompt()
                .user(u -> u.text("{actor} 배우가 출연한 영화 5편 알려줘.")
                        .param("actor", "정재영"))
                .call()
                .entity(ActorFilms.class);
    }

    /**
     * ChatModel API 직접 사용하는 방법 (하위 레벨 모델에서)
     */
    @GetMapping("/ai/structured-output/beanOutputConverterEx02")
    public ActorFilms beanOutputConverterEx02() {

        BeanOutputConverter<ActorFilms> beanOutputConverter = new BeanOutputConverter<>(ActorFilms.class);

        String format = beanOutputConverter.getFormat();

        String actor = "정재영";

        String template = """
                {actor} 배우가 출연한 영화 5편 알려줘.
                {format}
                """;

        Generation generation = chatModel.call(PromptTemplate.builder()
                .template(template)
                .variables(Map.of("actor", actor, "format", format))
                .build()
                .create()).getResult();

        return beanOutputConverter.convert(generation.getOutput().getText());
    }
}
