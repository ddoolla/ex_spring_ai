package com.example.exspringai.tool.resultconverter;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerTools {

    private final CustomerRepository customerRepository;

    @Tool(
            description = "고객 ID로 고객 정보를 조회합니다.",
            resultConverter = CustomToolCallResultConverter.class
    )
    public Customer getCustomerInfo(Long id) {
        return customerRepository.findById(id);
    }
}
