package com.github.llamarama.team.item.food;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public final class ModFoodComponents {

    public static final FoodComponent RAW_LLAMA_MEAT = new FoodComponent.Builder().hunger(4).saturationModifier(2).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 20), 0.4f).meat().build();
    public static final FoodComponent ROASTED_LLAMA_MEAT = new FoodComponent.Builder().hunger(8).saturationModifier(10).meat().build();
    /**
     * TODO: Find a use for this food component.
     */
    public static final FoodComponent LLAMA_MILK = new FoodComponent.Builder().hunger(2).saturationModifier(4).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 20), 0.4f).build();
    public static final FoodComponent LLAMA_CHEESE = new FoodComponent.Builder().hunger(4).saturationModifier(7).snack().build();

    private ModFoodComponents() {

    }

}
