package se.lexicon.recipedatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.recipedatabase.entity.RecipeCategory;

public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Integer> {
}
