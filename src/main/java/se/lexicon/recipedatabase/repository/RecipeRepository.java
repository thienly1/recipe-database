package se.lexicon.recipedatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.recipedatabase.entity.Recipe;
import se.lexicon.recipedatabase.entity.RecipeCategory;

import java.util.Collection;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    //Search for recipes where recipe name contains specified String.
    List<Recipe> findAllByRecipeNameContainingIgnoreCase(String recipeName);

    //Search for all recipes that contains a specified ingredient name.e.g. potato, tomato, salt, etc
    @Query("select s from Recipe s where exists(select m from RecipeIngredient m where m.recipe.recipeId= s.recipeId and exists (select t from Ingredient t where t.ingredientId= m.ingredient.ingredientId and t.ingredientName= :fn))")
    List<Recipe> findAllByRecipeIngredientsContainsIngredientName(@Param("fn") String ingredientName);


    // Search for all recipes that belong to a specific recipe category. e.g. Chicken, Vegan, Celebration, Weekend etc
    List<Recipe> findAllByCategoriesContainsIgnoreCase(RecipeCategory recipeCategory);

//    List<Recipe> findAllByCategoriesIn(Collection<RecipeCategory> categories);


    // Search for all recipes that match one or more categories.e.g. {”Spicy”,”Mexican”,”Weekend”}
    List<Recipe> findDistinctByCategoriesIn(Collection<RecipeCategory> categories);

}
