package io.github.llamarama.team.item.food;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public final class ModFoodComponents {

    public static final FoodComponent RAW_LLAMA_MEAT =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.8f).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 20), 0.4f).meat().build();
    public static final FoodComponent ROASTED_LLAMA_MEAT =
            new FoodComponent.Builder().hunger(8).saturationModifier(1.0f).meat().build();
    public static final FoodComponent LLAMA_CHEESE =
            new FoodComponent.Builder().hunger(4).saturationModifier(0.5f).snack().build();

    private ModFoodComponents() {

    }

}
