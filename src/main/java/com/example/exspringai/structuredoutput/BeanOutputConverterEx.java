package com.example.exspringai.structuredoutput;

import com.example.exspringai.ActorFilms;
import lombok.RequiredArgsConstructor;
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

    /**
     * chatModel API를 직접 사용하는 방법 (chatClient 의 entity() 동일 기능)
     */
    @GetMapping("/ai/structured-output/beanOutputConverterEx")
    public ActorFilms beanOutputConverterEx() {

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
