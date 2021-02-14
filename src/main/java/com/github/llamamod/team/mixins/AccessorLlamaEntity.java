package com.github.llamamod.team.mixins;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.LlamaEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LlamaEntity.class)
public interface AccessorLlamaEntity {

    @Accessor("STRENGTH")
    TrackedData<Integer> getStrength();
}
