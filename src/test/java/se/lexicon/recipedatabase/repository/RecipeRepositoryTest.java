package se.lexicon.recipedatabase.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.recipedatabase.entity.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RecipeRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    RecipeRepository recipeRepository;

    Recipe test;
    RecipeCategory categoryTest;
    Collection<RecipeCategory> categoriesTest;

    public List<RecipeIngredient> recipesIngredient(){
        RecipeIngredient ri1= new RecipeIngredient(new Ingredient("tomato"), 3, Measurement.KG);
        RecipeIngredient ri2= new RecipeIngredient(new Ingredient("potatoes"), 5, Measurement.KG);
        RecipeIngredient ri3= new RecipeIngredient(new Ingredient("chicken"), 2, Measurement.KG);
        RecipeIngredient ri4= new RecipeIngredient(new Ingredient("salt"), 100, Measurement.G);
        RecipeIngredient ri5= new RecipeIngredient(new Ingredient("pepper"), 50, Measurement.G);
        RecipeIngredient ri6= new RecipeIngredient(new Ingredient("milk"), 200, Measurement.ML);
        RecipeIngredient ri7= new RecipeIngredient(new Ingredient("oil"), 50, Measurement.TSP);
        RecipeIngredient ri8= new RecipeIngredient(new Ingredient("pepper"), 50, Measurement.G);

        return Arrays.asList(ri1,ri2,ri3,ri4,ri5,ri6,ri7,ri8);

    }
    public List<RecipeCategory> categories(){
        RecipeCategory classic =new RecipeCategory("classic");
        RecipeCategory spicy = new RecipeCategory("spicy");
        RecipeCategory vegetarian = new RecipeCategory("Vegetarian");
        RecipeCategory chicken = new RecipeCategory("Chicken");
        RecipeCategory beef = new RecipeCategory("beef");
        return Arrays.asList(classic,spicy,vegetarian,chicken,beef);
    }
    public List<Recipe> recipes(){
        Recipe recipe1 = new Recipe("köttfärsås");
        Recipe recipe2 = new Recipe("Chili con Carne");
        Recipe recipe3 = new Recipe("Chili sin Carne");
        Recipe recipe4 = new Recipe("Windalo Chicken");
        Recipe recipe5 = new Recipe("Mild Chicken");

        return Arrays.asList(recipe1,recipe2,recipe3,recipe4,recipe5);

    }
    @BeforeEach
    void setUp() {
        List<RecipeIngredient> persistedRecipeIn = recipesIngredient().stream().map(entityManager::persist).collect(Collectors.toList());
        List<RecipeCategory> persistedCategory = categories().stream().map(entityManager::persist).collect(Collectors.toList());
        List<Recipe> persistedRecipe = recipes().stream().map(entityManager::persist).collect(Collectors.toList());

        persistedRecipe.get(0).addRecipeIngredient(persistedRecipeIn.get(0));
        persistedRecipe.get(0).addRecipeIngredient(persistedRecipeIn.get(1));
        persistedRecipe.get(1).addRecipeIngredient(persistedRecipeIn.get(2));
        persistedRecipe.get(2).addRecipeIngredient(persistedRecipeIn.get(3));
        persistedRecipe.get(2).addRecipeIngredient(persistedRecipeIn.get(4));
        persistedRecipe.get(3).addRecipeIngredient(persistedRecipeIn.get(5));
        persistedRecipe.get(3).addRecipeIngredient(persistedRecipeIn.get(6));
        persistedRecipe.get(4).addRecipeIngredient(persistedRecipeIn.get(7));

        persistedRecipe.get(0).addCategory(persistedCategory.get(0));
        persistedRecipe.get(0).addCategory(persistedCategory.get(1));
        persistedRecipe.get(0).addCategory(persistedCategory.get(2));
        persistedRecipe.get(1).addCategory(persistedCategory.get(0));
        persistedRecipe.get(2).addCategory(persistedCategory.get(0));
        persistedRecipe.get(2).addCategory(persistedCategory.get(1));
        persistedRecipe.get(3).addCategory(persistedCategory.get(3));
        persistedRecipe.get(3).addCategory(persistedCategory.get(4));
        persistedRecipe.get(3).addCategory(persistedCategory.get(0));
        persistedRecipe.get(4).addCategory(persistedCategory.get(2));
        persistedRecipe.get(4).addCategory(persistedCategory.get(4));

        test= recipes().get(0);
        categoryTest= persistedCategory.get(1);
        categoriesTest= Arrays.asList(persistedCategory.get(1),persistedCategory.get(2));

    }

    @Test
    void findAllByRecipeNameContainingIgnoreCase() {
        List<Recipe> found = recipeRepository.findAllByRecipeNameContainingIgnoreCase("Carne");
        assertFalse(found.isEmpty());
        assertEquals(2, found.size());
    }

    @Test
    void findAllByRecipeIngredientsContainsIngredientName() {
        List<Recipe> found = recipeRepository.findAllByRecipeIngredientsContainsIngredientName("pepper");
        assertFalse(found.isEmpty());
        assertEquals(found.size(),2);
    }

    @Test
    void findAllByCategoriesContainsIgnoreCase() {
        List<Recipe> found = recipeRepository.findAllByCategoriesContainsIgnoreCase(categoryTest);
        assertEquals(2, found.size());

    }

    @Test
    void findDistinctByCategoriesIn() {
        List<Recipe> found = recipeRepository.findDistinctByCategoriesIn(categoriesTest);
        assertEquals(found.size(), 3);

    }
}