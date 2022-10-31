package se.lexicon.recipedatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import se.lexicon.recipedatabase.dto.*;
import se.lexicon.recipedatabase.entity.Recipe;
import se.lexicon.recipedatabase.entity.RecipeCategory;
import se.lexicon.recipedatabase.entity.RecipeIngredient;
import se.lexicon.recipedatabase.entity.RecipeInstruction;
import se.lexicon.recipedatabase.form.RecipeForm;
import se.lexicon.recipedatabase.form.RecipeInstructionForm;
import se.lexicon.recipedatabase.repository.RecipeCategoryRepository;
import se.lexicon.recipedatabase.repository.RecipeInstructionRepository;
import se.lexicon.recipedatabase.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ConversionServer {
    private final RecipeRepository recipeRepository;
    private final RecipeCategoryRepository categoryRepository;
    private final RecipeInstructionRepository instructionRepository;

    @Autowired
    public ConversionServer(RecipeRepository recipeRepository, RecipeCategoryRepository categoryRepository, RecipeInstructionRepository instructionRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.instructionRepository = instructionRepository;
    }

    public Recipe ToRecipeEntity(RecipeForm recipeForm){
        List<RecipeIngredient> ingredients= new ArrayList<>();
        Set<RecipeCategory> categories= new HashSet<>();
        Recipe recipe= new Recipe(0, recipeForm.getRecipeName(),ingredients,
                null, categories);
        return recipe;
    }
    public RecipeDto ToRecipeDto(Recipe recipeEntity){
        List<RecipeIngredientDtoSmall> smalls= new ArrayList<>();
        for(RecipeIngredient a: recipeEntity.getRecipeIngredients()) {
            smalls.add(new RecipeIngredientDtoSmall(a.getRecipeIngredientId(), a.getIngredient(), a.getAmount(), a.getMeasurement()));
        }
        Set<RecipeCategoryDtoSmall> categoryDtoSmalls= new HashSet<>();
        for(RecipeCategory b: recipeEntity.getCategories()){
            categoryDtoSmalls.add(new RecipeCategoryDtoSmall(b.getRecipeCategoryId(),b.getCategory()));
        }
        RecipeInstructionDto instructionDto= new RecipeInstructionDto(recipeEntity.getInstruction().getInstructionId(),recipeEntity.getInstruction().getInstructions());
        return new RecipeDto(recipeEntity.getRecipeId(), recipeEntity.getRecipeName(),smalls,instructionDto,
                categoryDtoSmalls);
    }
    public RecipeInstruction ToRecipeInstruction(RecipeInstructionForm form){
        RecipeInstruction recipeInstruction= new RecipeInstruction(0, form.getInstructions());
        RecipeInstruction saved = instructionRepository.save(recipeInstruction);
        return saved;
    }
    public RecipeInstructionDto ToRecipeInstructionDto(RecipeInstruction recipeInstruction){
        RecipeInstructionDto convert = new RecipeInstructionDto(recipeInstruction.getInstructionId(), recipeInstruction.getInstructions());
        return convert;
    }


}
