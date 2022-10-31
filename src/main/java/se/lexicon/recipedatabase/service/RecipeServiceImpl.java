package se.lexicon.recipedatabase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.recipedatabase.dto.RecipeDto;

import se.lexicon.recipedatabase.entity.Recipe;
import se.lexicon.recipedatabase.entity.RecipeCategory;

import se.lexicon.recipedatabase.entity.RecipeInstruction;
import se.lexicon.recipedatabase.exception.ResourceNotFoundException;
import se.lexicon.recipedatabase.form.RecipeForm;

import se.lexicon.recipedatabase.repository.RecipeInstructionRepository;
import se.lexicon.recipedatabase.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
    private final ConversionServer conversion;
    private final RecipeInstructionRepository instructionRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, ConversionServer conversion, RecipeInstructionRepository instructionRepository) {
        this.recipeRepository = recipeRepository;
        this.conversion = conversion;
        this.instructionRepository = instructionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findAll() {
        List<Recipe> all= recipeRepository.findAll();
        List<RecipeDto> convert = new ArrayList<>();
        for(Recipe a: all){
            RecipeDto b = conversion.ToRecipeDto(a);
            convert.add(b);
        }
        return convert;
    }

    @Override
    @Transactional
    public RecipeDto createRecipe(RecipeForm recipeForm) throws ResourceNotFoundException {
        if(recipeForm==null) throw new IllegalArgumentException("Recipe Dto is not allowed to be null");
        Recipe recipe= conversion.ToRecipeEntity(recipeForm);
        Recipe saved =recipeRepository.save(recipe);
        return conversion.ToRecipeDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findByRecipeName(String recipeName) throws ResourceNotFoundException {
        if(recipeName==null) throw new IllegalArgumentException("name is null");
        List<Recipe> foundsByName = recipeRepository.findAllByRecipeNameContainingIgnoreCase(recipeName);
        if(foundsByName.isEmpty()) throw new ResourceNotFoundException("Not found");
        return foundsByName.stream().map(conversion::ToRecipeDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findByRecipeIngredientName(String ingredientName) throws ResourceNotFoundException{
        if(ingredientName==null) throw new IllegalArgumentException("ingredientName is null");
        List<Recipe> containsIngredientName = recipeRepository.findAllByRecipeIngredientsContainsIngredientName(ingredientName);
        return containsIngredientName.stream().map(conversion::ToRecipeDto).collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findByCategories(Collection<RecipeCategory> categories) throws ResourceNotFoundException {
        if(categories.isEmpty()) throw new IllegalArgumentException("categories is empty");
        List<Recipe> found= recipeRepository.findDistinctByCategoriesIn(categories);
        return found.stream().map(conversion::ToRecipeDto).collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public RecipeDto findByRecipeId(Integer recipeId) throws ResourceNotFoundException {
        if(recipeId==null) throw new IllegalArgumentException("Id is invalid");
        Recipe found= recipeRepository.findById(recipeId).orElseThrow(()->new ResourceNotFoundException("can't found recipe with the recipeId"));
        return conversion.ToRecipeDto(found);
    }
    @Override
    @Transactional
    public void update(Integer recipeId, RecipeForm recipeForm) throws ResourceNotFoundException {
        if(!recipeRepository.existsById(recipeId)) throw new ResourceNotFoundException("can't found recipe with the recipeId =" + recipeId);
        Recipe needToUpdate= recipeRepository.findById(recipeId).get();
        needToUpdate.setRecipeName(recipeForm.getRecipeName());
        System.out.println(" recipe with id: " +recipeId + " have been deleted");
    }
    @Override
    @Transactional
    public void deleteRecipe(Integer recipeId) throws ResourceNotFoundException {
        if(recipeId==null) throw new IllegalArgumentException("Id is invalid");
        if(!recipeRepository.existsById(recipeId)) throw new ResourceNotFoundException("can not found any recipe with id: " + recipeId);
        Recipe found= recipeRepository.findById(recipeId).get();
        recipeRepository.findAll().remove(found);
        System.out.println("recipe with id = " + recipeId + "has been removed");
    }
}
