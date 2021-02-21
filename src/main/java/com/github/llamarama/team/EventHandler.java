package com.github.llamarama.team;

import com.github.llamarama.team.entity.ModEntityTypes;
import com.github.llamarama.team.item.ModItems;
import com.github.llamarama.team.util.IDBuilder;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;

import java.util.function.Predicate;

@SuppressWarnings({"deprecation", "unused"})
public final class EventHandler {

    private static EventHandler eventHandler;

    private EventHandler() {

    }

    public static EventHandler getInstance() {
        if (eventHandler == null) {
            eventHandler = new EventHandler();
        }
        return eventHandler;
    }

    public void lootTableListener(ResourceManager resourceManager, LootManager lootManager, Identifier identifier, FabricLootSupplierBuilder fabricLootSupplierBuilder, LootTableLoadingCallback.LootTableSetter lootTableSetter) {
        if (IDBuilder.vanillaOf("entities/llama").equals(identifier)) {

            FabricLootPoolBuilder pool = FabricLootPoolBuilder.builder().rolls(ConstantLootTableRange.create(1)).withEntry(ItemEntry.builder(ModItems.RAW_LLAMA_MEAT).build()).withFunction(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true).build()))).build());

            fabricLootSupplierBuilder.withPool(pool.build());
        }
    }

    public void addSpawnsListener(SpawnEventListener listener) {
        listener.addSpawns(biomeSelectionContext -> BiomeSelectors.includeByKey(BiomeKeys.MOUNTAINS, BiomeKeys.MOUNTAIN_EDGE, BiomeKeys.GRAVELLY_MOUNTAINS, BiomeKeys.MODIFIED_GRAVELLY_MOUNTAINS, BiomeKeys.SNOWY_MOUNTAINS, BiomeKeys.SNOWY_TAIGA_MOUNTAINS, BiomeKeys.TAIGA_MOUNTAINS, BiomeKeys.WOODED_MOUNTAINS).test(biomeSelectionContext), SpawnGroup.CREATURE, ModEntityTypes.WOOLLY_LLAMA, 5, 3, 6);
    }

    @FunctionalInterface
    public interface SpawnEventListener {

        void addSpawns(Predicate<BiomeSelectionContext> biomeSelector, SpawnGroup spawnGroup, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize);

    }

}
