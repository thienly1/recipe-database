package se.lexicon.recipedatabase.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RecipeInstructionDto {

    private int instructionId;
    private String instructions;
}
