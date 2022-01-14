package com.github.llamarama.team.mixins;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.LlamaEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LlamaEntity.class)
public interface AccessorLlamaEntity {

    @Accessor("STRENGTH")
    static TrackedData<Integer> getStrength() {
        throw new RuntimeException("This shouldn't happen");
    }

    @Accessor
    boolean getSpit();

    @Invoker
    void invokeSetSpit(boolean bool);

}
