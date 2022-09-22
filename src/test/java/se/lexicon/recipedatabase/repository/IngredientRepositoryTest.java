package se.lexicon.recipedatabase.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.recipedatabase.entity.Ingredient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IngredientRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    IngredientRepository ingredientRepository;

    Ingredient ingredientTest;

    public List<Ingredient> ingredients(){
        return Arrays.asList(
                new Ingredient("tomato"),
                new Ingredient("potatoes"),
                new Ingredient("salt"),
                new Ingredient("flour")
        );
    }
    @BeforeEach
    public void setUp(){
        List<Ingredient> savedIngredients= ingredients().stream().map(ingredientRepository::save).collect(Collectors.toList());
        ingredientTest= savedIngredients.get(0);
    }

    @Test
    void findByIngredientNameIs() {
        Ingredient found = ingredientRepository.findByIngredientNameIs("tomato");
        assertNotNull(found);
        assertEquals(found, ingredientTest);

    }

    @Test
    void findAllByIngredientNameContainingIgnoreCase() {

        List<Ingredient> found = ingredientRepository.findAllByIngredientNameContainingIgnoreCase("to");
        assertFalse(found.isEmpty());
        assertEquals(2, found.size());
    }
}