package com.github.llamarama.team;

import com.github.llamarama.team.block.blockentity.ModBlockEntityTypes;
import com.github.llamarama.team.client.blockentity.LlamaWoolBedBlockEntityRenderer;
import com.github.llamarama.team.client.entity.bumblellama.BumbleLlamaEntityRenderer;
import com.github.llamarama.team.client.entity.caravantrader.CaravanTraderRenderer;
import com.github.llamarama.team.client.entity.woollyllama.WoollyLlamaEntityRenderer;
import com.github.llamarama.team.entity.ModEntityTypes;
import com.github.llamarama.team.item.ModItems;
import com.github.llamarama.team.util.IdBuilder;
import com.github.llamarama.team.util.events.BlockEntityRendererRegistryListener;
import com.github.llamarama.team.util.events.EntityLayerRegistryListener;
import com.github.llamarama.team.util.events.EntityRendererListener;
import com.github.llamarama.team.util.events.SpawnEventListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings({"deprecation", "unused", "UnstableApiUsage"})
public final class EventHandler {

    private static EventHandler eventHandler;
    private final Collection<RegistryKey<Biome>> MOUNTAIN_KEYS = Stream.of(BiomeKeys.MOUNTAINS, BiomeKeys.MOUNTAIN_EDGE, BiomeKeys.GRAVELLY_MOUNTAINS, BiomeKeys.MODIFIED_GRAVELLY_MOUNTAINS, BiomeKeys.SNOWY_MOUNTAINS, BiomeKeys.SNOWY_TAIGA_MOUNTAINS, BiomeKeys.TAIGA_MOUNTAINS, BiomeKeys.WOODED_MOUNTAINS).collect(Collectors.toList());

    private EventHandler() {

    }

    public static EventHandler getInstance() {
        if (eventHandler == null) {
            eventHandler = new EventHandler();
        }
        return eventHandler;
    }

    public void lootTableListener(ResourceManager resourceManager, LootManager lootManager, Identifier identifier, FabricLootSupplierBuilder fabricLootSupplierBuilder, LootTableLoadingCallback.LootTableSetter lootTableSetter) {
        if (IdBuilder.vanillaOf("entities/llama").equals(identifier)) {

            FabricLootPoolBuilder pool =
                    FabricLootPoolBuilder.builder()
                            .rolls(ConstantLootNumberProvider.create(1))
                            .withEntry(ItemEntry.builder(ModItems.RAW_LLAMA_MEAT).build())
                            .withFunction(FurnaceSmeltLootFunction.builder()
                                    .conditionally(EntityPropertiesLootCondition
                                            .builder(LootContext.EntityTarget.THIS,
                                                    EntityPredicate.Builder.create()
                                                            .flags(EntityFlagsPredicate.Builder.create()
                                                                    .onFire(true).build())))
                                    .build())
                            .withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(1, 2))
                                    .build());

            fabricLootSupplierBuilder.withPool(pool.build());
        }
    }

    public void addSpawnsListener(SpawnEventListener listener) {
        listener.addSpawns(BiomeSelectors.includeByKey(this.MOUNTAIN_KEYS), SpawnGroup.CREATURE, ModEntityTypes.WOOLLY_LLAMA, 5, 3, 6);
        listener.addSpawns(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), SpawnGroup.CREATURE, ModEntityTypes.BUMBLE_LLAMA, 3, 4, 7);
    }

    public void registerSpawnRestrictions() {
        SpawnRestrictionAccessor.callRegister(ModEntityTypes.WOOLLY_LLAMA, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
        SpawnRestrictionAccessor.callRegister(ModEntityTypes.BUMBLE_LLAMA, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
        SpawnRestrictionAccessor.callRegister(ModEntityTypes.CARAVAN_TRADER, SpawnRestriction.Location.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn);
    }

    @Environment(EnvType.CLIENT)
    public void addBlockEntityRegisterListener(BlockEntityRendererRegistryListener listener) {
        listener.registerRenderer(ModBlockEntityTypes.LLAMA_WOOL_BED, LlamaWoolBedBlockEntityRenderer::new);
    }

    @Environment(EnvType.CLIENT)
    public void addEntityRendererListener(EntityRendererListener listener) {
        listener.registerRenderer(ModEntityTypes.WOOLLY_LLAMA, WoollyLlamaEntityRenderer::new);
        listener.registerRenderer(ModEntityTypes.BUMBLE_LLAMA, BumbleLlamaEntityRenderer::new);
        listener.registerRenderer(ModEntityTypes.CARAVAN_TRADER, CaravanTraderRenderer::new);
    }

    @Environment(EnvType.CLIENT)
    public void addEntityModelLayers(EntityLayerRegistryListener listener) {
        listener.register(LlamaWoolBedBlockEntityRenderer.LLAMA_BED_HEAD,
                LlamaWoolBedBlockEntityRenderer::getHeadTexturedModelData);
        listener.register(LlamaWoolBedBlockEntityRenderer.LLAMA_BED_FOOT,
                LlamaWoolBedBlockEntityRenderer::getFootTexturedModelData);
    }

}
