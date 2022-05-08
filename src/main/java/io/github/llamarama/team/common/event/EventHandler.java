package io.github.llamarama.team.common.event;

import io.github.llamarama.team.client.blockentity.LlamaWoolBedBlockEntityRenderer;
import io.github.llamarama.team.client.entity.bumblellama.BumbleLlamaEntityModel;
import io.github.llamarama.team.client.entity.bumblellama.BumbleLlamaEntityRenderer;
import io.github.llamarama.team.client.entity.caravantrader.CaravanTraderRenderer;
import io.github.llamarama.team.client.entity.mossyllama.MossyLlamaEntityModel;
import io.github.llamarama.team.client.entity.mossyllama.MossyLlamaEntityRenderer;
import io.github.llamarama.team.client.entity.woollyllama.WoollyLlamaEntityModel;
import io.github.llamarama.team.client.entity.woollyllama.WoollyLlamaEntityRenderer;
import io.github.llamarama.team.common.block.blockentity.ModBlockEntityTypes;
import io.github.llamarama.team.common.entity.ModEntityTypes;
import io.github.llamarama.team.common.entity.mossyllama.MossyLlamaEntity;
import io.github.llamarama.team.common.item.ModItems;
import io.github.llamarama.team.common.util.IdBuilder;
import io.github.llamarama.team.common.util.events.BlockEntityRendererRegistryListener;
import io.github.llamarama.team.common.util.events.EntityLayerRegistryListener;
import io.github.llamarama.team.common.util.events.EntityRendererListener;
import io.github.llamarama.team.common.util.events.SpawnEventListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
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
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Set;

@SuppressWarnings({"deprecation", "unused"})
public final class EventHandler {

    private static EventHandler eventHandler;
    private final Collection<RegistryKey<Biome>> MOUNTAIN_KEYS = Set.of(
        BiomeKeys.SNOWY_SLOPES,
        BiomeKeys.FROZEN_PEAKS,
        BiomeKeys.JAGGED_PEAKS,
        BiomeKeys.STONY_PEAKS,
        BiomeKeys.MEADOW,
        BiomeKeys.WINDSWEPT_HILLS,
        BiomeKeys.WINDSWEPT_GRAVELLY_HILLS
    );

    private EventHandler() {

    }

    public static EventHandler getInstance() {
        if (eventHandler == null) {
            eventHandler = new EventHandler();
        }
        return eventHandler;
    }

    public void lootTableListener(ResourceManager __, LootManager ___, Identifier identifier,
                                  FabricLootSupplierBuilder fabricLootSupplierBuilder,
                                  LootTableLoadingCallback.LootTableSetter ____) {
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
        listener.addSpawns(
            BiomeSelectors.includeByKey(this.MOUNTAIN_KEYS),
            SpawnGroup.CREATURE,
            ModEntityTypes.WOOLLY_LLAMA,
            5, 3, 6
        );
        listener.addSpawns(
            BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST),
            SpawnGroup.CREATURE,
            ModEntityTypes.BUMBLE_LLAMA,
            3, 4, 7
        );
        listener.addSpawns(
            BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES),
            SpawnGroup.CREATURE,
            ModEntityTypes.MOSSY_LLAMA,
            100, 1, 2
        );
    }

    public void registerSpawnRestrictions() {
        SpawnRestrictionAccessor.callRegister(
            ModEntityTypes.WOOLLY_LLAMA,
            SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            AnimalEntity::isValidNaturalSpawn
        );
        SpawnRestrictionAccessor.callRegister(
            ModEntityTypes.BUMBLE_LLAMA,
            SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            AnimalEntity::isValidNaturalSpawn
        );
        SpawnRestrictionAccessor.callRegister(
            ModEntityTypes.CARAVAN_TRADER,
            SpawnRestriction.Location.NO_RESTRICTIONS,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            MobEntity::canMobSpawn
        );
        SpawnRestrictionAccessor.callRegister(
            ModEntityTypes.MOSSY_LLAMA,
            SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            MossyLlamaEntity::canSpawn
        );
    }

    @Environment(EnvType.CLIENT)
    public void addBlockEntityRegisterListener(@NotNull BlockEntityRendererRegistryListener listener) {
        listener.registerRenderer(ModBlockEntityTypes.LLAMA_WOOL_BED, LlamaWoolBedBlockEntityRenderer::new);
    }

    @Environment(EnvType.CLIENT)
    public void addEntityRendererListener(@NotNull EntityRendererListener listener) {
        listener.registerRenderer(ModEntityTypes.WOOLLY_LLAMA, WoollyLlamaEntityRenderer::new);
        listener.registerRenderer(ModEntityTypes.BUMBLE_LLAMA, BumbleLlamaEntityRenderer::new);
        listener.registerRenderer(ModEntityTypes.CARAVAN_TRADER, CaravanTraderRenderer::new);
        listener.registerRenderer(ModEntityTypes.MOSSY_LLAMA, MossyLlamaEntityRenderer::new);
    }

    @Environment(EnvType.CLIENT)
    public void addEntityModelLayers(@NotNull EntityLayerRegistryListener listener) {
        listener.register(LlamaWoolBedBlockEntityRenderer.LLAMA_BED_HEAD,
            LlamaWoolBedBlockEntityRenderer::getHeadTexturedModelData);
        listener.register(LlamaWoolBedBlockEntityRenderer.LLAMA_BED_FOOT,
            LlamaWoolBedBlockEntityRenderer::getFootTexturedModelData);

        listener.register(WoollyLlamaEntityRenderer.WOOLLY_LLAMA,
            WoollyLlamaEntityModel::getTexturedModelData);
        listener.register(WoollyLlamaEntityRenderer.WoollyLlamaDecorRenderer.WOOLLY_LLAMA_DECOR,
            WoollyLlamaEntityModel::getTexturedModelData);

        listener.register(BumbleLlamaEntityRenderer.BUMBLE_LLAMA,
            BumbleLlamaEntityModel::getTexturedModelData);

        listener.register(CaravanTraderRenderer.CARAVAN_TRADER,
            () -> TexturedModelData.of(VillagerResemblingModel.getModelData(), 64, 64));

        listener.register(MossyLlamaEntityRenderer.MOSSY_LLAMA,
            MossyLlamaEntityModel::getTexturedModelData);
    }

}
