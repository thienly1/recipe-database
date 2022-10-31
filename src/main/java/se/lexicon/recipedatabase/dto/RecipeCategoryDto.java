package se.lexicon.recipedatabase.dto;

import lombok.*;
import se.lexicon.recipedatabase.entity.Recipe;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RecipeCategoryDto {

    private int recipeCategoryId;
    private String category;
    private Set<RecipeDtoSmall> recipe;
}
