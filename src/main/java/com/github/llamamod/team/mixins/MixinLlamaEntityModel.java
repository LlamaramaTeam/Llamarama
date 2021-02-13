package com.github.llamamod.team.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(LlamaEntityModel.class)
public abstract class MixinLlamaEntityModel {

}
