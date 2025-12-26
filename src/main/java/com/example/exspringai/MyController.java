package com.example.exspringai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class MyController {

    private final ChatClient chatClient;

    public MyController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("answer in {language}") // 시스템 기본 텍스트 설정
                .build();
    }

    /**
     * 기본 예제
     * call: 동기 요청 / stream: 비동기 요청
     * call(), stream()을 호출한다고해서 실제로 AI 모델이 실행되지 않는다. 동기 또는 스트리밍 호출을 할지 지시하는 것 뿐임.
     * 실제로 content, chatResponse, responseEntity()를 호출할 때 실제 AI 모델이 실행된다.
     */
    @GetMapping("/ex01")
    String generation_ex01() {
        String userInput = """
                    Generate the filmography of 5 movies for a random actor of korean.
                """;

        return chatClient
                .prompt(userInput)
                .call()
                .content();
    }

    /**
     * 엔터티 변환
     */
    @GetMapping("/ex02")
    ActorFilms generation_ex02() {
        String userInput = """
                    Generate the filmography of 5 movies for a random actor of korean.
                """;

        return chatClient
                .prompt(userInput)
                .call()
                .entity(ActorFilms.class);
    }

    /**
     * 프롬프트 템플릿 사용
     */
    @GetMapping("/ex03")
    String generation_ex03() {
        return chatClient
                .prompt()
                .user(u -> u
                        .text("{singer}의 노래 아무거나 5개 알려줘")
                        .param("singer", "데이식스"))
                .call()
                .content();
    }

    /**
     * user.text: 사용자 요청 텍스트.
     * system.text: 사용자에게 보이지 않는 배경 설정.
     * metadata: 사용자, 시스템 메타데이터 제공. ex) 어떤 사용자가 이 요청을 보냈는지? (defaultSystem 에서도 지원)
     *      - 메타데이터의 키, 값은 null 일 수 없으며, Map 을 전달할 때도 키, 값 모두 null 을 포함할 수 없다.
     */
    @GetMapping("/ex04")
    String generation_ex04() {
        return chatClient
                .prompt()
                .user(u -> u
                        .text("tell me any 5 songs")
                        .metadata("userId", "1"))
                .system(s -> s.text("한국어로 답변해주세요.")
                        .metadata("version", "v1.0"))
                .call()
                .content();
    }

    /**
     * defaultSystem 파라미터 전달
     */
    @GetMapping("/ex05")
    String generation_ex05() {
        return chatClient
                .prompt()
                .user(u -> u
                        .text("tell me any 5 songs"))
                .system(s -> s.param("language", "korean"))
                .call()
                .content();
    }
}
