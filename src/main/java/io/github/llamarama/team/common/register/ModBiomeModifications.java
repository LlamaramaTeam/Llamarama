package io.github.llamarama.team.common.register;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;

public class ModBiomeModifications {
    public static void init() {
        BiomeModifications.addSpawn(
            ctx -> ctx.hasTag(BiomeTags.IS_MOUNTAIN) ||
                ctx.hasTag(TagKey.of(RegistryKeys.BIOME, new Identifier("c", "mountains"))),
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
                ctx.hasTag(TagKey.of(RegistryKeys.BIOME, new Identifier("c", "desert"))),
            SpawnGroup.CREATURE,
            ModEntityTypes.SANDY_LLAMA,
            3, 2, 6
        );
    }

}
