package com.example.exspringai.structuredoutput;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ListOutputConverterEx {

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
}
