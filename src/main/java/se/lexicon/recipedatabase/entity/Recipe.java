package se.lexicon.recipedatabase.entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeId;
    private  String recipeName;
    @OneToMany(mappedBy = "recipe", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<RecipeIngredient> recipeIngredients;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private RecipeInstruction instruction;
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "recipe_recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "recipe_category_id"))
    private Set<RecipeCategory> categories;

    public Recipe() {
    }

    public Recipe(String recipeName) {
        this.recipeName = recipeName;
    }

    public Recipe(String recipeName, List<RecipeIngredient> recipeIngredients, RecipeInstruction instruction, Set<RecipeCategory> categories) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.instruction = instruction;
        this.categories = categories;
    }
    public void addCategory(RecipeCategory category){
        if(categories==null) categories= new HashSet<>();
        categories.add(category);
        category.getRecipe().add(this);
    }
    public void removeCategory(RecipeCategory category){
        if(categories==null) throw new IllegalArgumentException("category is null");
        if(!categories.contains(category)) throw new IllegalArgumentException("not found");
        categories.remove(category);
        category.getRecipe().remove(this);
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        if(recipeIngredient== null) throw new IllegalArgumentException("Recipe Ingredient is null");
        if(recipeIngredients== null) recipeIngredients= new ArrayList<>();
        recipeIngredients.add(recipeIngredient);
        recipeIngredient.setRecipe(this);
    }
    public void removeRecipeIngredient(RecipeIngredient recipeIngredient){
        if(recipeIngredient== null) throw new IllegalArgumentException("Recipe Ingredient is null");
        if(!recipeIngredients.contains(recipeIngredient)) throw new IllegalArgumentException("not found");
        recipeIngredients.remove(recipeIngredient);
        recipeIngredient.setRecipe(null);
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public RecipeInstruction getInstruction() {
        return instruction;
    }

    public void setInstruction(RecipeInstruction instruction) {
        this.instruction = instruction;
    }

    public Set<RecipeCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<RecipeCategory> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return getRecipeId() == recipe.getRecipeId() && Objects.equals(getRecipeName(), recipe.getRecipeName()) && Objects.equals(getRecipeIngredients(), recipe.getRecipeIngredients()) && Objects.equals(getInstruction(), recipe.getInstruction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecipeId(), getRecipeName(), getRecipeIngredients(), getInstruction());
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", recipeIngredients=" + recipeIngredients +
                ", instruction=" + instruction +
//                ", categories=" + categories +
                '}';
    }
}
