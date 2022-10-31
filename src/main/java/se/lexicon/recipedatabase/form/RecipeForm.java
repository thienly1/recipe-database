package se.lexicon.recipedatabase.form;

import lombok.*;
import se.lexicon.recipedatabase.entity.RecipeInstruction;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecipeForm {

    @NotBlank
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private  String recipeName;

    @NotNull
    @Valid//to activate Validation in RecipeInstruction (if you want to have it validated)
    private RecipeInstructionForm instruction;
}
