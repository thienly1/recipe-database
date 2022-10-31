package se.lexicon.recipedatabase.service;

import se.lexicon.recipedatabase.dto.RecipeDto;
import se.lexicon.recipedatabase.entity.RecipeCategory;
import se.lexicon.recipedatabase.entity.RecipeIngredient;
import se.lexicon.recipedatabase.entity.RecipeInstruction;
import se.lexicon.recipedatabase.exception.RESTExceptionHandler;
import se.lexicon.recipedatabase.exception.ResourceNotFoundException;
import se.lexicon.recipedatabase.form.RecipeForm;

import java.util.Collection;
import java.util.List;

public interface RecipeService {

    List<RecipeDto> findAll();
    RecipeDto createRecipe(RecipeForm recipeForm);
    List<RecipeDto> findByRecipeName(String recipeName) throws ResourceNotFoundException;
    List<RecipeDto> findByRecipeIngredientName(String ingredientName) throws ResourceNotFoundException ;
    List<RecipeDto> findByCategories(Collection<RecipeCategory> categories) throws ResourceNotFoundException ;
    RecipeDto findByRecipeId(Integer recipeId) throws ResourceNotFoundException;
    void update(Integer recipeId, RecipeForm recipeForm) throws ResourceNotFoundException ;
    void deleteRecipe(Integer recipeId) throws ResourceNotFoundException ;


}


