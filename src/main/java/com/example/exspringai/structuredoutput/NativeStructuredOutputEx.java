package com.example.exspringai.structuredoutput;

import com.example.exspringai.ActorFilms;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.AdvisorParams;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NativeStructuredOutputEx {

    private final ChatClient chatClient;

    /**
     * 최신 AI 모델이 지원하는 출력 구조화 기능 사용.
     * defaultAdvisors 를 사용하여 전역 설정도 가능.
     */
    @GetMapping("/ai/structured-output/nativeStructuredOutputEx01")
    public ActorFilms nativeStructuredOutputEx01() {
        return chatClient.prompt()
                .advisors(AdvisorParams.ENABLE_NATIVE_STRUCTURED_OUTPUT)
                .user("Generate the filmography for a random actor.")
                .call()
                .entity(ActorFilms.class);
    }
}
