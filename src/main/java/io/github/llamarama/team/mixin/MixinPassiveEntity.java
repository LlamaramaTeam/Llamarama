package io.github.llamarama.team.mixin;

import io.github.llamarama.team.common.util.duck.BoostTimeProvider;
import io.github.llamarama.team.common.util.duck.SaddledComponentProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PassiveEntity.class)
public abstract class MixinPassiveEntity extends LivingEntity {

    protected MixinPassiveEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "onTrackedDataSet", at = @At("HEAD"))
    private void llamaCustomTrackedDataSet(TrackedData<?> data, CallbackInfo ci) {
        var this$ = (PassiveEntity) (Object) this;

        if (this$ instanceof LlamaEntity llama) {
            if (data.equals(((BoostTimeProvider) llama).getBoostTime())) {
                ((SaddledComponentProvider) llama).getSaddledComponent().boost();
            }
        }
    }

}
