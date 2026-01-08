package com.example.exspringai.mcp;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.HttpClientStreamableHttpTransport;
import io.modelcontextprotocol.spec.McpSchema;

import java.util.Map;

/**
 * 앱 실행 먼저 하고 진행 해야함.
 */
public class McpWeatherClient {
    public static void main(String[] args) {
        var client = McpClient.sync(
                        HttpClientStreamableHttpTransport
                                .builder("http://localhost").build())
                .build();

        client.initialize();

        McpSchema.CallToolResult weather = client.callTool(
                new McpSchema.CallToolRequest("getTemperature",
                        Map.of("latitude", "47.6062",
                                "longitude", "-122.3321")));

        System.out.println("weather.content() = " + weather.content());
    }
}
