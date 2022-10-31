package se.lexicon.recipedatabase.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RecipeCategoryDtoSmall {

    private int recipeCategoryId;
    private String category;
}
