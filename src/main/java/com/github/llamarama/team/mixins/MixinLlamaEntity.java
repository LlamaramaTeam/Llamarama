package com.github.llamarama.team.mixins;

import com.github.llamarama.team.entity.ai.goal.CaravanGoal;
import com.github.llamarama.team.entity.ai.goal.VibeGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.FormCaravanGoal;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LlamaEntity.class)
public abstract class MixinLlamaEntity extends AbstractDonkeyEntity implements RangedAttackMob {

    protected MixinLlamaEntity(EntityType<? extends AbstractDonkeyEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals()V", at = @At("TAIL"))
    public void onInitGoals(CallbackInfo ci) {
        this.goalSelector.remove(new FormCaravanGoal(((LlamaEntity) (Object) this), 2.0999999046325684d));
        this.goalSelector.add(2, new CaravanGoal<>(((LlamaEntity) (Object) this), 2.99d));
        this.goalSelector.add(3, new VibeGoal(((LlamaEntity) (Object) this)));
    }

}
