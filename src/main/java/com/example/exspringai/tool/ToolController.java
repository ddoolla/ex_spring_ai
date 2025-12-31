package com.example.exspringai.tool;

import com.example.exspringai.tool.programmatic.ProgrammaticDateTimeTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.method.MethodToolCallback;
import org.springframework.ai.tool.support.ToolDefinitions;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

@RestController
@RequiredArgsConstructor
public class ToolController {

    private final ChatModel chatModel;

    /*
    * ChatClient 기본 도구 추가 방법
    * - 기본 도구와 런타임 도구가 모두 제공되는 경우 런타임 도구가 재정의 함.
    * */
    @GetMapping("/ai/tool/ex01")
    public String addToolEx01() {
        ChatClient chatClient = ChatClient.builder(chatModel)
                .defaultTools(new DateTimeTools())
                .build();

        return chatClient
                .prompt("What day is tomorrow?")
                .call()
                .content();
    }

    /*
    * ChatModel 도구 추가 방법
    * */
    @GetMapping("/ai/tool/ex02")
    public String addToolEx02() {
        ToolCallback[] dateTimeTools = ToolCallbacks.from(new DateTimeTools());

        ChatOptions chatOptions = ToolCallingChatOptions.builder()
                .toolCallbacks(dateTimeTools)
                .build();

        Prompt prompt = new Prompt("What day is tomorrow?", chatOptions);
        return chatModel.call(prompt).getResult().getOutput().getText();
    }

    /*
    * 프로그래밍 방식 도구 추가 방법
    * */
    @GetMapping("/ai/tool/ex03")
    public String addToolEx03() {
        Method method = ReflectionUtils.findMethod(ProgrammaticDateTimeTools.class, "getCurrentDateTime");

        MethodToolCallback toolCallback = MethodToolCallback.builder()
                .toolDefinition(ToolDefinitions.builder(method)
                        .description("Get the current date and time in the user's timezone")
                        .build())
                .toolMethod(method)
                .toolObject(new ProgrammaticDateTimeTools()) // 정적 메서드의 경우 생략 가능.
                .build();

        ChatClient chatClient = ChatClient.builder(chatModel).build();

        return chatClient
                .prompt("What day is tomorrow?")
                .toolCallbacks(toolCallback)
                .call()
                .content();
    }
}
