package net.atari09.atarisnewmegamodproject.datagen.recipebuilders;

import net.atari09.atarisnewmegamodproject.recipe.GrowthChamberRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class GrowthChamberRecipeBuilder extends SimpleRecipeBuilder{
    private final Ingredient inputItem;

    // Since we have exactly one of each input, we pass them to the constructor.
    // Builders for recipe serializers that have ingredient lists of some sort would usually
    // initialize an empty list and have #addIngredient or similar methods instead.
    public GrowthChamberRecipeBuilder(ItemStack result,Ingredient inputItem) {
        super(result);
        this.inputItem = inputItem;
    }

    // Saves a recipe using the given RecipeOutput and key. This method is defined in the RecipeBuilder interface.
    @Override
    public void save(RecipeOutput output, ResourceLocation key) {
        // Build the advancement.
        Advancement.Builder advancement = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(key))
                .rewards(AdvancementRewards.Builder.recipe(key))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement::addCriterion);
        // Our factory parameters are the result, the block state, and the ingredient.
        GrowthChamberRecipe recipe = new GrowthChamberRecipe(this.inputItem, this.result);
        // Pass the id, the recipe, and the recipe advancement into the RecipeOutput.
        output.accept(key, recipe, advancement.build(key.withPrefix("recipes/")));
    }
}
