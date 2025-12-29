package com.example.exspringai.structuredoutput;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ListOutputConverterEx {

    private final ChatModel chatModel;
    private final ChatClient chatClient;

    @GetMapping("/ai/structured-output/listOutputConverterEx01")
    public List<String> listOutputConverterEx01() {
        return chatClient
                .prompt()
                .user(u -> u.text("List five {subject}")
                        .param("subject", "ice cream flavors"))
                .call()
                .entity(new ListOutputConverter(new DefaultConversionService()));
    }

    /**
     * ChatModel API 직접 사용 방법 (하위 레벨 모델에서)
     */
    @GetMapping("/ai/structured-output/listOutputConverterEx02")
    public List<String> listOutputConverterEx02() {
        ListOutputConverter listOutputConverter = new ListOutputConverter(new DefaultConversionService());

        String format = listOutputConverter.getFormat();
        String template = """
                List five {subject}
                {format}
                """;

        Prompt prompt = PromptTemplate.builder()
                .template(template)
                .variables(Map.of(
                        "subject", "ice cream flavors",
                        "format", format))
                .build()
                .create();

        Generation generation = chatModel.call(prompt).getResult();

        return listOutputConverter.convert(generation.getOutput().getText());
    }
}
