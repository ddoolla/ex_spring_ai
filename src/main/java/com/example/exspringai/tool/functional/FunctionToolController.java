package com.example.exspringai.tool.functional;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.exspringai.tool.functional.WeatherTools.CURRENT_WEATHER_TOOL;

@RestController
@RequiredArgsConstructor
public class FunctionToolController {

    private final ChatModel chatModel;

    /*
    * ChatClient - functional tool 추가
    * */
    @GetMapping("/ai/tool/functional/ex01")
    public String functionalEx01() {
        ToolCallback toolCallback = FunctionToolCallback
                .builder("currentWeather", new WeatherService())
                .description("Get the weather in location")
                .inputType(WeatherRequest.class)
                .build();

        return ChatClient.create(chatModel)
                .prompt("What's the weather like in Copenhagen?")
                .toolCallbacks(toolCallback)
                .call()
                .content();
    }

    /*
    * ChatClient - 기본 functional tool 추가
    * */
    @GetMapping("/ai/tool/functional/ex02")
    public String functionalEx02() {
        ToolCallback toolCallback = FunctionToolCallback
                .builder("currentWeather", new WeatherService())
                .description("Get the weather in location")
                .inputType(WeatherRequest.class)
                .build();

        ChatClient chatClient = ChatClient.builder(chatModel)
                .defaultToolCallbacks(toolCallback)
                .build();

        return chatClient
                .prompt("What's the weather like in Copenhagen?")
                .toolCallbacks(toolCallback)
                .call()
                .content();
    }

    /*
    * ChatModel - functional tool 추가
    * */
    @GetMapping("/ai/tool/functional/ex03")
    public String functionalEx03() {
        ToolCallback toolCallback = FunctionToolCallback
                .builder("currentWeather", new WeatherService())
                .description("Get the weather in location")
                .inputType(WeatherRequest.class)
                .build();

        ChatOptions chatOptions = ToolCallingChatOptions.builder()
                .toolCallbacks(toolCallback)
                .build();

        Prompt prompt = new Prompt("What's the weather like in Copenhagen?", chatOptions);

        return chatModel.call(prompt).getResult().getOutput().getText();
    }

    /*
    * ChatClient - WeatherTool 추가
    * */
    @GetMapping("/ai/tool/functional/ex04")
    public String functionalEx04() {
        return ChatClient.create(chatModel)
                .prompt("What's the weather like in Copenhagen?")
                .toolNames("currentWeather")
                .call()
                .content();
    }

    /*
     * ChatModel - WeatherTool 추가
     * */
    @GetMapping("/ai/tool/functional/ex05")
    public String functionalEx05() {
        ChatOptions chatOptions = ToolCallingChatOptions.builder()
                .toolNames(CURRENT_WEATHER_TOOL) // 타입 안정성
                .build();

        Prompt prompt = new Prompt("What's the weather like in Copenhagen?", chatOptions);

        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
