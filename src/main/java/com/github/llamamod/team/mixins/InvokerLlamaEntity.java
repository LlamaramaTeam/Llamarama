package com.github.llamamod.team.mixins;

import net.minecraft.entity.passive.LlamaEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LlamaEntity.class)
public interface InvokerLlamaEntity {

    @Invoker("setSpit")
    void spit(boolean spit);
}
