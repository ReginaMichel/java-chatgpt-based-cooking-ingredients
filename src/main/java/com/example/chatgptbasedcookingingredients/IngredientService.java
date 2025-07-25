package com.example.chatgptbasedcookingingredients;

import com.example.chatgptbasedcookingingredients.model.OpenAiMessage;
import com.example.chatgptbasedcookingingredients.model.OpenAiRequest;
import com.example.chatgptbasedcookingingredients.model.OpenAiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

    private final RestClient restClient;
    public IngredientService(RestClient.Builder restClientBuilder,
                             @Value("${OPENAI_API_KEY}") String apiKey) {
        this.restClient = restClientBuilder
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization",
                        "Bearer " + apiKey).build();
    }

    String categorizeIngredient(String ingredient) {
        OpenAiMessage requestMessage = new OpenAiMessage("user",
                "Gib als String zurück, ob die folgende Zutat: "
                        + ingredient + " vegan, vegetarisch oder regular "
                        + "ist.");
        List<OpenAiMessage> messages = new ArrayList<>();
        messages.add(requestMessage);
        OpenAiRequest request = new OpenAiRequest("gpt-40-mini",
                messages);
        OpenAiResponse openAiResponse = restClient.post()
                .body(request)
                .retrieve().body(OpenAiResponse.class);
        return openAiResponse.choices().get(0).message().content();
    }
}
