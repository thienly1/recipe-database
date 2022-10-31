package se.lexicon.recipedatabase.dto;

import lombok.*;
import se.lexicon.recipedatabase.entity.RecipeCategory;
import se.lexicon.recipedatabase.entity.RecipeIngredient;
import se.lexicon.recipedatabase.entity.RecipeInstruction;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RecipeDtoSmall {
    private  String recipeName;
    private RecipeInstruction instruction;

}
