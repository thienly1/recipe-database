package se.lexicon.recipedatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.recipedatabase.dto.RecipeDto;
import se.lexicon.recipedatabase.form.RecipeForm;
import se.lexicon.recipedatabase.service.RecipeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController { ;
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<RecipeDto>> findAll() {
        return ResponseEntity.ok(recipeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> findByRecipeId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(recipeService.findByRecipeId(id));
    }

    @GetMapping("/name")
    public ResponseEntity<List<RecipeDto>> findByRecipeName(@RequestParam("name") String name) {
        return ResponseEntity.ok(recipeService.findByRecipeName(name));
    }


    @GetMapping("/ingredient")
    public ResponseEntity<List<RecipeDto>> findByIngredientName(@RequestParam("name") String ingredientName){
        return ResponseEntity.ok(recipeService.findByRecipeIngredientName(ingredientName));
    }

    @PostMapping
    public ResponseEntity<RecipeDto> create(@Valid @RequestBody RecipeForm recipeForm, @RequestBody Integer instructionId){
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createRecipe(recipeForm));
    }
}

