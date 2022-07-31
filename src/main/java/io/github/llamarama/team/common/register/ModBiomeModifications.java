package io.github.llamarama.team.common.register;

import io.github.llamarama.team.common.entity.mossyllama.MossyLlamaEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModBiomeModifications {

    public static void init() {
        BiomeModifications.addSpawn(
            ctx -> ctx.hasTag(BiomeTags.IS_MOUNTAIN) ||
                ctx.hasTag(TagKey.of(Registry.BIOME_KEY, new Identifier("c", "mountains"))),
            SpawnGroup.CREATURE,
            ModEntityTypes.WOOLLY_LLAMA,
            5, 3, 6
        );
        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST),
            SpawnGroup.CREATURE,
            ModEntityTypes.BUMBLE_LLAMA,
            3, 4, 7
        );
        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES),
            SpawnGroup.CREATURE,
            ModEntityTypes.MOSSY_LLAMA,
            100, 1, 2
        );
        BiomeModifications.addSpawn(
            ctx -> ctx.hasTag(BiomeTags.DESERT_PYRAMID_HAS_STRUCTURE) ||
                ctx.hasTag(TagKey.of(Registry.BIOME_KEY, new Identifier("c", "desert"))),
            SpawnGroup.CREATURE,
            ModEntityTypes.SANDY_LLAMA,
            3, 2, 6
        );
    }

    public static void registerSpawnRestrictions() {
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
            SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            MobEntity::canMobSpawn
        );
        SpawnRestrictionAccessor.callRegister(
            ModEntityTypes.MOSSY_LLAMA,
            SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            MossyLlamaEntity::canSpawn
        );
        SpawnRestrictionAccessor.callRegister(
            ModEntityTypes.SANDY_LLAMA,
            SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            LlamaEntity::canMobSpawn
        );
    }

}
