package se.lexicon.recipedatabase.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "recipeIngredients")
@EqualsAndHashCode
public class RecipeDto {

    private int recipeId;
    private  String recipeName;
    private List<RecipeIngredientDtoSmall> recipeIngredients;
    private RecipeInstructionDto instruction;
    private Set<RecipeCategoryDtoSmall> categories;

}
