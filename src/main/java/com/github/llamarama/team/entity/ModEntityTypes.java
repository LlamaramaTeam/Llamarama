package com.github.llamarama.team.entity;

import com.github.llamarama.team.Llamarama;
import com.github.llamarama.team.entity.woolyllama.WoollyLlamaEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public final class ModEntityTypes {

    public static final EntityType<WoollyLlamaEntity> WOOLLY_LLAMA = Registry.register(Registry.ENTITY_TYPE, new Identifier(Llamarama.MOD_ID, "woolly_llama"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WoollyLlamaEntity::new).dimensions(new EntityDimensions(0.9f, 1.87f, false)).trackRangeBlocks(10).build());
    private static ModEntityTypes instance;


    private ModEntityTypes() {
        register(WOOLLY_LLAMA, WoollyLlamaEntity::createLlamaAttributes);
    }

    public static void init() {
        if (instance == null) {
            instance = new ModEntityTypes();
        }
    }

    private void register(EntityType<? extends LivingEntity> type, Supplier<DefaultAttributeContainer.Builder> builderSupplier) {
        FabricDefaultAttributeRegistry.register(type, builderSupplier.get());
    }

}
