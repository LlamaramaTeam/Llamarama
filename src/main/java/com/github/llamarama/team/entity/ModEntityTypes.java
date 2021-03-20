package com.github.llamarama.team.entity;

import com.github.llamarama.team.entity.bumbllama.BumbleLlamaEntity;
import com.github.llamarama.team.entity.caravantrader.CaravanTraderEntity;
import com.github.llamarama.team.entity.woolyllama.WoollyLlamaEntity;
import com.github.llamarama.team.util.IdBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

@SuppressWarnings("SameParameterValue")
public final class ModEntityTypes {

    private static ModEntityTypes instance;
    public final EntityType<WoollyLlamaEntity> WOOLLY_LLAMA;
    public final EntityType<BumbleLlamaEntity> BUMBLE_LLAMA;
    public final EntityType<CaravanTraderEntity> CARAVAN_TRADER;


    private ModEntityTypes() {
        this.WOOLLY_LLAMA = register(WoollyLlamaEntity::new, SpawnGroup.CREATURE, 0.9f, 1.87f, false, 10, WoollyLlamaEntity::createLlamaAttributes, "woolly_llama");
        this.BUMBLE_LLAMA = register(BumbleLlamaEntity::new, SpawnGroup.CREATURE, 0.9f, 1.87f, false, 10, BumbleLlamaEntity::createLlamaAttributes, "bumble_llama");
        this.CARAVAN_TRADER = register(CaravanTraderEntity::new, SpawnGroup.AMBIENT, 0.6f, 2.0f, false, 10, CaravanTraderEntity::createAttributes, "caravan_trader");
    }

    public static ModEntityTypes get() {
        if (instance == null) {
            instance = new ModEntityTypes();
        }

        return instance;
    }

    private static <T extends LivingEntity> EntityType<T> register(EntityType.EntityFactory<T> factory, SpawnGroup group, float width, float height, boolean fixed, int range, Supplier<DefaultAttributeContainer.Builder> attributes, String id) {
        EntityType<T> type = FabricEntityTypeBuilder.create(group).entityFactory(factory).dimensions(new EntityDimensions(width, height, fixed)).trackRangeBlocks(range).build();
        FabricDefaultAttributeRegistry.register(type, attributes.get());
        return Registry.register(Registry.ENTITY_TYPE, IdBuilder.of(id), type);
    }

}
