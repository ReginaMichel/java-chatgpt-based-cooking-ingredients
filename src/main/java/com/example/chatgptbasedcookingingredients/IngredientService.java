package com.example.chatgptbasedcookingingredients;

import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    String categorizeIngredient(String ingredient) {

        // TODO: This should return "vegan", "vegetarian" or "regular" depending on the ingredient.
        return "vegan";
    }
}
