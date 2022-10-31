package se.lexicon.recipedatabase.dto;

import lombok.*;
import se.lexicon.recipedatabase.entity.Ingredient;
import se.lexicon.recipedatabase.entity.Measurement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RecipeIngredientDtoSmall {
    private String recipeIngredientId;
    private Ingredient ingredient;
    private double amount;
    private Measurement measurement;

}
