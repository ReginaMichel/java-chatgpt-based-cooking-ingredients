package com.example.chatgptbasedcookingingredients.model;

import java.util.List;

public record OpenAiResponse(List<OpenAiChoice> choices) {
}
