package com.github.llamarama.team.mixins;

import net.minecraft.entity.passive.BeeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BeeEntity.class)
public interface InvokerBeeEntity {

    @Invoker("setHasNectar")
    void invokeSetHasNectar(boolean newVal);

}
