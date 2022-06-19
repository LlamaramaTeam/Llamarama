package io.github.llamarama.team.mixin;

import io.github.llamarama.team.common.entity.bumbllama.BumbleLlamaEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class MixinMobEntity extends LivingEntity {

    protected MixinMobEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    private void removeBumbleTarget(LivingEntity target, CallbackInfo ci) {
        var this$ = (MobEntity) (Object) this;
        if (this$ instanceof BeeEntity && target instanceof BumbleLlamaEntity) {
            ci.cancel();
        }
    }

}
