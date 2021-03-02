package com.github.llamarama.team.entity;

import com.github.llamarama.team.entity.bumbllama.BumbllamaEntity;
import com.github.llamarama.team.entity.woolyllama.WoollyLlamaEntity;
import com.github.llamarama.team.util.IDBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

@SuppressWarnings("SameParameterValue")
public final class ModEntityTypes {

    public static final EntityType<WoollyLlamaEntity> WOOLLY_LLAMA = register(WoollyLlamaEntity::new, SpawnGroup.CREATURE, 0.9f, 1.87f, false, 10, "woolly_llama");
    public static final EntityType<BumbllamaEntity> BUMBLLAMA = register(BumbllamaEntity::new, SpawnGroup.CREATURE, 0.9f, 1.87f, false, 10, "bumbllama");
    private static ModEntityTypes instance;


    private ModEntityTypes() {
        registerAttributes(WOOLLY_LLAMA, WoollyLlamaEntity::createLlamaAttributes);
        registerAttributes(BUMBLLAMA, BumbllamaEntity::createLlamaAttributes);
    }

    public static void init() {
        if (instance == null) {
            instance = new ModEntityTypes();
        }
    }

    private static <T extends Entity> EntityType<T> register(EntityType.EntityFactory<T> factory, SpawnGroup group, float width, float height, boolean fixed, int range, String id) {
        return Registry.register(Registry.ENTITY_TYPE, IDBuilder.of(id), FabricEntityTypeBuilder.create(group).entityFactory(factory).dimensions(new EntityDimensions(width, height, fixed)).trackRangeBlocks(range).build());
    }

    private void registerAttributes(EntityType<? extends LivingEntity> type, Supplier<DefaultAttributeContainer.Builder> builderSupplier) {
        FabricDefaultAttributeRegistry.register(type, builderSupplier.get());
    }

}
