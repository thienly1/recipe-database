package se.lexicon.recipedatabase.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.recipedatabase.entity.RecipeIngredient;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, String> {
}
