package com.github.llamarama.team.mixins;

import com.github.llamarama.team.entity.ai.goal.BeeFollowBumbleLlamaGoal;
import com.github.llamarama.team.entity.ai.goal.BeeHelpBumbleLlamaGoal;
import com.github.llamarama.team.entity.bumbllama.BumbleLlamaEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeeEntity.class)
public abstract class MixinBeeEntity extends AnimalEntity implements Angerable, Flutterer {

    @SuppressWarnings("unused")
    protected MixinBeeEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals()V", at = @At("TAIL"))
    public void onInitGoals(CallbackInfo ci) {
        this.goalSelector.add(3, new BeeFollowBumbleLlamaGoal(((BeeEntity) (Object) this)));
        this.goalSelector.add(1, new BeeHelpBumbleLlamaGoal(((BeeEntity) (Object) this)));
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if (!(target instanceof BumbleLlamaEntity)) {
            super.setTarget(target);
        }
    }

}
