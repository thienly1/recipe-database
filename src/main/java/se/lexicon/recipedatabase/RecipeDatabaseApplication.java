package se.lexicon.recipedatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import se.lexicon.recipedatabase.entity.*;
import se.lexicon.recipedatabase.repository.*;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.HashSet;

@SpringBootApplication
public class RecipeDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeDatabaseApplication.class, args);
	}

//	@Profile(value = "dev")
//	@Transactional
//	@Component
//	class myCommandLineRunner implements CommandLineRunner {
//		private final IngredientRepository ingredientRepository;
//		private final RecipeIngredientRepository recipeIngredientRepository;
//		private final RecipeInstructionRepository recipeInstructionRepository;
//		private final RecipeRepository recipeRepository;
//		private final RecipeCategoryRepository categoryRepository;
//		private final EntityManager entityManager;
//
//		@Autowired
//		public myCommandLineRunner(IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository, RecipeInstructionRepository recipeInstructionRepository, RecipeRepository recipeRepository, RecipeCategoryRepository categoryRepository, EntityManager entityManager) {
//			this.ingredientRepository = ingredientRepository;
//			this.recipeIngredientRepository = recipeIngredientRepository;
//			this.recipeInstructionRepository = recipeInstructionRepository;
//			this.recipeRepository = recipeRepository;
//			this.categoryRepository = categoryRepository;
//			this.entityManager = entityManager;
//		}
//
//		@Override
//		public void run(String... args) throws Exception {
//			Ingredient ingredient1 = ingredientRepository.save(new Ingredient("pepper"));
//			Ingredient ingredient2 = ingredientRepository.save(new Ingredient("chilli"));
//			Ingredient ingredient3= ingredientRepository.save(new Ingredient("vegetable"));
//
//			System.out.println(ingredientRepository.findByIngredientNameIs("pepper"));
//			ingredientRepository.findAllByIngredientNameContainingIgnoreCase("ge").forEach(System.out::println);
//
//			Recipe recipe1 = recipeRepository.save(new Recipe("köttfärsås"));
//			Recipe recipe2 = recipeRepository.save(new Recipe("Chili con Carne"));
//			Recipe recipe3 = recipeRepository.save(new Recipe("Chili sin Carne"));
//			Recipe recipe4 = recipeRepository.save(new Recipe("Windalo Chicken"));
//			Recipe recipe5 = recipeRepository.save(new Recipe("Mild Chicken"));
//
//			RecipeIngredient recipeIngredient1= recipeIngredientRepository.save(new RecipeIngredient(100, Measurement.CL));
//			RecipeIngredient recipeIngredient2= recipeIngredientRepository.save(new RecipeIngredient(30, Measurement.DL));
//			RecipeIngredient recipeIngredient3= recipeIngredientRepository.save(new RecipeIngredient(20, Measurement.KG));
//			RecipeIngredient recipeIngredient4= recipeIngredientRepository.save(new RecipeIngredient(50, Measurement.HG));
//			RecipeIngredient recipeIngredient5= recipeIngredientRepository.save(new RecipeIngredient(100, Measurement.G));
//			RecipeIngredient recipeIngredient6= recipeIngredientRepository.save(new RecipeIngredient(30, Measurement.ML));
//			RecipeIngredient recipeIngredient7= recipeIngredientRepository.save(new RecipeIngredient(50, Measurement.TSP));
//			RecipeIngredient recipeIngredient8= recipeIngredientRepository.save(new RecipeIngredient(100, Measurement.TBSP));
//
//			recipe1.addRecipeIngredient(recipeIngredient1);
//			recipe1.addRecipeIngredient(recipeIngredient4);
//			recipe2.addRecipeIngredient(recipeIngredient2);
//			recipe2.addRecipeIngredient(recipeIngredient3);
//			recipe3.addRecipeIngredient(recipeIngredient5);
//			recipe4.addRecipeIngredient(recipeIngredient6);
//			recipe5.addRecipeIngredient(recipeIngredient7);
//			recipe5.addRecipeIngredient(recipeIngredient8);
//			entityManager.flush();
//
//			recipeRepository.findAllByRecipeNameContainingIgnoreCase("Carne").forEach(System.out::println);
//
//
//			recipeIngredient1.setIngredient(ingredient1);
//			recipeIngredient2.setIngredient(ingredient3);
//			recipeIngredient3.setIngredient(ingredient2);
//			recipeIngredient4.setIngredient(ingredient1);
//			recipeIngredient5.setIngredient(ingredient2);
//			recipeIngredient6.setIngredient(ingredient2);
//			recipeIngredient7.setIngredient(ingredient3);
//			recipeIngredient8.setIngredient(ingredient1);
//
//			recipeRepository.findAllByRecipeIngredientsContainsIngredientName("pepper").forEach(System.out::println);
//
//			RecipeCategory classic =categoryRepository.save(new RecipeCategory("classic"));
//			RecipeCategory Spicy = categoryRepository.save(new RecipeCategory("Spicy"));
//			RecipeCategory Vegetarian = categoryRepository.save(new RecipeCategory("Vegetarian"));
//			RecipeCategory Chicken = categoryRepository.save(new RecipeCategory("Chicken"));
//			RecipeCategory beef = categoryRepository.save(new RecipeCategory("beef"));
//
//			recipe1.addCategory(classic);
//			recipe2.addCategory(classic);
//			recipe2.addCategory(Spicy);
//			recipe3.addCategory(classic);
//			recipe3.addCategory(Spicy);
//			recipe3.addCategory(Vegetarian);
//			recipe4.addCategory(Spicy);
//			recipe4.addCategory(Chicken);
//			recipe5.addCategory(Chicken);
//			recipe5.addCategory(beef);
//
//			recipeRepository.findAllByCategoriesContainsIgnoreCase(Spicy).forEach(System.out::println);
//			Collection<RecipeCategory> categories= new HashSet<>();
//			categories.add(classic);
//			categories.add(Vegetarian);
//
////			recipeRepository.findAllByCategoriesIn(categories).forEach(System.out::println);
//			recipeRepository.findDistinctByCategoriesIn(categories).forEach(System.out::println);
//
//		}
//	}

}
