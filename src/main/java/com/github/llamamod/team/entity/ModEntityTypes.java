package com.github.llamamod.team.entity;

import com.github.llamamod.team.LlamaMod;
import com.github.llamamod.team.entity.woolyllama.WoollyLlamaEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ModEntityTypes {

    public static final EntityType<WoollyLlamaEntity> WOOLLY_LLAMA = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(LlamaMod.MOD_ID, "woolly_llama"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WoollyLlamaEntity::new)
                    .dimensions(new EntityDimensions(0.9f, 1.87f, true))
                    .trackRangeBlocks(10)
                    .build());


    private ModEntityTypes() {

    }

    public static void init() {
        FabricDefaultAttributeRegistry.register(WOOLLY_LLAMA, WoollyLlamaEntity.createLlamaAttributes());
    }
}
