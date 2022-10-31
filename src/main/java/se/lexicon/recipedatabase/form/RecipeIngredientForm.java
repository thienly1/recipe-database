package se.lexicon.recipedatabase.form;

import lombok.*;
import se.lexicon.recipedatabase.entity.Ingredient;
import se.lexicon.recipedatabase.entity.Measurement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeIngredientForm {

    private Ingredient ingredient;
    private double amount;
    private Measurement measurement;

}
