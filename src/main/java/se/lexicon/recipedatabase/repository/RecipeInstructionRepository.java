package se.lexicon.recipedatabase.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.recipedatabase.entity.RecipeInstruction;

public interface RecipeInstructionRepository extends CrudRepository<RecipeInstruction, Integer> {
}
