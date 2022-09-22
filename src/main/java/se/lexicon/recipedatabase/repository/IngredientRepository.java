package se.lexicon.recipedatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.recipedatabase.entity.Ingredient;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    Ingredient findByIngredientNameIs(String ingredientName);
    List<Ingredient> findAllByIngredientNameContainingIgnoreCase(String ingredientName);

}
