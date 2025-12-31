package com.example.exspringai.tool.resultconverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.ai.tool.execution.ToolCallResultConverter;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/*
* 사용자 정보의 민감한 부분을 마스킹하여 결과를 모델에 제공하는 컨버터
* */
public class CustomToolCallResultConverter implements ToolCallResultConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @NotNull
    @Override
    public String convert(Object result, Type returnType) {
        if (result == null) {
            return "\"message\": \"고객을 찾을 수 없습니다.\"";
        }

        try {
            Customer customer = (Customer) result;

            Map<String, Object> maskedData = new HashMap<>();
            maskedData.put("id", customer.getId());
            maskedData.put("name", customer.getName());
            maskedData.put("email", maskEmail(customer.getEmail()));
            maskedData.put("phone", maskPhone(customer.getPhone()));
            maskedData.put("privacyNotice", "개인정보는 마스킹 처리되었습니다.");

            return objectMapper.writeValueAsString(maskedData);

        } catch (Exception e) {
            return "{\"error\": \"변환 실패\"}";
        }
    }

    private String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return "***@***.com";
        }
        String[] parts = email.split("@");
        String localPart = parts[0];
        if (localPart.length() <= 2) {
            return "**@" + parts[1];
        }
        return localPart.substring(0, 2) + "**@" + parts[1];
    }

    private String maskPhone(String phone) {
        if (phone == null) {
            return "***-****-****";
        }
        return phone.replaceAll("(\\d{3})-(\\d{4})-(\\d{4})", "$1-****-$3");
    }
}
